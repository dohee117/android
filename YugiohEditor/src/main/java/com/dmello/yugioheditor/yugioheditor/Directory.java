package com.dmello.yugioheditor.yugioheditor;

/**
 * Created by Joseph on 26/05/2015.
 */
public enum Directory {


    CARD_LIB("/CardLib/");

    private String dir;

    private Directory(String dir){
        this.dir = dir;
    }

    public String getDir(){
        return dir;
    }

}
