package com.baccarat.game;

/**
 * Created by amanmahato on 6/27/17.
 */
public class Bet {

    private BetType betType;
    private double betAmount;

    public Bet(BetType betType, double betAmount) {
        this.betType = betType;
        this.betAmount = betAmount;
    }

    public BetType getBetType() {
        return betType;
    }

    public double getBetAmount() {
        return betAmount;
    }



}
