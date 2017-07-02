package com.baccarat.game;

/**
 * Created by amanmahato on 6/27/17.
 */
public class Cards {
   private final int faceValue;
   private int pointValue;
   private Suits suits;

   public Cards(Suits suits,int faceValue){
       this.suits=suits;
       this.faceValue=faceValue;
       if(faceValue>9){
           this.pointValue=0;
       }else if(faceValue==1){
           this.pointValue=faceValue;
       }else this.pointValue=faceValue;
   }

    public int getFaceValue() {
        return faceValue;
    }

    public int getPointValue() {
        return pointValue;
    }

}
