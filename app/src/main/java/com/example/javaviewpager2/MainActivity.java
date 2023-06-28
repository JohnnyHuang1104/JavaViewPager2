package com.example.javaviewpager2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // create fake person
        MyPerson myPerson1 = new MyPerson("Anne", 1, 12346578);

        // ... then create a list of people to insert into your adapter
    }
}