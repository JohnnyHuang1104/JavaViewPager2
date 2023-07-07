package com.example.javaviewpager2;

import android.view.animation.Transformation;
import androidx.annotation.NonNull;

public class CubeAnimation extends PropertyAnimation {

    //@IntDef({LEFT, RIGHT})
    //@Retention(RetentionPolicy.SOURCE)
    @interface Direction {}

    public static final int Left = 1;
    public static final int Right = 2;

    protected final @Direction int mDirection;
    protected final boolean mEnter;

    /**
     * Create new Animation.
     *
     * @param direction Direction of animation
     * @param enter     true for Enter / false for Exit
     * @param duration  Duration of Animation
     * @return
     */
    public static @NonNull
    CubeAnimation create(@Direction int direction, boolean enter, long duration) {
        switch (direction) {
            case Left:
            case Right:
                return new HorizontalCubeAnimation(direction, enter, duration);
        }
        return null;
    }

    private CubeAnimation(@Direction int direction, boolean enter, long duration) {
        mDirection = direction;
        mEnter = enter;
        setDuration(duration);
    }

    private static class HorizontalCubeAnimation extends CubeAnimation {

        private HorizontalCubeAnimation(@Direction int direction, boolean enter, long duration) {
            super(direction, enter, duration);
        }

        @Override
        public void initialize(int width, int height, int parentWidth, int parentHeight) {
            super.initialize(width, height, parentWidth, parentHeight);
            mPivotX = (mEnter == (mDirection == Left)) ? 0.0f : width;
            mPivotY = height * 0.5f;
            mCameraZ = -width * 0.015f;
        }

        @Override
        protected void applyTransformation(float interpolatedTime, Transformation t) {
            float value = mEnter ? (interpolatedTime - 1.0f) : interpolatedTime;
            if (mDirection == Right) value *= -1.0f;
            mRotationY = -value * 90.0f;
            mTranslationX = -value * mWidth;

            super.applyTransformation(interpolatedTime, t);
            applyTransformation(t);
        }
    }
}


