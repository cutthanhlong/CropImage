package com.textonphoto.addtext.editphoto.view;

import android.content.Context;
import android.util.AttributeSet;

import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatImageView;

public class GallerySquareImageView extends AppCompatImageView {
    public GallerySquareImageView(Context context) {
        super(context);
    }

    public GallerySquareImageView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public GallerySquareImageView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int i = getMeasuredWidth();
        setMeasuredDimension(i, i);
    }
}
