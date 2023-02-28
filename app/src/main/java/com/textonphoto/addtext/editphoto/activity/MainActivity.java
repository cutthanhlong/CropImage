package com.textonphoto.addtext.editphoto.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.textonphoto.addtext.editphoto.R;

public class MainActivity extends AppCompatActivity {
    Button collage,gallery,btn_creation;
    private static final int PERMISSIONS_REQUEST = 922;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        collage = findViewById(R.id.collage);
        gallery = findViewById(R.id.Gallery);
        btn_creation = findViewById(R.id.btn_creation);
        btn_creation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (ContextCompat.checkSelfPermission(MainActivity.this.getApplicationContext(), "android.permission.READ_EXTERNAL_STORAGE") != 0
                        || ContextCompat.checkSelfPermission(MainActivity.this.getApplicationContext(), "android.permission.WRITE_EXTERNAL_STORAGE") != 0
                        || ContextCompat.checkSelfPermission(MainActivity.this.getApplicationContext(), "android.permission.CAMERA") != 0) {
                    if (Build.VERSION.SDK_INT >= 23) {
                        MainActivity.this.requestPermissions(new String[]{"android.permission.CAMERA", "android.permission.READ_EXTERNAL_STORAGE", "android.permission.WRITE_EXTERNAL_STORAGE"}, MainActivity.PERMISSIONS_REQUEST);
                    }
                } else {
                    startActivity(new Intent(MainActivity.this, MyCreationActivity.class));
                }
            }
        });
        collage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (ContextCompat.checkSelfPermission(MainActivity.this.getApplicationContext(), "android.permission.READ_EXTERNAL_STORAGE") != 0
                        || ContextCompat.checkSelfPermission(MainActivity.this.getApplicationContext(), "android.permission.WRITE_EXTERNAL_STORAGE") != 0
                        || ContextCompat.checkSelfPermission(MainActivity.this.getApplicationContext(), "android.permission.CAMERA") != 0) {
                    if (Build.VERSION.SDK_INT >= 23) {
                        MainActivity.this.requestPermissions(new String[]{"android.permission.CAMERA", "android.permission.READ_EXTERNAL_STORAGE", "android.permission.WRITE_EXTERNAL_STORAGE"}, MainActivity.PERMISSIONS_REQUEST);
                    }
                } else {

                    Intent intent = new Intent(MainActivity.this,SelectAlbumActivity.class);
                    startActivity(intent);
                }
            }
        });
        gallery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (ContextCompat.checkSelfPermission(MainActivity.this.getApplicationContext(), "android.permission.READ_EXTERNAL_STORAGE") != 0
                        || ContextCompat.checkSelfPermission(MainActivity.this.getApplicationContext(), "android.permission.WRITE_EXTERNAL_STORAGE") != 0
                        || ContextCompat.checkSelfPermission(MainActivity.this.getApplicationContext(), "android.permission.CAMERA") != 0) {
                    if (Build.VERSION.SDK_INT >= 23) {
                        MainActivity.this.requestPermissions(new String[]{"android.permission.CAMERA", "android.permission.READ_EXTERNAL_STORAGE", "android.permission.WRITE_EXTERNAL_STORAGE"}, MainActivity.PERMISSIONS_REQUEST);
                    }
                } else {

                    Intent intent = new Intent(MainActivity.this,GalleryActivity.class);
                    startActivity(intent);
                }
            }
        });
    }

}