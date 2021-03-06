package com.dmello.yugioheditor.yugioheditor.CardLibrary;

import com.dmello.yugioheditor.yugioheditor.Card;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Created by Joseph on 26/05/2015.
 */
public class CardList {

    private ArrayList<Card> cardList;

    public CardList(Card card){
        this.cardList = new ArrayList<Card>();
        cardList.add(card);
    }

    public CardList(ArrayList<Card> cardList){
        this.cardList = cardList;
    }

    public CardList(){
        this.cardList = new ArrayList<Card>();
    }

    public void quickAdd(Card card){
        if(cardList.contains(card)){
            addMore(card);
        }
        else {
            cardList.add(card);
        }
    }

    private void addMore(Card card){
        int index = cardList.indexOf(card);
        Card cardFromList = cardList.get(index);
        int newAmount = card.getCardAmount() + card.getCardAmount();
        cardFromList.setCardAmount(newAmount);
    }

    public void add(Card card){
        quickAdd(card);
        sort();
    }

    public void sort(){
        Collections.sort(cardList);
    }
    
    public void sanitize(){
        //Set last salt to -127
        for(Card card:cardList){
            if(card.getSalt()==-127 && cardList.indexOf(card)!=(cardList.size()-1)){
                    card.setSalt(127);
            }
        }
        cardList.get(cardList.size()-1).setSalt(-127);
    }

    public ArrayList<Card> asArrayList(){
        return cardList;
    }


    public int getCardCount(){
        int cardCount = -1;
        for(Card card:cardList){
            cardCount += card.getCardAmount();
        }
        return cardCount;
    }
}
