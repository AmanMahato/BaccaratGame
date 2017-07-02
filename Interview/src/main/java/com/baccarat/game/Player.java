package com.baccarat.game;

/**
 * Created by amanmahato on 6/26/17.
 */
public class Player {

    private final String name;
    private double balance;
    public static Double INITIAL_BALANCE=0.0;

    public Player(String name){
        this(name,INITIAL_BALANCE);
    }

    public Player(String name, Double balance) {
        this.name = name;
        this.balance = balance;
    }

    public String getName() {
        return name;
    }


    public void setBalance(double balance) {
        this.balance = balance;
    }

    public Double getBalance() {
        return balance;
    }


}






