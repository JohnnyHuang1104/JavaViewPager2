package com.example.javaviewpager2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.CompositePageTransformer;
import androidx.viewpager2.widget.MarginPageTransformer;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ListAdapter;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ViewPager2 viewPager2;

    private ListAdapter listadapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        viewPager2 = findViewById(R.id.viewPager2);
        // create fake person
        MyPerson myPerson1 = new MyPerson("菊草葉", "草", 14,R.drawable.green);
        MyPerson myPerson2 = new MyPerson("波加曼","水",16,R.drawable.blue);
        MyPerson myPerson3 = new MyPerson("火雉雞","火",15,R.drawable.red);

        // ... then create a list of people to insert into your adapter
        List<MyPerson> myList= new ArrayList<>();
        myList.add(myPerson1);
        myList.add(myPerson2);
        myList.add(myPerson3);
        MyListAdapter adapter = new MyListAdapter(myList);
        viewPager2.setAdapter(adapter);
        // 設置一屏三頁的效果
        viewPager2.setOffscreenPageLimit(1);
        viewPager2.setClipToPadding(false);
        viewPager2.setClipChildren(false);

        // 設置預覽效果的間距和偏移量
        CompositePageTransformer compositePageTransformer = new CompositePageTransformer();
        compositePageTransformer.addTransformer(new MarginPageTransformer(40));

        compositePageTransformer.addTransformer(new ViewPager2.PageTransformer(){
            @Override
            public void transformPage(@NonNull View page, float position) {
                float r = 1 - Math.abs(position);
                page.setScaleY(0.85f + r*0.15f);
            }
        });
        viewPager2.setPageTransformer(compositePageTransformer);

    }

}