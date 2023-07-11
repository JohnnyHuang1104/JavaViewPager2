package com.example.javaviewpager2;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.widget.TextView;

import androidx.annotation.IntDef;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.javaviewpager2.animation.CubeAnimation;
import com.example.javaviewpager2.animation.FlipAnimation;
import com.example.javaviewpager2.animation.MoveAnimation;
import com.example.javaviewpager2.animation.PushPullAnimation;
import com.example.javaviewpager2.animation.SidesAnimation;
import com.google.android.material.snackbar.Snackbar;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link MainFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MainFragment extends Fragment {

    @IntDef({NONE, MOVE, CUBE, FLIP, PUSHPULL, SIDES, CUBEMOVE, MOVECUBE, PUSHMOVE, MOVEPULL, FLIPMOVE, MOVEFLIP, FLIPCUBE, CUBEFLIP})
    public @interface AnimationStyle {}
    public static final int NONE     = 0;
    public static final int MOVE     = 1;
    public static final int CUBE     = 2;
    public static final int FLIP     = 3;
    public static final int PUSHPULL = 4;
    public static final int SIDES    = 5;
    public static final int CUBEMOVE = 6;
    public static final int MOVECUBE = 7;
    public static final int PUSHMOVE = 8;
    public static final int MOVEPULL = 9;
    public static final int FLIPMOVE = 10;
    public static final int MOVEFLIP = 11;
    public static final int FLIPCUBE = 12;
    public static final int CUBEFLIP = 13;

    @IntDef({NODIR, UP, DOWN, LEFT, RIGHT})
    public @interface AnimationDirection {}
    public static final int NODIR = 0;
    public static final int UP    = 1;
    public static final int DOWN  = 2;
    public static final int LEFT  = 3;
    public static final int RIGHT = 4;

    private static final long DURATION = 500;

    @AnimationStyle
    private static int sAnimationStyle = CUBE;

    @SuppressLint("NonConstantResourceId")
    TextView mTextAnimationStyle;


    public static MainFragment newInstance(@AnimationDirection int direction) {
        MainFragment f = new MainFragment();
        f.setArguments(new Bundle());
        assert f.getArguments() != null;
        f.getArguments().putInt("direction", direction);
        return f;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.page, container, false); // layout名稱改過
        int color = Color.rgb((int) Math.floor(Math.random() * 128) + 64,
                (int) Math.floor(Math.random() * 128) + 64,
                (int) Math.floor(Math.random() * 128) + 64);
        view.setBackgroundColor(color);
        ButterKnife.bind(this, view);

        mTextAnimationStyle = view.findViewById(R.id.textAnimationStyle);
        setAnimationStyleText();

        return view;
    }

    @Override
    @Nullable
    public Animation onCreateAnimation(int transit, boolean enter, int nextAnim) {
        assert getArguments() != null;
        switch (sAnimationStyle) {
            case MOVE:
                switch (getArguments().getInt("direction")) {
                    case UP:
                        return MoveAnimation.create(MoveAnimation.UP, enter, DURATION);
                    case DOWN:
                        return MoveAnimation.create(MoveAnimation.DOWN, enter, DURATION);
                    case LEFT:
                        return MoveAnimation.create(MoveAnimation.LEFT, enter, DURATION);
                    case RIGHT:
                        return MoveAnimation.create(MoveAnimation.RIGHT, enter, DURATION);
                }
                break;
            case CUBE:
                switch (getArguments().getInt("direction")) {
                    case UP:
                        return CubeAnimation.create(CubeAnimation.UP, enter, DURATION);
                    case DOWN:
                        return CubeAnimation.create(CubeAnimation.DOWN, enter, DURATION);
                    case LEFT:
                        return CubeAnimation.create(CubeAnimation.LEFT, enter, DURATION);
                    case RIGHT:
                        return CubeAnimation.create(CubeAnimation.RIGHT, enter, DURATION);
                }
                break;
            case FLIP:
                switch (getArguments().getInt("direction")) {
                    case UP:
                        return FlipAnimation.create(FlipAnimation.UP, enter, DURATION);
                    case DOWN:
                        return FlipAnimation.create(FlipAnimation.DOWN, enter, DURATION);
                    case LEFT:
                        return FlipAnimation.create(FlipAnimation.LEFT, enter, DURATION);
                    case RIGHT:
                        return FlipAnimation.create(FlipAnimation.RIGHT, enter, DURATION);
                }
                break;
            case PUSHPULL:
                switch (getArguments().getInt("direction")) {
                    case UP:
                        return PushPullAnimation.create(PushPullAnimation.UP, enter, DURATION);
                    case DOWN:
                        return PushPullAnimation.create(PushPullAnimation.DOWN, enter, DURATION);
                    case LEFT:
                        return PushPullAnimation.create(PushPullAnimation.LEFT, enter, DURATION);
                    case RIGHT:
                        return PushPullAnimation.create(PushPullAnimation.RIGHT, enter, DURATION);
                }
                break;
            case SIDES:
                switch (getArguments().getInt("direction")) {
                    case UP:
                        return SidesAnimation.create(SidesAnimation.UP, enter, DURATION);
                    case DOWN:
                        return SidesAnimation.create(SidesAnimation.DOWN, enter, DURATION);
                    case LEFT:
                        return SidesAnimation.create(SidesAnimation.LEFT, enter, DURATION);
                    case RIGHT:
                        return SidesAnimation.create(SidesAnimation.RIGHT, enter, DURATION);
                }
                break;
            case CUBEMOVE:
                switch (getArguments().getInt("direction")) {
                    case UP:
                        return enter ? MoveAnimation.create(MoveAnimation.UP, enter, DURATION).fading(0.3f, 1.0f) :
                                CubeAnimation.create(CubeAnimation.UP, enter, DURATION).fading(1.0f, 0.3f);
                    case DOWN:
                        return enter ? MoveAnimation.create(MoveAnimation.DOWN, enter, DURATION).fading(0.3f, 1.0f) :
                                CubeAnimation.create(CubeAnimation.DOWN, enter, DURATION).fading(1.0f, 0.3f);
                    case LEFT:
                        return enter ? MoveAnimation.create(MoveAnimation.LEFT, enter, DURATION).fading(0.3f, 1.0f) :
                                CubeAnimation.create(CubeAnimation.LEFT, enter, DURATION).fading(1.0f, 0.3f);
                    case RIGHT:
                        return enter ? MoveAnimation.create(MoveAnimation.RIGHT, enter, DURATION).fading(0.3f, 1.0f) :
                                CubeAnimation.create(CubeAnimation.RIGHT, enter, DURATION).fading(1.0f, 0.3f);
                }
                break;
            case MOVECUBE:
                switch (getArguments().getInt("direction")) {
                    case UP:
                        return enter ? CubeAnimation.create(CubeAnimation.UP, enter, DURATION).fading(0.3f, 1.0f) :
                                MoveAnimation.create(MoveAnimation.UP, enter, DURATION).fading(1.0f, 0.3f);
                    case DOWN:
                        return enter ? CubeAnimation.create(CubeAnimation.DOWN, enter, DURATION).fading(0.3f, 1.0f) :
                                MoveAnimation.create(MoveAnimation.DOWN, enter, DURATION).fading(1.0f, 0.3f);
                    case LEFT:
                        return enter ? CubeAnimation.create(CubeAnimation.LEFT, enter, DURATION).fading(0.3f, 1.0f) :
                                MoveAnimation.create(MoveAnimation.LEFT, enter, DURATION).fading(1.0f, 0.3f);
                    case RIGHT:
                        return enter ? CubeAnimation.create(CubeAnimation.RIGHT, enter, DURATION).fading(0.3f, 1.0f) :
                                MoveAnimation.create(MoveAnimation.RIGHT, enter, DURATION).fading(1.0f, 0.3f);
                }
                break;
            case PUSHMOVE:
                switch (getArguments().getInt("direction")) {
                    case UP:
                        return enter ? MoveAnimation.create(MoveAnimation.UP, enter, DURATION) :
                                PushPullAnimation.create(PushPullAnimation.UP, enter, DURATION);
                    case DOWN:
                        return enter ? MoveAnimation.create(MoveAnimation.DOWN, enter, DURATION) :
                                PushPullAnimation.create(PushPullAnimation.DOWN, enter, DURATION);
                    case LEFT:
                        return enter ? MoveAnimation.create(MoveAnimation.LEFT, enter, DURATION) :
                                PushPullAnimation.create(PushPullAnimation.LEFT, enter, DURATION);
                    case RIGHT:
                        return enter ? MoveAnimation.create(MoveAnimation.RIGHT, enter, DURATION) :
                                PushPullAnimation.create(PushPullAnimation.RIGHT, enter, DURATION);
                }
                break;
            case MOVEPULL:
                switch (getArguments().getInt("direction")) {
                    case UP:
                        return enter ? PushPullAnimation.create(PushPullAnimation.UP, enter, DURATION) :
                                MoveAnimation.create(MoveAnimation.UP, enter, DURATION).fading(1.0f, 0.3f);
                    case DOWN:
                        return enter ? PushPullAnimation.create(PushPullAnimation.DOWN, enter, DURATION) :
                                MoveAnimation.create(MoveAnimation.DOWN, enter, DURATION).fading(1.0f, 0.3f);
                    case LEFT:
                        return enter ? PushPullAnimation.create(PushPullAnimation.LEFT, enter, DURATION) :
                                MoveAnimation.create(MoveAnimation.LEFT, enter, DURATION).fading(1.0f, 0.3f);
                    case RIGHT:
                        return enter ? PushPullAnimation.create(PushPullAnimation.RIGHT, enter, DURATION) :
                                MoveAnimation.create(MoveAnimation.RIGHT, enter, DURATION).fading(1.0f, 0.3f);
                }
                break;
            case FLIPMOVE:
                switch (getArguments().getInt("direction")) {
                    case UP:
                        return enter ? MoveAnimation.create(MoveAnimation.UP, enter, DURATION) :
                                FlipAnimation.create(FlipAnimation.UP, enter, DURATION);
                    case DOWN:
                        return enter ? MoveAnimation.create(MoveAnimation.DOWN, enter, DURATION) :
                                FlipAnimation.create(FlipAnimation.DOWN, enter, DURATION);
                    case LEFT:
                        return enter ? MoveAnimation.create(MoveAnimation.LEFT, enter, DURATION) :
                                FlipAnimation.create(FlipAnimation.LEFT, enter, DURATION);
                    case RIGHT:
                        return enter ? MoveAnimation.create(MoveAnimation.RIGHT, enter, DURATION) :
                                FlipAnimation.create(FlipAnimation.RIGHT, enter, DURATION);
                }
                break;
            case MOVEFLIP:
                switch (getArguments().getInt("direction")) {
                    case UP:
                        return enter ? FlipAnimation.create(FlipAnimation.UP, enter, DURATION) :
                                MoveAnimation.create(MoveAnimation.UP, enter, DURATION).fading(1.0f, 0.3f);
                    case DOWN:
                        return enter ? FlipAnimation.create(FlipAnimation.DOWN, enter, DURATION) :
                                MoveAnimation.create(MoveAnimation.DOWN, enter, DURATION).fading(1.0f, 0.3f);
                    case LEFT:
                        return enter ? FlipAnimation.create(FlipAnimation.LEFT, enter, DURATION) :
                                MoveAnimation.create(MoveAnimation.LEFT, enter, DURATION).fading(1.0f, 0.3f);
                    case RIGHT:
                        return enter ? FlipAnimation.create(FlipAnimation.RIGHT, enter, DURATION) :
                                MoveAnimation.create(MoveAnimation.RIGHT, enter, DURATION).fading(1.0f, 0.3f);
                }
                break;
            case FLIPCUBE:
                switch (getArguments().getInt("direction")) {
                    case UP:
                        return enter ? CubeAnimation.create(CubeAnimation.UP, enter, DURATION) :
                                FlipAnimation.create(FlipAnimation.UP, enter, DURATION);
                    case DOWN:
                        return enter ? CubeAnimation.create(CubeAnimation.DOWN, enter, DURATION) :
                                FlipAnimation.create(FlipAnimation.DOWN, enter, DURATION);
                    case LEFT:
                        return enter ? CubeAnimation.create(CubeAnimation.LEFT, enter, DURATION) :
                                FlipAnimation.create(FlipAnimation.LEFT, enter, DURATION);
                    case RIGHT:
                        return enter ? CubeAnimation.create(CubeAnimation.RIGHT, enter, DURATION) :
                                FlipAnimation.create(FlipAnimation.RIGHT, enter, DURATION);
                }
                break;
            case CUBEFLIP:
                switch (getArguments().getInt("direction")) {
                    case UP:
                        return enter ? FlipAnimation.create(FlipAnimation.UP, enter, DURATION) :
                                CubeAnimation.create(CubeAnimation.UP, enter, DURATION).fading(1.0f, 0.3f);
                    case DOWN:
                        return enter ? FlipAnimation.create(FlipAnimation.DOWN, enter, DURATION) :
                                CubeAnimation.create(CubeAnimation.DOWN, enter, DURATION).fading(1.0f, 0.3f);
                    case LEFT:
                        return enter ? FlipAnimation.create(FlipAnimation.LEFT, enter, DURATION) :
                                CubeAnimation.create(CubeAnimation.LEFT, enter, DURATION).fading(1.0f, 0.3f);
                    case RIGHT:
                        return enter ? FlipAnimation.create(FlipAnimation.RIGHT, enter, DURATION) :
                                CubeAnimation.create(CubeAnimation.RIGHT, enter, DURATION).fading(1.0f, 0.3f);
                }
                break;
            case NONE:
                break;
        }
        return null;
    }


    //@SuppressLint("NonConstantResourceId")
    //@SuppressWarnings("unused")
    //@OnClick(R.id.buttonUp)
    //void onButtonUp() {
    //    assert getArguments() != null;
    //    getArguments().putInt("direction", UP);
    //    FragmentManager fragmentManager = this.getParentFragmentManager();
    //    FragmentTransaction ft = fragmentManager.beginTransaction();
    //    ft.replace(R.id.layout_main, MainFragment.newInstance(UP));
    //    ft.commit();
    //}

    //@SuppressLint("NonConstantResourceId")
    //@SuppressWarnings("unused")
    //@OnClick(R.id.buttonDown)
    //void onButtonDown() {
    //    assert getArguments() != null;
    //    getArguments().putInt("direction", DOWN);
    //    FragmentManager fragmentManager = this.getParentFragmentManager();
    //    FragmentTransaction ft = fragmentManager.beginTransaction();
        //FragmentTransaction ft =  getFragmentManager().beginTransaction();
    //    ft.replace(R.id.layout_main, MainFragment.newInstance(DOWN));
    //    ft.commit();
    //}

    @SuppressLint("NonConstantResourceId")
    @SuppressWarnings("unused")
    @OnClick(R.id.buttonLeft)
    void onButtonLeft() {
        assert getArguments() != null;
        getArguments().putInt("direction", LEFT);
        FragmentManager fragmentManager = this.getParentFragmentManager();
        FragmentTransaction ft = fragmentManager.beginTransaction();
        //FragmentTransaction ft = getFragmentManager().beginTransaction();
        ft.replace(R.id.layout_main, MainFragment.newInstance(LEFT));
        ft.commit();
    }

    @SuppressLint("NonConstantResourceId")
    @SuppressWarnings("unused")
    @OnClick(R.id.buttonRight)
    void onButtonRight() {
        assert getArguments() != null;
        getArguments().putInt("direction", RIGHT);
        FragmentManager fragmentManager = this.getParentFragmentManager();
        FragmentTransaction ft = fragmentManager.beginTransaction();
        //FragmentTransaction ft = getFragmentManager().beginTransaction();
        ft.replace(R.id.layout_main, MainFragment.newInstance(RIGHT));
        ft.commit();
    }

    @SuppressLint("NonConstantResourceId")
    @SuppressWarnings("unused")
    @OnClick(R.id.textAnimationStyle)
    public void switchAnimationStyle(View view) {
        @AnimationStyle int[] styles;
        styles = new int[]{MOVE, CUBE, FLIP, PUSHPULL, SIDES, CUBEMOVE, MOVECUBE, PUSHMOVE, MOVEPULL, FLIPMOVE, MOVEFLIP, FLIPCUBE, CUBEFLIP};
        for (int i = 0; i<styles.length-1; ++i) {
            if (styles[i] == sAnimationStyle) {
                setAnimationStyle(styles[i+1]);
                return;
            }
        }
        setAnimationStyle(MOVE);
    }

    public void setAnimationStyle(@AnimationStyle int style) {
        if (sAnimationStyle != style) {
            sAnimationStyle = style;
            setAnimationStyleText();
            Snackbar.make(getView(), "Animation Style is Changed", Snackbar.LENGTH_SHORT).show();
        }
    }


    @SuppressLint("SetTextI18n")
    private void setAnimationStyleText() {
        switch (sAnimationStyle) {
            case NONE:
                mTextAnimationStyle.setText("None");
                break;
            case MOVE:
                mTextAnimationStyle.setText("Move");
                break;
            case CUBE:
                mTextAnimationStyle.setText("Cube");
                break;
            case FLIP:
                mTextAnimationStyle.setText("Flip");
                break;
            case PUSHPULL:
                mTextAnimationStyle.setText("Push/Pull");
                break;
            case SIDES:
                mTextAnimationStyle.setText("Sides");
                break;
            case CUBEMOVE:
                mTextAnimationStyle.setText("Cube/Move");
                break;
            case MOVECUBE:
                mTextAnimationStyle.setText("Move/Cube");
                break;
            case PUSHMOVE:
                mTextAnimationStyle.setText("Push/Move");
                break;
            case MOVEPULL:
                mTextAnimationStyle.setText("Move/Pull");
                break;
            case FLIPMOVE:
                mTextAnimationStyle.setText("Flip/Move");
                break;
            case MOVEFLIP:
                mTextAnimationStyle.setText("Move/Flip");
                break;
            case FLIPCUBE:
                mTextAnimationStyle.setText("Flip/Cube");
                break;
            case CUBEFLIP:
                mTextAnimationStyle.setText("Cube/Flip");
                break;
        }
    }
}



