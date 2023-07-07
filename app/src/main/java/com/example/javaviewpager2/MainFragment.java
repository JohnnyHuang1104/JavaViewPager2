package com.example.javaviewpager2;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.animation.Animation;
import android.widget.TextView;

import androidx.annotation.IntDef;
import androidx.fragment.app.Fragment;

public class MainFragment extends Fragment {

    // 宣告各個動畫效果其Int值要設為多少
    @IntDef({None, Move, Cube})
    public @interface AnimationStyle {
    }
    public static final int None = 0;
    public static final int Move = 1;
    public static final int Cube = 2;

    // 宣告Left和Right其Int值要設為多少，以及duration的設定
    @IntDef({Left, Right})
    public @interface AnimationDirection {
    }

    public static final int Left = 1;
    public static final int Right = 2;

    public static final long duration = 1000;

    private static int sAnimationStyle = Move;


    public static MainFragment newInstance(@AnimationDirection int direction) {
        MainFragment f = new MainFragment();
        f.setArguments(new Bundle());
        assert f.getArguments() != null;  // 確保f的參數不為空
        f.getArguments().putInt("direction", direction);
        return f;
    }

    public Animation onCreateAnimation(int transit, boolean enter, int nextAnim) {
        assert getArguments() != null;
        switch (sAnimationStyle) {
            case Move:
                switch (getArguments().getInt("direction")) {

                    case Left:
                        return MoveAnimation.create(MoveAnimation.Left, enter, duration);
                    case Right:
                        return MoveAnimation.create(MoveAnimation.Right, enter, duration);
                }
                break;

            case Cube:
                switch (getArguments().getInt("direction")) {

                    case Left:
                        return MoveAnimation.create(MoveAnimation.Left, enter, duration);
                    case Right:
                        return MoveAnimation.create(MoveAnimation.Right, enter, duration);
                }
                break;
        }
        return null;
    }

 }


