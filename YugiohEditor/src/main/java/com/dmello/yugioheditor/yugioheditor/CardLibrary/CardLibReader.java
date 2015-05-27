package com.dmello.yugioheditor.yugioheditor.CardLibrary;

import android.util.Log;

import com.dmello.yugioheditor.yugioheditor.Card;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

/**
 * Created by Joseph on 26/05/2015.
 */
public class CardLibReader {

    FileInputStream is;
    String libFile;
    public CardLibReader(String libFile){
        this.libFile = libFile;

    }

    public CardList readAll(){

        byte[] lineBuffer = new byte[4];
        CardList cardLibrary = new CardList();
        try {
            is = new FileInputStream(new File(libFile));
            while(is.read(lineBuffer)!=-1){
                cardLibrary.quickAdd(new Card(lineBuffer));
            }
            cardLibrary.sort();
            is.close();
        }catch (IOException e){
            Log.e("CardLibReader.readAll","Error reading card library\n" + e.getMessage());
        }

        return cardLibrary;
    }





}
