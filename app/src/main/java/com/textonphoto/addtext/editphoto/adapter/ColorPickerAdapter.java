package com.textonphoto.addtext.editphoto.adapter;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import androidx.recyclerview.widget.RecyclerView;


import com.textonphoto.addtext.editphoto.R;

import java.util.ArrayList;
import java.util.List;

@SuppressLint({"InflateParams"})
public class ColorPickerAdapter extends MyRecylceAdapterBase<ColorPickerAdapter.ViewHolder> implements OnClickListener {
    private static final String TAG = "Adapter";
    int colorCount;
    int colorDefault;
    private List<Integer> colorList;
    int colorSelected;
    String[] colors;
    CollageImageAdapter.CurrentCollageIndexChangedListener listener;
    RecyclerView recylceView;
    View selectedListItem;
    int selectedPosition;

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private static final String TAG = "ViewHolder";
        public View colorPickerView;
        private int item;
        CollageImageAdapter.CurrentCollageIndexChangedListener viewHolderListener;

        public void setCurrentCollageIndexChangedListener(CollageImageAdapter.CurrentCollageIndexChangedListener l) {
            this.viewHolderListener = l;
        }

        public void setItem(int items) {
            this.item = items;
            this.colorPickerView.setBackgroundColor(this.item);
        }

        public ViewHolder(View itemLayoutView) {
            super(itemLayoutView);
            this.colorPickerView = itemLayoutView.findViewById(R.id.color_picker_view);
        }
    }

    //khởi tạo list color
    public ColorPickerAdapter(CollageImageAdapter.CurrentCollageIndexChangedListener l, int cDefault, int cSelected) {
        this.colorCount = 3;
        this.colorList = new ArrayList<>();
        this.colors = new String[]{"#FFFFFF", "#0066FF", "#AFAFAF"};
        this.listener = l;
        this.colorDefault = cDefault;
        this.colorSelected = cSelected;
        createColorList();
    }

    private void createColorList() {
        for (String parseColor : this.colors) {
            this.colorList.add(Color.parseColor(parseColor));
        }
    }

    public void setSelectedPositinVoid() {
        this.selectedListItem = null;
        this.selectedPosition = -1;
    }

    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemLayoutView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_view_color_recycler, null);
        ViewHolder viewHolder = new ViewHolder(itemLayoutView);
        viewHolder.setCurrentCollageIndexChangedListener(this.listener);
        itemLayoutView.setOnClickListener(this);
        return viewHolder;
    }

    public void onBindViewHolder(ViewHolder viewHolder, int position) {
        viewHolder.setItem(((Integer) this.colorList.get(position)).intValue());
        if (this.selectedPosition == position) {
            viewHolder.itemView.setBackgroundColor(this.colorSelected);
        } else {
            viewHolder.itemView.setBackgroundColor(this.colorDefault);
        }
    }

    public void onAttachedToRecyclerView(RecyclerView recylceView) {
        this.recylceView = recylceView;
    }

    public void onClick(View view) {
        int position = this.recylceView.getChildPosition(view);
        RecyclerView.ViewHolder oldViewHolder = this.recylceView.findViewHolderForPosition(this.selectedPosition);
        if (oldViewHolder != null) {
            View oldView = oldViewHolder.itemView;
            if (oldView != null) {
                oldView.setBackgroundColor(this.colorDefault);
            }
        }
        if (this.selectedListItem != null) {
            Log.d(TAG, "selectedListItem " + position);
        }
        Log.d(TAG, "onClick " + position);
        this.listener.onIndexChanged(((Integer) this.colorList.get(position)).intValue());
        this.selectedPosition = position;
        view.setBackgroundColor(this.colorSelected);
        this.selectedListItem = view;
    }

    public int getItemCount() {
        return this.colorList.size();
    }
}
