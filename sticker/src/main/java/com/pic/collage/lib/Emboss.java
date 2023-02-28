package com.pic.collage.lib;

public class Emboss {
    private float ambient;
    private float blurRadius;
    private float[] directions;
    private float specular;
    private float x;
    private float y;
    private float z;

    public Emboss(float[] fArr, float f, float f2, float f3) {
        this.directions = fArr;
        this.x = fArr[0];
        this.y = fArr[1];
        this.z = fArr[2];
        this.ambient = f;
        this.specular = f2;
        this.blurRadius = f3;
    }

    public float[] getDirections() {
        return this.directions;
    }

    public void setDirections(float[] fArr) {
        this.directions = fArr;
    }

    public float getX() {
        return this.x;
    }

    public void setX(float f) {
        this.x = f;
        this.directions[0] = f;
    }

    public float getY() {
        return this.y;
    }

    public void setY(float f) {
        this.y = f;
        this.directions[1] = f;
    }

    public float getZ() {
        return this.z;
    }

    public void setZ(float f) {
        this.z = f;
        this.directions[2] = f;
    }

    public float getAmbient() {
        return this.ambient;
    }

    public void setAmbient(float f) {
        this.ambient = f;
    }

    public float getSpecular() {
        return this.specular;
    }

    public void setSpecular(float f) {
        this.specular = f;
    }

    public float getBlurRadius() {
        return this.blurRadius;
    }

    public void setBlurRadius(float f) {
        this.blurRadius = f;
    }
}

