package com.pic.collage.lib;

import android.text.Layout;
import android.text.TextPaint;
import android.util.Log;


public class TextStickerCard {
    String text;
    TextPaint textPaintOutline;
    TextPaint textPaint;
    Rectangle rectangleDrawable;
    String matrix;
    float size;
    String font;
    int color;
    boolean check;
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

    public String getMatrix() {
        return matrix;
    }

    public void setMatrix(String matrix) {
        this.matrix = matrix;
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

    public TextPaint getTextPaintOutline() {
        return textPaintOutline;
    }

    public void setTextPaintOutline(TextPaint textPaintOutline) {
        this.textPaintOutline = textPaintOutline;
    }

    public TextPaint getTextPaint() {
        return textPaint;
    }

    public void setTextPaint(TextPaint textPaint) {
        this.textPaint = textPaint;
    }

    public Rectangle getRectangleDrawable() {
        return rectangleDrawable;
    }

    public void setRectangleDrawable(Rectangle rectangleDrawable) {
        this.rectangleDrawable = rectangleDrawable;
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

    @Override
    public String toString() {
        Log.d("TAG", "toStringdsfa: \n" + Convert.fromTextPaint(textPaintOutline) );
        return "TextStickerCard{" +
                ",\ntext='" + text + '\'' +
                ",\n textPaintOutline=" + Convert.fromTextPaint(textPaintOutline) +
                ",\n textPaint=" + Convert.fromTextPaint(textPaint) +
                ",\n rectangleDrawable=" + Convert.fromRectangle(rectangleDrawable) +
                ",\n matrix='" + matrix + '\'' +
                ",\n size=" + size +
                ",\n font='" + font + '\'' +
                ",\n color=" + color +
                ",\n check=" + check +
                ",\n linearGradientShader='" + linearGradientShader + '\'' +
                ",\n alignment1=" + alignment1 +
                ",\n backgroundColor=" + backgroundColor +
                ",\n strokeColor=" + strokeColor +
                ",\n backgroundStrokeColor=" + backgroundStrokeColor +
                ",\n textShadowColor=" + textShadowColor +
                ",\n letterSpacingExtra=" + letterSpacingExtra +
                ",\n lineSpacingMultiplier=" + lineSpacingMultiplier +
                '}';
    }

}
