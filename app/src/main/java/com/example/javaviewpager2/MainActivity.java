package com.example.javaviewpager2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.CompositePageTransformer;
import androidx.viewpager2.widget.MarginPageTransformer;
import androidx.viewpager2.widget.ViewPager2;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toolbar;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ViewPager2 viewPager2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        viewPager2 = findViewById(R.id.viewPager2);

        // 將清單放入ListAdapter，並將ListAdapter綁定到viewPager2上。
        MyListAdapter adapter = new MyListAdapter(getPersonList());
        viewPager2.setAdapter(adapter);

        // 設置viewPager動畫效果
        setViewPagerScroll();
        setViewPagerTransformerEnlargeWhenScroll(0.2f,20);

        //Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        //setSupportActionBar(toolbar);
        //ButterKnife.bind(this);

    }

    private List<MyPerson> getPersonList() {
        // create list
        List<MyPerson> myList= new ArrayList<>();

        // create fake person
        MyPerson myPerson1 = new MyPerson("菊草葉", "草", 14, R.drawable.green);
        MyPerson myPerson2 = new MyPerson("波加曼", "水", 16, R.drawable.blue);
        MyPerson myPerson3 = new MyPerson("火雉雞", "火", 15, R.drawable.red);

        // then add people into the list
        myList.add(myPerson1);
        myList.add(myPerson2);
        myList.add(myPerson3);

        return myList;
    }

    private void setViewPagerScroll() {
        // 設置一屏三頁的效果
        viewPager2.setOffscreenPageLimit(1); // 設置為1，會載入左右各一頁，避免使用者滑動過快來不及加載。
        viewPager2.setClipToPadding(false); // 保留viewPager2視窗被padding的地方
        viewPager2.setClipChildren(false); // 保留左右兩頁的視窗
        // 讓首頁跟末頁沒有滑動效果
        viewPager2.getChildAt(0).setOverScrollMode(RecyclerView.OVER_SCROLL_NEVER);
    }

    private void setViewPagerTransformer(float scale, int margin) {
        // 在viewPager2上設置間距與轉場效果
        // margin可設定相鄰頁面間需間隔多少pixel
        // scale可設定左右頁面的高度倍率，移動到主頁時倍率會逐漸增加至1，就能看出0.8->1的轉場放大效果。
        // scale只能是0~1的值
        CompositePageTransformer compositePageTransformer = new CompositePageTransformer();
        compositePageTransformer.addTransformer(new MarginPageTransformer(margin));
        compositePageTransformer.addTransformer((page, position) -> {
            float r = 1 - Math.abs(position);
            page.setScaleY(scale + r * (1 - scale));
        });
        viewPager2.setPageTransformer(compositePageTransformer);
    }

    private void setViewPagerTransformerEnlargeWhenScroll(float zoom, int margin) {
        // 在viewPager2上設置間距與轉場效果
        // margin可設定相鄰頁面間需間隔多少pixel
        // zoom可設定每個頁面在滑動時的放大倍率(建議值:0.1f~0.3f)
        CompositePageTransformer compositePageTransformer = new CompositePageTransformer();
        compositePageTransformer.addTransformer(new MarginPageTransformer(margin));
        // page代表每個頁面,而position是每個頁面的位置(左、中、右分別為-1,0,1)。
        compositePageTransformer.addTransformer((page, position) -> {
            // pos參數將position加上絕對值
            float pos = Math.abs(position);
            // (1-pos)會讓position在-1和1的page保持高度為1的倍率
            // pos會讓position在0的page保持高度為1的倍率
            // 所以1 + (1 - pos) * pos這個算法會同時讓position在-1,0,1的page高度設定為1倍。
            // 在滑動時pos為小數，所以倍率會是1點多倍，就會產生滑動時微放大的轉場效果。
            // 只用 1 + (1 - pos) * pos的算法會使頁面過度放大，乘上zoom參數用來抑制這個現象，所以才會有建議值。
            float scaleFactor = Math.max(1, 1 + zoom * (1 - pos) * pos);
            page.setScaleY(scaleFactor);
        });
        viewPager2.setPageTransformer(compositePageTransformer);
    }

    @SuppressLint("ResourceType")
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        MainFragment f = (MainFragment) getSupportFragmentManager().findFragmentById(R.id.style_cube); // style_cube記的做更改
        switch (id) {
            case R.id.style_move:
                //f.setAnimationStyle(MainFragment.Move);
                return true;
            case R.id.style_cube:
                //f.setAnimationStyle(MainFragment.Cube);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}