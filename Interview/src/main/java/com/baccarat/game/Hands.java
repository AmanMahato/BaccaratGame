package com.baccarat.game;

import java.util.ArrayList;

/**
 * Created by amanmahato on 6/27/17.
 */
public class Hands {

    HandsType handsType;
    ArrayList<Cards> cardsOnHand;

    public Hands(HandsType handsType){
        this.handsType=handsType;
        cardsOnHand=new ArrayList<Cards>();
    }

    public void clearCardsOnHand(){
        this.cardsOnHand.clear();
    }

    public void addCards(Cards card){
        this.cardsOnHand.add(card);
    }

    public HandsType getHandsType() {
        return handsType;
    }

    public ArrayList<Cards> getCardsOnHand() {
        return cardsOnHand;
    }

    public int calculatePointValueOnHand(){
        int result=0;
        for(Cards individualCardOnHand:this.cardsOnHand){
            result += individualCardOnHand.getPointValue();
        }
        result %=10;
        return (result);
    }
}
