package com.dmello.yugioheditor.yugioheditor;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.dmello.yugioheditor.yugioheditor.CardLibrary.CardLibReader;
import com.dmello.yugioheditor.yugioheditor.CardLibrary.CardLibWriter;
import com.dmello.yugioheditor.yugioheditor.CardLibrary.CardList;

import java.io.BufferedOutputStream;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class MainActivity extends ActionBarActivity {

    private Button listReadButton;
    private Button listWriteButton;
    private Button addCardButton;
    private Button addAllButton;
    private Button printCardsButton;
    private CardList cardLibrary;
    private String appDir;


    private void listReadButtonClick(){
            Log.d("MainActivity.listReadButtonClick", "List Read Button");
            CardLibReader mainListReader = new CardLibReader(this.getApplicationContext().getFilesDir() + Directory.CARD_LIB.getDir() + "u00_cardlib.dat");
            cardLibrary = mainListReader.readAll();
        Log.d("MainActivity.listReadButtonClick", cardLibrary.asArrayList().size() + " different cards read");
        Log.d("MainActivity.listReadButtonClick", cardLibrary.getCardCount() + " total different cards read");

    }

    private void listWriteButtonClick(){
        Log.d("MainActivity.listWriteButtonClick", "List Write Button");
        CardLibWriter mainListWriter = new CardLibWriter(this.getApplicationContext().getFilesDir() + Directory.CARD_LIB.getDir() + "u00_cardlib.dat");
        mainListWriter.writeAll(cardLibrary);
    }

    private void addCardButtonClick(){
        Log.d("MainActivity.addCardButtonClick","Add Card Button");
        TextView cardIdView = (TextView) findViewById(R.id.cardId);
        String cardIdText = cardIdView.getText().toString();
        if(cardIdText.equals("")){
            Log.w("MainActivity.addCardButtonClick","Empty Card ID Field");
        }
        else{
            short cardId = Short.parseShort(cardIdText);
            Card newCard = new Card(cardId,3,0);
            cardLibrary.add(newCard);
        }
    }

    private void printCardsButtonClick(){
        Log.d("MainActivity.printCardsButtonClick","Print Card List");
        for(Card card:cardLibrary.asArrayList()){
            Log.d("MainActivity.printCardsButtonClick",card.toString());
        }
    }

    private void addAllButtonClick(){
        Log.d("MainActivity.addAllButtonClick","Add All Button");
        boolean first = true;
        for(Card card:cardLibrary.asArrayList()){
            if(first){
                first = false;
            }
            else{
                card.setCardAmount(3);
            }
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Check Environment
        appDir = this.getApplicationContext().getFilesDir().toString();
        CheckEnvironment.checkDirs(appDir);

        //Create Buttons and listeners
        listReadButton = (Button) findViewById(R.id.listReadButton);
        listWriteButton = (Button) findViewById(R.id.listWriteButton);
        addCardButton = (Button) findViewById(R.id.addCardButton);
        addAllButton = (Button) findViewById(R.id.addAllButton);
        printCardsButton = (Button) findViewById(R.id.printCardsButton);

        listReadButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listReadButtonClick();
            }
        });

        listWriteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listWriteButtonClick();
            }
        });

        addCardButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addCardButtonClick();
            }
        });

        addAllButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addAllButtonClick();
            }
        });

        printCardsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                printCardsButtonClick();
            }
        });

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
