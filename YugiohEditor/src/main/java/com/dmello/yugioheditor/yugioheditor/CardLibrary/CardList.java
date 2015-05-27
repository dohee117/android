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
        cardList.add(card);
    }

    private void addMore(Card card){
        int index = cardList.indexOf(card);
        Card cardFromList = cardList.get(index);
        cardFromList.setCardAmount(cardFromList.getCardAmount() + card.getCardAmount());
        cardList.set(index,cardFromList);
    }

    public void add(Card card){
        if(cardList.contains(card)){
            addMore(card);
        }
        else {
            cardList.add(card);
        }
        sort();
    }

    public void sort(){
        Collections.sort(cardList);
    }

    public ArrayList<Card> asArrayList(){
        return cardList;
    }

}
