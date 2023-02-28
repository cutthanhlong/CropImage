package com.textonphoto.addtext.editphoto.adapter;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.makeramen.roundedimageview.RoundedImageView;
import com.textonphoto.addtext.editphoto.R;
import com.textonphoto.addtext.editphoto.activity.MainActivity;
import com.textonphoto.addtext.editphoto.activity.ShareImageScreenActivity;
import com.textonphoto.addtext.editphoto.interfacee.onClickItemGallery;
import com.textonphoto.addtext.editphoto.model.PhotoGalleryModel;

import java.util.List;

public class AdapterGallery extends RecyclerView.Adapter<AdapterGallery.ViewHolderMyCreation> {
   List<PhotoGalleryModel> photoGalleryModelList;
   Context context;
   onClickItemGallery onClickItemGallery;



    public AdapterGallery(List<PhotoGalleryModel> photoGalleryModelList, Context context, onClickItemGallery clickItemGallery) {
        this.photoGalleryModelList = photoGalleryModelList;
        this.context = context;
        this.onClickItemGallery = clickItemGallery;
    }

    @NonNull
    @Override
    public ViewHolderMyCreation onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_album_saved,parent,false);
        return new ViewHolderMyCreation(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderMyCreation holder, int position) {


        if (position ==0){
            holder.iv_camera.setVisibility(View.VISIBLE);
        }else {
            holder.iv_camera.setVisibility(View.GONE);
            Glide.with(this.context).load(photoGalleryModelList.get(position).getPath()).thumbnail(0.1f).dontAnimate().centerCrop().placeholder(R.drawable.no_image).error(R.drawable.no_image).into(holder.iv_album);

        }
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PhotoGalleryModel photoGalleryModel;
                photoGalleryModel = photoGalleryModelList.get(position);

                onClickItemGallery.onClickItem(position,photoGalleryModel);

            }
        });

    }

    @Override
    public int getItemCount() {
        return photoGalleryModelList.size();
    }

    public class ViewHolderMyCreation extends RecyclerView.ViewHolder{
        ImageView iv_album;
        CardView iv_camera;


        public ViewHolderMyCreation(@NonNull View itemView) {
            super(itemView);
            iv_album = itemView.findViewById(R.id.iv_album);
            iv_camera = itemView.findViewById(R.id.iv_camera);
        }
    }

}
