package com.textonphoto.addtext.editphoto.Sticker;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.textonphoto.addtext.editphoto.R;


public class NavigationDrawerListAdapter extends BaseAdapter {
    Context context;
    int[] b = new int[]{R.drawable.set1_1, R.drawable.set2_1, R.drawable.set3_1, R.drawable.set4_1, R.drawable.set5_1, R.drawable.set6_1
            , R.drawable.set7_1};

    LayoutInflater layoutInflater;


    public Object getItem(int i) {
        return null;
    }

    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        layoutInflater = LayoutInflater.from(context);
        View view;
        view = layoutInflater.inflate(R.layout.item_list_nav_sticker, parent, false);

        ImageView a = (ImageView) view.findViewById(R.id.nav_list_image);

        a.setImageDrawable(this.context.getResources().getDrawable(this.b[position]));

        return view;
    }

    public NavigationDrawerListAdapter(Context context) {
        this.context = context;
    }

    public int getCount() {
        return this.b.length;
    }


}
