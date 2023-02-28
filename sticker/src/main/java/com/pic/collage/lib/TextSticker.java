package com.pic.collage.lib;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import androidx.annotation.ColorInt;
import androidx.annotation.Dimension;
import androidx.annotation.IntRange;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import android.text.Layout;
import android.text.StaticLayout;
import android.text.TextPaint;
import android.util.Log;

/**
 * Customize your sticker with text and image background.
 * You can place some text into a given region, however,
 * you can also add a plain text sticker. To support text
 * auto resizing , I take most of the code from AutoResizeTextView.
 * See https://adilatwork.blogspot.com/2014/08/android-textview-which-resizes-its-text.html
 * Notice: It's not efficient to add long text due to too much of
 * StaticLayout object allocation.
 * Created by liutao on 30/11/2016.
 */

public class TextSticker extends Sticker {


  /**
   * Our ellipsis string.
   */
  private static final String mEllipsis = "\u2026";

  private final Context context;
  private final Rect realBounds;
  private final Rect textRect;
  private TextPaint textPaint;
  private Drawable drawable;
  private StaticLayout staticLayout;
  private Layout.Alignment alignment;
  private String text;
  private RectangleDrawable rectangleDrawable;
  private boolean underlineText;
  private TextPaint textPaintOutline;
  private boolean strikeThruText;
  private boolean boldText;
  private boolean italicText;
  public int tTextColor, tBackgroundColor, tStrokeColor, tBackgroundStrokeColor, tTextShadowColor;
  private float arcStartAngle;
  private float arcSweepAngle;
  private Shadow shadowLayer;
  public String linearGradientShader = "";
  public String fontPath;
  private int alpha;
  private float letterSpacingExtra;
  private int blurMaskRadius;
  private Emboss textEmboss;
  public boolean checkClick;
  public float sizeAdd;
  int posX = 0, posY = 0;


  public TextSticker(Context context2, Drawable drawable2) {
    this.alpha = 255;
    this.lineSpacingMultiplier = 1.0F;
    this.lineSpacingExtra = 0.0F;
    this.letterSpacingExtra = 0.05F;
    this.arcStartAngle = 200.0F;
    this.arcSweepAngle = 120.0F;
    this.shadowLayer = new Shadow();
    this.blurMaskRadius = 1;
    this.textEmboss = new Emboss(new float[]{1.0F, 5.0F, 1.0F}, 0.6F, 11.0F, 7.2F);
    this.context = context2;
    if (drawable2 == null) {
      this.drawable = ContextCompat.getDrawable(context2, R.drawable.sticker_transparent_background);
    }

    this.textPaint = new TextPaint(1);
    this.textPaintOutline = new TextPaint(1);
    this.realBounds = new Rect(0, 0, this.getWidth(), this.getHeight());
    this.textRect = new Rect(0, 0, this.getWidth(), this.getHeight());
//        this.minTextSizePixels = this.convertSpToPx(12.0F);
//        this.maxTextSizePixels = this.convertSpToPx(36.0F);
    this.minTextSizePixels = this.convertSpToPx(3.0F);
    this.maxTextSizePixels = this.convertSpToPx(80.0F);
    this.alignment = Layout.Alignment.ALIGN_CENTER;
    this.textPaint.setTextSize(this.maxTextSizePixels);
    this.textPaintOutline.setTextSize(this.maxTextSizePixels);
    checkClick = false;

  }
  public TextSticker(Context context2) {
    this(context2, (Drawable) null);
  }
  public TextSticker(Context context2, TextStickerCard textStickerCard) {
    this(context2, (Drawable) null);
    setText(textStickerCard.getText(), true);
    setTextPaint(new TextPaint(textStickerCard.getTextPaint()));
    setTextPaintOutline(new TextPaint(textStickerCard.getTextPaintOutline()));
    setRectangleDrawable(new RectangleDrawable(textStickerCard.getRectangleDrawable()));
    setTypeface(Typeface.createFromAsset(context2.getAssets(), textStickerCard.getFont()));
    setTextAlign(textStickerCard.getAlignment1());
    setMatrix(Convert.toMatrix(textStickerCard.getMatrix()));
    resizeText();
    text = textStickerCard.getText();
    fontPath = textStickerCard.getFont();
    sizeAdd = textStickerCard.getSize();
    tTextColor = textStickerCard.getColor();
    linearGradientShader = textStickerCard.getLinearGradientShader();
    tBackgroundColor = textStickerCard.getBackgroundColor();
    tStrokeColor = textStickerCard.getStrokeColor();
    tBackgroundStrokeColor = textStickerCard.getBackgroundStrokeColor();
    tTextShadowColor = textStickerCard.getBackgroundColor();
    letterSpacingExtra = textStickerCard.getLetterSpacingExtra();
    lineSpacingMultiplier = textStickerCard.getLineSpacingMultiplier();
  }

  public TextStickerCard textStickerCard() {
    TextStickerCard textStickerCard = new TextStickerCard();
    textStickerCard.setText(text);
    textStickerCard.setFont(fontPath);
    textStickerCard.setSize(sizeAdd);
    textStickerCard.setColor(getTextColor());
    textStickerCard.setMatrix(Convert.fromMatrix(getMatrix()));
    textStickerCard.setAlignment1(alignment);
    textStickerCard.setLinearGradientShader(linearGradientShader);
    textStickerCard.setBackgroundColor(tBackgroundColor);
    textStickerCard.setStrokeColor(tStrokeColor);
    textStickerCard.setBackgroundStrokeColor(tBackgroundStrokeColor);
    textStickerCard.setTextShadowColor(tTextShadowColor);
    textStickerCard.setTextPaint(new TextPaint(getTextPaint()));
    textStickerCard.setTextPaintOutline(new TextPaint(getTextPaintOutline()));
    textStickerCard.setRectangleDrawable(getRectangleDrawable().rectangle());
    textStickerCard.setLetterSpacingExtra(this.letterSpacingExtra);
    textStickerCard.setLineSpacingMultiplier(this.lineSpacingMultiplier);
    return textStickerCard;
  }
  public TextTemplate textTemplate() {
    TextTemplate textStickerCard = new TextTemplate();
    textStickerCard.setText(text);
    textStickerCard.setFont(fontPath);
    textStickerCard.setSize(sizeAdd);
    textStickerCard.setColor(getTextColor());
    textStickerCard.setPosX(StickerUtils.convertDpToPx((int) this.getMatrixValue(this.getMatrix(), 2)));
    textStickerCard.setPosY(StickerUtils.convertDpToPx((int) this.getMatrixValue(this.getMatrix(), 5)));
    textStickerCard.setAlignment1(alignment);
    textStickerCard.setLinearGradientShader(linearGradientShader);
    textStickerCard.setBackgroundColor(tBackgroundColor);
    textStickerCard.setStrokeColor(tStrokeColor);
    textStickerCard.setBackgroundStrokeColor(tBackgroundStrokeColor);
    textStickerCard.setTextShadowColor(tTextShadowColor);
    textStickerCard.setwStoke(getStrokeWidth());
    textStickerCard.setRectangleDrawable(getRectangleDrawable().rectangle());
    textStickerCard.setLetterSpacingExtra(this.letterSpacingExtra);
    textStickerCard.setLineSpacingMultiplier(this.lineSpacingMultiplier);
    return textStickerCard;
  }
  public TextSticker(Context context2, TextTemplate textTemplate) {
    this(context2, (Drawable) null);
    this.minTextSizePixels = this.convertSpToPx(3.0F);
    this.maxTextSizePixels = this.convertSpToPx(80.0F);
    setText(textTemplate.getText(), true);
    setTextColor(textTemplate.getColor());
    setTextStroke(textTemplate.getStrokeColor(), textTemplate.getwStoke());
    setRectangleDrawable(new RectangleDrawable(textTemplate.getRectangleDrawable()));
    setTypeface(Typeface.createFromAsset(context2.getAssets(), textTemplate.getFont()));
    setTextAlign(textTemplate.getAlignment1());
    setLetterSpacing(textTemplate.getLetterSpacingExtra()).resizeText();
    setLineSpacing(textTemplate.getLineSpacingMultiplier(), 1.0F).resizeText();
    posX = textTemplate.getPosX();
    posY = textTemplate.getPosY();
    float x1 = StickerUtils.convertDpToPx(posX / 10);
    float y1 = StickerUtils.convertDpToPx(posY / 10);
    Matrix tmpMatrix = new Matrix();
    tmpMatrix.postTranslate(x1, y1);
    setMatrix(tmpMatrix);
    resizeText();
    text = textTemplate.getText();
    fontPath = textTemplate.getFont();
    sizeAdd = textTemplate.getSize();
    tTextColor = textTemplate.getColor();
    linearGradientShader = textTemplate.getLinearGradientShader();
    tBackgroundColor = textTemplate.getBackgroundColor();
    tStrokeColor = textTemplate.getStrokeColor();
    tBackgroundStrokeColor = textTemplate.getBackgroundStrokeColor();
    tTextShadowColor = textTemplate.getBackgroundColor();
    letterSpacingExtra = textTemplate.getLetterSpacingExtra();
    lineSpacingMultiplier = textTemplate.getLineSpacingMultiplier();
  }
  public TextSticker setLetterSpacing(float f) {
    this.letterSpacingExtra = f;
    this.textPaint.setLetterSpacing(f);
    this.textPaintOutline.setLetterSpacing(this.letterSpacingExtra);
    return this;
  }
  public void setTextPaint(TextPaint textPaint2) {
    this.textPaint = textPaint2;
  }
  public TextPaint getTextPaint() {
    return this.textPaint;
  }

  public void setTextPaintOutline(TextPaint textPaint2) {
    this.textPaintOutline = textPaint2;
  }

  public TextPaint getTextPaintOutline() {
    return this.textPaintOutline;
  }
  public float getStrokeWidth() {
    return this.textPaintOutline.getStrokeWidth();
  }
  public TextSticker setTextStroke(int i, float f) {
    this.textPaintOutline.setAntiAlias(true);
    this.textPaintOutline.setColor(i);
    this.textPaintOutline.setStyle(Paint.Style.STROKE);
    this.textPaintOutline.setStrokeWidth(f);
    return this;
  }


  /**
   * Upper bounds for text size.
   * This acts as a starting point for resizing.
   */
  private float maxTextSizePixels;

  /**
   * Lower bounds for text size.
   */
  private float minTextSizePixels;

  /**
   * Line spacing multiplier.
   */
  private float lineSpacingMultiplier = 1.0f;

  /**
   * Additional line spacing.
   */
  private float lineSpacingExtra = 0.0f;






  @Override public void draw(@NonNull Canvas canvas) {
    Matrix matrix = getMatrix();
    canvas.save();
    canvas.concat(matrix);
    if (drawable != null) {
      drawable.setBounds(realBounds);
      drawable.draw(canvas);
    }
    canvas.restore();

    canvas.save();
    canvas.concat(matrix);
    if (textRect.width() == getWidth()) {
      int dy = getHeight() / 2 - staticLayout.getHeight() / 2;
      // center vertical
      canvas.translate(0, dy);
    } else {
      int dx = textRect.left;
      int dy = textRect.top + textRect.height() / 2 - staticLayout.getHeight() / 2;
      canvas.translate(dx, dy);
    }
    staticLayout.draw(canvas);
    canvas.restore();
  }

  @Override public int getWidth() {
    return drawable.getIntrinsicWidth();
  }

  @Override public int getHeight() {
    return drawable.getIntrinsicHeight();
  }

  @Override public void release() {
    super.release();
    if (drawable != null) {
      drawable = null;
    }
  }

  @NonNull @Override public TextSticker setAlpha(@IntRange(from = 0, to = 255) int alpha) {
    textPaint.setAlpha(alpha);
    return this;
  }

  @NonNull @Override public Drawable getDrawable() {
    return drawable;
  }

  @Override public TextSticker setDrawable(@NonNull Drawable drawable) {
    this.drawable = drawable;
    realBounds.set(0, 0, getWidth(), getHeight());
    textRect.set(0, 0, getWidth(), getHeight());
    return this;
  }

  @NonNull public TextSticker setDrawable(@NonNull Drawable drawable, @Nullable Rect region) {
    this.drawable = drawable;
    realBounds.set(0, 0, getWidth(), getHeight());
    if (region == null) {
      textRect.set(0, 0, getWidth(), getHeight());
    } else {
      textRect.set(region.left, region.top, region.right, region.bottom);
    }
    return this;
  }

  @NonNull public TextSticker setTypeface(@Nullable Typeface typeface) {
    textPaint.setTypeface(typeface);
    return this;
  }
  public Typeface getTypeface() {
    return this.textPaint.getTypeface();
  }


  @NonNull public TextSticker setTextColor(@ColorInt int color) {
    textPaint.setColor(color);
    return this;
  }
  public int getTextColor() {
    return this.textPaint.getColor();
  }


  @NonNull public TextSticker setTextAlign(@NonNull Layout.Alignment alignment) {
    this.alignment = alignment;
    return this;
  }
  public Layout.Alignment getTextAlignment() {
    return this.alignment;
  }


  @NonNull public TextSticker setMaxTextSize(@Dimension(unit = Dimension.SP) float size) {
    textPaint.setTextSize(convertSpToPx(size));
    maxTextSizePixels = textPaint.getTextSize();
    return this;
  }

  /**
   * Sets the lower text size limit
   *
   * @param minTextSizeScaledPixels the minimum size to use for text in this view,
   * in scaled pixels.
   */
  @NonNull public TextSticker setMinTextSize(float minTextSizeScaledPixels) {
    minTextSizePixels = convertSpToPx(minTextSizeScaledPixels);
    return this;
  }

  @NonNull public TextSticker setLineSpacing(float add, float multiplier) {
    lineSpacingMultiplier = multiplier;
    lineSpacingExtra = add;
    return this;
  }

  @NonNull public TextSticker setText(@Nullable String text) {
    this.text = text;
    return this;
  }



  @Nullable public String getText() {
    return text;
  }

  /**
   * Resize this view's text size with respect to its width and height
   * (minus padding). You should always call this method after the initialization.
   */
  @NonNull public TextSticker resizeText() {
    final int availableHeightPixels = textRect.height();

    final int availableWidthPixels = textRect.width();

    final CharSequence text = getText();

    // Safety check
    // (Do not resize if the view does not have dimensions or if there is no text)
    if (text == null
        || text.length() <= 0
        || availableHeightPixels <= 0
        || availableWidthPixels <= 0
        || maxTextSizePixels <= 0) {
      return this;
    }

    float targetTextSizePixels = maxTextSizePixels;
    int targetTextHeightPixels =
        getTextHeightPixels(text, availableWidthPixels, targetTextSizePixels);

    // Until we either fit within our TextView
    // or we have reached our minimum text size,
    // incrementally try smaller sizes
    while (targetTextHeightPixels > availableHeightPixels
        && targetTextSizePixels > minTextSizePixels) {
      targetTextSizePixels = Math.max(targetTextSizePixels - 2, minTextSizePixels);

      targetTextHeightPixels =
          getTextHeightPixels(text, availableWidthPixels, targetTextSizePixels);
    }

    // If we have reached our minimum text size and the text still doesn't fit,
    // append an ellipsis
    // (NOTE: Auto-ellipsize doesn't work hence why we have to do it here)
    if (targetTextSizePixels == minTextSizePixels
        && targetTextHeightPixels > availableHeightPixels) {
      // Make a copy of the original TextPaint object for measuring
      TextPaint textPaintCopy = new TextPaint(textPaint);
      textPaintCopy.setTextSize(targetTextSizePixels);

      // Measure using a StaticLayout instance
      StaticLayout staticLayout =
          new StaticLayout(text, textPaintCopy, availableWidthPixels, Layout.Alignment.ALIGN_NORMAL,
              lineSpacingMultiplier, lineSpacingExtra, false);

      // Check that we have a least one line of rendered text
      if (staticLayout.getLineCount() > 0) {
        // Since the line at the specific vertical position would be cut off,
        // we must trim up to the previous line and add an ellipsis
        int lastLine = staticLayout.getLineForVertical(availableHeightPixels) - 1;

        if (lastLine >= 0) {
          int startOffset = staticLayout.getLineStart(lastLine);
          int endOffset = staticLayout.getLineEnd(lastLine);
          float lineWidthPixels = staticLayout.getLineWidth(lastLine);
          float ellipseWidth = textPaintCopy.measureText(mEllipsis);

          // Trim characters off until we have enough room to draw the ellipsis
          while (availableWidthPixels < lineWidthPixels + ellipseWidth) {
            endOffset--;
            lineWidthPixels =
                textPaintCopy.measureText(text.subSequence(startOffset, endOffset + 1).toString());
          }

          setText(text.subSequence(0, endOffset) + mEllipsis);
        }
      }
    }
    textPaint.setTextSize(targetTextSizePixels);
    staticLayout =
        new StaticLayout(this.text, textPaint, textRect.width(), alignment, lineSpacingMultiplier,
            lineSpacingExtra, true);
    return this;
  }

  /**
   * @return lower text size limit, in pixels.
   */
  public float getMinTextSizePixels() {
    return minTextSizePixels;
  }

  /**
   * Sets the text size of a clone of the view's {@link TextPaint} object
   * and uses a {@link StaticLayout} instance to measure the height of the text.
   *
   * @return the height of the text when placed in a view
   * with the specified width
   * and when the text has the specified size.
   */
  protected int getTextHeightPixels(@NonNull CharSequence source, int availableWidthPixels,
      float textSizePixels) {
    textPaint.setTextSize(textSizePixels);
    // It's not efficient to create a StaticLayout instance
    // every time when measuring, we can use StaticLayout.Builder
    // since api 23.
    StaticLayout staticLayout =
        new StaticLayout(source, textPaint, availableWidthPixels, Layout.Alignment.ALIGN_NORMAL,
            lineSpacingMultiplier, lineSpacingExtra, true);
    return staticLayout.getHeight();
  }

  /**
   * @return the number of pixels which scaledPixels corresponds to on the device.
   */








  private float convertSpToPx(float scaledPixels) {
    return scaledPixels * context.getResources().getDisplayMetrics().scaledDensity;
  }

  public TextSticker setText(String str, boolean z) {
    this.text = str;
    if (z) {
      this.setShapeDrawable(str);
    }

    return this;
  }
  private void setShapeDrawable(String str) {
    int i = this.context.getResources().getDisplayMetrics().widthPixels;


    int convertDpToPx = StickerUtils.convertDpToPx(str.length() * (int) this.minTextSizePixels);
//    if (convertDpToPx <= i) {
//      i = convertDpToPx < 100 ? (int) this.minTextSizePixels + convertDpToPx : convertDpToPx;
//    }

    int textHeightPixels = this.getTextHeightPixels(str, i, this.maxTextSizePixels);
    Log.e("TAG", "setShapeDrawable: "+i );
    Log.e("TAG", "setShapeDrawable: "+textHeightPixels );
//    if (textHeightPixels < 100) {
//      textHeightPixels += (int) this.minTextSizePixels;
//    }

    RectangleDrawable rectangleDrawable2 = new RectangleDrawable(i, textHeightPixels);
    this.rectangleDrawable = rectangleDrawable2;
    this.drawable = rectangleDrawable2;
    this.realBounds.set(0, 0, this.getWidth(), this.getHeight());
    this.textRect.set(0, 0, this.getWidth(), this.getHeight());
  }
  public void setTextUnderline(boolean z) {
    this.underlineText = z;
    this.textPaint.setUnderlineText(z);
    this.textPaintOutline.setUnderlineText(z);
  }
  public boolean isUnderlineText() {
    return this.underlineText;
  }

  public void setTextStrikeThru(boolean z) {
    this.strikeThruText = z;
    this.textPaint.setStrikeThruText(z);
    this.textPaintOutline.setStrikeThruText(z);
  }
  public boolean isStrikeThruText() {
    return this.strikeThruText;
  }

  public RectangleDrawable getRectangleDrawable() {
    return this.rectangleDrawable;
  }
  public void setTextBold(boolean z) {
    this.boldText = z;
  }
  public boolean isBoldText() {
    return this.boldText;
  }

  public void setTextItalic(boolean z) {
    this.italicText = z;
  }
  public boolean isItalicText() {
    return this.italicText;
  }

  public TextSticker setRectangleDrawable(RectangleDrawable rectangleDrawable2) {
    this.rectangleDrawable = rectangleDrawable2;
    return this.setDrawable(rectangleDrawable2);
  }





}
