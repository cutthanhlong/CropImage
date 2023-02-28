package com.textonphoto.addtext.editphoto.Sticker;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;


import com.textonphoto.addtext.editphoto.R;
import com.textonphoto.addtext.editphoto.activity.CreateCollageActivity;

import java.util.ArrayList;

public class StickerGalleryFragment extends Fragment implements OnItemClickListener {
    final int a = 12;
    int ae = 0;
    StickerGalleryFragment af = this;
    float ag = 0.0f;
    ListView ah;
    ArrayList<Integer> aj = new ArrayList();
    Animation ak;
    Animation al;
    StickerGridItem[][] am;
    int ao = 0;
    Activity b;

    int d = 0;
    public StickerGalleryListener f;
    StickerGridAdapter g;
    GridView h;
    TextView i;
    View view;

    public interface StickerGalleryListener {
        void a();

        void a(int[] iArr);
    }

    private void S() {
        int length = Utility.a.length;
        this.am = new StickerGridItem[length][];
        for (int i = 0; i < length; i++) {
            int length2 = Utility.a[i].length;
            this.am[i] = new StickerGridItem[length2];
            for (int i2 = 0; i2 < length2; i2++) {
                this.am[i][i2] = new StickerGridItem(Utility.a[i][i2]);
            }
        }
    }

    final void c() {
        StringBuilder stringBuilder = new StringBuilder("initialTogglePos ");
        stringBuilder.append(this.ae);
        Log.e("GalleryActivity", stringBuilder.toString());
    }


    public final void StickerGalleryFragment(Activity activity) {
        this.b = activity;
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_sticker_gallery, container, false);
        this.i = (TextView) view.findViewById(R.id.textView_header);
        ((ImageView) view.findViewById(R.id.btnDoneSticker)).setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                int i = 0;
                int lenggg = am.length;
                for (int i2 = 0; i2 < lenggg; i2++) {
                    for (StickerGridItem stickerGridItem : am[i2]) {
                        stickerGridItem.b = 0;
                    }
                }
                int asize = aj.size();
                int[] iArr = new int[asize];
                while (i < asize) {
                    iArr[i] = ((Integer) aj.get(i)).intValue();
                    i++;
                }
                aj.clear();
//                if (f != null) {
//                    f.a(iArr);
//                    return;
//                }
//                getActivity().getSupportFragmentManager().beginTransaction().attach(af).commit();

                Intent intent = new Intent(getActivity(), CreateCollageActivity.class);
                Bundle bundle = new Bundle();
                bundle.putIntArray("MyArray", iArr);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
        this.ak = AnimationUtils.loadAnimation(getActivity(), R.anim.slide_in_left_galler_toggle);
        this.al = AnimationUtils.loadAnimation(getActivity(), R.anim.slide_out_left_gallery_toggle);
        this.ak.setFillAfter(true);
        this.al.setFillAfter(true);


        this.h = (GridView) view.findViewById(R.id.gridView);

        S();

        this.g = new StickerGridAdapter(getActivity(), this.am[0], h);
        this.h.setAdapter(g);
        this.h.setOnItemClickListener(this);

        NavigationDrawerListAdapter aaaa = new NavigationDrawerListAdapter(getActivity());
        this.ah = (ListView) view.findViewById(R.id.drawer);
        this.ah.setAdapter(aaaa);
        this.ah.setItemChecked(0, true);
        this.ah.setOnItemClickListener(new OnItemClickListener() {


            public  void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
                if (d != i) {

                    S();

                    g.stickerGridItems = am[i];
                    h.smoothScrollToPosition(i);
                    g.notifyDataSetChanged();

                }
                d = i;
            }
        });
        return view;
    }


//    public final void aaa(boolean z) {
//        super.aaaa(z);
//        if (this.h != null && !z) {
//            Log.e("GalleryActivity", "onHiddenChanged gridView");
//            this.g.notifyDataSetChanged();
//            this.h.invalidateViews();
//        }
//    }

    public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
        int i2 = 0;
        if (this.ao + this.aj.size() >= 12) {
            AlertDialog.Builder alertDialog = new AlertDialog.Builder(getActivity());
            alertDialog.setMessage(R.string.sticker_choose_limit);
            alertDialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {


                public final void onClick(DialogInterface dialogInterface, int i) {
                }

                {

                }
            });
            alertDialog.create().show();
            return;
        }
        if (this.g.stickerGridItems[i].b == 0) {
            //adapterView = this.g.stickerGridItems[i];
            g.stickerGridItems[i].b++;
        } else {
            this.g.stickerGridItems[i].b = 0;
        }
        ImageView imageView = (ImageView) view.findViewById(R.id.image_view_item_selected);
        if (imageView.getVisibility() == View.INVISIBLE && this.g.stickerGridItems[i].b == 1) {
            imageView.setVisibility(View.VISIBLE);
        }
        if (imageView.getVisibility() == View.VISIBLE && this.g.stickerGridItems[i].b == 0) {
            imageView.setVisibility(View.INVISIBLE);
        }
        int view2 = this.g.stickerGridItems[i].a;
        if (this.g.stickerGridItems[i].b == 1) {
            this.aj.add(Integer.valueOf(view2));
        } else {
            while (i2 < this.aj.size()) {
                if (((Integer) this.aj.get(i2)).intValue() == view2) {
                    this.aj.remove(i2);
                    break;
                }
                i2++;
            }
        }
        TextView aaaaaa = this.i;
        StringBuilder r1 = new StringBuilder();
        r1.append(this.ao + this.aj.size());
        r1.append(getActivity().getString(R.string.sticker_items_selected));
        aaaaaa.setText(r1.toString());
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    public final void d(int ii) {
        this.ao = ii;
        if (ao != 0) {

            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(this.ao + this.aj.size());
            stringBuilder.append(getActivity().getString(R.string.sticker_items_selected));
            i.setText(stringBuilder.toString());
        }
    }
}
