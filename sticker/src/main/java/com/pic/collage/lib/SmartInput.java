package com.pic.collage.lib;

import android.graphics.Typeface;
import android.text.Layout;

public class SmartInput {
    private boolean boldText;
    private String fontDirectory;
    private int gravity;
    private boolean italicText;
    private boolean strikeThruText;
    private String text;
    private int textColor;
    private Typeface typeface;
    private boolean underlineText;

    public SmartInput() {
        this.text = null;
        this.textColor = -16777216;
        this.typeface = Typeface.DEFAULT;
        this.gravity = 51;
    }

    public SmartInput(String str, int i, Typeface typeface2) {
        this.text = str;
        this.textColor = i;
        this.typeface = typeface2;
    }

    public String getFontDirectory() {
        return this.fontDirectory;
    }

    public void setFontDirectory(String str) {
        this.fontDirectory = str;
    }

    public String getText() {
        return this.text;
    }

    public void setText(String str) {
        this.text = str;
    }

    public int getGravity() {
        return this.gravity;
    }

    public void setGravity(int i) {
        this.gravity = i;
    }

    public int getTextColor() {
        return this.textColor;
    }

    public void setTextColor(int i) {
        this.textColor = i;
    }

    public Typeface getTypeface() {
        return this.typeface;
    }

    public void setTypeface(Typeface typeface2) {
        this.typeface = typeface2;
    }

    public boolean isBoldText() {
        return this.boldText;
    }

    public void setBoldText(boolean z) {
        this.boldText = z;
    }

    public boolean isItalicText() {
        return this.italicText;
    }

    public void setItalicText(boolean z) {
        this.italicText = z;
    }

    public boolean isUnderlineText() {
        return this.underlineText;
    }

    public void setUnderlineText(boolean z) {
        this.underlineText = z;
    }

    public boolean isStrikeThruText() {
        return this.strikeThruText;
    }

    public void setStrikeThruText(boolean z) {
        this.strikeThruText = z;
    }

    public Layout.Alignment getTextAlignment() {
        Layout.Alignment alignment = Layout.Alignment.ALIGN_NORMAL;
        int i = this.gravity;
        if (i == 49) {
            return Layout.Alignment.ALIGN_CENTER;
        } else if (i == 51) {
            return Layout.Alignment.ALIGN_NORMAL;
        } else {
            return i != 53 ? alignment : Layout.Alignment.ALIGN_OPPOSITE;
        }
    }

    public void setTextAlignment(Layout.Alignment alignment) {
        int i = AnonymousClass1.$SwitchMap$android$text$Layout$Alignment[alignment.ordinal()];
        if (i == 1) {
            this.gravity = 51;
        } else if (i == 2) {
            this.gravity = 49;
        } else if (i == 3) {
            this.gravity = 53;
        }

    }

    @Override
    public String toString() {
        return "SmartInput{" +
                "boldText=" + boldText +
                ", fontDirectory='" + fontDirectory + '\'' +
                ", gravity=" + gravity +
                ", italicText=" + italicText +
                ", strikeThruText=" + strikeThruText +
                ", text='" + text + '\'' +
                ", textColor=" + textColor +
                ", typeface=" + typeface +
                ", underlineText=" + underlineText +
                '}';
    }

    static class AnonymousClass1 {
        static final int[] $SwitchMap$android$text$Layout$Alignment;

        AnonymousClass1() {
        }

        static {
            int[] iArr = new int[Layout.Alignment.values().length];
            $SwitchMap$android$text$Layout$Alignment = iArr;
            iArr[Layout.Alignment.ALIGN_NORMAL.ordinal()] = 1;
            $SwitchMap$android$text$Layout$Alignment[Layout.Alignment.ALIGN_CENTER.ordinal()] = 2;

            try {
                $SwitchMap$android$text$Layout$Alignment[Layout.Alignment.ALIGN_OPPOSITE.ordinal()] = 3;
            } catch (NoSuchFieldError var2) {
            }

        }
    }
}

