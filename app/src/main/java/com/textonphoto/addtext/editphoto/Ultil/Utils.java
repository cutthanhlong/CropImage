package com.textonphoto.addtext.editphoto.Ultil;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.AssetFileDescriptor;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.BitmapFactory;
import android.graphics.BitmapFactory.Options;
import android.graphics.Matrix;
import android.media.ExifInterface;
import android.net.Uri;
import android.os.Build.VERSION;
import android.os.Debug;
import android.provider.MediaStore.Images.Media;
import android.util.Log;


import com.textonphoto.addtext.editphoto.R;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class Utils {
    private static final String TAG;
    private static final float limitDivider = 30.0f;
    private static final float limitDividerGinger = 160.0f;
    public static final int[] patternResIdList;
    public static final int[][] patternResIdList2;

    private static final int[][] r0;

    static {
        TAG = Utils.class.getSimpleName();
        patternResIdList = new int[]{R.drawable.no_pattern, R.drawable.color_picker, R.drawable.pattern_icon_01, R.drawable.pattern_icon_02
                , R.drawable.pattern_icon_03, R.drawable.pattern_icon_04, R.drawable.pattern_icon_05, R.drawable.pattern_icon_06};
        r0 = new int[6][];
        r0[0] = new int[]{R.drawable.pattern_01, R.drawable.pattern_02, R.drawable.pattern_03, R.drawable.pattern_04, R.drawable.pattern_05, R.drawable.pattern_06};
        r0[1] = new int[]{R.drawable.pattern_07, R.drawable.pattern_08, R.drawable.pattern_09, R.drawable.pattern_10, R.drawable.pattern_11, R.drawable.pattern_12};
        r0[2] = new int[]{R.drawable.pattern_13, R.drawable.pattern_14, R.drawable.pattern_15, R.drawable.pattern_16, R.drawable.pattern_17, R.drawable.pattern_18};
        r0[3] = new int[]{R.drawable.pattern_19, R.drawable.pattern_20, R.drawable.pattern_21, R.drawable.pattern_22, R.drawable.pattern_23, R.drawable.pattern_24};
        r0[4] = new int[]{R.drawable.pattern_25, R.drawable.pattern_26, R.drawable.pattern_27, R.drawable.pattern_28, R.drawable.pattern_29, R.drawable.pattern_30};
        r0[5] = new int[]{R.drawable.pattern_31, R.drawable.pattern_32, R.drawable.pattern_33, R.drawable.pattern_34, R.drawable.pattern_35, R.drawable.pattern_36};
        patternResIdList2 = r0;
    }

    @SuppressLint({"NewApi"})
    public static Bitmap getScaledBitmapFromId(Context context, File imageID, int orientation, int requiredSize, boolean isScrapBook) {
        Uri uri = Uri.fromFile(imageID);
        Options boundsOption = new Options();
        boundsOption.inJustDecodeBounds = true;
        AssetFileDescriptor fileDescriptor = null;
        try {
            fileDescriptor = context.getContentResolver().openAssetFileDescriptor(uri, "r");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        if (fileDescriptor == null) {
            return null;
        }
        BitmapFactory.decodeFileDescriptor(fileDescriptor.getFileDescriptor(), null, boundsOption);
        Options options = new Options();
        options.inSampleSize = calculateInSampleSize(boundsOption, requiredSize, requiredSize);
        if (VERSION.SDK_INT >= 11 && isScrapBook) {
            options.inMutable = true;
        }
        Bitmap actuallyUsableBitmap = BitmapFactory.decodeFileDescriptor(fileDescriptor.getFileDescriptor(), null, options);
        if (actuallyUsableBitmap == null) {
            return null;
        }
        Bitmap bitmap = rotateImage(actuallyUsableBitmap, orientation);
        if (!(bitmap == null || actuallyUsableBitmap == bitmap)) {
            actuallyUsableBitmap.recycle();
        }
        if (bitmap.isMutable() || !isScrapBook) {
            return bitmap;
        }
        Log.e(TAG, "bitmap is not mutable");
        Bitmap mutableBitmap = bitmap.copy(Config.ARGB_8888, true);
        if (mutableBitmap != bitmap) {
            bitmap.recycle();
        }
        return mutableBitmap;
    }

    @SuppressLint({"NewApi"})
    public static Bitmap decodeFile(String path, int requiredSize, boolean isScrapBook) {
        try {
            File f = new File(path);
            Options boundsOption = new Options();
            boundsOption.inJustDecodeBounds = true;
            BitmapFactory.decodeStream(new FileInputStream(f), null, boundsOption);
            Options o2 = new Options();
            if (VERSION.SDK_INT >= 11 && isScrapBook) {
                o2.inMutable = true;
            }
            o2.inSampleSize = calculateInSampleSize(boundsOption, requiredSize, requiredSize);
            Bitmap bitmap = BitmapFactory.decodeStream(new FileInputStream(f), null, o2);
            ExifInterface exif = null;
            try {
                exif = new ExifInterface(path);
            } catch (IOException e) {
                e.printStackTrace();
            }
            bitmap = rotateImage(bitmap, exif.getAttributeInt("Orientation", 0));
            if (bitmap.isMutable()) {
                return bitmap;
            }
            Bitmap mutableBitmap = bitmap.copy(Config.ARGB_8888, true);
            if (mutableBitmap != bitmap) {
                bitmap.recycle();
            }
            return mutableBitmap;
        } catch (FileNotFoundException e2) {
            return null;
        }
    }

    private static Bitmap rotateImage(Bitmap bitmap, int orientation) {
        Bitmap resultBitmap = bitmap;
        Matrix matrix = new Matrix();
        if (orientation == 90) {
            matrix.postRotate(90.0f);
        } else if (orientation == 180) {
            matrix.postRotate(180.0f);
        } else if (orientation == 270) {
            matrix.postRotate(270.0f);
        }
        if (orientation == 0) {
            return resultBitmap;
        }
        return Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), matrix, true);
    }

    public static int calculateInSampleSize(Options options, int reqWidth, int reqHeight) {
        int height = options.outHeight;
        int width = options.outWidth;
        int inSampleSize = 1;
        if (height > reqHeight || width > reqWidth) {
            int halfHeight = height / 2;
            int halfWidth = width / 2;
            while (true) {
                if (halfHeight / inSampleSize <= reqHeight && halfWidth / inSampleSize <= reqWidth) {
                    break;
                }
                inSampleSize *= 2;
            }
        }
        return inSampleSize;
    }

    public static double getLeftSizeOfMemory() {
        double totalSize = (double) Runtime.getRuntime().maxMemory();
        double heapAllocated = (double) Runtime.getRuntime().totalMemory();
        return (totalSize - (heapAllocated - (double) Runtime.getRuntime().freeMemory())) - (double) Debug.getNativeHeapAllocatedSize();
    }

    public static int maxSizeForDimension(Context context, int count, float upperLimit) {
        float divider = limitDivider;
        if (VERSION.SDK_INT <= 11) {
            divider = limitDividerGinger;
        }
        Log.e(TAG, "divider = " + divider);
        int maxSize = (int) Math.sqrt(getLeftSizeOfMemory() / ((double) (((float) count) * divider)));
        if (maxSize <= 0) {
            maxSize = getDefaultLimit(count, upperLimit);
        }
        return Math.min(maxSize, getDefaultLimit(count, upperLimit));
    }

    public static int maxSizeForSave(Context context, float upperLimit) {
        float divider = limitDivider;
        if (VERSION.SDK_INT <= 11) {
            divider = limitDividerGinger;
        }
        Log.e(TAG, "divider = " + divider);
        int maxSize = (int) Math.sqrt(getLeftSizeOfMemory() / ((double) divider));
        if (maxSize > 0) {
            return (int) Math.min((float) maxSize, upperLimit);
        }
        return (int) upperLimit;
    }

    private static int getDefaultLimit(int count, float upperLimit) {
        int limit = (int) (((double) upperLimit) / Math.sqrt((double) count));
        Log.e(TAG, "limit = " + limit);
        return limit;
    }

    public static float pointToAngle(float x, float y, float centerX, float centerY) {
        float degree = 0.0f;
        if (x >= centerX && y < centerY) {
            degree = (float) (270.0d + Math.toDegrees(Math.atan(((double) (x - centerX)) / ((double) (centerY - y)))));
        } else if (x > centerX && y >= centerY) {
            degree = (float) Math.toDegrees(Math.atan(((double) (y - centerY)) / ((double) (x - centerX))));
        } else if (x <= centerX && y > centerY) {
            degree = (float) (90.0d + Math.toDegrees(Math.atan(((double) (centerX - x)) / ((double) (y - centerY)))));
        } else if (x < centerX && y <= centerY) {
            degree = (float) (((int) Math.toDegrees(Math.atan(((double) (centerY - y)) / ((double) (centerX - x))))) + 180);
        }
        if (degree < -180.0f) {
            degree += 360.0f;
        }
        if (degree > 180.0f) {
            return degree - 360.0f;
        }
        return degree;
    }

    public static int maxSizeForSave() {
        int maxSize = (int) Math.sqrt(getLeftSizeOfMemory() / 40.0d);
        if (maxSize > 1080) {
            return 1080;
        }
        return maxSize;
    }

}
