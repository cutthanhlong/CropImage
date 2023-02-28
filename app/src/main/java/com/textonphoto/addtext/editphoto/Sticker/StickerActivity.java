package com.textonphoto.addtext.editphoto.Sticker;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.FragmentActivity;


import com.textonphoto.addtext.editphoto.R;

import java.util.ArrayList;

public class StickerActivity extends FragmentActivity implements AdapterView.OnItemClickListener {

    ArrayList<Integer> aj = new ArrayList();
    StickerGridItem[][] am;
    int ao = 0;
    public StickerGalleryFragment.StickerGalleryListener f;
    StickerGridAdapter g;
    GridView h;
    TextView i;

    ImageView btnSticker0, btnSticker1, btnSticker2, btnSticker3, btnSticker4, btnSticker5, btnSticker6;
    ImageView btnCloseSticker, btnDoneSticker;

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
//        SystemUtil.setLocale(this);
        setContentView(R.layout.fragment_sticker_gallery);

        initViews();

        btnCloseSticker.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        btnDoneSticker.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                int i = 0;
                for (StickerGridItem[] stickerGridItems : am) {
                    for (StickerGridItem stickerGridItem : stickerGridItems) {
                        stickerGridItem.b = 0;
                    }
                }
                int asize = aj.size();
                int[] iArr = new int[asize];
                while (i < asize) {
                    iArr[i] = aj.get(i);
                    i++;
                }
                aj.clear();

                Intent intent = new Intent();
                Bundle bundle = new Bundle();
                bundle.putIntArray("MyArray", iArr);
                intent.putExtras(bundle);
                setResult(RESULT_OK, intent);
                finish();
            }
        });

        btnSticker0.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                setDefaultBtn();
                btnSticker0.setBackgroundResource(R.drawable.bg_border_icon_d1dbff);
                S();
                g.stickerGridItems = am[0];
                h.smoothScrollToPosition(0);
                g.notifyDataSetChanged();
            }
        });
        btnSticker1.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                setDefaultBtn();
                btnSticker1.setBackgroundResource(R.drawable.bg_border_icon_d1dbff);
                S();
                g.stickerGridItems = am[1];
                h.smoothScrollToPosition(1);
                g.notifyDataSetChanged();
            }
        });
        btnSticker2.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                setDefaultBtn();
                btnSticker2.setBackgroundResource(R.drawable.bg_border_icon_d1dbff);
                S();
                g.stickerGridItems = am[2];
                h.smoothScrollToPosition(2);
                g.notifyDataSetChanged();
            }
        });
        btnSticker3.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                setDefaultBtn();
                btnSticker3.setBackgroundResource(R.drawable.bg_border_icon_d1dbff);
                S();
                g.stickerGridItems = am[3];
                h.smoothScrollToPosition(3);
                g.notifyDataSetChanged();
            }
        });
        btnSticker4.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                setDefaultBtn();
                btnSticker4.setBackgroundResource(R.drawable.bg_border_icon_d1dbff);
                S();
                g.stickerGridItems = am[4];
                h.smoothScrollToPosition(4);
                g.notifyDataSetChanged();
            }
        });
        btnSticker5.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                setDefaultBtn();
                btnSticker5.setBackgroundResource(R.drawable.bg_border_icon_d1dbff);
                S();
                g.stickerGridItems = am[5];
                h.smoothScrollToPosition(5);
                g.notifyDataSetChanged();
            }
        });
        btnSticker6.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                setDefaultBtn();
                btnSticker6.setBackgroundResource(R.drawable.bg_border_icon_d1dbff);
                S();
                g.stickerGridItems = am[6];
                h.smoothScrollToPosition(6);
                g.notifyDataSetChanged();
            }
        });
    }

    private void initViews() {
        this.i = findViewById(R.id.textView_header);
        btnDoneSticker = findViewById(R.id.btnDoneSticker);
        btnCloseSticker = findViewById(R.id.btnCloseSticker);

        this.h = (GridView) findViewById(R.id.gridView);

        btnSticker0 = findViewById(R.id.btnSticker0);
        btnSticker1 = findViewById(R.id.btnSticker1);
        btnSticker2 = findViewById(R.id.btnSticker2);
        btnSticker3 = findViewById(R.id.btnSticker3);
        btnSticker4 = findViewById(R.id.btnSticker4);
        btnSticker5 = findViewById(R.id.btnSticker5);
        btnSticker6 = findViewById(R.id.btnSticker6);

        S();

        this.g = new StickerGridAdapter(this, this.am[0], h);
        this.h.setAdapter(g);
        this.h.setOnItemClickListener(this);
    }

    private void setDefaultBtn() {
        btnSticker0.setBackgroundColor(getResources().getColor(R.color.white));
        btnSticker1.setBackgroundColor(getResources().getColor(R.color.white));
        btnSticker2.setBackgroundColor(getResources().getColor(R.color.white));
        btnSticker3.setBackgroundColor(getResources().getColor(R.color.white));
        btnSticker4.setBackgroundColor(getResources().getColor(R.color.white));
        btnSticker5.setBackgroundColor(getResources().getColor(R.color.white));
        btnSticker6.setBackgroundColor(getResources().getColor(R.color.white));
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int i, long id) {

        int i2 = 0;
        if (this.ao + this.aj.size() >= 12) {
            AlertDialog.Builder alertDialog = new AlertDialog.Builder(this);
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
        ImageView imageView = view.findViewById(R.id.image_view_item_selected);
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
                if (this.aj.get(i2).intValue() == view2) {
                    this.aj.remove(i2);
                    break;
                }
                i2++;
            }
        }
        TextView aaaaaa = this.i;
        StringBuilder r1 = new StringBuilder();
        r1.append(this.ao + this.aj.size());
        Log.e("abcd", "bao: " + ao + ", aj: " + aj.size());
        if (aj.size() < 2) {
            r1.append(getString(R.string.sticker_item_selected));
        } else {
            r1.append(getString(R.string.sticker_items_selected));
        }
        aaaaaa.setText(r1.toString());

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

    public final void d(int ii) {
        this.ao = ii;
        if (ao != 0) {

            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(this.ao + this.aj.size());
            Log.e("abcd", "bao: " + ao + ", aj: " + aj.size());
            if (aj.size() < 2) {
                stringBuilder.append(getString(R.string.sticker_item_selected));
            } else {
                stringBuilder.append(getString(R.string.sticker_items_selected));
            }
            i.setText(stringBuilder.toString());
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}
