package com.example.javaviewpager2;

import android.view.View;
import android.view.animation.Animation;
import android.widget.TextView;

import androidx.annotation.IntDef;
import androidx.fragment.app.Fragment;

import com.example.javaviewpager2.animation.MoveAnimation;
import com.google.android.material.snackbar.Snackbar;

public class MainFragment extends Fragment {

    // 宣告各個動畫效果其Int值要設為多少
    @IntDef({None, Move, Cube})
    public @interface AnimationStyle {}

    public static final int None = 0;
    public static final int Move = 1;
    public static final int Cube = 2;

    // 宣告Left和Right其Int值要設為多少，以及duration的設定

    @IntDef({NoDir, Left, Right})
    public @interface AnimationDirection {
    }

    public static final int NoDir = 0;
    public static final int Left = 1;
    public static final int Right = 2;
    public static final long duration = 1000; // 設定從A到B頁面轉換所需要的時間(單位:毫秒)
    private static int sAnimationStyle = Cube;

    TextView mTextAnimationStyle;

    public Animation onCreateAnimation(int transit, boolean enter, int nextAnim) {
        assert getArguments() != null;
        switch (sAnimationStyle) {
            case Move:
                switch (getArguments().getInt("direction")) {
                    case Left: // 設定向左或向右滑各會產生的效果
                        return MoveAnimation.create(MoveAnimation.Left, enter, duration);
                    case Right:
                        return MoveAnimation.create(MoveAnimation.Right, enter, duration);
                }
                break;

            case Cube:
                switch (getArguments().getInt("direction")) {
                    case Left:
                        return CubeAnimation.create(CubeAnimation.Left, enter, duration);
                    case Right:
                        return CubeAnimation.create(CubeAnimation.Right, enter, duration);
                }
                break;
        }
        return null;
    }
    public void switchAnimationStyle(View view) {
        @AnimationStyle int[] styles;
        styles = new int[]{Move, Cube};
        for (int i = 0; i<= styles.length-1; ++i) {
            if (styles[i] == sAnimationStyle) {
                setAnimationStyle(styles[i+1]);
                return;
            }
        }
        setAnimationStyle(Move);
    }

    public void setAnimationStyle(@AnimationStyle int style) {
        if (sAnimationStyle != style) {
            sAnimationStyle = style;
            setAnimationStyleText();
            Snackbar.make(getView(), "Animation Style is Changed", Snackbar.LENGTH_SHORT).show(); // 如同Toast寫法告知使用者知道自己轉換頁面
        }
    }

    private void setAnimationStyleText() {
        switch (sAnimationStyle) {
            case None:
                mTextAnimationStyle.setText("None");
                break;
            case Move:
                mTextAnimationStyle.setText("Move");
                break;
        }
    }
}




