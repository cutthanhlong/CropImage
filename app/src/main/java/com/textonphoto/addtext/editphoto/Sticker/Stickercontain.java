package com.textonphoto.addtext.editphoto.Sticker;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;


import com.textonphoto.addtext.editphoto.R;

import java.util.ArrayList;

public class Stickercontain extends AppCompatActivity {

    Button choose;
    FrameLayout frameLayout;
    int c = 0;
    ArrayList e;
    ImageView img;

//    StickerView.StickerViewSelectedListener g = new StickerView.StickerViewSelectedListener() {
//
//        public final void a(StickerView stickerView) {
//            stickerView.bringToFront();
//            c = e.indexOf(stickerView);
//            for (int i = 0; i < e.size(); i++) {
//                if (c != i) {
//                    ((StickerView) e.get(i)).setViewSelected(false);
//                } else {
//                    ((StickerView) e.get(i)).setViewSelected(true);
//                }
//            }
//            stickerView.bringToFront();
//            if (Build.VERSION.SDK_INT < 19) {
//
//                frameLayout.requestLayout();
//            }
//        }
//    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stickercontain);

        this.e = new ArrayList();
        choose = (Button) findViewById(R.id.choose);

        frameLayout = (FrameLayout) findViewById(R.id.sticker_view_container);

        choose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                startActivity(new Intent(Stickercontain.this, StickerActivity.class));

            }
        });

        if (getIntent().getExtras() != null) {
            Bundle bundle = getIntent().getExtras();
            int arrayReceived[] = bundle.getIntArray("MyArray");

            Log.e("getIntent", "getIntent" + arrayReceived.length);

            Bitmap decodeResource = BitmapFactory.decodeResource(getResources(), R.drawable.ic_close);
            Bitmap decodeResource2 = BitmapFactory.decodeResource(getResources(), R.drawable.ic_rright);
            for (int i = 0; i < arrayReceived.length; i++) {

                Log.e("bitmapsticker", "" + BitmapFactory.decodeResource(getResources(), arrayReceived[i]));
                if (frameLayout==null){

                    frameLayout = (FrameLayout) findViewById(R.id.sticker_view_container);
                }

//                if (img==null){
//
//                    img=(ImageView)findViewById(R.id.img);
//                }
//                img.setImageBitmap(BitmapFactory.decodeResource(getResources(), arrayReceived[0]));
//                StickerView stickerView = new StickerView(this, BitmapFactory.decodeResource(getResources(), arrayReceived[i]), null, decodeResource, decodeResource2, arrayReceived[i]);
//                stickerView.setStickerViewSelectedListener(g);

                StickerImageView iv_sticker = new StickerImageView(this);
                iv_sticker.setImageBitmap(BitmapFactory.decodeResource(getResources(), arrayReceived[i]));
                frameLayout.addView(iv_sticker);



            }
        }

    }
}
