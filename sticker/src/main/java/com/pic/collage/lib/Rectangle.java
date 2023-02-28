package com.pic.collage.lib;

public class Rectangle {
    private int color = 0;
    private int height;
    private int radius;
    private int shape = 0;
    private int strokeColor = -16777216;
    private int strokeWidth = 0;
    private int width;

    public Rectangle(int color, int height, int radius, int shape, int strokeColor, int strokeWidth, int width) {
        this.color = color;
        this.height = height;
        this.radius = radius;
        this.shape = shape;
        this.strokeColor = strokeColor;
        this.strokeWidth = strokeWidth;
        this.width = width;
    }

    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getRadius() {
        return radius;
    }

    public void setRadius(int radius) {
        this.radius = radius;
    }

    public int getShape() {
        return shape;
    }

    public void setShape(int shape) {
        this.shape = shape;
    }

    public int getStrokeColor() {
        return strokeColor;
    }

    public void setStrokeColor(int strokeColor) {
        this.strokeColor = strokeColor;
    }

    public int getStrokeWidth() {
        return strokeWidth;
    }

    public void setStrokeWidth(int strokeWidth) {
        this.strokeWidth = strokeWidth;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }
}
