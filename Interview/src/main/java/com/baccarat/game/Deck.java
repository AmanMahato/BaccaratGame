package com.baccarat.game;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by amanmahato on 6/27/17.
 */
public class Deck {
    List<ArrayList<Cards>> activePile;
    int NUMBER_OF_CARD_DECKS=8;

    public Deck(){
        this.activePile=buildDeck();//new ArrayList<ArrayList<Cards>>();

    }
    public ArrayList<ArrayList<Cards>> buildDeck(){
        ArrayList<ArrayList<Cards>> result=new ArrayList<ArrayList<Cards>>();
        for(int j=0;j<NUMBER_OF_CARD_DECKS;j++){
            ArrayList<Cards> arrayList=new ArrayList<Cards>();
            Suits suits;
            for(int i=0;i<52;i++){
                switch ((int)Math.floor(i/13)){
                    case 0:
                        suits=Suits.Spade;
                        break;
                    case 1:
                        suits=Suits.Club;
                        break;
                    case 2:
                        suits=Suits.Diamond;
                        break;
                    case 3:
                        suits=Suits.Heart;
                        break;
                    default:
                        suits=Suits.Spade;
                }
                arrayList.add(new Cards(suits,(i % 13) + 1));

            }
            result.add(arrayList);
        }
        System.out.println("deck built");
     return result;

    }

    public Cards dealCard(){
        Random random=new Random();
        int upperIndex=random.nextInt(NUMBER_OF_CARD_DECKS);
        int innerIndex=random.nextInt(activePile.get(upperIndex).size());
        Cards card=activePile.get(upperIndex).get(innerIndex);
        List<Cards> toBeRemovedFrom=activePile.get(upperIndex);
        toBeRemovedFrom.remove(innerIndex);
        return card;
    }

    public boolean isEmpty(){
        return (activePile.isEmpty());
    }
}
