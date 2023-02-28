package com.textonphoto.addtext.editphoto.activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.FileProvider;

import com.textonphoto.addtext.editphoto.Bitmap.BitmapLoader;
import com.textonphoto.addtext.editphoto.R;

import java.io.File;


public class SaveShareImageActivity extends AppCompatActivity {

    private Bundle bundle;
    private String imagePath;
    private ImageView shareImageview;
    ImageView btnBack, btnShare;
    TextView btnHome;
    public static String PACKAGE_NAME;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        SystemUtil.setLocale(this);
        setContentView(R.layout.activity_save_share_image);
        PACKAGE_NAME = getApplicationContext().getPackageName();
        Log.e("TAG", "onCreate: "+PACKAGE_NAME );
        bundle = getIntent().getExtras();
        if (bundle != null) {
            imagePath = bundle.getString("imagePath");
        }

        initViews();
        new BitmapWorkerTask().execute();
        final ActionBar ab = getSupportActionBar();
        if (ab != null) {
            ab.setTitle(getString(R.string.tab_title_stores));
            ab.setDisplayHomeAsUpEnabled(true);
        }

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        btnShare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                shareFileImage(imagePath);
            }
        });

        btnHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(SaveShareImageActivity.this, MainActivity.class));
                finish();
            }
        });
    }

    private void initViews() {
        shareImageview = findViewById(R.id.share_imageView);
        btnBack = findViewById(R.id.btnBack);
        btnShare = findViewById(R.id.btnShare);
        btnHome = findViewById(R.id.btnHome);
    }

    private class BitmapWorkerTask extends AsyncTask<Void, Void, Bitmap> {
        DisplayMetrics metrics;
        BitmapLoader bitmapLoader;

        public BitmapWorkerTask() {
            metrics = getResources().getDisplayMetrics();
            bitmapLoader = new BitmapLoader();
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        // Decode image in background.
        @Override
        protected Bitmap doInBackground(Void... arg0) {
            try {
                return bitmapLoader.load(getApplicationContext(), new int[]{metrics.widthPixels, metrics.heightPixels}, imagePath);
            } catch (Exception e) {
                return null;
            }
        }

        @Override
        protected void onPostExecute(Bitmap bitmap) {
            if (bitmap != null) {
                shareImageview.setImageBitmap(bitmap);
            } else {
                Toast.makeText(SaveShareImageActivity.this, getString(R.string.error_img_not_found), Toast.LENGTH_SHORT).show();
            }
        }
    }

    String uri1 = "";
    private void shareFileImage(String path) {
        Log.d("TAG", "shareFileVideo: " + path);
        File file = null;
        if (path.contains("content://com."))
            intentFile(new File(Uri.parse(path).getPath()));
        else if (path.contains("content://")) {
            uri1 = "file://" + path;
            file = new File(Uri.parse(uri1).getPath());
            intentFile(file);
        } else {
            uri1 = "file://" + path;
            file = new File(Uri.parse(uri1).getPath());
            intentFile(file);
        }
    }

    private void intentFile(File file) {
        if (file.exists()) {
            Uri _uri = FileProvider.getUriForFile(this,
                    PACKAGE_NAME+".provider", file);
            Intent intent2 = new Intent(Intent.ACTION_SEND);
            intent2.putExtra("android.intent.extra.SUBJECT", getResources().getString(R.string.app_name));
            intent2.setType("video/*");
            intent2.putExtra("android.intent.extra.STREAM", _uri);
            intent2.putExtra("android.intent.extra.TEXT", "video");
            startActivity(Intent.createChooser(intent2, "Where to Share?"));
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        startActivity(new Intent(SaveShareImageActivity.this, MainActivity.class));
        finish();
    }
}
