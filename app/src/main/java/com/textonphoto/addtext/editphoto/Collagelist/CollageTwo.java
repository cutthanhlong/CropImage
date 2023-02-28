package com.textonphoto.addtext.editphoto.Collagelist;

import android.graphics.PointF;

import java.util.ArrayList;
import java.util.List;

public class CollageTwo extends Collage {

    public static int shapeCount = 2;

    public CollageTwo(int i, int j) {
        collageLayoutList = new ArrayList<>();
        Object obj = new ArrayList<>();
        ((List) (obj)).add(new PointF[]{
                new PointF((float) i * 0.0F, (float) j * 1.0F), new PointF(0.5F * (float) i, (float) j * 1.0F), new PointF(0.5F * (float) i, (float) j * 0.0F), new PointF((float) i * 0.0F, (float) j * 0.0F)
        });
        ((List) (obj)).add(new PointF[]{
                new PointF(0.5F * (float) i, (float) j * 0.0F), new PointF((float) i * 1.0F, (float) j * 0.0F), new PointF((float) i * 1.0F, (float) j * 1.0F), new PointF(0.5F * (float) i, (float) j * 1.0F)
        });
        obj = new CollageLayout(((List) (obj)));
        collageLayoutList.add(obj);

        obj = new ArrayList();
        ((List) (obj)).add(new PointF[]{
                new PointF((float) i * 0.0F, (float) j * 1.0F), new PointF((float) i * 0.0F, 0.5F * (float) j), new PointF((float) i * 1.0F, 0.5F * (float) j), new PointF((float) i * 1.0F, (float) j * 1.0F)
        });
        ((List) (obj)).add(new PointF[]{
                new PointF((float) i * 0.0F, 0.5F * (float) j), new PointF((float) i * 0.0F, (float) j * 0.0F), new PointF((float) i * 1.0F, (float) j * 0.0F), new PointF((float) i * 1.0F, 0.5F * (float) j)
        });
        obj = new CollageLayout(((List) (obj)));
        collageLayoutList.add(obj);

        obj = new ArrayList();
        ((List) (obj)).add(new PointF[]{
                new PointF((float) i * 0.0F, (float) j * 1.0F), new PointF(0.6F * (float) i, (float) j * 1.0F), new PointF(0.6F * (float) i, (float) j * 0.0F), new PointF((float) i * 0.0F, (float) j * 0.0F)
        });
        ((List) (obj)).add(new PointF[]{
                new PointF(0.4F * (float) i, (float) j * 0.0F), new PointF((float) i * 1.0F, (float) j * 0.0F), new PointF((float) i * 1.0F, (float) j * 1.0F), new PointF(0.4F * (float) i, (float) j * 1.0F)
        });

        obj = new ArrayList();
        ((List) (obj)).add(new PointF[]{
                new PointF((float) i * 0.0F, (float) j * 1.0F), new PointF((float) i * 0.0F, 0.3333333F * (float) j), new PointF((float) i * 1.0F, 0.3333333F * (float) j), new PointF((float) i * 1.0F, (float) j * 1.0F)
        });
        ((List) (obj)).add(new PointF[]{
                new PointF((float) i * 0.0F, 0.3333333F * (float) j), new PointF((float) i * 0.0F, (float) j * 0.0F), new PointF((float) i * 1.0F, (float) j * 0.0F), new PointF((float) i * 1.0F, 0.3333333F * (float) j)
        });
        obj = new CollageLayout(((List) (obj)));
        collageLayoutList.add(obj);

        obj = new ArrayList();
        ((List) (obj)).add(new PointF[]{
                new PointF((float) i * 0.0F, (float) j * 1.0F), new PointF((float) i * 0.0F, (float) j * 0.0F), new PointF((float) i * 1.0F, (float) j * 0.0F), new PointF((float) i * 1.0F, (float) j * 1.0F)
        });
        ((List) (obj)).add(new PointF[]{
                new PointF(0.13F * (float) i, 0.13F * (float) j), new PointF(0.13F * (float) i, 0.87F * (float) j), new PointF(0.87F * (float) i, 0.87F * (float) j), new PointF(0.87F * (float) i, 0.13F * (float) j)
        });

        obj = new ArrayList();
        ((List) (obj)).add(new PointF[]{
                new PointF((float) i * 0.0F, (float) j * 1.0F), new PointF((float) i * 0.0F, 0.6666667F * (float) j), new PointF((float) i * 1.0F, 0.6666667F * (float) j), new PointF((float) i * 1.0F, (float) j * 1.0F)
        });
        ((List) (obj)).add(new PointF[]{
                new PointF((float) i * 0.0F, 0.6666667F * (float) j), new PointF((float) i * 0.0F, (float) j * 0.0F), new PointF((float) i * 1.0F, (float) j * 0.0F), new PointF((float) i * 1.0F, 0.6666667F * (float) j)
        });
        obj = new CollageLayout(((List) (obj)));
        collageLayoutList.add(obj);

        obj = new ArrayList();
        ((List) (obj)).add(new PointF[]{
                new PointF((float) i * 0.0F, (float) j * 1.0F), new PointF((float) i * 0.0F, (float) j * 0.0F), new PointF(0.3333333F * (float) i, (float) j * 0.0F), new PointF(0.3333333F * (float) i, (float) j * 1.0F)
        });
        ((List) (obj)).add(new PointF[]{
                new PointF(0.3333333F * (float) i, (float) j * 0.0F), new PointF((float) i * 1.0F, (float) j * 0.0F), new PointF((float) i * 1.0F, (float) j * 1.0F), new PointF(0.3333333F * (float) i, (float) j * 1.0F)
        });
        obj = new CollageLayout(((List) (obj)));
        collageLayoutList.add(obj);

        obj = new ArrayList();
        ((List) (obj)).add(new PointF[]{
                new PointF((float) i * 0.0F, (float) j * 1.0F), new PointF((float) i * 0.0F, (float) j * 0.0F), new PointF(0.6666667F * (float) i, (float) j * 0.0F), new PointF(0.6666667F * (float) i, (float) j * 1.0F)
        });
        ((List) (obj)).add(new PointF[]{
                new PointF(0.6666667F * (float) i, (float) j * 0.0F), new PointF((float) i * 1.0F, (float) j * 0.0F), new PointF((float) i * 1.0F, (float) j * 1.0F), new PointF(0.6666667F * (float) i, (float) j * 1.0F)
        });
        obj = new CollageLayout(((List) (obj)));
        collageLayoutList.add(obj);

        obj = new ArrayList();
        ((List) (obj)).add(new PointF[]{
                new PointF((float) i * 0.0F, (float) j * 1.0F), new PointF((float) i * 0.0F, (float) j * 0.0F), new PointF(0.3333333F * (float) i, (float) j * 0.0F), new PointF(0.6666667F * (float) i, (float) j * 1.0F)
        });
        ((List) (obj)).add(new PointF[]{
                new PointF(0.3333333F * (float) i, (float) j * 0.0F), new PointF((float) i * 1.0F, (float) j * 0.0F), new PointF((float) i * 1.0F, (float) j * 1.0F), new PointF(0.6666667F * (float) i, (float) j * 1.0F)
        });
        obj = new CollageLayout(((List) (obj)));
        collageLayoutList.add(obj);

        obj = new ArrayList();
        ((List) (obj)).add(new PointF[]{
                new PointF((float) i * 0.0F, (float) j * 1.0F), new PointF((float) i * 0.0F, (float) j * 0.0F), new PointF(0.6666667F * (float) i, (float) j * 0.0F), new PointF(0.3333333F * (float) i, (float) j * 1.0F)
        });
        ((List) (obj)).add(new PointF[]{
                new PointF(0.6666667F * (float) i, (float) j * 0.0F), new PointF((float) i * 1.0F, (float) j * 0.0F), new PointF((float) i * 1.0F, (float) j * 1.0F), new PointF(0.3333333F * (float) i, (float) j * 1.0F)
        });
        obj = new CollageLayout(((List) (obj)));
        collageLayoutList.add(obj);
    }

}
