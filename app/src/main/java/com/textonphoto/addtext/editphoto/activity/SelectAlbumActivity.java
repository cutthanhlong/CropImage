package com.textonphoto.addtext.editphoto.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.textonphoto.addtext.editphoto.R;
import com.textonphoto.addtext.editphoto.adapter.AdapterAlbum;
import com.textonphoto.addtext.editphoto.adapter.AdapterImageSelect;
import com.textonphoto.addtext.editphoto.model.AlbumImageModel;
import com.textonphoto.addtext.editphoto.model.PhotoModel;

import java.io.File;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;

public class SelectAlbumActivity extends AppCompatActivity {
    RecyclerView rv_list_album;
    AdapterAlbum adapterAlbum;
    TextView btn_next;
    public static AdapterImageSelect adapterImageSelect;
    Vector<String> albumsNames = new Vector<>();
    Vector<AlbumImageModel> phoneAlbums = new Vector<>();
    ProgressBar progressBar;
    public static List<String> listImageSelect;
    public static RecyclerView rv_list_image_select;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_album);
        initView();
        LoadImageTask loadImageTask = new LoadImageTask();
        loadImageTask.execute(new Void[0]);
         rv_list_album.setAdapter(adapterAlbum);
    }
    private void initView(){
        rv_list_album = findViewById(R.id.rv_list_album);
        progressBar = findViewById(R.id.progressBar);
        rv_list_image_select = findViewById(R.id.rv_list_image_select);
        btn_next = findViewById(R.id.btn_next);
        listImageSelect = new ArrayList<>();
        adapterAlbum = new AdapterAlbum(SelectAlbumActivity.this,phoneAlbums);
        adapterImageSelect = new AdapterImageSelect(SelectAlbumActivity.this,listImageSelect);
        rv_list_image_select.setAdapter(adapterImageSelect);

        btn_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SelectAlbumActivity.this, CreateCollageActivity.class);
                intent.putExtra("list_arr", (Serializable) listImageSelect);
                startActivity(intent);
            }
        });
    }

    public boolean initViewAction() {
        String str = "_id";
        String str2 = "_data";
        String str3 = "bucket_display_name";
        String str4 = "DeviceImageManager";
        try {
            String[] strArr = {str3, str2, str};
            Cursor query = getContentResolver().query(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, strArr, null, null, null);
            if (query == null || query.getCount() <= 0) {
                return false;
            }
            StringBuilder sb = new StringBuilder();
            sb.append(" query count=");
            sb.append(query.getCount());
            Log.i(str4, sb.toString());
//            lin_no_photo.setVisibility(View.GONE);
//            rcv_album.setVisibility(View.VISIBLE);
            if (query.moveToFirst()) {
                int columnIndex = query.getColumnIndex(str3);
                int columnIndex2 = query.getColumnIndex(str2);
                int columnIndex3 = query.getColumnIndex(str);
                while (true) {
                    String string = query.getString(columnIndex);
                    String string2 = query.getString(columnIndex2);
                    String string3 = query.getString(columnIndex3);
                    PhotoModel phonePhoto = new PhotoModel();
                    phonePhoto.setAlbumName(string);
                    phonePhoto.setPhotoUri(string2);
                    phonePhoto.setId(Integer.valueOf(string3).intValue());
                    String str5 = "A photo was added to album => ";
                    String str6 = ".gif";
                    if (albumsNames.contains(string)) {
                        Iterator it = phoneAlbums.iterator();
                        while (true) {
                            if (!it.hasNext()) {
                                break;
                            }
                            AlbumImageModel phoneAlbum = (AlbumImageModel) it.next();
                            if (phoneAlbum != null && phoneAlbum.getName() != null && phoneAlbum.getName().equalsIgnoreCase(string)) {
                                if (new File(string2).length() != 0) {
                                    if (!phonePhoto.getPhotoUri().endsWith(str6)) {
                                        phoneAlbum.getAlbumPhotos().add(phonePhoto);
                                    }
                                    StringBuilder sb2 = new StringBuilder();
                                    sb2.append(str5);
                                    sb2.append(string);
                                    Log.i(str4, sb2.toString());
                                } else {
                                    StringBuilder sb3 = new StringBuilder();
                                    sb3.append("data --> ");
                                    sb3.append(string2);
                                    sb3.append(" size --> ");
                                    sb3.append(new File(string2).length());
                                    Log.e("initViewAction: ", sb3.toString());
                                }
                            }
                        }
                    } else if (new File(string2).length() != 0) {
                        AlbumImageModel phoneAlbum2 = new AlbumImageModel();
                        StringBuilder sb4 = new StringBuilder();
                        sb4.append("A new album was created => ");
                        sb4.append(string);
                        Log.i(str4, sb4.toString());
                        phoneAlbum2.setId(phonePhoto.getId());
                        phoneAlbum2.setName(string);
                        phoneAlbum2.setCoverUri(phonePhoto.getPhotoUri());
                        if (!phonePhoto.getPhotoUri().endsWith(str6)) {
                            phoneAlbum2.getAlbumPhotos().add(phonePhoto);
                            phoneAlbums.add(phoneAlbum2);
                            albumsNames.add(string);
                        }
                        StringBuilder sb5 = new StringBuilder();
                        sb5.append(str5);
                        sb5.append(string);
                        Log.i(str4, sb5.toString());
                    }
                    if (!query.moveToNext()) {
                        break;
                    }
                }
            }
            query.close();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    public class LoadImageTask extends AsyncTask<Void, Void, Boolean> {
        public LoadImageTask() {
        }


        public void onPreExecute() {
            super.onPreExecute();
            progressBar.setVisibility(View.VISIBLE);
        }


        public Boolean doInBackground(Void... voidArr) {
            return Boolean.valueOf(initViewAction());
        }


        public void onPostExecute(Boolean bool) {
            super.onPostExecute(bool);
            progressBar.setVisibility(View.GONE);
            if (bool.booleanValue()) {

                if (phoneAlbums != null) {
                    for (int i = 0; i < phoneAlbums.size(); i++) {
                        phoneAlbums.get(i).setCoverUri(phoneAlbums.get(i).getAlbumPhotos().get(phoneAlbums.get(i).getAlbumPhotos().size() - 1).getPhotoUri());
                    }
                }

                adapterAlbum.notifyDataSetChanged();
                return;
            }
//            rcv_album.setVisibility(View.GONE);
//            lin_no_photo.setVisibility(View.VISIBLE);
        }
    }

}