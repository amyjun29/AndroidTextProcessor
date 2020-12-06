package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private Button newMessageButton;
    private Button searchMessageButton;

    //first page layout that will have two buttons to either write/display text file
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        newMessageButton = (Button) findViewById(R.id.newMessageButton);
        newMessageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick (View v) {
                openNewMessageActivity();
            }  //open a new layout to write a text file when button is clicked

        });

        searchMessageButton = (Button) findViewById(R.id.searchMessageButton);
        searchMessageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick (View v) {
                openSearchMessageActivity(); //open a new layout to display text file when button is clicked
            }

        });
    }

    public void openNewMessageActivity(){   //method to open a layout to write text file
        Intent newIntent = new Intent(this, NewMessageActivity.class);
        startActivity(newIntent);

    }

    public void openSearchMessageActivity(){    //method to open a layout to display text file
        Intent searchIntent = new Intent(this, SearchMessageActivity.class);
        startActivity(searchIntent);

    }
}