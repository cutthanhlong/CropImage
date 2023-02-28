package com.pic.collage.lib;

import android.content.res.Resources;
import android.graphics.drawable.GradientDrawable;


public class RectangleDrawable extends GradientDrawable {
    private int color = 0;
    private int height;
    private int radius;
    private int shape = 0;
    private int strokeColor = -16777216;
    private int strokeWidth = 0;
    private int width;

    public RectangleDrawable(int i, int i2) {
        this.width = i;
        this.height = i2;
        this.setSize(i, i2);
        this.setShape(this.shape);
        this.setColor(this.color);
    }

    public RectangleDrawable(RectangleDrawable rectangleDrawable) {
        int w = Resources.getSystem().getDisplayMetrics().widthPixels;
        int h = Resources.getSystem().getDisplayMetrics().heightPixels;
        int i = rectangleDrawable.width * w / 1440;
        this.width = i;
        int i2 = rectangleDrawable.height * h / 2862;
        this.height = i2;
        this.setSize(i, i2);
        this.setShape(this.shape);
        this.setBackgroundColor(rectangleDrawable.getBackgroundColor());
        this.setRadius(rectangleDrawable.getRadius());
        this.setStrokeWidth(rectangleDrawable.getStrokeWidth());
        this.setStrokeColor(rectangleDrawable.getStrokeColor());
    }

    public RectangleDrawable(Rectangle rectangleDrawable) {
        int w = Resources.getSystem().getDisplayMetrics().widthPixels;
        int h = Resources.getSystem().getDisplayMetrics().heightPixels;
        int i = rectangleDrawable.getWidth() * w / 1440;
        this.width = i;
        int i2 = rectangleDrawable.getHeight() * h / 2862;
        this.height = i2;
        this.setSize(i, i2);
        this.setShape(this.shape);
        this.setBackgroundColor(rectangleDrawable.getColor());
        this.setRadius(rectangleDrawable.getRadius());
        this.setStrokeWidth(rectangleDrawable.getStrokeWidth());
        this.setStrokeColor(rectangleDrawable.getStrokeColor());
    }

    public Rectangle rectangle() {
        Rectangle rectangle = new Rectangle(color, height, radius, shape, strokeColor, strokeWidth, width);
        return rectangle;
    }

    public void setBackgroundColor(int i) {
        this.color = i;
        this.setColor(i);
    }

    public int getBackgroundColor() {
        return this.color;
    }

    public void setWidth(int i) {
        this.width = i;
        this.setSize(i, this.height);
    }

    public int getWidth() {
        return this.width;
    }

    public void setHeight(int i) {
        this.height = i;
        this.setSize(this.width, i);
    }

    public int getHeight() {
        return this.height;
    }

    public void setRadius(int i) {
        this.radius = i;
        this.setCornerRadius((float) i);
    }

    public int getRadius() {
        return this.radius;
    }

    public void setStrokeColor(int i) {
        this.strokeColor = i;
        this.setStroke(this.strokeWidth, i);
    }

    public int getStrokeColor() {
        return this.strokeColor;
    }

    public void setStrokeWidth(int i) {
        this.strokeWidth = i;
        this.setStroke(i, this.strokeColor);
    }

    public int getStrokeWidth() {
        return this.strokeWidth;
    }
}

