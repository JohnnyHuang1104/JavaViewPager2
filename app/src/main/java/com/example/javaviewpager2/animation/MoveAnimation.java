package com.example.javaviewpager2.animation;

import android.view.animation.Transformation;

import androidx.annotation.IntDef;
import androidx.annotation.NonNull;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * Move Animation
 * @author kakajika
 * @since 2015/11/28
 */
public class MoveAnimation extends ViewPropertyAnimation {

    @IntDef({LEFT, RIGHT})
    @Retention(RetentionPolicy.SOURCE)
    @interface Direction {}
    public static final int LEFT  = 1;
    public static final int RIGHT = 2;

    protected final @Direction int mDirection;
    protected final boolean mEnter;

    /**
     * Create new Animation.
     * @param direction Direction of animation
     * @param enter true for Enter / false for Exit
     * @param duration Duration of Animation
     * @return
     */
    public static @NonNull
    MoveAnimation create(@Direction int direction, boolean enter, long duration) {
        switch (direction) {
            case LEFT:
            case RIGHT:
            default:
                return new HorizontalMoveAnimation(direction, enter, duration);
        }
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
            if (mDirection == RIGHT) value *= -1.0f;
            mTranslationX = -value * mWidth;

            super.applyTransformation(interpolatedTime, t);
            applyTransformation(t);
        }

    }

}


