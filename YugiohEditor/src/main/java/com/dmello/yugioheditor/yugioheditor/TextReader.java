package com.dmello.yugioheditor.yugioheditor;

import android.util.Log;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by Joseph on 28/05/2015.
 */
public class TextReader {

    BufferedReader reader;
    String fileName;

    public TextReader(String fileName){
        Log.v("TextReader.TextReader(String)",fileName);
        this.fileName = fileName;
    }

    public ArrayList<Short> readAsShorts(){

        ArrayList<Short> returnList = new ArrayList<Short>();

        try {
            reader = new BufferedReader (new FileReader(new File(fileName)));
            String line = "";
            while((line = reader.readLine()) != null){
                short nextShort = Short.parseShort(line);
                returnList.add(nextShort);
            }
            reader.close();
        }
        catch(IOException e){
            e.printStackTrace();
        }

        return returnList;
    }
}
