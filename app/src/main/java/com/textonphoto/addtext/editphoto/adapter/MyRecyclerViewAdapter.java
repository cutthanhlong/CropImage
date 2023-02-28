package com.textonphoto.addtext.editphoto.adapter;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.RecyclerView.Adapter;

import com.textonphoto.addtext.editphoto.R;


@SuppressLint({"InflateParams"})
public class MyRecyclerViewAdapter extends Adapter<MyRecyclerViewAdapter.ViewHolder> implements OnClickListener {
    int colorDefault;
    public int[] iconList;
    RecyclerAdapterIndexChangedListener listener;
    int proIndex;
    RecyclerView recylceView;
    private int selectedIndex;
    SelectedIndexChangedListener selectedIndexChangedListener;
    View selectedListItem;

    public MyRecyclerViewAdapter(int[] iconList, RecyclerAdapterIndexChangedListener l, int cDefault, int proIndex) {
        this.proIndex = 100;
        this.iconList = iconList;
        this.listener = l;
        this.colorDefault = cDefault;
        this.proIndex = proIndex;
    }

    public interface RecyclerAdapterIndexChangedListener {
        void onIndexChanged(int i);
    }

    public interface SelectedIndexChangedListener {
        void selectedIndexChanged(int i);
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public ImageView imageView, imgFilClick;
        RecyclerAdapterIndexChangedListener viewHolderListener;

        public void setRecyclerAdapterIndexChangedListener(RecyclerAdapterIndexChangedListener l) {
            this.viewHolderListener = l;
        }

        public void setItem(int item) {
            this.imageView.setImageResource(item);
        }

        public ViewHolder(View itemLayoutView) {
            super(itemLayoutView);
            this.imageView = (ImageView) itemLayoutView.findViewById(R.id.filter_image);
            this.imgFilClick = (ImageView) itemLayoutView.findViewById(R.id.imgFilClick);
        }
    }

    public void setSelectedIndexChangedListener(SelectedIndexChangedListener l) {
        this.selectedIndexChangedListener = l;
    }

    public int getSelectedIndex() {
        return this.selectedIndex;
    }

    public void setSelectedIndex(int index) {
        this.selectedIndex = index;
        this.selectedIndexChangedListener.selectedIndexChanged(this.selectedIndex);
    }

    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemLayoutView = LayoutInflater.from(parent.getContext()).inflate(R.layout.viewitem, null);
        ViewHolder viewHolder = new ViewHolder(itemLayoutView);
        viewHolder.setRecyclerAdapterIndexChangedListener(this.listener);
        itemLayoutView.setOnClickListener(this);
        return viewHolder;
    }

    public void onBindViewHolder(ViewHolder viewHolder, int position) {
        viewHolder.setItem(this.iconList[position]);
        if (this.selectedIndex == position) {
            viewHolder.imgFilClick.setVisibility(View.VISIBLE);
        } else {
            viewHolder.imgFilClick.setVisibility(View.GONE);
        }
    }

    public void onAttachedToRecyclerView(RecyclerView recylceView) {
        this.recylceView = recylceView;
    }

    public void setSelectedView(int index) {
        if (this.selectedListItem != null) {
            this.selectedListItem.findViewById(R.id.imgFilClick).setVisibility(View.GONE);
        }
        this.selectedListItem = this.recylceView.getChildAt(index);
        if (this.selectedListItem != null) {
            this.selectedListItem.findViewById(R.id.imgFilClick).setVisibility(View.VISIBLE);
        }
        setSelectedIndex(index);
    }

    @Override
    public void onClick(View view) {
        int position = recylceView.getChildPosition(view);
        RecyclerView.ViewHolder oldViewHolder = recylceView.findViewHolderForPosition(this.selectedIndex);
        if (oldViewHolder != null) {
            View oldView = oldViewHolder.itemView;
            oldView.findViewById(R.id.imgFilClick).setVisibility(View.GONE);
        }
        selectedIndex = position;
        selectedIndexChangedListener.selectedIndexChanged(selectedIndex);
        view.findViewById(R.id.imgFilClick).setVisibility(View.VISIBLE);
        selectedListItem = view;
        listener.onIndexChanged(position);
    }

    @Override
    public int getItemCount() {
        return this.iconList.length;
    }


}
