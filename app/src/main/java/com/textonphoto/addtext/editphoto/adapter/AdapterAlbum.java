package com.textonphoto.addtext.editphoto.adapter;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.textonphoto.addtext.editphoto.R;
import com.textonphoto.addtext.editphoto.Ultil.Sharef;
import com.textonphoto.addtext.editphoto.activity.SelectAlbumActivity;
import com.textonphoto.addtext.editphoto.model.AlbumImageModel;
import com.textonphoto.addtext.editphoto.model.PhotoModel;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Vector;

public class AdapterAlbum extends RecyclerView.Adapter<AdapterAlbum.ViewHolderAlbum> {
    Context context;
    public static List<AlbumImageModel> al_album;
    public static AdapterListFoder adapterListFoder;



    public AdapterAlbum(Context context, Vector<AlbumImageModel> vector) {
        this.context = context;
        this.al_album = vector;
    }

    @NonNull
    @Override
    public ViewHolderAlbum onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_album,parent,false);
        return new ViewHolderAlbum(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderAlbum holder, int position) {
        Sharef.lst_album_image.clear();
        ArrayList<String> arr = new ArrayList<>();
        ArrayList<Long> arrId = new ArrayList<>();
        for (int i1 = 0; i1 < ( al_album.get(position)).getAlbumPhotos().size(); i1++) {
            arr.add(((PhotoModel) ((AlbumImageModel) al_album.get(position)).getAlbumPhotos().get(i1)).getPhotoUri());

        }
        Collections.reverse(Sharef.lst_album_image);
        holder.tv_album_name.setText(((AlbumImageModel) this.al_album.get(position)).getName());

        al_album.get(position).setAdapterListFoder(new AdapterListFoder(arr,context));
        holder.rv_list_album.setLayoutManager(new GridLayoutManager(holder.rv_list_album.getContext(), 3, GridLayoutManager.VERTICAL, false));

        holder.rv_list_album.setAdapter(al_album.get(position).getAdapterListFoder());



    }



    @Override
    public int getItemCount() {
        return al_album.size();
    }

    public class ViewHolderAlbum extends RecyclerView.ViewHolder{
        TextView tv_album_name;
        RecyclerView rv_list_album;


        public ViewHolderAlbum(@NonNull View itemView) {
            super(itemView);
            this.tv_album_name =  itemView.findViewById(R.id.tv_album_name);
            this.rv_list_album = itemView.findViewById(R.id.rv_list_album);
        }
    }
}
