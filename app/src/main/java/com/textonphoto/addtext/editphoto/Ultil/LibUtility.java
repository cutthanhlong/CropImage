package com.textonphoto.addtext.editphoto.Ultil;

import android.os.Debug;

import com.textonphoto.addtext.editphoto.R;


public class LibUtility {
    public static int MODE_MULTIPLY;
    public static int MODE_NONE;
    public static int MODE_OVERLAY;
    public static int MODE_SCREEN;
    public static int[] borderRes;
    public static int[] borderResThumb;
    public static int[] filterResThumb;
    public static int[] overlayDrawableList;
    public static int[] overlayResThumb;
    public static int[] textureModes;
    public static int[] textureRes;
    public static int[] textureResThumb;


    public interface ExcludeTabListener {
        void exclude();
    }

    public interface FooterVisibilityListener {
        void setVisibility();
    }

    static {
        borderRes = new int[]{-1, R.drawable.border_0, R.drawable.border_1, R.drawable.border_2, R.drawable.border_3, R.drawable.border_4, R.drawable.border_5, R.drawable.border_6, R.drawable.border_7, R.drawable.border_8, R.drawable.border_9, R.drawable.border_10, R.drawable.border_11, R.drawable.border_12, R.drawable.border_13, R.drawable.border_14, R.drawable.border_15, R.drawable.border_16, R.drawable.border_17, R.drawable.border_18, R.drawable.border_19, R.drawable.border_20, R.drawable.border_21, R.drawable.border_22, R.drawable.border_23, R.drawable.border_24, R.drawable.border_25, R.drawable.border_26, R.drawable.border_27, R.drawable.border_28, R.drawable.border_29, R.drawable.border_30, R.drawable.border_31, R.drawable.border_32, R.drawable.border_33, R.drawable.border_34, R.drawable.border_35, R.drawable.border_36, R.drawable.border_37, R.drawable.border_38, R.drawable.border_39, R.drawable.border_40, R.drawable.border_41};
        borderResThumb = new int[]{R.drawable.effect_thumb_0, R.drawable.border_thumb_0, R.drawable.border_thumb_1, R.drawable.border_thumb_2, R.drawable.border_thumb_3, R.drawable.border_thumb_4, R.drawable.border_thumb_5, R.drawable.border_thumb_6, R.drawable.border_thumb_7, R.drawable.border_thumb_8, R.drawable.border_thumb_9, R.drawable.border_thumb_10, R.drawable.border_thumb_11, R.drawable.border_thumb_12, R.drawable.border_thumb_13, R.drawable.border_thumb_14, R.drawable.border_thumb_15, R.drawable.border_thumb_16, R.drawable.border_thumb_17, R.drawable.border_thumb_18, R.drawable.border_thumb_19, R.drawable.border_thumb_20, R.drawable.border_thumb_21, R.drawable.border_thumb_22, R.drawable.border_thumb_23, R.drawable.border_thumb_24, R.drawable.border_thumb_25, R.drawable.border_thumb_26, R.drawable.border_thumb_27, R.drawable.border_thumb_28, R.drawable.border_thumb_29, R.drawable.border_thumb_30, R.drawable.border_thumb_31, R.drawable.border_thumb_32, R.drawable.border_thumb_33, R.drawable.border_thumb_34, R.drawable.border_thumb_35, R.drawable.border_thumb_36, R.drawable.border_thumb_37, R.drawable.border_thumb_38, R.drawable.border_thumb_39, R.drawable.border_thumb_40, R.drawable.border_thumb_41};
        overlayDrawableList = new int[]{-1, R.drawable.overlay_thumb_00, R.drawable.overlay_thumb_01, R.drawable.overlay_thumb_02, R.drawable.overlay_thumb_03, R.drawable.overlay_thumb_04, R.drawable.overlay_thumb_05};
        overlayResThumb = new int[]{R.drawable.effect_thumb_0, R.drawable.overlay_00, R.drawable.overlay_01, R.drawable.overlay_02, R.drawable.overlay_03, R.drawable.overlay_04, R.drawable.overlay_05};
        textureRes = new int[]{-1, R.drawable.texture_0, R.drawable.texture_1, R.drawable.texture_2, R.drawable.texture_3, R.drawable.texture_4, R.drawable.texture_5};
        textureResThumb = new int[]{R.drawable.effect_thumb_0, R.drawable.texture_thumb_0, R.drawable.texture_thumb_1, R.drawable.texture_thumb_2, R.drawable.texture_thumb_3, R.drawable.texture_thumb_4, R.drawable.texture_thumb_5};
        MODE_NONE = -1;
        MODE_SCREEN = 3;
        MODE_MULTIPLY = 1;
        MODE_OVERLAY = 2;
        textureModes = new int[]{MODE_NONE, MODE_OVERLAY, MODE_SCREEN, MODE_OVERLAY, MODE_OVERLAY, MODE_SCREEN, MODE_SCREEN, MODE_OVERLAY, MODE_OVERLAY, MODE_OVERLAY, MODE_OVERLAY, MODE_OVERLAY, MODE_SCREEN, MODE_SCREEN, MODE_SCREEN, MODE_OVERLAY, MODE_SCREEN, MODE_SCREEN, MODE_SCREEN, MODE_OVERLAY, MODE_MULTIPLY, MODE_MULTIPLY, MODE_SCREEN, MODE_OVERLAY};
        filterResThumb = new int[]{R.drawable.effect_thumb_0, R.drawable.effect_thumb_1, R.drawable.effect_thumb_2, R.drawable.effect_thumb_3,
                R.drawable.effect_thumb_4, R.drawable.effect_thumb_5, R.drawable.effect_thumb_6, R.drawable.effect_thumb_7, R.drawable.effect_thumb_8
                , R.drawable.effect_thumb_9, R.drawable.effect_thumb_10, R.drawable.effect_thumb_11, R.drawable.effect_thumb_12, R.drawable.effect_thumb_13
                , R.drawable.effect_thumb_14, R.drawable.effect_thumb_15, R.drawable.effect_thumb_16, R.drawable.effect_thumb_17, R.drawable.effect_thumb_18
                , R.drawable.effect_thumb_19, R.drawable.effect_thumb_20, R.drawable.effect_thumb_21};
    }

    public static double getLeftSizeOfMemory() {
        double totalSize = (double) Runtime.getRuntime().maxMemory();
        double heapAllocated = (double) Runtime.getRuntime().totalMemory();
        return (totalSize - (heapAllocated - (double) Runtime.getRuntime().freeMemory())) - (double) Debug.getNativeHeapAllocatedSize();
    }
}
