package com.example.javaviewpager2;

import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.CompositePageTransformer;
import androidx.viewpager2.widget.MarginPageTransformer;
import androidx.viewpager2.widget.ViewPager2;

import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = MainActivity.class.getSimpleName();

    private ViewPager2 viewPager2;
    private TabLayout tabLayout;

    /** MainActivity 初始化之後，可以依照選單的項目進行特效轉換。
     * onCreate 為初始化效果，接下來的特效變化由 Menu 中被選中的 Item 來決定。
     * onCreateOptionsMenu(匯入自定義清單) onOptionsItemSelected(根據被選中的Item進行操作) **/
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        viewPager2 = findViewById(R.id.viewPager2);
        tabLayout = findViewById(R.id.tab_layout);

        // 將清單放入ListAdapter，並將ListAdapter綁定到viewPager2上。
        MyListAdapter adapter = new MyListAdapter(getPersonList());
        viewPager2.setAdapter(adapter);
        setViewPagerTransformerEnlargeWhenScroll(0f, 40);
        Log.d(TAG, "onCreate");
    }

    // 將menu.xml的Layout與Item匯入activity_main.xml
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    // 獲得menu上不同Item的Id，並將對應的效果放到activity_main.xml。
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        // 根據不同id產生不同的動效
        // 以下的Log用來查驗拿到的Id是否正確
        switch (id) {
            // 基本的頁面切換，不能預覽(Clip = true)，也沒有放大效果(zoom = 0)。
            case R.id.regular:
                Log.d(TAG, "regular");
                setViewPagerScroll(true); // 參數為Clip，true表示要裁減被Padding到的頁面。
                setViewPagerTransformerEnlargeWhenScroll(0, 40);
                return true;

            // 基本的一屏三頁特效，能預覽(Clip = false)，沒有放大效果(zoom = 0)。
            case R.id.one_screen_three_page_basic:
                Log.d(TAG, "one_screen_three_page_basic");
                setViewPagerScroll(false);
                setViewPagerTransformerEnlargeWhenScroll(0, 40);
                return true;

            // 進階的一屏三頁特效，能預覽(Clip = false)，有放大效果(zoom = 0.35f)。
            case R.id.one_screen_three_page_advanced:
                Log.d(TAG, "one_screen_three_page_advanced");
                setViewPagerScroll(false);
                setViewPagerTransformerEnlargeWhenScroll(0.35f, 40);
                return true;

            // 將PageIndicator放到activity_main.xml
            case R.id.page_indicator:
                Log.d(TAG, "page_indicator");
                linkPageIndicatorAndViewPager2();
                return true;

            case R.id.style_move:
                Log.d(TAG, "style_move");
                return true;

            case R.id.style_cube:
                Log.d(TAG, "style_cube");
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private List<MyPerson> getPersonList() {
        // create list
        List<MyPerson> myList = new ArrayList<>();

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

    private void setViewPagerScroll(Boolean clip) {
        // 設置一屏三頁的效果
        viewPager2.setOffscreenPageLimit(1); // 設置為1，會載入左右各一頁，避免使用者滑動過快來不及加載。
        viewPager2.setClipToPadding(clip); // 保留viewPager2視窗被padding的地方
        viewPager2.setClipChildren(clip); // 保留左右兩頁的視窗
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

    private void linkPageIndicatorAndViewPager2() {
        // TabLayoutMediator會對應viewPager2的頁面數量，產生對應的tab(選項卡)。
        // 每個被產生的tab在TabLayout裡會以水平方式排列，並根據在activity_main.xml的設定在背景放置對應顏色的圓點。
        // 綜合上述兩個註解，可看出這個函式就是產生PageIndicator的原因。
        new TabLayoutMediator(tabLayout, viewPager2, new TabLayoutMediator.TabConfigurationStrategy() {
            @Override
            public void onConfigureTab(TabLayout.Tab tab, int position) {

            }
        }).attach();
    }
}