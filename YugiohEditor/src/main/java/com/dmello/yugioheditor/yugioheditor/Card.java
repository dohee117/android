package com.dmello.yugioheditor.yugioheditor;

import android.util.Log;

/**
 * Created by Joseph on 26/05/2015.
 */
public class Card implements Comparable<Card> {

    private byte[] cardAsBytes;
    private int cardId;
    private int cardAmount;
    private int salt;

    public byte[] getCardAsBytes() { return cardAsBytes;}

    public int getCardId() {
        return cardId;
    }

    public int getCardAmount() {
        return cardAmount;
    }

    public void setCardAmount(int cardAmount) {
        this.cardAmount = cardAmount;
    }

    public int getSalt() {
        return salt;
    }

    public void setSalt(int salt) {
        this.salt = salt;
    }

    public Card(int cardId, int cardAmount, int salt){
        byte[] cardAsBytes = new byte[4];
        cardAsBytes[0] = (byte)(cardId % 256);
        cardAsBytes[1] = ((byte)((int) Math.floor(cardId/256)));
        cardAsBytes[2] = (byte)cardAmount;
        cardAsBytes[3] = ((byte)0);
        this.cardAsBytes = cardAsBytes;
        this.cardAmount = cardAmount;
        this.salt = salt;
        this.cardId = cardId;
        Log.v("Card.Card","Card created from id "+this + " " + bytesToString());
    }

    public Card(byte[] cardAsBytes){
        if(cardAsBytes.length != 4){
            Log.w("Card.Card","Byte[] not long enough, size "+cardAsBytes.length);
        }else{
            this.cardAsBytes = cardAsBytes;
            cardId = (cardAsBytes[0] + 128) + cardAsBytes[1]*256;
            cardAmount = cardAsBytes[2];
            salt = cardAsBytes[3];
            Log.v("Card.Card","Card created from byte[] "+this + " " + bytesToString());
        }
    }

    @Override
    public String toString(){
        return "{"+cardId+","+cardAmount+","+salt+"}";
    }

    @Override
    public boolean equals(Object o){
        if(!(o instanceof Card)){
            return false;
        }else{
            return ((Card) o).getCardId() == cardId;
        }
    }

    public String bytesToString(){
        return "{"+cardAsBytes[0]+","+cardAsBytes[1]+","+cardAsBytes[2]+","+cardAsBytes[3]+"}";
    }

    @Override
    public int compareTo(Card card) {
        Integer thisInt = cardId;
        Integer thatInt = card.getCardId();
        return thisInt.compareTo(thatInt);
    }
}
