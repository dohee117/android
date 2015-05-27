package com.dmello.yugioheditor.yugioheditor;

import android.util.Log;

import java.io.File;

/**
 * Created by Joseph on 26/05/2015.
 */
public class CheckEnvironment {

    private static String[] dirs = {"CardLib","DeckList"};

    public static void checkDirs(String appDir){

        for(String dir:dirs) {
            File deckListDir = new File(appDir+'/'+dir+'/');
            if (deckListDir.exists()) {
                Log.d("CheckEnvironment.checkDirs", dir+" Directory Found");
            } else {
                Log.i("CheckEnvironment.checkDirs", dir+" Directory Missing");
                deckListDir.mkdir();
                Log.i("CheckEnvironment.checkDirs", dir+" Directory Created");
            }
        }
    }


}
