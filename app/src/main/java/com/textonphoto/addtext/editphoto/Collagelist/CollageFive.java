package com.textonphoto.addtext.editphoto.Collagelist;

import android.graphics.PointF;

import java.util.ArrayList;
import java.util.List;

public class CollageFive extends Collage {

    public static int shapeCount = 5;

    public CollageFive(int i, int j) {
        collageLayoutList = new ArrayList();
        Object obj = new ArrayList();
        ((List) (obj)).add(new PointF[]{
                new PointF((float) i * 0.0F, (float) j * 1.0F), new PointF((float) i * 0.0F, 0.7083333F * (float) j), new PointF(0.7083333F * (float) i, 0.7083333F * (float) j), new PointF(0.7083333F * (float) i, (float) j * 1.0F)
        });
        ((List) (obj)).add(new PointF[]{
                new PointF((float) i * 0.0F, (float) j * 0.0F), new PointF(0.2916667F * (float) i, (float) j * 0.0F), new PointF(0.2916667F * (float) i, 0.7083333F * (float) j), new PointF((float) i * 0.0F, 0.7083333F * (float) j)
        });
        ((List) (obj)).add(new PointF[]{
                new PointF((float) i * 1.0F, (float) j * 0.0F), new PointF((float) i * 1.0F, 0.2916667F * (float) j), new PointF(0.2916667F * (float) i, 0.2916667F * (float) j), new PointF(0.2916667F * (float) i, (float) j * 0.0F)
        });
        ((List) (obj)).add(new PointF[]{
                new PointF(0.7083333F * (float) i, (float) j * 1.0F), new PointF((float) i * 1.0F, (float) j * 1.0F), new PointF((float) i * 1.0F, 0.2916667F * (float) j), new PointF(0.7083333F * (float) i, 0.2916667F * (float) j)
        });
        ((List) (obj)).add(new PointF[]{
                new PointF(0.2916667F * (float) i, 0.7083333F * (float) j), new PointF(0.2916667F * (float) i, 0.2916667F * (float) j), new PointF(0.7083333F * (float) i, 0.2916667F * (float) j), new PointF(0.7083333F * (float) i, 0.7083333F * (float) j)
        });
        obj = new CollageLayout(((List) (obj)));
        collageLayoutList.add(obj);

        obj = new ArrayList();
        ((List) (obj)).add(new PointF[]{
                new PointF((float) i * 0.0F, (float) j * 1.0F), new PointF((float) i * 0.5F, (float) j * 1.0F), new PointF((float) i * 0.5F, (float) j * 0.5F), new PointF((float) i * 0.0F, (float) j * 0.5F)
        });
        ((List) (obj)).add(new PointF[]{
                new PointF((float) i * 0.0F, (float) j * 0.0F), new PointF((float) i * 0.0F, (float) j * 0.5F), new PointF((float) i * 0.5F, (float) j * 0.5F), new PointF((float) i * 0.5F, (float) j * 0.0F)
        });
        ((List) (obj)).add(new PointF[]{
                new PointF((float) i * 0.5F, (float) j * 1.0F), new PointF((float) i * 1.0F, (float) j * 1.0F), new PointF((float) i * 1.0F, 0.6666667F * (float) j), new PointF((float) i * 0.5F, 0.6666667F * (float) j)
        });
        ((List) (obj)).add(new PointF[]{
                new PointF((float) i * 1.0F, (float) j * 0.0F), new PointF((float) i * 0.5F, (float) j * 0.0F), new PointF((float) i * 0.5F, 0.3333333F * (float) j), new PointF((float) i * 1.0F, 0.3333333F * (float) j)
        });
        ((List) (obj)).add(new PointF[]{
                new PointF((float) i * 1.0F, 0.6666667F * (float) j), new PointF((float) i * 1.0F, 0.3333333F * (float) j), new PointF((float) i * 0.5F, 0.3333333F * (float) j), new PointF((float) i * 0.5F, 0.6666667F * (float) j)
        });
        obj = new CollageLayout(((List) (obj)));
        collageLayoutList.add(obj);

        obj = new ArrayList();
        ((List) (obj)).add(new PointF[]{
                new PointF((float) i * 0.0F, (float) j * 1.0F), new PointF((float) i * 0.0F, (float) j * 0.5F), new PointF((float) i * 0.5F, (float) j * 0.5F), new PointF((float) i * 0.5F, (float) j * 1.0F)
        });
        ((List) (obj)).add(new PointF[]{
                new PointF((float) i * 1.0F, (float) j * 1.0F), new PointF((float) i * 1.0F, (float) j * 0.5F), new PointF((float) i * 0.5F, (float) j * 0.5F), new PointF((float) i * 0.5F, (float) j * 1.0F)
        });
        ((List) (obj)).add(new PointF[]{
                new PointF((float) i * 0.0F, (float) j * 0.0F), new PointF((float) i * 0.0F, (float) j * 0.5F), new PointF(0.3333333F * (float) i, (float) j * 0.5F), new PointF(0.3333333F * (float) i, (float) j * 0.0F)
        });
        ((List) (obj)).add(new PointF[]{
                new PointF(0.6666667F * (float) i, (float) j * 0.0F), new PointF(0.3333333F * (float) i, (float) j * 0.0F), new PointF(0.3333333F * (float) i, (float) j * 0.5F), new PointF(0.6666667F * (float) i, (float) j * 0.5F)
        });
        ((List) (obj)).add(new PointF[]{
                new PointF((float) i * 1.0F, (float) j * 0.0F), new PointF(0.6666667F * (float) i, (float) j * 0.0F), new PointF(0.6666667F * (float) i, (float) j * 0.5F), new PointF((float) i * 1.0F, (float) j * 0.5F)
        });
        obj = new CollageLayout(((List) (obj)));
        collageLayoutList.add(obj);

        obj = new ArrayList();
        ((List) (obj)).add(new PointF[]{
                new PointF((float) i * 0.0F, (float) j * 1.0F), new PointF((float) i * 0.0F, 0.6666667F * (float) j), new PointF(0.3333333F * (float) i, 0.6666667F * (float) j), new PointF(0.3333333F * (float) i, (float) j * 1.0F)
        });
        ((List) (obj)).add(new PointF[]{
                new PointF((float) i * 1.0F, (float) j * 1.0F), new PointF((float) i * 1.0F, 0.6666667F * (float) j), new PointF(0.3333333F * (float) i, 0.6666667F * (float) j), new PointF(0.3333333F * (float) i, (float) j * 1.0F)
        });
        ((List) (obj)).add(new PointF[]{
                new PointF((float) i * 0.0F, 0.3333333F * (float) j), new PointF((float) i * 1.0F, 0.3333333F * (float) j), new PointF((float) i * 1.0F, 0.6666667F * (float) j), new PointF((float) i * 0.0F, 0.6666667F * (float) j)
        });
        ((List) (obj)).add(new PointF[]{
                new PointF((float) i * 0.0F, 0.3333333F * (float) j), new PointF((float) i * 0.0F, (float) j * 0.0F), new PointF(0.6666667F * (float) i, (float) j * 0.0F), new PointF(0.6666667F * (float) i, 0.3333333F * (float) j)
        });
        ((List) (obj)).add(new PointF[]{
                new PointF(0.6666667F * (float) i, (float) j * 0.0F), new PointF((float) i * 1.0F, (float) j * 0.0F), new PointF((float) i * 1.0F, 0.3333333F * (float) j), new PointF(0.6666667F * (float) i, 0.3333333F * (float) j)
        });
        obj = new CollageLayout(((List) (obj)));
        collageLayoutList.add(obj);

        obj = new ArrayList();
        ((List) (obj)).add(new PointF[]{
                new PointF((float) i * 0.0F, (float) j * 1.0F), new PointF((float) i * 0.0F, 0.6666667F * (float) j), new PointF(0.6666667F * (float) i, 0.6666667F * (float) j), new PointF(0.6666667F * (float) i, (float) j * 1.0F)
        });
        ((List) (obj)).add(new PointF[]{
                new PointF((float) i * 1.0F, (float) j * 1.0F), new PointF((float) i * 1.0F, 0.6666667F * (float) j), new PointF(0.6666667F * (float) i, 0.6666667F * (float) j), new PointF(0.6666667F * (float) i, (float) j * 1.0F)
        });
        ((List) (obj)).add(new PointF[]{
                new PointF((float) i * 0.0F, 0.3333333F * (float) j), new PointF((float) i * 1.0F, 0.3333333F * (float) j), new PointF((float) i * 1.0F, 0.6666667F * (float) j), new PointF((float) i * 0.0F, 0.6666667F * (float) j)
        });
        ((List) (obj)).add(new PointF[]{
                new PointF((float) i * 0.0F, 0.3333333F * (float) j), new PointF((float) i * 0.0F, (float) j * 0.0F), new PointF(0.3333333F * (float) i, (float) j * 0.0F), new PointF(0.3333333F * (float) i, 0.3333333F * (float) j)
        });
        ((List) (obj)).add(new PointF[]{
                new PointF(0.3333333F * (float) i, (float) j * 0.0F), new PointF((float) i * 1.0F, (float) j * 0.0F), new PointF((float) i * 1.0F, 0.3333333F * (float) j), new PointF(0.3333333F * (float) i, 0.3333333F * (float) j)
        });
        obj = new CollageLayout(((List) (obj)));
        collageLayoutList.add(obj);

        obj = new ArrayList();
        ((List) (obj)).add(new PointF[]{
                new PointF((float) i * 0.0F, (float) j * 1.0F), new PointF((float) i * 0.0F, (float) j * 0.0F), new PointF(0.5833333F * (float) i, (float) j * 0.0F), new PointF(0.5833333F * (float) i, (float) j * 1.0F)
        });
        ((List) (obj)).add(new PointF[]{
                new PointF((float) i * 1.0F, (float) j * 0.0F), new PointF(0.5833333F * (float) i, (float) j * 0.0F), new PointF(0.5833333F * (float) i, 0.25F * (float) j), new PointF((float) i * 1.0F, 0.25F * (float) j)
        });
        ((List) (obj)).add(new PointF[]{
                new PointF(0.5833333F * (float) i, 0.25F * (float) j), new PointF(0.5833333F * (float) i, (float) j * 0.5F), new PointF((float) i * 1.0F, (float) j * 0.5F), new PointF((float) i * 1.0F, 0.25F * (float) j)
        });
        ((List) (obj)).add(new PointF[]{
                new PointF(0.5833333F * (float) i, (float) j * 0.5F), new PointF(0.5833333F * (float) i, 0.75F * (float) j), new PointF((float) i * 1.0F, 0.75F * (float) j), new PointF((float) i * 1.0F, (float) j * 0.5F)
        });
        ((List) (obj)).add(new PointF[]{
                new PointF((float) i * 1.0F, (float) j * 1.0F), new PointF((float) i * 1.0F, 0.75F * (float) j), new PointF(0.5833333F * (float) i, 0.75F * (float) j), new PointF(0.5833333F * (float) i, (float) j * 1.0F)
        });
        obj = new CollageLayout(((List) (obj)));
        collageLayoutList.add(obj);

        obj = new ArrayList();
        ((List) (obj)).add(new PointF[]{
                new PointF((float) i * 0.0F, 0.3333333F * (float) j), new PointF((float) i * 0.0F, (float) j * 0.0F), new PointF((float) i * 1.0F, (float) j * 0.0F), new PointF((float) i * 1.0F, 0.3333333F * (float) j)
        });
        ((List) (obj)).add(new PointF[]{
                new PointF((float) i * 0.0F, 0.3333333F * (float) j), new PointF((float) i * 0.0F, 0.6666667F * (float) j), new PointF((float) i * 0.5F, 0.6666667F * (float) j), new PointF((float) i * 0.5F, 0.3333333F * (float) j)
        });
        ((List) (obj)).add(new PointF[]{
                new PointF((float) i * 0.5F, 0.6666667F * (float) j), new PointF((float) i * 1.0F, 0.6666667F * (float) j), new PointF((float) i * 1.0F, 0.3333333F * (float) j), new PointF((float) i * 0.5F, 0.3333333F * (float) j)
        });
        ((List) (obj)).add(new PointF[]{
                new PointF((float) i * 0.0F, (float) j * 1.0F), new PointF((float) i * 0.5F, (float) j * 1.0F), new PointF((float) i * 0.5F, 0.6666667F * (float) j), new PointF((float) i * 0.0F, 0.6666667F * (float) j)
        });
        ((List) (obj)).add(new PointF[]{
                new PointF((float) i * 0.5F, (float) j * 1.0F), new PointF((float) i * 1.0F, (float) j * 1.0F), new PointF((float) i * 1.0F, 0.6666667F * (float) j), new PointF((float) i * 0.5F, 0.6666667F * (float) j)
        });
        obj = new CollageLayout(((List) (obj)));
        collageLayoutList.add(obj);

        obj = new ArrayList();
        ((List) (obj)).add(new PointF[]{
                new PointF((float) i * 0.0F, (float) j * 0.0F), new PointF((float) i * 0.0F, 0.3333333F * (float) j), new PointF((float) i * 0.5F, 0.3333333F * (float) j), new PointF((float) i * 0.5F, (float) j * 0.0F)
        });
        ((List) (obj)).add(new PointF[]{
                new PointF((float) i * 0.5F, 0.3333333F * (float) j), new PointF((float) i * 1.0F, 0.3333333F * (float) j), new PointF((float) i * 1.0F, (float) j * 0.0F), new PointF((float) i * 0.5F, (float) j * 0.0F)
        });
        ((List) (obj)).add(new PointF[]{
                new PointF((float) i * 0.0F, 0.6666667F * (float) j), new PointF((float) i * 0.5F, 0.6666667F * (float) j), new PointF((float) i * 0.5F, 0.3333333F * (float) j), new PointF((float) i * 0.0F, 0.3333333F * (float) j)
        });
        ((List) (obj)).add(new PointF[]{
                new PointF((float) i * 0.5F, 0.6666667F * (float) j), new PointF((float) i * 1.0F, 0.6666667F * (float) j), new PointF((float) i * 1.0F, 0.3333333F * (float) j), new PointF((float) i * 0.5F, 0.3333333F * (float) j)
        });
        ((List) (obj)).add(new PointF[]{
                new PointF((float) i * 0.0F, (float) j * 1.0F), new PointF((float) i * 0.0F, 0.6666667F * (float) j), new PointF((float) i * 1.0F, 0.6666667F * (float) j), new PointF((float) i * 1.0F, (float) j * 1.0F)
        });
        obj = new CollageLayout(((List) (obj)));
        collageLayoutList.add(obj);
    }

}
