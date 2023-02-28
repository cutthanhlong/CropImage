package com.textonphoto.addtext.editphoto.activity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.core.content.FileProvider;
import androidx.recyclerview.widget.RecyclerView;

import com.textonphoto.addtext.editphoto.R;
import com.textonphoto.addtext.editphoto.Ultil.Sharef;
import com.textonphoto.addtext.editphoto.adapter.AdapterGallery;
import com.textonphoto.addtext.editphoto.interfacee.onClickItemGallery;
import com.textonphoto.addtext.editphoto.model.PhotoGalleryModel;
import com.yalantis.ucrop.UCrop;
import com.yalantis.ucrop.util.FileUtils;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.UUID;

import ja.burhanrashid52.photoeditor.PhotoEditor;
import ja.burhanrashid52.photoeditor.PhotoEditorView;

public class GalleryActivity extends AppCompatActivity {
    RecyclerView re_all_folder_image;
    List<PhotoGalleryModel> photoGalleryModelList;
    AdapterGallery adapterGallery;
    ProgressBar progressBar;
    private static final int PERMISSIONS_REQUEST = 922;
    private static final int CAMERA_CAPTURE_IMAGE_REQUEST_CODE = 0001;
    String IMAGE_DIRECTORY_NAME = "PhotoEditor";
    int MEDIA_TYPE_IMAGE = 1;
    final String dir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES) + "/";
    Uri outputFileUri;
    PhotoEditorView photo_viewer;
    private PhotoEditor photoEditor;
    File file;
    Intent intentData;
    List<String> stringList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gallery);
        File newdir = new File(dir);
        newdir.mkdirs();
        initViews();

        getListImage getListImage = new getListImage();
        getListImage.execute();

    }

    private void initViews() {
        re_all_folder_image = findViewById(R.id.re_all_folder_image);
        progressBar = findViewById(R.id.pro_load);
        photo_viewer = findViewById(R.id.photo_viewer);
        photoEditor = new PhotoEditor.Builder(this, photo_viewer).setPinchTextScalable(true).build();


    }

    private class getListImage extends AsyncTask<Void, Void, Void> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressBar.setVisibility(View.VISIBLE);
        }

        @Override
        protected Void doInBackground(Void... voids) {
            photoGalleryModelList = getPhotoGalleryModelList(GalleryActivity.this);
            return null;
        }

        @Override
        protected void onPostExecute(Void unused) {
            super.onPostExecute(unused);
            progressBar.setVisibility(View.GONE);
            adapterGallery = new AdapterGallery(photoGalleryModelList, GalleryActivity.this, new onClickItemGallery() {
                @Override
                public void onClickItem(int position, PhotoGalleryModel photoGalleryModel) {
                    Log.e("TAG", "onClickItem: " + position);
                    if (position == 0) {
                        if (ContextCompat.checkSelfPermission(GalleryActivity.this, "android.permission.READ_EXTERNAL_STORAGE") != 0
                                || ContextCompat.checkSelfPermission(GalleryActivity.this, "android.permission.WRITE_EXTERNAL_STORAGE") != 0
                                || ContextCompat.checkSelfPermission(GalleryActivity.this, "android.permission.CAMERA") != 0) {
                            if (Build.VERSION.SDK_INT >= 23) {
                                GalleryActivity.this.requestPermissions(new String[]{"android.permission.CAMERA", "android.permission.READ_EXTERNAL_STORAGE", "android.permission.WRITE_EXTERNAL_STORAGE"}, GalleryActivity.PERMISSIONS_REQUEST);
                            }
                        } else {
                            capturarFoto();
                        }

                    } else {
                        file = new File(photoGalleryModel.getPath());
                        Log.e("TAG", "onClickItem: " + file.getPath());
                        BitmapFactory.Options bmOptions = new BitmapFactory.Options();
                        Bitmap bitmap = BitmapFactory.decodeFile(file.getAbsolutePath(), bmOptions);
                        Sharef.bitmapReplace = bitmap;
                        String randomName = new StringBuilder(UUID.randomUUID().toString()).append(".jpg").toString();
                        UCrop.Options options = new UCrop.Options();
                        options.setToolbarTitle(getString(R.string.Crop_Image));
                        options.setCircleDimmedLayer(false);
                        options.setFreeStyleCropEnabled(true);
                        options.setToolbarCancelDrawable(R.drawable.ic_back);
                        UCrop.of(Sharef.getImageUri(GalleryActivity.this, Sharef.bitmapReplace), Uri.fromFile(new File(getCacheDir(), randomName))).withOptions(options).start(GalleryActivity.this, UCrop.REQUEST_CROP);

                    }
                }
            });
            re_all_folder_image.setAdapter(adapterGallery);
        }
    }

    private void capturarFoto() {
        String file = dir + DateFormat.format("yyyy-MM-dd_hhmmss", new Date()).toString() + ".jpg";
        File newfile = new File(file);
        try {
            newfile.createNewFile();
        } catch (IOException e) {
        }
        String pk = getApplicationContext().getPackageName();
        outputFileUri = FileProvider.getUriForFile(
                GalleryActivity.this,
                pk + ".provider", //(use your app signature + ".provider" )
                newfile);
        Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        cameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, outputFileUri);

        startActivityForResult(cameraIntent, CAMERA_CAPTURE_IMAGE_REQUEST_CODE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK) {
            if (requestCode == CAMERA_CAPTURE_IMAGE_REQUEST_CODE) {
                Log.e("TAG", "outputFileUri: " + outputFileUri.getPath());

            }
            if (requestCode == UCrop.REQUEST_CROP && resultCode == RESULT_OK) {
                intentData = data;
                CropImage cropImage = new CropImage();
                cropImage.execute();

            }
        }

    }
    public class CropImage extends AsyncTask<Void,Void,Void>{
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressBar.setVisibility(View.VISIBLE);
        }

        @Override
        protected Void doInBackground(Void... voids) {
            handleCropResult(intentData);
            File filePath = bitmapToFile(GalleryActivity.this,Sharef.bitmapReplace,file.getName());
            Log.e("TAG", "handleCropResult: "+filePath.getPath());
            String path = filePath.getPath();
             stringList = new ArrayList<>();
            stringList.add(path);
            Log.e("TAG", "handleCropResult: "+stringList.size());
            return null;
        }

        @Override
        protected void onPostExecute(Void unused) {
            super.onPostExecute(unused);
            progressBar.setVisibility(View.GONE);
            Intent intent1 = new Intent(GalleryActivity.this,CreateCollageActivity.class);
            intent1.putExtra("path_crop", (Serializable) stringList);
            intent1.putExtra("check_intent",true);
            startActivity(intent1);
        }
    }

    private void handleCropResult(Intent intent)  {
        FileInputStream fileInputStream;
        Uri output = UCrop.getOutput(intent);
        if (output != null) {
            try {
                fileInputStream = new FileInputStream(new File(FileUtils.getPath(this, output)));
            } catch (FileNotFoundException e) {
                e.printStackTrace();
                fileInputStream = null;
            }
            Sharef.bitmapReplace = BitmapFactory.decodeStream(fileInputStream);
            photo_viewer.getSource().setImageBitmap(Sharef.bitmapReplace);



            return;
        }



    }
    public static File bitmapToFile(Context context,Bitmap bitmap, String fileNameToSave) { // File name like "image.png"
        //create a file to write bitmap data
        File file = null;
        try {
            file = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS) +"/" +"Crop_"+System.currentTimeMillis()+"_"+fileNameToSave);
            file.createNewFile();

//Convert bitmap to byte array
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            bitmap.compress(Bitmap.CompressFormat.PNG, 0 , bos); // YOU can also save it in JPEG
            byte[] bitmapdata = bos.toByteArray();

//write the bytes in file
            FileOutputStream fos = new FileOutputStream(file);
            fos.write(bitmapdata);
            fos.flush();
            fos.close();
            Log.e("TAG", "bitmapToFile: "+file.getPath());

            return file;

        }catch (Exception e){
            e.printStackTrace();
            Log.e("TAG", "bitmapToFile: "+file.getPath());

            return file; // it will return null
        }
    }



    /**
     * Creating file uri to store image/video
     */
    public Uri getOutputMediaFileUri(int type) {
        return Uri.fromFile(getOutputMediaFile(type));
    }

    /**
     * returning image / video
     */
    public File getOutputMediaFile(int type) {
        // External sdcard location
        File mediaStorageDir = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES), IMAGE_DIRECTORY_NAME);

        // Create the storage directory if it does not exist
        if (!mediaStorageDir.exists()) {
            if (!mediaStorageDir.mkdirs()) {
                Log.d(IMAGE_DIRECTORY_NAME, "Oops! Failed create " + IMAGE_DIRECTORY_NAME + " directory");
                return null;
            }
        }
        // Create a media file name
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss", Locale.getDefault()).format(new Date());
        File mediaFile;
        if (type == MEDIA_TYPE_IMAGE) {
            mediaFile = new File(mediaStorageDir.getPath() + File.separator + "IMG_" + timeStamp + ".jpg");
        } else {
            return null;
        }

        return mediaFile;
    }

    public List<PhotoGalleryModel> getPhotoGalleryModelList(Context context) {
        List<PhotoGalleryModel> listAllImage = new ArrayList<>();
        listAllImage.add(new PhotoGalleryModel());
        Cursor cursor;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.Q) {
            // Codigo version de api 29 en adelante

            cursor = context.getContentResolver().query(MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
                    new String[]{MediaStore.Images.ImageColumns.DATA, MediaStore.Images.Media.DISPLAY_NAME, MediaStore.Images.Media.SIZE,},
                    null,
                    null,
                    MediaStore.Images.Media.DATE_TAKEN + " DESC"
            );

            if (null == cursor) {
                return listAllImage;
            }

            if (cursor.moveToFirst()) {
                do {
                    PhotoGalleryModel photo = new PhotoGalleryModel();
                    photo.setPath(cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA)));
                    File file = new File(photo.getPath());
                    if (file.length() > 0) {
                        listAllImage.add(photo);

                    }

                } while (cursor.moveToNext());
            }
        } else {

            cursor = context.getContentResolver().query(
                    MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
                    null,
                    null,
                    null,
                    MediaStore.Images.Media.DATA + " DESC"
            );

            if (null == cursor) {
                return listAllImage;
            }

            if (cursor.moveToFirst()) {
                do {
                    @SuppressLint("Range") String fullPath = cursor.getString(cursor.getColumnIndex(MediaStore.Images.Media.DATA));
                    PhotoGalleryModel photo = new PhotoGalleryModel();
                    photo.setPath(fullPath);
                    File file = new File(photo.getPath());
                    if (file.length() > 0) {
                        listAllImage.add(photo);

                    }

                } while (cursor.moveToNext());
            }

        }
        cursor.close();
        return listAllImage;
    }
}