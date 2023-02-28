package com.pic.collage.lib;

import android.graphics.Matrix;
import android.text.TextPaint;


import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

public class Convert {
    public static String fromMatrix(Matrix value) {
        return new Gson().toJson(value);
    }

    public static Matrix toMatrix(String value) {
        Type type = new TypeToken<Matrix>() {
        }.getType();
        return new Gson().fromJson(value, type);
    }

    public static String fromTextPaint(TextPaint value) {
        return new Gson().toJson(value);
    }

    public static TextPaint toTextPaint(String value) {
        Type type = new TypeToken<TextPaint>() {
        }.getType();
        return new Gson().fromJson(value, type);
    }

    public static String fromRectangle(Rectangle value) {
        return new Gson().toJson(value);
    }

    public static Rectangle toRectangle(String value) {
        Type type = new TypeToken<Rectangle>() {
        }.getType();
        return new Gson().fromJson(value, type);
    }

    public static String fromListItem(List<ItemTopic> value) {
        return new Gson().toJson(value);
    }

    public static List<ItemTopic> toListItem(String value) {
        Type type = new TypeToken<List<ItemTopic>>() {
        }.getType();
        return new Gson().fromJson(value, type);
    }
}
