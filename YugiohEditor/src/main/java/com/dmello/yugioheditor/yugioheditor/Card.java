package com.dmello.yugioheditor.yugioheditor;

import android.util.Log;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;

/**
 * Created by Joseph on 26/05/2015.
 */
public class Card implements Comparable<Card> {

    private byte[] cardAsBytes;
    private short cardId;
    private int cardAmount;
    private int salt;

    public byte[] getCardAsBytes() { return cardAsBytes;}

    public short getCardId() {
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

    public Card(short cardId, int cardAmount, int salt){
        byte[] cardAsBytes = new byte[4];
        cardAsBytes[0] = (byte)(cardId & 0xff);
        cardAsBytes[1] = (byte)((cardId >> 8) & 0xff);
        cardAsBytes[2] = (byte)cardAmount;
        cardAsBytes[3] = (byte)salt;
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
            ByteBuffer bb = ByteBuffer.wrap(cardAsBytes).order(ByteOrder.LITTLE_ENDIAN);
            
            this.cardAsBytes = cardAsBytes;
            this.cardId = bb.getShort();
            this.cardAmount = bb.get();
            this.salt = bb.get();
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
        Short thisShort = cardId;
        Short thatShort = card.getCardId();
        return thisShort.compareTo(thatShort);
    }
}
