package com.textonphoto.addtext.editphoto.Ultil;

import android.content.Context;
import android.view.View;
import android.view.animation.AnimationUtils;

import com.textonphoto.addtext.editphoto.R;


public class Constant {
    public static void leftAnimation(Context context, View view) {
        view.startAnimation(AnimationUtils.loadAnimation(context, R.anim.slide_in_left));
        view.setVisibility(View.VISIBLE);
    }

    public static void rightAnimation(Context context, View view) {
        view.startAnimation(AnimationUtils.loadAnimation(context, R.anim.slide_in_right));
        view.setVisibility(View.GONE);
    }
}
