package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class NewMessageActivity extends AppCompatActivity {



    private static final String FILE_NAME = "textfile.txt";    //file name will be textfile.txt
    EditText textField;

    //class to write to text file
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_message);

        textField = (EditText)findViewById(R.id.textField); //display text input
    }

    public void saveText(View v){                       //method to save user text input as a text file
        String text = textField.getText().toString();   //convert user text input to a string
        FileOutputStream fos = null;


        try{                                            //try catch phrase to write to a text file
            fos = openFileOutput(FILE_NAME, MODE_PRIVATE);
            fos.write(text.getBytes());
            textField.getText().clear();
            Toast.makeText(this, "Saved to " + getFilesDir() + "/" + FILE_NAME, Toast.LENGTH_LONG).show();
                                                        //display toast text to show where the text file is saved

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if( fos != null){
                try {
                    fos.close();
                }   catch (IOException e){
                    e.printStackTrace();
                }
            }

        }

    }




}
