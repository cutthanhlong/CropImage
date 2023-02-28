package com.pic.collage.lib;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.DashPathEffect;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PointF;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Typeface;
import android.os.SystemClock;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.widget.FrameLayout;
import android.widget.Toast;

import androidx.annotation.IntDef;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.core.content.res.ResourcesCompat;
import androidx.core.view.MotionEventCompat;
import androidx.core.view.ViewCompat;

import java.io.File;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Sticker View
 *
 * @author wupanjie
 */
public class StickerView extends FrameLayout {

    private final boolean showIcons;
    private final boolean showBorder;
    private final boolean bringToFrontCurrentSticker;
    private String fontsDirectoryInAssets;
    private final Paint borderPaintSticker;



    @IntDef({
            ActionMode.NONE, ActionMode.DRAG, ActionMode.ZOOM_WITH_TWO_FINGER, ActionMode.ICON,
            ActionMode.CLICK
    })
    @Retention(RetentionPolicy.SOURCE)
    protected @interface ActionMode {
        int NONE = 0;
        int DRAG = 1;
        int ZOOM_WITH_TWO_FINGER = 2;
        int ICON = 3;
        int CLICK = 4;
    }

    @IntDef(flag = true, value = {FLIP_HORIZONTALLY, FLIP_VERTICALLY})
    @Retention(RetentionPolicy.SOURCE)
    protected @interface Flip {
    }

    private static final String TAG = "StickerView";

    private static final int DEFAULT_MIN_CLICK_DELAY_TIME = 200;

    public static final int FLIP_HORIZONTALLY = 1;
    public static final int FLIP_VERTICALLY = 1 << 1;

    public static  List<Sticker> stickers ;
    private OnStickerViewListener onStickerViewListener;

    private final List<BitmapStickerIcon> icons;

    private final Paint borderPaint;
    private final RectF stickerRect ;

    private final Matrix sizeMatrix;
    private final Matrix downMatrix;
    private final Matrix moveMatrix ;

    // region storing variables
    private final float[] bitmapPoints ;
    private final float[] bounds;
    private final float[] point;
    private final PointF currentCenterPoint ;
    private final float[] tmp ;
    private PointF midPoint;
    // endregion
    private final int touchSlop;

    private BitmapStickerIcon currentIcon;
    //the first point down position
    private float downX;
    private float downY;

    private float oldDistance = 0f;
    private float oldRotation = 0f;

    @ActionMode
    private int currentMode;

    private Sticker handlingSticker;

    private boolean locked;
    private boolean constrained;

    private OnStickerOperationListener onStickerOperationListener;

    private long lastClickTime = 0;
    private int minClickDelayTime = DEFAULT_MIN_CLICK_DELAY_TIME;
    private boolean isReplaceText;
    TextSticker textSticker;
    public static int positionSelect = 0;


    public StickerView(Context context) {
        this(context, null);
    }

    public StickerView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public StickerView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.stickers = new ArrayList();
        this.icons = new ArrayList(4);
        this.borderPaint = new Paint();
        this.borderPaintSticker = new Paint();
        this.stickerRect = new RectF();
        this.sizeMatrix = new Matrix();
        this.downMatrix = new Matrix();
        this.moveMatrix = new Matrix();
        this.bitmapPoints = new float[8];
        this.bounds = new float[8];
        this.point = new float[2];
        this.currentCenterPoint = new PointF();
        this.tmp = new float[2];
        this.midPoint = new PointF();
        this.oldDistance = 0.0F;
        this.oldRotation = 0.0F;
        this.currentMode = ActionMode.NONE;
        this.lastClickTime = 0L;
        this.minClickDelayTime = 200;
        this.touchSlop = ViewConfiguration.get(context).getScaledTouchSlop();
        TypedArray typedArray = null;

        try {
            typedArray = context.obtainStyledAttributes(attributeSet, R.styleable.StickerView);
            this.showIcons = typedArray.getBoolean(R.styleable.StickerView_showIcons, false);
            this.showBorder = typedArray.getBoolean(R.styleable.StickerView_showBorder, false);
            this.bringToFrontCurrentSticker = typedArray.getBoolean(R.styleable.StickerView_bringToFrontCurrentSticker, false);
            this.borderPaint.setAntiAlias(true);
            this.borderPaint.setColor(typedArray.getColor(R.styleable.StickerView_borderColor, -16777216));
            this.borderPaint.setAlpha(typedArray.getInteger(R.styleable.StickerView_borderAlpha, 128));
            this.borderPaint.setPathEffect(new DashPathEffect(new float[]{10.0F, 20.0F}, 0.0F));

            setLayerType(View.LAYER_TYPE_SOFTWARE, null);
            this.borderPaintSticker.setColor(Color.WHITE);
            this.borderPaintSticker.setStyle(Paint.Style.STROKE);
            this.borderPaintSticker.setStrokeWidth(1.0f);
            this.borderPaintSticker.setPathEffect(new DashPathEffect(new float[]{20, 20,}, 0));

            this.configDefaultIcons();

        } finally {
            if (typedArray != null) {
                typedArray.recycle();
            }

        }

    }

    public void configDefaultIcons() {
        BitmapStickerIcon deleteIcon = new BitmapStickerIcon(
                ContextCompat.getDrawable(getContext(), R.drawable.sticker_ic_close_white_18dp),
                BitmapStickerIcon.LEFT_TOP);
        deleteIcon.setIconEvent(new DeleteIconEvent());
        BitmapStickerIcon zoomIcon = new BitmapStickerIcon(
                ContextCompat.getDrawable(getContext(), R.drawable.sticker_ic_scale_white_18dp),
                BitmapStickerIcon.RIGHT_BOTOM);
        zoomIcon.setIconEvent(new ZoomIconEvent());
        BitmapStickerIcon flipIcon = new BitmapStickerIcon(
                ContextCompat.getDrawable(getContext(), R.drawable.sticker_ic_flip_white_18dp),
                BitmapStickerIcon.RIGHT_TOP);
        flipIcon.setIconEvent(new FlipHorizontallyEvent());

        icons.clear();
        icons.add(deleteIcon);
        icons.add(zoomIcon);
        icons.add(flipIcon);
    }

    /**
     * Swaps sticker at layer [[oldPos]] with the one at layer [[newPos]].
     * Does nothing if either of the specified layers doesn't exist.
     */
    public void swapLayers(int oldPos, int newPos) {
        if (stickers.size() >= oldPos && stickers.size() >= newPos) {
            Collections.swap(stickers, oldPos, newPos);
            invalidate();
        }
    }

    /**
     * Sends sticker from layer [[oldPos]] to layer [[newPos]].
     * Does nothing if either of the specified layers doesn't exist.
     */
    public void sendToLayer(int oldPos, int newPos) {
        if (stickers.size() >= oldPos && stickers.size() >= newPos) {
            Sticker s = stickers.get(oldPos);
            stickers.remove(oldPos);
            stickers.add(newPos, s);
            invalidate();
        }
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        if (changed) {
            stickerRect.left = left;
            stickerRect.top = top;
            stickerRect.right = right;
            stickerRect.bottom = bottom;
        }
    }

    @Override
    protected void dispatchDraw(Canvas canvas) {
        super.dispatchDraw(canvas);
        drawStickers(canvas);
    }
    public void drawStickers(Canvas canvas) {
        Canvas canvas2 = canvas;

        for (int i = 0; i < this.stickers.size(); ++i) {
            Sticker sticker = (Sticker) this.stickers.get(i);
            if (sticker != null && sticker.isVisible()) {
                sticker.draw(canvas2);
            }
        }

        Sticker sticker2 = this.handlingSticker;
        if (sticker2 != null && !this.locked && sticker2.isVisible() && (this.showBorder || this.showIcons)) {
            this.getStickerPoints(this.handlingSticker, this.bitmapPoints);
            float[] fArr = this.bitmapPoints;
            float f5 = fArr[0];
            float f6 = fArr[1];
            float f7 = fArr[2];
            float f8 = fArr[3];
            float f9 = fArr[4];
            float f10 = fArr[5];
            float f11 = fArr[6];
            float f12 = fArr[7];
            float f;
            float f2;
            float f3;
            float f4;
            if (this.showBorder) {
                canvas.drawLine(f5, f6, f7, f8, this.borderPaintSticker);
                canvas.drawLine(f5, f6, f9, f10, this.borderPaintSticker);
                canvas.drawLine(f7, f8, f11, f12, this.borderPaintSticker);
                canvas.drawLine(f11, f12, f9, f10, this.borderPaintSticker);

//        canvas.drawRect(f5,f6,f11,f12,borderPaintSticker);

                Paint paint = new Paint();
                paint.setAntiAlias(true);
                paint.setColor(-16777216);
                paint.setStyle(Paint.Style.STROKE);
                paint.setAlpha(128);
                paint.setStrokeWidth(4.0F);

//        paint.setColor(Color.WHITE);
//        paint.setPathEffect(new DashPathEffect(new float[]{5, 10, 15, 20}, 0));

                paint.setTextSize(30.0F);
                float f20 = (f10 + f6) / 2.0F;
                f = f12;
                f4 = f10;
                float f22 = (f12 + f8) / 2.0F;
//        canvas.drawLine(f5, f20, f7, f22, paint);
                f2 = f11;
                f3 = f9;
//        canvas.drawLine((f5 + f7) / 2.0F, f6, (f9 + f11) / 2.0F, f12, paint);
            } else {
                f = f12;
                f2 = f11;
                f4 = f10;
                f3 = f9;
            }

            if (this.showIcons) {
                float f25 = f;
                float calculateRotation = this.calculateRotation(f2, f, f3, f4);

                for (int i2 = 0; i2 < this.icons.size(); ++i2) {
                    BitmapStickerIcon bitmapStickerIcon = (BitmapStickerIcon) this.icons.get(i2);
                    int position = bitmapStickerIcon.getPosition();
                    if (position == 0) {
                        this.configIconMatrix(bitmapStickerIcon, f5, f6, calculateRotation);
                    } else if (position == 1) {
                        this.configIconMatrix(bitmapStickerIcon, f7, f8, calculateRotation);
                    } else if (position == 2) {
                        this.configIconMatrix(bitmapStickerIcon, f3, f4, calculateRotation);
                    } else if (position == 3) {
                        this.configIconMatrix(bitmapStickerIcon, f2, f25, calculateRotation);
                    }

                    bitmapStickerIcon.draw(canvas2, this.borderPaint);
                }
            }
        }

    }

//    protected void drawStickers(Canvas canvas) {
//        for (int i = 0; i < stickers.size(); i++) {
//            Sticker sticker = stickers.get(i);
//            if (sticker != null) {
//                sticker.draw(canvas);
//            }
//        }
//
//        if (handlingSticker != null && !locked && (showBorder || showIcons)) {
//
//            getStickerPoints(handlingSticker, bitmapPoints);
//
//            float x1 = bitmapPoints[0];
//            float y1 = bitmapPoints[1];
//            float x2 = bitmapPoints[2];
//            float y2 = bitmapPoints[3];
//            float x3 = bitmapPoints[4];
//            float y3 = bitmapPoints[5];
//            float x4 = bitmapPoints[6];
//            float y4 = bitmapPoints[7];
//
//            if (showBorder) {
//                canvas.drawLine(x1, y1, x2, y2, borderPaint);
//                canvas.drawLine(x1, y1, x3, y3, borderPaint);
//                canvas.drawLine(x2, y2, x4, y4, borderPaint);
//                canvas.drawLine(x4, y4, x3, y3, borderPaint);
//            }
//
//            //draw icons
//            if (showIcons) {
//                float rotation = calculateRotation(x4, y4, x3, y3);
//                for (int i = 0; i < icons.size(); i++) {
//                    BitmapStickerIcon icon = icons.get(i);
//                    switch (icon.getPosition()) {
//                        case BitmapStickerIcon.LEFT_TOP:
//
//                            configIconMatrix(icon, x1, y1, rotation);
//                            break;
//
//                        case BitmapStickerIcon.RIGHT_TOP:
//                            configIconMatrix(icon, x2, y2, rotation);
//                            break;
//
//                        case BitmapStickerIcon.LEFT_BOTTOM:
//                            configIconMatrix(icon, x3, y3, rotation);
//                            break;
//
//                        case BitmapStickerIcon.RIGHT_BOTOM:
//                            configIconMatrix(icon, x4, y4, rotation);
//                            break;
//                    }
//                    icon.draw(canvas, borderPaint);
//                }
//            }
//        }
//    }

    protected void configIconMatrix(@NonNull BitmapStickerIcon icon, float x, float y,
                                    float rotation) {
        icon.setX(x);
        icon.setY(y);
        icon.getMatrix().reset();

        icon.getMatrix().postRotate(rotation, icon.getWidth() / 2, icon.getHeight() / 2);
        icon.getMatrix().postTranslate(x - icon.getWidth() / 2, y - icon.getHeight() / 2);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        if (locked) return super.onInterceptTouchEvent(ev);

        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                downX = ev.getX();
                downY = ev.getY();

                return findCurrentIconTouched() != null || findHandlingSticker() != null;
        }

        return super.onInterceptTouchEvent(ev);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (this.locked) {
            return super.onTouchEvent(event);
        } else {
            int actionMasked = MotionEventCompat.getActionMasked(event);
            if (actionMasked != 0) {
                if (actionMasked == 1) {
                    this.onTouchUp(event);
                } else if (actionMasked == 2) {
                    this.handleCurrentMode(event);
                    this.invalidate();
                } else if (actionMasked == 5) {
                    this.oldDistance = this.calculateDistance(event);
                    this.oldRotation = this.calculateRotation(event);
                    this.midPoint = this.calculateMidPoint(event);
                    Sticker sticker2 = this.handlingSticker;
                    if (sticker2 != null && this.isInStickerArea(sticker2, event.getX(1), event.getY(1)) && this.findCurrentIconTouched() == null) {
                        this.currentMode = ActionMode.ZOOM_WITH_TWO_FINGER;
                    }
                } else if (actionMasked == 6) {
                    Sticker sticker;
                    OnStickerOperationListener onStickerOperationListener2;
                    if (this.currentMode == 2 && (sticker = this.handlingSticker) != null && (onStickerOperationListener2 = this.onStickerOperationListener) != null) {
                        onStickerOperationListener2.onStickerZoomFinished(sticker);
                    }

                    this.currentMode = ActionMode.NONE;
                }
            } else if (!this.onTouchDown(event)) {
                OnStickerViewListener onStickerViewListener2 = this.onStickerViewListener;
                if (onStickerViewListener2 != null) {
                    onStickerViewListener2.onTouch(event);
                }

                return false;
            }

            return true;
        }
    }

    /**
     * @param event MotionEvent received from {@link #onTouchEvent)
     * @return true if has touch something
     */
    protected boolean onTouchDown( MotionEvent event) {
//        this.currentMode = ActionMode.DRAG;
//        this.downX = event.getX();
//        this.downY = event.getY();
//        PointF calculateMidPoint = this.calculateMidPoint();
//        this.midPoint = calculateMidPoint;
//        this.oldDistance = this.calculateDistance(calculateMidPoint.x, this.midPoint.y, this.downX, this.downY);
//        this.oldRotation = this.calculateRotation(this.midPoint.x, this.midPoint.y, this.downX, this.downY);
//        BitmapStickerIcon findCurrentIconTouched = this.findCurrentIconTouched();
//        this.currentIcon = findCurrentIconTouched;
//        if (findCurrentIconTouched != null) {
//            this.currentMode = ActionMode.ICON;
//            findCurrentIconTouched.onActionDown(this, event);
//        } else {
//            this.handlingSticker = this.findHandlingSticker();
//        }
//
//        Sticker sticker = this.handlingSticker;
//        if (sticker != null) {
//            this.downMatrix.set(sticker.getMatrix());
//            if (this.bringToFrontCurrentSticker) {
//                this.stickers.remove(this.handlingSticker);
//                this.stickers.add(this.handlingSticker);
//            }
//
//            OnStickerOperationListener onStickerOperationListener2 = this.onStickerOperationListener;
//            if (onStickerOperationListener2 != null) {
//                onStickerOperationListener2.onStickerTouchedDown(this.handlingSticker);
//            }
//        }
//
//        if (this.currentIcon == null && this.handlingSticker == null) {
//            this.invalidate();
//            return false;
//        } else {
//            this.invalidate();
//            return true;
//        }
        currentMode = ActionMode.DRAG;

        downX = event.getX();
        downY = event.getY();

        midPoint = calculateMidPoint();
        oldDistance = calculateDistance(midPoint.x, midPoint.y, downX, downY);
        oldRotation = calculateRotation(midPoint.x, midPoint.y, downX, downY);

        currentIcon = findCurrentIconTouched();
        if (currentIcon != null) {
            currentMode = ActionMode.ICON;
            currentIcon.onActionDown(this, event);
        } else {
            handlingSticker = findHandlingSticker();


        }

        Log.e("stickerpostion1", "" + stickers.indexOf(handlingSticker));
        if (handlingSticker != null) {
            onStickerOperationListener.onStickerTouchedDown(handlingSticker);
            downMatrix.set(handlingSticker.getMatrix());
            if (bringToFrontCurrentSticker) {
                stickers.remove(handlingSticker);
                stickers.add(handlingSticker);
            }
        }

        if (currentIcon == null && handlingSticker == null) {

            if (onStickerOperationListener != null) {
                onStickerOperationListener.onStickerNotClicked();
            }
            return false;
        }
        invalidate();
        return true;
    }

    protected void onTouchUp( MotionEvent event) {
        long currentTime = SystemClock.uptimeMillis();

        if (currentMode == ActionMode.ICON && currentIcon != null && handlingSticker != null) {
            currentIcon.onActionUp(this, event);
        }

        if (currentMode == ActionMode.DRAG
                && Math.abs(event.getX() - downX) < touchSlop
                && Math.abs(event.getY() - downY) < touchSlop
                && handlingSticker != null) {
            currentMode = ActionMode.CLICK;
            if (onStickerOperationListener != null) {
                onStickerOperationListener.onStickerClicked(handlingSticker);


            }
            if (currentTime - lastClickTime < minClickDelayTime) {
                if (onStickerOperationListener != null) {
                    onStickerOperationListener.onStickerDoubleTapped(handlingSticker);
                }
            }
        }

        if (currentMode == ActionMode.DRAG && handlingSticker != null) {
            if (onStickerOperationListener != null) {
                onStickerOperationListener.onStickerDragFinished(handlingSticker);
            }
        }

        currentMode = ActionMode.NONE;
        lastClickTime = currentTime;
//        long uptimeMillis = SystemClock.uptimeMillis();
//        BitmapStickerIcon bitmapStickerIcon;
//        if (this.currentMode == 3 && (bitmapStickerIcon = this.currentIcon) != null && this.handlingSticker != null) {
//            bitmapStickerIcon.onActionUp(this, event);
//        }
//
//        Sticker sticker2;
//        if (this.currentMode == 1 && Math.abs(event.getX() - this.downX) < (float) this.touchSlop && Math.abs(event.getY() - this.downY) < (float) this.touchSlop && (sticker2 = this.handlingSticker) != null) {
//            this.currentMode = ActionMode.CLICK;
//            OnStickerOperationListener onStickerOperationListener4 = this.onStickerOperationListener;
//            if (onStickerOperationListener4 != null) {
//                onStickerOperationListener4.onStickerClicked(sticker2);
//            }
//
//
//            OnStickerOperationListener onStickerOperationListener3;
//            if (uptimeMillis - this.lastClickTime < (long) this.minClickDelayTime && (onStickerOperationListener3 = this.onStickerOperationListener) != null) {
//                onStickerOperationListener3.onStickerDoubleTapped(this.handlingSticker);
//            }
//        }
//
//        Sticker sticker;
//        OnStickerOperationListener onStickerOperationListener2;
//        if (this.currentMode == 1 && (sticker = this.handlingSticker) != null && (onStickerOperationListener2 = this.onStickerOperationListener) != null) {
//            onStickerOperationListener2.onStickerDragFinished(sticker);
//        }
//
//        this.currentMode = ActionMode.NONE;
//        this.lastClickTime = uptimeMillis;
    }

    protected void handleCurrentMode(@NonNull MotionEvent event) {
        switch (currentMode) {
            case ActionMode.NONE:
            case ActionMode.CLICK:
                break;
            case ActionMode.DRAG:
                if (handlingSticker != null) {
                    moveMatrix.set(downMatrix);
                    moveMatrix.postTranslate(event.getX() - downX, event.getY() - downY);
                    handlingSticker.setMatrix(moveMatrix);
                    if (constrained) {
                        constrainSticker(handlingSticker);
                    }
                }
                break;
            case ActionMode.ZOOM_WITH_TWO_FINGER:
                if (handlingSticker != null) {
                    float newDistance = calculateDistance(event);
                    float newRotation = calculateRotation(event);

                    moveMatrix.set(downMatrix);
                    moveMatrix.postScale(newDistance / oldDistance, newDistance / oldDistance, midPoint.x,
                            midPoint.y);
                    moveMatrix.postRotate(newRotation - oldRotation, midPoint.x, midPoint.y);
                    handlingSticker.setMatrix(moveMatrix);
                }

                break;

            case ActionMode.ICON:
                if (handlingSticker != null && currentIcon != null) {
                    currentIcon.onActionMove(this, event);
                }
                break;
        }
//        int i = this.currentMode;
//        Sticker sticker2;
//        if (i == 1) {
//            sticker2 = this.handlingSticker;
//            if (sticker2 == null || sticker2.isLocked()) {
//                this.showHint(getContext().getString(R.string.Object_is_locked));
//                return;
//            }
//
//            this.moveMatrix.set(this.downMatrix);
//            this.moveMatrix.postTranslate(event.getX() - this.downX, event.getY() - this.downY);
//            this.handlingSticker.setMatrix(this.moveMatrix);
//            if (this.constrained) {
//                this.constrainSticker(this.handlingSticker);
//            }
//        } else if (i == 2) {
//            sticker2 = this.handlingSticker;
//            if (sticker2 != null && !sticker2.isLocked()) {
//                float calculateDistance = this.calculateDistance(event);
//                float calculateRotation = this.calculateRotation(event);
//                this.moveMatrix.set(this.downMatrix);
//                Matrix matrix = this.moveMatrix;
//                float f = this.oldDistance;
//                matrix.postScale(calculateDistance / f, calculateDistance / f, this.midPoint.x, this.midPoint.y);
//                this.moveMatrix.postRotate(calculateRotation - this.oldRotation, this.midPoint.x, this.midPoint.y);
//                this.handlingSticker.setMatrix(this.moveMatrix);
//            }
//        } else {
//            BitmapStickerIcon bitmapStickerIcon;
//            if (i == 3 && this.handlingSticker != null && (bitmapStickerIcon = this.currentIcon) != null) {
//                bitmapStickerIcon.onActionMove(this, event);
//            }
//        }
    }
    public void showHint(String str) {
        Sticker sticker = this.handlingSticker;
        if (sticker != null) {
            float[] stickerPoints = this.getStickerPoints(sticker);
            Rect rect = new Rect();
            this.getWindowVisibleDisplayFrame(rect);
            Context context = this.getContext();
            int width = this.handlingSticker.getWidth();
            int height = this.handlingSticker.getHeight();
            int i = (int) stickerPoints[1] + height / 2;
            int i2 = (int) stickerPoints[0] + width / 2;
            @SuppressLint("WrongConstant") Toast makeText = Toast.makeText(context, str.toString(), 0);
            if (i < rect.height()) {
                makeText.setGravity(8388661, i2, (int) stickerPoints[1] + height - rect.top);
            } else {
                makeText.setGravity(81, 0, height);
            }

            makeText.show();
        }

    }

    public void zoomAndRotateCurrentSticker(@NonNull MotionEvent event) {
        zoomAndRotateSticker(handlingSticker, event);
    }

    public void zoomAndRotateSticker(@Nullable Sticker sticker, @NonNull MotionEvent event) {
        if (sticker != null) {
            float newDistance = calculateDistance(midPoint.x, midPoint.y, event.getX(), event.getY());
            float newRotation = calculateRotation(midPoint.x, midPoint.y, event.getX(), event.getY());

            moveMatrix.set(downMatrix);
            moveMatrix.postScale(newDistance / oldDistance, newDistance / oldDistance, midPoint.x,
                    midPoint.y);
            moveMatrix.postRotate(newRotation - oldRotation, midPoint.x, midPoint.y);
            handlingSticker.setMatrix(moveMatrix);
        }
    }

    protected void constrainSticker(@NonNull Sticker sticker) {
        float moveX = 0;
        float moveY = 0;
        int width = getWidth();
        int height = getHeight();
        sticker.getMappedCenterPoint(currentCenterPoint, point, tmp);
        if (currentCenterPoint.x < 0) {
            moveX = -currentCenterPoint.x;
        }

        if (currentCenterPoint.x > width) {
            moveX = width - currentCenterPoint.x;
        }

        if (currentCenterPoint.y < 0) {
            moveY = -currentCenterPoint.y;
        }

        if (currentCenterPoint.y > height) {
            moveY = height - currentCenterPoint.y;
        }

        sticker.getMatrix().postTranslate(moveX, moveY);
    }

    @Nullable
    protected BitmapStickerIcon findCurrentIconTouched() {
        for (BitmapStickerIcon icon : icons) {
            float x = icon.getX() - downX;
            float y = icon.getY() - downY;
            float distance_pow_2 = x * x + y * y;
            if (distance_pow_2 <= Math.pow(icon.getIconRadius() + icon.getIconRadius(), 2)) {
                return icon;
            }
        }

        return null;
    }

    /**
     * find the touched Sticker
     **/
    @Nullable
    protected Sticker findHandlingSticker() {
        for (int i = stickers.size() - 1; i >= 0; i--) {
            if (isInStickerArea(stickers.get(i), downX, downY)) {
                return stickers.get(i);
            }
        }
        return null;
    }

    protected boolean isInStickerArea(@NonNull Sticker sticker, float downX, float downY) {
        tmp[0] = downX;
        tmp[1] = downY;
        return sticker.contains(tmp);
    }

    @NonNull
    protected PointF calculateMidPoint(@Nullable MotionEvent event) {

        if (event == null || event.getPointerCount() < 2) {
            midPoint.set(0, 0);
            return midPoint;
        }
        float x = (event.getX(0) + event.getX(1)) / 2;
        float y = (event.getY(0) + event.getY(1)) / 2;
        midPoint.set(x, y);
        return midPoint;
    }

    @NonNull
    protected PointF calculateMidPoint() {
        Sticker sticker = handlingSticker;
        if (sticker == null) {
            midPoint.set(0, 0);
            return midPoint;
        }
        sticker.getMappedCenterPoint(midPoint, point, tmp);
        return midPoint;
    }

    /**
     * calculate rotation in line with two fingers and x-axis
     **/
    protected float calculateRotation(@Nullable MotionEvent event) {
        if (event == null || event.getPointerCount() < 2) {
            return 0f;
        }
        return calculateRotation(event.getX(0), event.getY(0), event.getX(1), event.getY(1));
    }

    protected float calculateRotation(float x1, float y1, float x2, float y2) {
        double x = x1 - x2;
        double y = y1 - y2;
        double radians = Math.atan2(y, x);
        return (float) Math.toDegrees(radians);
    }

    /**
     * calculate Distance in two fingers
     **/
    protected float calculateDistance(@Nullable MotionEvent event) {
        if (event == null || event.getPointerCount() < 2) {
            return 0f;
        }
        return calculateDistance(event.getX(0), event.getY(0), event.getX(1), event.getY(1));
    }

    protected float calculateDistance(float x1, float y1, float x2, float y2) {
        double x = x1 - x2;
        double y = y1 - y2;

        return (float) Math.sqrt(x * x + y * y);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldW, int oldH) {
        super.onSizeChanged(w, h, oldW, oldH);
        for (int i = 0; i < stickers.size(); i++) {
            Sticker sticker = stickers.get(i);
            if (sticker != null) {
                transformSticker(sticker);
            }
        }
    }

    /**
     * Sticker's drawable will be too bigger or smaller
     * This method is to transform it to fit
     * step 1：let the center of the sticker image is coincident with the center of the View.
     * step 2：Calculate the zoom and zoom
     **/
    protected void transformSticker(@Nullable Sticker sticker) {
        if (sticker == null) {
            Log.e(TAG, "transformSticker: the bitmapSticker is null or the bitmapSticker bitmap is null");
            return;
        }

        sizeMatrix.reset();

        float width = getWidth();
        float height = getHeight();
        float stickerWidth = sticker.getWidth();
        float stickerHeight = sticker.getHeight();
        //step 1
        float offsetX = (width - stickerWidth) / 2;
        float offsetY = (height - stickerHeight) / 2;

        sizeMatrix.postTranslate(offsetX, offsetY);

        //step 2
        float scaleFactor;
        if (width < height) {
            scaleFactor = width / stickerWidth;
        } else {
            scaleFactor = height / stickerHeight;
        }

        sizeMatrix.postScale(scaleFactor / 2f, scaleFactor / 2f, width / 2f, height / 2f);

        sticker.getMatrix().reset();
        sticker.setMatrix(sizeMatrix);

        invalidate();
    }

    public void flipCurrentSticker(int direction) {
        flip(handlingSticker, direction);
    }

    public void flip(@Nullable Sticker sticker, @Flip int direction) {
        if (sticker != null) {
            sticker.getCenterPoint(midPoint);
            if ((direction & FLIP_HORIZONTALLY) > 0) {
                sticker.getMatrix().preScale(-1, 1, midPoint.x, midPoint.y);
                sticker.setFlippedHorizontally(!sticker.isFlippedHorizontally());
            }
            if ((direction & FLIP_VERTICALLY) > 0) {
                sticker.getMatrix().preScale(1, -1, midPoint.x, midPoint.y);
                sticker.setFlippedVertically(!sticker.isFlippedVertically());
            }

            if (onStickerOperationListener != null) {
                onStickerOperationListener.onStickerFlipped(sticker);
            }

            invalidate();
        }
    }


    public boolean replace(@Nullable Sticker sticker) {
        return replace(sticker, true);
    }

    public boolean replace(@Nullable Sticker sticker, boolean needStayState) {
        if (handlingSticker != null && sticker != null) {
            float width = getWidth();
            float height = getHeight();
            if (needStayState) {
                sticker.setMatrix(handlingSticker.getMatrix());
                sticker.setFlippedVertically(handlingSticker.isFlippedVertically());
                sticker.setFlippedHorizontally(handlingSticker.isFlippedHorizontally());
            } else {
                handlingSticker.getMatrix().reset();
                // reset scale, angle, and put it in center
                float offsetX = (width - handlingSticker.getWidth()) / 2f;
                float offsetY = (height - handlingSticker.getHeight()) / 2f;
                sticker.getMatrix().postTranslate(offsetX, offsetY);

                float scaleFactor;
                if (width < height) {
                    scaleFactor = width / handlingSticker.getDrawable().getIntrinsicWidth();
                } else {
                    scaleFactor = height / handlingSticker.getDrawable().getIntrinsicHeight();
                }
                sticker.getMatrix().postScale(scaleFactor / 2f, scaleFactor / 2f, width / 2f, height / 2f);
            }
            int index = stickers.indexOf(handlingSticker);
            stickers.set(index, sticker);
            handlingSticker = sticker;

            invalidate();
            return true;
        } else {
            return false;
        }
    }

    public boolean remove(@Nullable Sticker sticker) {
//        if (stickers.contains(sticker)) {
//            stickers.remove(sticker);
//            if (onStickerOperationListener != null) {
//                onStickerOperationListener.onStickerDeleted(sticker);
//            }
//            if (handlingSticker == sticker) {
//                handlingSticker = null;
//            }
//            invalidate();
//
//            return true;
//        } else {
//            Log.d(TAG, "remove: the sticker is not in this StickerView");
//
//            return false;
//        }
        if (!this.stickers.contains(sticker)) {
            Log.d("StickerView", "remove: the sticker is not in this StickerView");
            return false;
        } else if (!sticker.isLocked()) {
            this.stickers.remove(sticker);
            OnStickerOperationListener onStickerOperationListener2 = this.onStickerOperationListener;
            if (onStickerOperationListener2 != null) {
                onStickerOperationListener2.onStickerDeleted(sticker);
            }

            if (this.handlingSticker == sticker) {
                this.handlingSticker = null;
            }

            this.invalidate();
            return true;
        } else {
            this.showHint(getContext().getString(R.string.Object_is_locked));
            return false;
        }
    }

    public boolean removeCurrentSticker() {
        return remove(handlingSticker);
    }

    public void removeAllStickers() {
        stickers.clear();
        if (handlingSticker != null) {
            handlingSticker.release();
            handlingSticker = null;
        }
        invalidate();
    }

    @NonNull
    public StickerView addSticker(@NonNull Sticker sticker) {
        return addSticker(sticker, Sticker.Position.CENTER);
    }

    public StickerView addSticker(@NonNull final Sticker sticker,
                                  final @Sticker.Position int position) {
        if (ViewCompat.isLaidOut(this)) {
            addStickerImmediately(sticker, position);
        } else {
            post(new Runnable() {
                @Override
                public void run() {
                    addStickerImmediately(sticker, position);
                }
            });
        }
        return this;
    }

    protected void addStickerImmediately(@NonNull Sticker sticker, @Sticker.Position int position) {
//        setStickerPosition(sticker, position);
//
//
//        float scaleFactor, widthScaleFactor, heightScaleFactor;
//
//        widthScaleFactor = (float) getWidth() / sticker.getDrawable().getIntrinsicWidth();
//        heightScaleFactor = (float) getHeight() / sticker.getDrawable().getIntrinsicHeight();
//        scaleFactor = widthScaleFactor > heightScaleFactor ? heightScaleFactor : widthScaleFactor;
//
//        sticker.getMatrix()
//                .postScale(scaleFactor / 2, scaleFactor / 2, getWidth() / 2, getHeight() / 2);

        handlingSticker = sticker;
        stickers.add(sticker);
        if (onStickerOperationListener != null) {
            onStickerOperationListener.onStickerAdded(sticker);
        }
        invalidate();
    }

    protected void setStickerPosition(@NonNull Sticker sticker, @Sticker.Position int position) {

        Log.e("stickerpostion", "" + position);

        float width = getWidth();
        float height = getHeight();
        float offsetX = width - sticker.getWidth();
        float offsetY = height - sticker.getHeight();
        if ((position & Sticker.Position.TOP) > 0) {
            offsetY /= 4f;
        } else if ((position & Sticker.Position.BOTTOM) > 0) {
            offsetY *= 3f / 4f;
        } else {
            offsetY /= 2f;
        }
        if ((position & Sticker.Position.LEFT) > 0) {
            offsetX /= 4f;
        } else if ((position & Sticker.Position.RIGHT) > 0) {
            offsetX *= 3f / 4f;
        } else {
            offsetX /= 2f;
        }
        sticker.getMatrix().postTranslate(offsetX, offsetY);
    }

    @NonNull
    public float[] getStickerPoints(@Nullable Sticker sticker) {
        float[] points = new float[8];
        getStickerPoints(sticker, points);
        return points;
    }

    public void getStickerPoints(@Nullable Sticker sticker, @NonNull float[] dst) {
        if (sticker == null) {
            Arrays.fill(dst, 0);
            return;
        }
        sticker.getBoundPoints(bounds);
        sticker.getMappedPoints(dst, bounds);
    }

    public void save(@NonNull File file) {
        try {
            StickerUtils.saveImageToGallery(file, createBitmap());
            StickerUtils.notifySystemGallery(getContext(), file);
        } catch (IllegalArgumentException | IllegalStateException ignored) {
            //
        }
    }

    @NonNull
    public Bitmap createBitmap() throws OutOfMemoryError {
        handlingSticker = null;
        Bitmap bitmap = Bitmap.createBitmap(getWidth(), getHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        this.draw(canvas);
        return bitmap;
    }

    public int getStickerCount() {
        return stickers.size();
    }

    public boolean isNoneSticker() {
        return getStickerCount() == 0;
    }

    public boolean isLocked() {
        return locked;
    }

    @NonNull
    public StickerView setLocked(boolean locked) {
        this.locked = locked;
        invalidate();
        return this;
    }

    @NonNull
    public StickerView setMinClickDelayTime(int minClickDelayTime) {
        this.minClickDelayTime = minClickDelayTime;
        return this;
    }

    public int getMinClickDelayTime() {
        return minClickDelayTime;
    }

    public boolean isConstrained() {
        return constrained;
    }

    @NonNull
    public StickerView setConstrained(boolean constrained) {
        this.constrained = constrained;
        postInvalidate();
        return this;
    }

    @NonNull
    public StickerView setOnStickerOperationListener(
            @Nullable OnStickerOperationListener onStickerOperationListener) {
        this.onStickerOperationListener = onStickerOperationListener;
        return this;
    }

    @Nullable
    public OnStickerOperationListener getOnStickerOperationListener() {
        return onStickerOperationListener;
    }


    public Sticker getCurrentSticker() {
        return handlingSticker;
    }

    @NonNull
    public List<BitmapStickerIcon> getIcons() {
        return icons;
    }

    public void setIcons(@NonNull List<BitmapStickerIcon> icons) {
        this.icons.clear();
        this.icons.addAll(icons);
        invalidate();
    }

    public interface OnStickerOperationListener {
        void onStickerAdded(@NonNull Sticker sticker);

        void onStickerClicked(@NonNull Sticker sticker);

        void onStickerDeleted(@NonNull Sticker sticker);

        void onStickerDragFinished(@NonNull Sticker sticker);

        void onStickerTouchedDown(@NonNull Sticker sticker);

        void onStickerZoomFinished(@NonNull Sticker sticker);

        void onStickerFlipped(@NonNull Sticker sticker);

        void onStickerDoubleTapped(@NonNull Sticker sticker);

        void onStickerNotClicked();
    }


    public void inputTextSticker() {
        SmartInput smartInput = new SmartInput();
        smartInput.setFontDirectory(this.fontsDirectoryInAssets);
        SmartInputAlert.input(this.getContext(), smartInput, getContext().getString(R.string.InputText), new SmartInputAlert.SmartInputAlertListener() {
            public void result(int i, SmartInput smartInput) {
                if (i == 1 && smartInput.getText().length() > 0) {
                    Log.e("CheckActive", "result: " + fontsDirectoryInAssets);
                     textSticker = new TextSticker(StickerView.this.getContext());
                    textSticker.setTextAlign(smartInput.getTextAlignment());
                    textSticker.setText(smartInput.getText(), true);
                    Log.e(TAG, "result: "+textSticker.getText() );
                    textSticker.setTextColor(smartInput.getTextColor());
                    textSticker.setTextUnderline(smartInput.isUnderlineText());
                    textSticker.setTextStrikeThru(smartInput.isStrikeThruText());
                    RectangleDrawable rectangleDrawable = textSticker.getRectangleDrawable();
                    textSticker.setTextBold(smartInput.isBoldText());
                    textSticker.setTextItalic(smartInput.isItalicText());
                    textSticker.setTypeface(ResourcesCompat.getFont(getContext(), R.font.micross));
                    textSticker.resizeText();
                    textSticker.setRectangleDrawable(rectangleDrawable).resizeText();
                    textSticker.fontPath = "fonts/Simple/f107.ttf";
                    textSticker.tTextColor = smartInput.getTextColor();
                    StickerView.this.addStickerTemp(textSticker);
                }

            }
        });
    }
    public void textEdit() {
        Sticker currentSticker = stickers.get(positionSelect);
        if (currentSticker instanceof TextSticker) {
            TextSticker textSticker = (TextSticker) currentSticker;
            Log.e("CheckActive", "textEdit: " + textSticker);
            String text = textSticker.getText();
            int textColor = textSticker.getTextColor();
            Typeface typeface = textSticker.getTypeface();
            SmartInput smartInput = new SmartInput();
            smartInput.setFontDirectory(this.getFontsDirectoryInAssets());
            Log.e("CheckActive", "textEdit2: " + getFontsDirectoryInAssets());
            smartInput.setTextAlignment(textSticker.getTextAlignment());
            smartInput.setText(text);
            smartInput.setTextColor(textColor);
            smartInput.setTypeface(typeface);
            smartInput.setBoldText(textSticker.isBoldText());
            smartInput.setItalicText(textSticker.isItalicText());
            smartInput.setUnderlineText(textSticker.isUnderlineText());
            smartInput.setStrikeThruText(textSticker.isStrikeThruText());
             editTextSticker(smartInput);
        }

    }
    public void editTextSticker(SmartInput smartInput) {
        this.setTextReplaceable(true);
        SmartInputAlert.edit(this.getContext(), smartInput, getContext().getString(R.string.EditText), new SmartInputAlert.SmartInputAlertListener() {
            public void result(int i, SmartInput smartInput) {
                if (i != 1) {
                    if (i == 2 || i == 3) {
                        StickerView.this.setTextReplaceable(false);
                    }
                } else if (StickerView.this.getStickerCount() > 0) {
                    Sticker currentSticker =  stickers.get(positionSelect);
                    if (currentSticker instanceof TextSticker) {
                        TextSticker textSticker = (TextSticker) currentSticker;
                        textSticker.setText(smartInput.getText(), false);
                        textSticker.setTextColor(smartInput.getTextColor());
                        textSticker.setTextUnderline(smartInput.isUnderlineText());
                        textSticker.setTextStrikeThru(smartInput.isStrikeThruText());
                        textSticker.setTextBold(smartInput.isBoldText());
                        textSticker.setTextItalic(smartInput.isItalicText());
                        textSticker.setTypeface(smartInput.getTypeface());
                        textSticker.resizeText();
                        StickerView.this.replace(currentSticker);
                        StickerView.this.setTextReplaceable(false);
                        setHandlingSticker(currentSticker);
                        StickerView.this.invalidate();
                    }
                }

            }
        });
    }
    public void setTextReplaceable(boolean z) {
        this.isReplaceText = z;
    }
    public boolean isTextReplaceable() {
        return this.isReplaceText;
    }


    public String getFontsDirectoryInAssets() {
        return this.fontsDirectoryInAssets;
    }

    public StickerView addStickerTemp(Sticker sticker) {
        sticker.setStyleSticker(1);

        return this.addStickerTemp(sticker,0);
    }


    public StickerView addStickerTemp(final Sticker sticker, final int i) {
        if (ViewCompat.isLaidOut(this)) {
            this.addStickerImmediatelyTemp(sticker, i);
        } else {
            this.post(new Runnable() {
                public void run() {
                    StickerView.this.addStickerImmediatelyTemp(sticker, i);
                }
            });
        }

        return this;
    }

    public void addStickerImmediatelyTemp(Sticker sticker, int i) {
        this.setStickerPosition(sticker, i);
        float width = (float) this.getWidth() / (float) sticker.getDrawable().getIntrinsicWidth();
        float height = (float) this.getHeight() / (float) sticker.getDrawable().getIntrinsicHeight();
        if (width > height) {
            width = height;
        }

        float f = width / 2.0F;
        sticker.getMatrix().postScale(f, f, (float) (this.getWidth() / 2), (float) (this.getHeight() / 2));
        this.handlingSticker = sticker;
        this.stickers.add(sticker);
        Log.e(TAG, "addStickerImmediatelyTemp: "+stickers.indexOf(handlingSticker) );

        addSticker(sticker);
        OnStickerOperationListener onStickerOperationListener2 = this.onStickerOperationListener;
        if (onStickerOperationListener2 != null) {
            onStickerOperationListener2.onStickerAdded(sticker);
        }

        this.invalidate();
    }
    public void setHandlingSticker(Sticker sticker) {
        this.handlingSticker = sticker;
        this.invalidate();
    }

}
