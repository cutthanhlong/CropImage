package com.textonphoto.addtext.editphoto.Sticker;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapFactory.Options;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.textonphoto.addtext.editphoto.R;


public class StickerGridAdapter extends BaseAdapter {
    Context context;
    GridView gridView;
    LayoutInflater c;
    StickerGridItem[] stickerGridItems;
    Options options = new Options();
    Bitmap f;


    static class ViewHolder {
        ImageView stickera;
        ImageView select;

        ViewHolder(View v) {
            stickera = v.findViewById(R.id.imageView);
            select = v.findViewById(R.id.image_view_item_selected);
        }
    }

    public long getItemId(int i) {
        return (long) i;
    }

    public StickerGridAdapter(Context context, StickerGridItem[] stickerGridItemArr, GridView gridView) {
        this.c = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.gridView = gridView;
        this.f = BitmapFactory.decodeResource(context.getResources(), R.drawable.effect_thumb_0);
        this.context = context;
        this.options.inSampleSize = 2;
        this.stickerGridItems = stickerGridItemArr;
    }

    public int getCount() {
        return stickerGridItems.length;
    }

    @SuppressLint({"NewApi"})
    public View getView(int i, View view, ViewGroup viewGroup) {


        ViewHolder viewHolder;

        if (view==null){

            view = this.c.inflate(R.layout.item_grid_sticker, null);
            viewHolder = new ViewHolder(view);
            view.setTag(viewHolder);

        }else{
            viewHolder = (ViewHolder) view.getTag();
        }
//        view.setTag(viewGroup);
//stickerGridItems[i].b
//        Glide.with(context)
//                .load(stickerGridItems[i].b)
//                .into(a);

        Glide.with(context).load(stickerGridItems[i].a).into(viewHolder.stickera);

        if (stickerGridItems[i].b > 0) {
            if (viewHolder.select.getVisibility() == View.INVISIBLE) {
                viewHolder.select.setVisibility(View.VISIBLE);
            }
        } else if (viewHolder.select.getVisibility() == View.VISIBLE) {
            viewHolder.select.setVisibility(View.INVISIBLE);
            return view;
        }
        return view;
    }

    public Object getItem(int i) {
        return stickerGridItems[i];
    }
}
