package com.pic.collage.lib;

import android.text.Layout;

public class TextTemplate {
    String text;
    Rectangle rectangleDrawable;
    int posX;
    int posY;
    float size;
    String font;
    int color;
    boolean check;
    float wStoke;
    String linearGradientShader;
    Layout.Alignment alignment1;
    int backgroundColor, strokeColor, backgroundStrokeColor, textShadowColor;
    float letterSpacingExtra, lineSpacingMultiplier;

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Rectangle getRectangleDrawable() {
        return rectangleDrawable;
    }

    public void setRectangleDrawable(Rectangle rectangleDrawable) {
        this.rectangleDrawable = rectangleDrawable;
    }

    public int getPosX() {
        return posX;
    }

    public void setPosX(int posX) {
        this.posX = posX;
    }

    public int getPosY() {
        return posY;
    }

    public void setPosY(int posY) {
        this.posY = posY;
    }

    public float getSize() {
        return size;
    }

    public void setSize(float size) {
        this.size = size;
    }

    public String getFont() {
        return font;
    }

    public void setFont(String font) {
        this.font = font;
    }

    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
    }

    public boolean isCheck() {
        return check;
    }

    public void setCheck(boolean check) {
        this.check = check;
    }

    public String getLinearGradientShader() {
        return linearGradientShader;
    }

    public void setLinearGradientShader(String linearGradientShader) {
        this.linearGradientShader = linearGradientShader;
    }

    public Layout.Alignment getAlignment1() {
        return alignment1;
    }

    public void setAlignment1(Layout.Alignment alignment1) {
        this.alignment1 = alignment1;
    }

    public int getBackgroundColor() {
        return backgroundColor;
    }

    public void setBackgroundColor(int backgroundColor) {
        this.backgroundColor = backgroundColor;
    }

    public int getStrokeColor() {
        return strokeColor;
    }

    public void setStrokeColor(int strokeColor) {
        this.strokeColor = strokeColor;
    }

    public int getBackgroundStrokeColor() {
        return backgroundStrokeColor;
    }

    public void setBackgroundStrokeColor(int backgroundStrokeColor) {
        this.backgroundStrokeColor = backgroundStrokeColor;
    }

    public int getTextShadowColor() {
        return textShadowColor;
    }

    public void setTextShadowColor(int textShadowColor) {
        this.textShadowColor = textShadowColor;
    }

    public float getLetterSpacingExtra() {
        return letterSpacingExtra;
    }

    public void setLetterSpacingExtra(float letterSpacingExtra) {
        this.letterSpacingExtra = letterSpacingExtra;
    }

    public float getLineSpacingMultiplier() {
        return lineSpacingMultiplier;
    }

    public void setLineSpacingMultiplier(float lineSpacingMultiplier) {
        this.lineSpacingMultiplier = lineSpacingMultiplier;
    }

    public float getwStoke() {
        return wStoke;
    }

    public void setwStoke(float wStoke) {
        this.wStoke = wStoke;
    }
}
