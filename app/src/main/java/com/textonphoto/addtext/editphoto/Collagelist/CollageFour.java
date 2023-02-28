package com.textonphoto.addtext.editphoto.Collagelist;

import android.graphics.PointF;

import java.util.ArrayList;
import java.util.List;

public class CollageFour extends Collage {

    public static int shapeCount = 4;

    public CollageFour(int i, int j) {
        collageLayoutList = new ArrayList();
        Object obj = new ArrayList();
        ((List) (obj)).add(new PointF[]{
                new PointF((float) i * 0.0F, 0.5F * (float) j), new PointF(0.5F * (float) i, 0.5F * (float) j), new PointF(0.5F * (float) i, (float) j * 0.0F), new PointF((float) i * 0.0F, (float) j * 0.0F)
        });
        ((List) (obj)).add(new PointF[]{
                new PointF((float) i * 0.0F, (float) j * 1.0F), new PointF(0.5F * (float) i, (float) j * 1.0F), new PointF(0.5F * (float) i, 0.5F * (float) j), new PointF((float) i * 0.0F, 0.5F * (float) j)
        });
        ((List) (obj)).add(new PointF[]{
                new PointF(0.5F * (float) i, 0.5F * (float) j), new PointF((float) i * 1.0F, 0.5F * (float) j), new PointF((float) i * 1.0F, (float) j * 0.0F), new PointF(0.5F * (float) i, (float) j * 0.0F)
        });
        ((List) (obj)).add(new PointF[]{
                new PointF(0.5F * (float) i, (float) j * 1.0F), new PointF(0.5F * (float) i, 0.5F * (float) j), new PointF((float) i * 1.0F, 0.5F * (float) j), new PointF((float) i * 1.0F, (float) j * 1.0F)
        });
        obj = new CollageLayout(((List) (obj)));
        collageLayoutList.add(obj);

        obj = new ArrayList();
        ((List) (obj)).add(new PointF[]{
                new PointF((float) i * 0.0F, (float) j * 1.0F), new PointF(0.25F * (float) i, (float) j * 1.0F), new PointF(0.25F * (float) i, (float) j * 0.0F), new PointF((float) i * 0.0F, (float) j * 0.0F)
        });
        ((List) (obj)).add(new PointF[]{
                new PointF(0.25F * (float) i, (float) j * 1.0F), new PointF(0.5F * (float) i, (float) j * 1.0F), new PointF(0.5F * (float) i, (float) j * 0.0F), new PointF(0.25F * (float) i, (float) j * 0.0F)
        });
        ((List) (obj)).add(new PointF[]{
                new PointF(0.5F * (float) i, (float) j * 1.0F), new PointF(0.75F * (float) i, (float) j * 1.0F), new PointF(0.75F * (float) i, (float) j * 0.0F), new PointF(0.5F * (float) i, (float) j * 0.0F)
        });
        ((List) (obj)).add(new PointF[]{
                new PointF(0.75F * (float) i, (float) j * 1.0F), new PointF((float) i * 1.0F, (float) j * 1.0F), new PointF((float) i * 1.0F, (float) j * 0.0F), new PointF(0.75F * (float) i, (float) j * 0.0F)
        });
        obj = new CollageLayout(((List) (obj)));
        collageLayoutList.add(obj);

        obj = new ArrayList();
        ((List) (obj)).add(new PointF[]{
                new PointF((float) i * 0.0F, 0.4166667F * (float) j), new PointF(0.3333333F * (float) i, 0.4166667F * (float) j), new PointF(0.3333333F * (float) i, (float) j * 0.0F), new PointF((float) i * 0.0F, (float) j * 0.0F)
        });
        ((List) (obj)).add(new PointF[]{
                new PointF(0.3333333F * (float) i, 0.4166667F * (float) j), new PointF(0.6666667F * (float) i, 0.4166667F * (float) j), new PointF(0.6666667F * (float) i, (float) j * 0.0F), new PointF(0.3333333F * (float) i, (float) j * 0.0F)
        });
        ((List) (obj)).add(new PointF[]{
                new PointF(0.6666667F * (float) i, 0.4166667F * (float) j), new PointF((float) i * 1.0F, 0.4166667F * (float) j), new PointF((float) i * 1.0F, (float) j * 0.0F), new PointF(0.6666667F * (float) i, (float) j * 0.0F)
        });
        ((List) (obj)).add(new PointF[]{
                new PointF((float) i * 0.0F, (float) j * 1.0F), new PointF((float) i * 0.0F, 0.4166667F * (float) j), new PointF((float) i * 1.0F, 0.4166667F * (float) j), new PointF((float) i * 1.0F, (float) j * 1.0F)
        });
        obj = new CollageLayout(((List) (obj)));
        collageLayoutList.add(obj);

        obj = new ArrayList();
        ((List) (obj)).add(new PointF[]{
                new PointF((float) i * 0.0F, (float) j * 1.0F), new PointF(0.5833333F * (float) i, (float) j * 1.0F), new PointF(0.5833333F * (float) i, (float) j * 0.0F), new PointF((float) i * 0.0F, (float) j * 0.0F)
        });
        ((List) (obj)).add(new PointF[]{
                new PointF((float) i * 1.0F, 0.3333333F * (float) j), new PointF((float) i * 1.0F, (float) j * 0.0F), new PointF(0.5833333F * (float) i, (float) j * 0.0F), new PointF(0.5833333F * (float) i, 0.3333333F * (float) j)
        });
        ((List) (obj)).add(new PointF[]{
                new PointF((float) i * 1.0F, 0.3333333F * (float) j), new PointF(0.5833333F * (float) i, 0.3333333F * (float) j), new PointF(0.5833333F * (float) i, 0.6666667F * (float) j), new PointF((float) i * 1.0F, 0.6666667F * (float) j)
        });
        ((List) (obj)).add(new PointF[]{
                new PointF(0.5833333F * (float) i, (float) j * 1.0F), new PointF((float) i * 1.0F, (float) j * 1.0F), new PointF((float) i * 1.0F, 0.6666667F * (float) j), new PointF(0.5833333F * (float) i, 0.6666667F * (float) j)
        });
        obj = new CollageLayout(((List) (obj)));
        collageLayoutList.add(obj);

        obj = new ArrayList();
        ((List) (obj)).add(new PointF[]{
                new PointF((float) i * 0.0F, 0.5833333F * (float) j), new PointF((float) i * 0.0F, (float) j * 0.0F), new PointF((float) i * 1.0F, (float) j * 0.0F), new PointF((float) i * 1.0F, 0.5833333F * (float) j)
        });
        ((List) (obj)).add(new PointF[]{
                new PointF((float) i * 0.0F, (float) j * 1.0F), new PointF((float) i * 0.0F, 0.5833333F * (float) j), new PointF(0.3333333F * (float) i, 0.5833333F * (float) j), new PointF(0.3333333F * (float) i, (float) j * 1.0F)
        });
        ((List) (obj)).add(new PointF[]{
                new PointF(0.3333333F * (float) i, (float) j * 1.0F), new PointF(0.6666667F * (float) i, (float) j * 1.0F), new PointF(0.6666667F * (float) i, 0.5833333F * (float) j), new PointF(0.3333333F * (float) i, 0.5833333F * (float) j)
        });
        ((List) (obj)).add(new PointF[]{
                new PointF(0.6666667F * (float) i, (float) j * 1.0F), new PointF((float) i * 1.0F, (float) j * 1.0F), new PointF((float) i * 1.0F, 0.5833333F * (float) j), new PointF(0.6666667F * (float) i, 0.5833333F * (float) j)
        });
        obj = new CollageLayout(((List) (obj)));
        collageLayoutList.add(obj);

        obj = new ArrayList();
        ((List) (obj)).add(new PointF[]{
                new PointF(0.4166667F * (float) i, (float) j * 0.0F), new PointF(0.4166667F * (float) i, (float) j * 1.0F), new PointF((float) i * 1.0F, (float) j * 1.0F), new PointF((float) i * 1.0F, (float) j * 0.0F)
        });
        ((List) (obj)).add(new PointF[]{
                new PointF((float) i * 0.0F, (float) j * 1.0F), new PointF(0.4166667F * (float) i, (float) j * 1.0F), new PointF(0.4166667F * (float) i, 0.6666667F * (float) j), new PointF((float) i * 0.0F, 0.6666667F * (float) j)
        });
        ((List) (obj)).add(new PointF[]{
                new PointF(0.4166667F * (float) i, 0.6666667F * (float) j), new PointF(0.4166667F * (float) i, 0.3333333F * (float) j), new PointF((float) i * 0.0F, 0.3333333F * (float) j), new PointF((float) i * 0.0F, 0.6666667F * (float) j)
        });
        ((List) (obj)).add(new PointF[]{
                new PointF(0.4166667F * (float) i, 0.3333333F * (float) j), new PointF(0.4166667F * (float) i, (float) j * 0.0F), new PointF((float) i * 0.0F, (float) j * 0.0F), new PointF((float) i * 0.0F, 0.3333333F * (float) j)
        });
        obj = new CollageLayout(((List) (obj)));
        collageLayoutList.add(obj);

        obj = new ArrayList();
        ((List) (obj)).add(new PointF[]{
                new PointF(0.6666667F * (float) i, 0.3333333F * (float) j), new PointF(0.6666667F * (float) i, (float) j * 0.0F), new PointF((float) i * 0.0F, (float) j * 0.0F), new PointF((float) i * 0.0F, 0.3333333F * (float) j)
        });
        ((List) (obj)).add(new PointF[]{
                new PointF((float) i * 0.0F, (float) j * 1.0F), new PointF(0.6666667F * (float) i, (float) j * 1.0F), new PointF(0.6666667F * (float) i, 0.3333333F * (float) j), new PointF((float) i * 0.0F, 0.3333333F * (float) j)
        });
        ((List) (obj)).add(new PointF[]{
                new PointF(0.6666667F * (float) i, (float) j * 1.0F), new PointF((float) i * 1.0F, (float) j * 1.0F), new PointF((float) i * 1.0F, 0.3333333F * (float) j), new PointF(0.6666667F * (float) i, 0.3333333F * (float) j)
        });
        ((List) (obj)).add(new PointF[]{
                new PointF(0.6666667F * (float) i, (float) j * 0.0F), new PointF((float) i * 1.0F, (float) j * 0.0F), new PointF((float) i * 1.0F, 0.3333333F * (float) j), new PointF(0.6666667F * (float) i, 0.3333333F * (float) j)
        });
        obj = new CollageLayout(((List) (obj)));
        collageLayoutList.add(obj);

        obj = new ArrayList();
        ((List) (obj)).add(new PointF[]{
                new PointF(0.3333333F * (float) i, 0.6666667F * (float) j), new PointF(0.3333333F * (float) i, (float) j * 0.0F), new PointF((float) i * 0.0F, (float) j * 0.0F), new PointF((float) i * 0.0F, 0.6666667F * (float) j)
        });
        ((List) (obj)).add(new PointF[]{
                new PointF((float) i * 0.0F, (float) j * 1.0F), new PointF(0.3333333F * (float) i, (float) j * 1.0F), new PointF(0.3333333F * (float) i, 0.6666667F * (float) j), new PointF((float) i * 0.0F, 0.6666667F * (float) j)
        });
        ((List) (obj)).add(new PointF[]{
                new PointF(0.3333333F * (float) i, (float) j * 1.0F), new PointF((float) i * 1.0F, (float) j * 1.0F), new PointF((float) i * 1.0F, 0.6666667F * (float) j), new PointF(0.3333333F * (float) i, 0.6666667F * (float) j)
        });
        ((List) (obj)).add(new PointF[]{
                new PointF(0.3333333F * (float) i, (float) j * 0.0F), new PointF((float) i * 1.0F, (float) j * 0.0F), new PointF((float) i * 1.0F, 0.6666667F * (float) j), new PointF(0.3333333F * (float) i, 0.6666667F * (float) j)
        });
        obj = new CollageLayout(((List) (obj)));
        collageLayoutList.add(obj);
    }

}
