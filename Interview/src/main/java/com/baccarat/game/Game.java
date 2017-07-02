package com.baccarat.game;

import java.util.ArrayList;

/**
 * Created by amanmahato on 6/27/17.
 */
public class Game {

    ArrayList<Player> playerList;
    Deck deck;
    private static final double STARTING_BALANCE = 1000;

    public Game() {
        this.playerList = new ArrayList<Player>();
        this.deck = new Deck();
    }

    //addPlayer : method to add a player to the game
    public void addPlayer(String name) {
        String playerName = name;
        double playerBalance = STARTING_BALANCE;
        this.playerList.add(new Player(playerName, playerBalance));
    }

    public void addPlayer(String name, double bal) {
        String playerName = name;
        double playerBalance = bal;
        this.playerList.add(new Player(playerName, playerBalance));
    }

    public void addPlayer(Player player) {
        this.playerList.add(player);
    }

    //removePlayer : method to remove a player from the game
    public void removePlayer(String playerName) {
        for (int i = 0; i < this.playerList.size(); i++) {
            if (this.playerList.get(i).getName().equals(playerName)) {
                this.playerList.remove(i);
                break;
            }
        }
    }

    public ArrayList<Player> getPlayerList() {
        return playerList;
    }

    public static void main(String[] args) {
        Game game=new Game();
        game.addPlayer("Aman",1000);
        //game.addPlayer("Aaditya");
        Round round = new Round();
        round.newRound();
        round.placeBets(game.playerList);
        round.dealTwoCards(game.deck);
        round.dealThirdCard(game.playerList, game.deck);
    }
}
