package com.dmello.yugioheditor.yugioheditor.CardLibrary;

import android.util.Log;

import com.dmello.yugioheditor.yugioheditor.Card;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Joseph on 26/05/2015.
 */
public class CardLibWriter {

    FileOutputStream os;
    String libName;

    public CardLibWriter(String libName){
        this.libName = libName;
    }


    public void writeAll(CardList libList){
        try{
            File libFile = new File(libName);
            if(libFile.exists()){
                DateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
                Date date = new Date();
                String backupName = libName + "." + dateFormat.format(date);

                Log.d("CardLibWriter.writeAll","Existing file "+libName+" found. Creating new file "+backupName);
                libFile = new File(backupName);

                //TODO BACKUP OLD FILE INSTEAD OF CREATING NEW ONE
                //Log.d("CardLibWriter.writeAll","Existing file "+libName+" found. Creating backup "+backupName);

            }
            os = new FileOutputStream(libFile);

            for(Card card:libList.asArrayList()){
                os.write(card.getCardAsBytes());
                Log.v("CardLibWriter.writeAll","Wrote "+card+" to "+libFile.getAbsolutePath());
            }
            os.close();
        }
        catch (IOException e){
            Log.e("CardLibWriter.writeAll","Error writing file "+libName);
        }

    }

}
