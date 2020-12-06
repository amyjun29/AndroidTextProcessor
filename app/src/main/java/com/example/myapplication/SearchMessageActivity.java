package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.BackgroundColorSpan;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;

public class SearchMessageActivity extends AppCompatActivity {

    private static final String FILE_NAME = "textfile.txt";
    Button caseButton, searchButton;
    TextView textView;
    EditText searchTerm;


    //class to display saved text file
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_message);
        caseButton = (Button)findViewById(R.id.caseButton);
        searchButton = (Button)findViewById(R.id.searchButton);
        textView = (TextView) findViewById(R.id.fileMessage);
        searchTerm = (EditText)findViewById(R.id.searchTerm);

        FileInputStream fis = null;

        try {                                                   //try catch phrase to open and display text file
            fis = openFileInput(FILE_NAME);
            InputStreamReader isr = new InputStreamReader(fis);
            BufferedReader br = new BufferedReader(isr);
            StringBuilder sb = new StringBuilder();
            String text;

            while ((text = br.readLine()) != null) {
                sb.append(text).append("\n");
            }

            textView.setText(sb.toString());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (fis != null) {
                try {
                    fis.close();
                } catch (IOException e) {
                    e.printStackTrace();


                }
            }
        }

    }


    //method to switch text to lower/upper case
    public void switchCase(View v) {

        String message = textView.getText().toString();
        String changecase;

        if (!message.isEmpty()) {
            if (message.length() > 0) {
                if(!message.equals(message.toLowerCase())) {                    //if the text has upper case letters
                    changecase = textView.getText().toString().toLowerCase();   //switch to lower case
                    textView.setText(String.valueOf(changecase));
                    caseButton.setText(String.valueOf("upper case"));           //set button to say "upper case"

                }
                else if(!message.equals(message.toUpperCase())){                //if the text has lower case letters
                    changecase = textView.getText().toString().toUpperCase();   //switch to upper class
                    textView.setText(String.valueOf(changecase));
                    caseButton.setText(String.valueOf("lower Case"));           //set button to say "lower case"
                }
            }
        } else {                                                                //if there's no text
            textView.setError("There's no text");
            Toast.makeText(this, "There's no text", Toast.LENGTH_LONG).show();
        }


    }

    //method to delete text file
    public void deleteText(View v){
    File file = new File(getFilesDir() + "/" + FILE_NAME);
        boolean deleted = file.delete();
        Toast.makeText(this, "text file deleted", Toast.LENGTH_LONG).show(); //toast display to say file was deleted
        textView.setText(textView.getText().toString());
        Intent reload = new Intent(this, SearchMessageActivity.class);  //refresh layout to show empty text file
        startActivity(reload);

    }

    //method to highlight a search keyword
    public void setHighLightedText(View v) {
        String tv = textView.getText().toString();                  //text file
        String textToHighlight = searchTerm.getText().toString();   //search keyword to look for in text file
        int offset = tv.indexOf(textToHighlight, 0);
        Spannable wordToSpan = new SpannableString(textView.getText());

        if (searchButton.getText().equals("reset")) {               //if the button is set to reset, clear search keyword
            textView.setText(tv);
            searchButton.setText("go");
            searchTerm.setText("");


        } else {
            for (int ofs = 0; ofs < tv.length() && offset != -1; ofs = offset + 1) {
                offset = tv.indexOf(textToHighlight, ofs);
                if (offset == -1)
                    break;
                //high light the found search keyword to yellow
                wordToSpan.setSpan(new BackgroundColorSpan(Color.YELLOW), offset, offset + textToHighlight.length(), 0);
                textView.setText(wordToSpan);
                //set button to say reset
                searchButton.setText("reset");

            }
        }
    }


}