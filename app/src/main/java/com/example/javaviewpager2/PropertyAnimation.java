package com.example.javaviewpager2;

import android.graphics.Camera;
import android.graphics.Matrix;
import android.view.animation.Animation;
import android.view.animation.Transformation;

public class PropertyAnimation extends Animation {
    private final Camera mCamera= new Camera();

    protected float mWidth = 0;
    protected float mPivotX = 0.0f;
    protected float mPivotY = 0.0f;
    protected float mRotationY = 0.0f;
    protected float mCameraZ = -8.0f;
    protected float mTranslationX = 0.0f;

    protected void applyTransformation(Transformation t) {
        final Matrix m = t.getMatrix();
        final float w = mWidth;
    }
}
