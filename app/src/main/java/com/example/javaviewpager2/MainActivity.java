package com.example.javaviewpager2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
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
    CompositePageTransformer compositePageTransformer = new CompositePageTransformer();
    List<MyPerson> myList= new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        viewPager2 = findViewById(R.id.viewPager2);

        //建立需要的物件並放入清單
        ListSetting();

        //將清單放入ListAdapter，並將ListAdapter綁定到viewPager2上。
        MyListAdapter adapter = new MyListAdapter(myList);
        viewPager2.setAdapter(adapter);

        // 設置預覽效果
        viewPager2Preview(viewPager2);

        //在viewPager2上設置間距與轉場效果
        //scale可設定左右頁面的高度倍率，移動到主頁時倍率會逐漸增加至1，就能看出0.8->1的轉場放大效果。
        //margin可設定相鄰頁面間需間隔多少pixel
        viewPager2MarginAnimation(viewPager2,0.8f,20);

    }

    private void ListSetting() {
        // create fake person
        MyPerson myPerson1 = new MyPerson("菊草葉", "草", 14,R.drawable.green);
        MyPerson myPerson2 = new MyPerson("波加曼","水",16,R.drawable.blue);
        MyPerson myPerson3 = new MyPerson("火雉雞","火",15,R.drawable.red);

        // ... then create a list of people to insert into your adapter
        myList.add(myPerson1);
        myList.add(myPerson2);
        myList.add(myPerson3);

    }

    private void viewPager2Preview(ViewPager2 viewPager2) {
        // 設置一屏三頁的效果
        viewPager2.setOffscreenPageLimit(1);//設置為1，會載入左右各一頁。
        viewPager2.setClipToPadding(false);//保留viewPager2視窗被padding的地方
        viewPager2.setClipChildren(false);//保留左右兩頁的視窗
        viewPager2.getChildAt(0).setOverScrollMode(RecyclerView.OVER_SCROLL_NEVER);

    }

    private void viewPager2MarginAnimation(ViewPager2 ViewPager2,float scale,int margin) {

        compositePageTransformer.addTransformer(new MarginPageTransformer(margin));
        compositePageTransformer.addTransformer((page, position) -> {
            float r = 1-Math.abs(position);
            page.setScaleY(scale + r * (1-scale));
        });
        ViewPager2.setPageTransformer(compositePageTransformer);
    }

}