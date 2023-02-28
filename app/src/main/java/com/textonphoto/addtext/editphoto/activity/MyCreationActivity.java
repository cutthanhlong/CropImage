package com.textonphoto.addtext.editphoto.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;

import com.textonphoto.addtext.editphoto.R;
import com.textonphoto.addtext.editphoto.adapter.AdapterMyCreation;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

public class MyCreationActivity extends AppCompatActivity {
    RecyclerView rv_list_image_save;
    public static List<Uri> listImage;
    AdapterMyCreation adapterMyCreation;
    String[] extensions = new String[]{"jpg", "jpeg", "JPG", "JPEG"};
    BroadcastReceiver broadcastReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            initView();
            listImage = new ArrayList<>();
            listImage = loadAllImages(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS).toString());
            adapterMyCreation = new AdapterMyCreation(listImage,MyCreationActivity.this);
            rv_list_image_save.setAdapter(adapterMyCreation);

        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_creation);
        initView();
        LocalBroadcastManager.getInstance(MyCreationActivity.this).registerReceiver(broadcastReceiver,new IntentFilter("load_my_creation"));
        listImage = new ArrayList<>();
        listImage = loadAllImages(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS).toString());
        adapterMyCreation = new AdapterMyCreation(listImage,MyCreationActivity.this);
        rv_list_image_save.setAdapter(adapterMyCreation);
    }
    private void initView(){
        rv_list_image_save = findViewById(R.id.rv_list_image_save);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        LocalBroadcastManager.getInstance(MyCreationActivity.this).unregisterReceiver(broadcastReceiver);

    }

    private List<Uri> loadAllImages(String str) {
        int size;
        HashMap hashMap = new HashMap();
        File file = new File(str, "/TextOnPhoto/PhotoEditor");
        if (file.isDirectory()) {
            File[] listFiles = file.listFiles();
            if (listFiles != null && listFiles.length > 0) {
                for (File file2 : listFiles) {
                    if (!file2.isDirectory()) {
                        for (String endsWith : this.extensions) {
                            if (file2.getAbsolutePath().endsWith(endsWith)) {
                                hashMap.put(Long.valueOf(file2.lastModified()), Uri.fromFile(file2));
                            }
                        }
                    }
                }
            }
        }
        if (hashMap.size() == 0) {
            return new ArrayList();
        }
        ArrayList arrayList = new ArrayList(hashMap.keySet());
        Collections.sort(arrayList);
        size = arrayList.size();
        ArrayList arrayList2 = new ArrayList();
        for (size--; size >= 0; size--) {
            arrayList2.add(hashMap.get(arrayList.get(size)));
        }
        return arrayList2;
    }
}