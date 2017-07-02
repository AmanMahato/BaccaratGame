package com.baccarat.game;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * Created by amanmahato on 6/27/17.
 */
public class Round {

    private ArrayList<Hands> handList;
    private ArrayList<Bet> betList;

    public Round() {
        this.handList = new ArrayList<Hands>();
        this.betList = new ArrayList<Bet>();
        this.handList.add(new Hands(HandsType.PLAYER));
        this.handList.add(new Hands(HandsType.BANKER));
    }

    //clears everything
    public void newRound() {
        for (int i = 0; i < 2; i++) {
            //Remove any cards from the hands
            this.handList.get(i).clearCardsOnHand();
        }
        //Clear all bets
        this.betList.clear();
    }

    //playerBet : method for a player to create a bet
    public Bet playerBet(Player player) {
        int betIndex;
        double betAmount;
        BetType betType;
        System.out.println(player.getName() + " is placing a bet!");
        System.out.println("0: Player, 1: Banker, Default: Tie");
        System.out.println("Enter Bet Type: ");
        Scanner scan=new Scanner(System.in);
        betIndex=scan.nextInt();
        betAmount=scan.nextInt();
        player.setBalance(player.getBalance() - betAmount);
        switch (betIndex){
            case 0:
                betType=BetType.Player;
                break;
            case 1:
                betType=BetType.Banker;
                break;
            default:
                betType=BetType.Tie;
                break;

        }
        System.out.println(player.getName() + "'s bet: " + betType);
        System.out.println(player.getName() + "'s bet: " + betAmount);
        System.out.println(player.getName() + "'s balance: " + player.getBalance());
        return new Bet(betType, betAmount);
    }

    //placeBets : method for players to place bets
    public void placeBets(ArrayList<Player> players) {
        for (int i = 0; i < players.size(); i++) {
            //Add bets to the betList in player order
            Bet bet = playerBet(players.get(i));
            this.betList.add(bet);
        }
    }

    //deal : method to deal a card to a hand
    public Cards deal(Hands hand, Deck deck) {
        //Check if deck is empty, if so add new cards
        if (deck.isEmpty())
            deck.buildDeck();
        Cards dealtCard = deck.dealCard();
        //Add card to a hand
        hand.addCards(dealtCard);
        System.out.println("card dealt: "+ dealtCard.getFaceValue()+" to "+hand.handsType);

        return dealtCard;
    }

    //dealTwoCards : method to deal 2 cards to each hand
    public void dealTwoCards(Deck deck) {
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 2; j++) {
                //Call deal method to handle card dealing
                deal(this.handList.get(i), deck);
            }
        }
    }

    public ArrayList<Hands> getHands(){
        return handList;
    }

    //dealThirdCard : method to determine and deal third card
    public void dealThirdCard(ArrayList<Player> players, Deck deck) {
        boolean playerDrawStatus = false,
                bankerDrawStatus = false;
        int playerThirdCard = 0;

        for(Cards card: handList.get(0).getCardsOnHand()){
            System.out.println(handList.get(0).handsType+"'s card: "+ card.getFaceValue()+" "+card.getPointValue());
        }
        for(Cards card: handList.get(1).getCardsOnHand()){
            System.out.println(handList.get(1).handsType+"'s card: "+ card.getFaceValue()+" "+card.getPointValue());
        }


        int[] handValues = {handList.get(0).calculatePointValueOnHand(), handList.get(1).calculatePointValueOnHand()};
        System.out.println(handList.get(0).getHandsType()+"'s hand value: "+handList.get(0).calculatePointValueOnHand());
        System.out.println(handList.get(1).getHandsType()+"'s hand value: "+handList.get(1).calculatePointValueOnHand());


        if (!(handValues[0] > 7) || !(handValues[1] > 7)) {

            //Determine Player Hand first
            if (handValues[0] < 6) {
                playerThirdCard = deal(this.handList.get(0), deck).getPointValue();
                playerDrawStatus = true;
            } else
                playerDrawStatus = false;

            //Determine Banker Hand
            if (playerDrawStatus == false) {
                if (handValues[1] < 6)
                    bankerDrawStatus = true;
            } else {
                if (handValues[1] < 3)
                    bankerDrawStatus = true;
                else {
                    switch (handValues[1]) {
                        case 3:
                            if (playerThirdCard != 8)
                                bankerDrawStatus = true;
                            break;
                        case 4:
                            if (playerThirdCard > 1 && playerThirdCard < 8)
                                bankerDrawStatus = true;
                            break;
                        case 5:
                            if (playerThirdCard > 3 && playerThirdCard < 8)
                                bankerDrawStatus = true;
                            break;
                        case 6:
                            if (playerThirdCard > 5 && playerThirdCard < 8)
                                bankerDrawStatus = true;
                            break;
                        default:
                    }
                }
            }
        }

        //deal banker third card
        if (bankerDrawStatus == true)
            deal(this.handList.get(1), deck);

        for (Hands hand : handList) {
            System.out.println(hand.getHandsType() + ": " + hand.calculatePointValueOnHand());
        }

        giveWinnings(determineWinner(), players);
        System.out.println(players.get(0).getName()+"'s balc: "+ players.get(0).getBalance());
    }


    //
    //determineWinner : method to determine the winner of the round
    public BetType determineWinner() {
        int[] totals = new int[2];
        BetType winner;
        String winnerName;

        for (int i = 0; i < 2; i++) {
            totals[i] = this.handList.get(i).calculatePointValueOnHand();
            totals[i] = Math.abs(9 - totals[i]);
        }

        if (totals[0] < totals[1]) {
            winner = BetType.Player;
            winnerName = "Player";
        }
        else if (totals[0] > totals[1]) {
            winner = BetType.Banker;
            winnerName = "Banker";
        }
        else {
            winner = BetType.Tie;
            winnerName = "Tie";
        }

        System.out.println(winnerName + " Wins!");
        return winner;
    }


    //giveWinnings : method to distribute winnings based on round winner
    public void giveWinnings(BetType winner, ArrayList<Player> players){
        for (int i = 0; i < players.size(); i++) {
            String winnerName;

            //Determine if player won the bet
            if (this.betList.get(i).getBetType() == winner) {

                //Give winnings to a player
                players.get(i).setBalance(players.get(i).getBalance() + calculateWinnings(winner, this.betList.get(i).getBetAmount()));

                switch(winner) {
                    case Banker:
                        winnerName = "Banker";
                        break;
                    case Player:
                        winnerName = "Player";
                        break;
                    default:
                        winnerName = "tie";
                        break;
                }

                System.out.println("Winner: " + winnerName);
            }
        }
    }

    //calculateWinnings : method to calculate the winnings for a player's bet
    public double calculateWinnings(BetType winner, double amount) {
        double winnings = 0;

        if (winner == BetType.Player)
            winnings = amount * 2;
        else if (winner == BetType.Banker)
            winnings = (amount * 2) - (amount * 2 * 0.05);
        else if (winner == BetType.Tie)
            winnings = amount * 8;
        return winnings;
    }
}
