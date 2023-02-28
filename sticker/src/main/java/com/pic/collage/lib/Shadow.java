package com.pic.collage.lib;

public class Shadow {
    private int shadowColor = -16777216;
    private float shadowDx = 0.0F;
    private float shadowDy = 0.0F;
    private float shadowRadius = 0.0F;

    public Shadow() {
    }

    public void setShadowLayer(float f, float f2, float f3, int i) {
        this.shadowRadius = f;
        this.shadowDx = f2;
        this.shadowDy = f3;
        this.shadowColor = i;
    }

    public void setShadowRadius(float f) {
        this.shadowRadius = f;
    }

    public void setShadowDx(float f) {
        this.shadowDx = f;
    }

    public void setShadowDy(float f) {
        this.shadowDy = f;
    }

    public void setShadowColor(int i) {
        this.shadowColor = i;
    }

    public float getShadowRadius() {
        return this.shadowRadius;
    }

    public float getShadowDx() {
        return this.shadowDx;
    }

    public float getShadowDy() {
        return this.shadowDy;
    }

    public int getShadowColor() {
        return this.shadowColor;
    }
}
