package com.textonphoto.addtext.editphoto.adapter;

import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.textonphoto.addtext.editphoto.R;
import com.textonphoto.addtext.editphoto.interfacee.onClickItemFont;
import com.textonphoto.addtext.editphoto.interfacee.onClickItemGallery;

import java.util.List;

import me.grantland.widget.AutofitTextView;


public class AdapterFont extends RecyclerView.Adapter<AdapterFont.ViewHolderFont>{
    List<String> listFont;
    Context context;
    private String fontDisplayText;
    public int selectedPosition = -1;
    onClickItemFont onClickItemFont;





    public AdapterFont(List<String> listFont, Context context,onClickItemFont onClickItemFont) {
        this.listFont = listFont;
        this.context = context;
        this.onClickItemFont = onClickItemFont;
    }

    @NonNull
    @Override
    public ViewHolderFont onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_font,parent,false);
        return new ViewHolderFont(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderFont holder, int position) {
        try {
            Typeface createFromAsset = Typeface.createFromAsset(this.context.getAssets(), (String) this.listFont.get(position));
            holder.fontNo.setTypeface(createFromAsset);
            holder.fontNo.setText(String.format("%02d", position + 1));
            holder.fontView.setTypeface(createFromAsset);
            holder.fontView.setText((CharSequence) (this.fontDisplayText == null ? (CharSequence) this.listFont.get(position) : this.fontDisplayText));
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (selectedPosition != position) {
                       selectedPosition = position;
                        notifyDataSetChanged();
                    }

                    onClickItemFont.onClickItemFont(listFont.get(position),position);

                }
            });
         } catch (Exception var4) {
            holder.fontView.setText(context.getString(R.string.Error));
        }

    }

    @Override
    public int getItemCount() {
        return listFont.size();
    }

    public class ViewHolderFont extends RecyclerView.ViewHolder{
        private FrameLayout fontLayout;
        public AutofitTextView fontNo;
        public AutofitTextView fontView;
        private View rootView;
        private ImageView selectedFont;

        public ViewHolderFont(@NonNull View itemView) {
            super(itemView);
            this.fontLayout = (FrameLayout) itemView.findViewById(R.id.fontItemLayout);
            this.fontNo = (AutofitTextView) itemView.findViewById(R.id.tvFontNo);
            this.fontView = (AutofitTextView) itemView.findViewById(R.id.tvFontView);
            this.selectedFont = (ImageView) itemView.findViewById(R.id.ivFontSelected);
            this.selectedFont = (ImageView) itemView.findViewById(R.id.ivFontSelected);
        }
    }
}
