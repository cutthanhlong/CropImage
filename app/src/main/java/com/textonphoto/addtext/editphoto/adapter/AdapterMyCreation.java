package com.textonphoto.addtext.editphoto.adapter;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.makeramen.roundedimageview.RoundedImageView;
import com.textonphoto.addtext.editphoto.R;
import com.textonphoto.addtext.editphoto.activity.ShareImageScreenActivity;

import java.util.List;

public class AdapterMyCreation extends RecyclerView.Adapter<AdapterMyCreation.ViewHolderMyCreation> {
   List<Uri> listImageSaved;
   Context context;

    public AdapterMyCreation(List<Uri> listImageSaved, Context context) {
        this.listImageSaved = listImageSaved;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolderMyCreation onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_album_saved,parent,false);
        return new ViewHolderMyCreation(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderMyCreation holder, int position) {
        Uri uri = listImageSaved.get(position);
        Glide.with(this.context).load(uri.toString()).thumbnail(0.1f).dontAnimate().centerCrop().placeholder(R.drawable.no_image).error(R.drawable.no_image).into(holder.iv_album);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, ShareImageScreenActivity.class);
                intent.putExtra("uri", uri.toString());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return listImageSaved.size();
    }

    public class ViewHolderMyCreation extends RecyclerView.ViewHolder{
        ImageView iv_album;


        public ViewHolderMyCreation(@NonNull View itemView) {
            super(itemView);
            iv_album = itemView.findViewById(R.id.iv_album);
        }
    }
}
