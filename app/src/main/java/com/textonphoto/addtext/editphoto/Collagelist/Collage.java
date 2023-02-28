package com.textonphoto.addtext.editphoto.Collagelist;


import com.textonphoto.addtext.editphoto.R;

import java.util.ArrayList;
import java.util.List;


public class Collage {

    public static int collageIconArray[][];
    public static float scrapBookShapeScale = 1.0F;
    public List collageLayoutList;

    public Collage() {
    }

    public static Collage CreateCollage(int i, int j, int k, boolean flag) {
        if (flag) {
            return createScrapLaout(i, j, k);
        }
        if (i == 1) {
            return new CollageOne(j, k);
        }
        if (i == 2) {
            return new CollageTwo(j, k);
        }
        if (i == 3) {
            return new CollageThree(j, k);
        }
        if (i == 4) {
            return new CollageFour(j, k);
        }
        if (i == 5) {
            return new CollageFive(j, k);
        }
        if (i == 6) {
            return new CollageSix(j, k);
        }
        if (i == 7) {
            return new CollageSeven(j, k);
        }
        if (i == 8) {
            return new CollageEight(j, k);
        }
        if (i == 9) {
            return new CollageNine(j, k);
        } else {
            return null;
        }
    }

    public static Collage createScrapLaout(int i, int j, int k) {
        Collage collage = new Collage();
        collage.collageLayoutList = new ArrayList();
        CollageScrapBook collagescrapbook = new CollageScrapBook(j, k);
        if (i == 1) {
            collage.collageLayoutList.add((CollageLayout) collagescrapbook.collageLayoutList.get(0));
            scrapBookShapeScale = 0.7F;
        }
        if (i == 2) {
            if (k > j) {
                collage.collageLayoutList.add((CollageLayout) collagescrapbook.collageLayoutList.get(2));
            } else {
                collage.collageLayoutList.add((CollageLayout) collagescrapbook.collageLayoutList.get(1));
            }
            scrapBookShapeScale = 1.0F;
        } else {
            if (i == 3) {
                collage.collageLayoutList.add((CollageLayout) collagescrapbook.collageLayoutList.get(3));
                scrapBookShapeScale = 0.95F;
                return collage;
            }
            if (i == 4) {
                collage.collageLayoutList.add((CollageLayout) collagescrapbook.collageLayoutList.get(4));
                scrapBookShapeScale = 1.0F;
                return collage;
            }
            if (i == 5) {
                collage.collageLayoutList.add((CollageLayout) collagescrapbook.collageLayoutList.get(5));
                scrapBookShapeScale = 0.95F;
                return collage;
            }
            if (i == 6) {
                if (k > j) {
                    collage.collageLayoutList.add((CollageLayout) collagescrapbook.collageLayoutList.get(6));
                } else {
                    collage.collageLayoutList.add((CollageLayout) collagescrapbook.collageLayoutList.get(7));
                }
                scrapBookShapeScale = 1.0F;
                return collage;
            }
            if (i == 7) {
                if (k > j) {
                    collage.collageLayoutList.add((CollageLayout) collagescrapbook.collageLayoutList.get(8));
                } else {
                    collage.collageLayoutList.add((CollageLayout) collagescrapbook.collageLayoutList.get(9));
                }
                scrapBookShapeScale = 1.0F;
                return collage;
            }
            if (i == 8) {
                collage.collageLayoutList.add((CollageLayout) collagescrapbook.collageLayoutList.get(10));
                scrapBookShapeScale = 1.0F;
                return collage;
            }
            if (i == 9) {
                collage.collageLayoutList.add((CollageLayout) collagescrapbook.collageLayoutList.get(11));
                scrapBookShapeScale = 1.0F;
                return collage;
            }
            if (i == 10) {
                collage.collageLayoutList.add((CollageLayout) collagescrapbook.collageLayoutList.get(12));
                scrapBookShapeScale = 1.0F;
                return collage;
            }
        }
        return collage;
    }

    //doi layout
    static {
        int ai1[] = {
                R.drawable.collage_1_0, R.drawable.collage_1_1, R.drawable.collage_1_2, R.drawable.collage_1_3,
                R.drawable.collage_1_4, R.drawable.collage_1_5, R.drawable.collage_1_6, R.drawable.collage_1_7,
                R.drawable.collage_1_8, R.drawable.collage_1_9, R.drawable.collage_1_10};

        int ai2[] = {R.drawable.collage_2_0, R.drawable.collage_2_1, R.drawable.collage_2_2, R.drawable.collage_2_3, R.drawable.collage_2_4,
                R.drawable.collage_2_5, R.drawable.collage_2_6, R.drawable.collage_2_7};

        int ai3[] = {
                R.drawable.collage_3_0, R.drawable.collage_3_1, R.drawable.collage_3_2, R.drawable.collage_3_3, R.drawable.collage_3_4,
                R.drawable.collage_3_5, R.drawable.collage_3_6, R.drawable.collage_3_7, R.drawable.collage_3_8};

        int ai4[] = {R.drawable.collage_4_0, R.drawable.collage_4_1, R.drawable.collage_4_2, R.drawable.collage_4_3, R.drawable.collage_4_4,
                R.drawable.collage_4_5, R.drawable.collage_4_6, R.drawable.collage_4_7};

        int ai5[] = {
                R.drawable.collage_5_0, R.drawable.collage_5_1, R.drawable.collage_5_2, R.drawable.collage_5_3, R.drawable.collage_5_4,
                R.drawable.collage_5_5, R.drawable.collage_5_6, R.drawable.collage_5_7};

        int ai6[] = {R.drawable.collage_6_0, R.drawable.collage_6_1, R.drawable.collage_6_2, R.drawable.collage_6_3, R.drawable.collage_6_4,
                R.drawable.collage_6_5, R.drawable.collage_6_6, R.drawable.collage_6_7};

        int ai7[] = {R.drawable.collage_7_0, R.drawable.collage_7_1, R.drawable.collage_7_2, R.drawable.collage_7_3, R.drawable.collage_7_4,
                R.drawable.collage_7_5, R.drawable.collage_7_6, R.drawable.collage_7_7};

        int ai8[] = {R.drawable.collage_8_0, R.drawable.collage_8_1, R.drawable.collage_8_2, R.drawable.collage_8_3, R.drawable.collage_8_4,
                R.drawable.collage_8_5, R.drawable.collage_8_6, R.drawable.collage_8_7};

        int ai9[] = {R.drawable.collage_9_0, R.drawable.collage_9_1, R.drawable.collage_9_2, R.drawable.collage_9_3, R.drawable.collage_9_4,
                R.drawable.collage_9_5, R.drawable.collage_9_6, R.drawable.collage_9_7};

        collageIconArray = (new int[][]{
                ai1, ai2, ai3, ai4, ai5, ai6, ai7, ai8, ai9
        });
    }
}
