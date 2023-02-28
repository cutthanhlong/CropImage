package com.textonphoto.addtext.editphoto.adapter;

import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.makeramen.roundedimageview.RoundedImageView;
import com.textonphoto.addtext.editphoto.R;
import com.textonphoto.addtext.editphoto.Ultil.Sharef;
import com.textonphoto.addtext.editphoto.activity.SelectAlbumActivity;


import java.util.ArrayList;

public class AdapterListFoder extends RecyclerView.Adapter<AdapterListFoder.ViewHolder> {
    ArrayList<String> lst_album;
    Context context;
    int count = 0;

    public AdapterListFoder(ArrayList<String> lst_album, Context context) {
        this.lst_album = lst_album;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_photo_list_forder, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Glide.with(this.context).load(lst_album.get(position)).into(holder.img_album_foder);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                if (SelectAlbumActivity.listImageSelect.size()<9){
                     SelectAlbumActivity.listImageSelect.add(lst_album.get(position));
                    SelectAlbumActivity.adapterImageSelect.notifyDataSetChanged();
                    SelectAlbumActivity.rv_list_image_select.scrollToPosition(SelectAlbumActivity.listImageSelect.size()-1);
                 }

                notifyItemChanged(position);
            }
        });
        int count = getCount(lst_album.get(position));
        if (count == 0){
            holder.count_choose.setVisibility(View.GONE);
        }else {
            holder.count_choose.setVisibility(View.VISIBLE);
            holder.count_choose.setText(String.valueOf(count));

        }



    }
    private int getCount(String path){
        int count =0;
        for (int i = 0 ; i < SelectAlbumActivity.listImageSelect.size();i++){
            if (path.equals(SelectAlbumActivity.listImageSelect.get(i))){
                count++;
            }
        }
        return count;
    }



    @Override
    public int getItemCount() {
        return this.lst_album.size();

    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        RoundedImageView img_album_foder;
        TextView count_choose;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            this.img_album_foder = itemView.findViewById(R.id.iv_album);
            this.count_choose = itemView.findViewById(R.id.count_choose);


        }
    }
}
