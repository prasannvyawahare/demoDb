package com.example.dbdemo1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private EventDatabase eventDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        eventDatabase = new EventDatabase(this);
        eventDatabase. saveEvents(new Profile("abc",1));


       List<Profile> profiles=eventDatabase. getAllEarlierEvent();
        Log.d("logmeee",profiles.toString());
    }

}