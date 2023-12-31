package com.example.javaviewpager2.animation;

import android.view.animation.Transformation;

import androidx.annotation.IntDef;
import androidx.annotation.NonNull;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * 3D Push/Pull Animation
 * @author kakajika
 * @since 2015/11/28
 */
public class PushPullAnimation extends ViewPropertyAnimation {

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
    PushPullAnimation create(@Direction int direction, boolean enter, long duration) {
        switch (direction) {
            case LEFT:
            case RIGHT:
            default:
                return new HorizontalPushPullAnimation(direction, enter, duration);
        }
    }

    private PushPullAnimation(@Direction int direction, boolean enter, long duration) {
        mDirection = direction;
        mEnter = enter;
        setDuration(duration);
    }

    private static class HorizontalPushPullAnimation extends PushPullAnimation {

        private HorizontalPushPullAnimation(@Direction int direction, boolean enter, long duration) {
            super(direction, enter, duration);
        }

        @Override
        public void initialize(int width, int height, int parentWidth, int parentHeight) {
            super.initialize(width, height, parentWidth, parentHeight);
            mPivotX = (mEnter == (mDirection == RIGHT)) ? 0.0f : width;
            mPivotY = height * 0.5f;
        }

        @Override
        protected void applyTransformation(float interpolatedTime, Transformation t) {
            float value = mEnter ? (interpolatedTime - 1.0f) : interpolatedTime;
            if (mDirection == LEFT) value *= -1.0f;
            mRotationY = -value * 90.0f;
            mAlpha = mEnter ? interpolatedTime : (1.0f - interpolatedTime);

            super.applyTransformation(interpolatedTime, t);
            applyTransformation(t);
        }

    }

}
