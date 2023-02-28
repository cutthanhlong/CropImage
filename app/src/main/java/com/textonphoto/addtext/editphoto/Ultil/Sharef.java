package com.textonphoto.addtext.editphoto.Ultil;

import android.content.Context;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;

public class Sharef {
    public static ArrayList<String> lst_album_image = new ArrayList<>();
    public static Bitmap bitmapReplace;
    public static Bitmap original;

    public static Uri SAVED_BITMAP = null;
    public static String imageUrl;
    public class KEYNAME {
        public static final String ALBUM_NAME = "album_name";
        public static final String SELECTED_PHONE_IMAGE = "selected_phone_image";

        public KEYNAME() {
        }
    }
    public static Uri getImageUri(Context context, Bitmap bm) {
        String title = "Img_" + System.currentTimeMillis();
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        bm.compress(Bitmap.CompressFormat.JPEG, 60, bytes);
        String path = MediaStore.Images.Media.insertImage(context.getContentResolver(), bm, title, null);
        return Uri.parse(path);
    }
}
