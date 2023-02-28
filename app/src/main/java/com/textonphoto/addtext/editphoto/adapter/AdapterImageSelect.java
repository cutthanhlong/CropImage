package com.textonphoto.addtext.editphoto.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.textonphoto.addtext.editphoto.R;
import com.textonphoto.addtext.editphoto.Ultil.Sharef;
import com.textonphoto.addtext.editphoto.activity.SelectAlbumActivity;
import com.textonphoto.addtext.editphoto.model.AlbumImageModel;
import com.textonphoto.addtext.editphoto.model.PhotoModel;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Vector;

public class AdapterImageSelect extends RecyclerView.Adapter<AdapterImageSelect.ViewHolderAlbum> {
    Context context;
    List<String> al_album;


    public AdapterImageSelect(Context context, List<String> listImage) {
        this.context = context;
        this.al_album = listImage;
    }

    @NonNull
    @Override
    public ViewHolderAlbum onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_image_select,parent,false);
        return new ViewHolderAlbum(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderAlbum holder, int position) {
        Glide.with(this.context).load(al_album.get(position)).into(holder.iv_image_select);
        holder.iv_remove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getPosition(al_album.get(position));
                SelectAlbumActivity.listImageSelect.remove(position);
                SelectAlbumActivity.adapterImageSelect.notifyDataSetChanged();

            }
        });


    }
    private void getPosition(String path){
        int indexAlbum = 0;
        int indexItem = 0;
        for (int i = 0 ; i <AdapterAlbum.al_album.size();i++){
            if (path.contains(AdapterAlbum.al_album.get(i).getName())){
                indexAlbum = i;
                break;
            }
        }
        for (int i = 0 ; i <AdapterAlbum.al_album.get(indexAlbum).getAlbumPhotos().size();i++){

            if (path.equals(AdapterAlbum.al_album.get(indexAlbum).getAlbumPhotos().get(i).getPhotoUri())){
                indexItem = i;
                break;
            }
        }
        Log.e("TAG", "getPosition: "+indexAlbum );
        Log.e("TAG", "getPosition: "+indexItem );
        AdapterAlbum.al_album.get(indexAlbum).getAdapterListFoder().notifyItemChanged(indexItem);


    }



    @Override
    public int getItemCount() {
        return al_album.size();
    }

    public class ViewHolderAlbum extends RecyclerView.ViewHolder{
       ImageView iv_image_select,iv_remove;


        public ViewHolderAlbum(@NonNull View itemView) {
            super(itemView);
            this.iv_image_select =  itemView.findViewById(R.id.iv_image_select);
            this.iv_remove = itemView.findViewById(R.id.remove_item_image_select);
        }
    }
}
