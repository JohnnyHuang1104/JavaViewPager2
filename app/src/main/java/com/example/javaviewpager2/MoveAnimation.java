package com.example.javaviewpager2;

import android.view.animation.Transformation;

import androidx.annotation.IntDef;
import androidx.annotation.NonNull;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

public class MoveAnimation extends PropertyAnimation{

    @IntDef({Left,Right})
    @Retention(RetentionPolicy.SOURCE)
    @interface Direction {}

    public static final int Left  = 1;
    public static final int Right = 2;

    protected final @Direction int mDirection;
    protected final boolean mEnter;

    public static @NonNull MoveAnimation create(@Direction int direction, boolean enter, long duration) {
        switch (direction) {
            case Left:
            case Right:
                return new HorizontalMoveAnimation(direction, enter, duration);
        }
        return null;
    }

    private MoveAnimation(@Direction int direction, boolean enter, long duration) {
        mDirection = direction;
        mEnter = enter;
        setDuration(duration);
    }

    private static class HorizontalMoveAnimation extends MoveAnimation {

        private HorizontalMoveAnimation(@Direction int direction, boolean enter, long duration) {
            super(direction, enter, duration);
        }

        @Override
        protected void applyTransformation(float interpolatedTime, Transformation t) {
            float value = mEnter ? (interpolatedTime - 1.0f) : interpolatedTime;
            if (mDirection == Right) value *= -1.0f;
            mTranslationX = -value * mWidth;

            super.applyTransformation(interpolatedTime, t);
            applyTransformation(t);
        }
    }
}
