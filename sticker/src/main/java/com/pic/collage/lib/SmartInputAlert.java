package com.pic.collage.lib;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.CycleInterpolator;
import android.view.animation.TranslateAnimation;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.core.content.res.ResourcesCompat;

import com.flask.colorpicker.ColorPickerView;
import com.flask.colorpicker.OnColorSelectedListener;
import com.flask.colorpicker.builder.ColorPickerClickListener;
import com.flask.colorpicker.builder.ColorPickerDialogBuilder;

import java.io.IOException;

public class SmartInputAlert extends Dialog {
    static final boolean $assertionsDisabled = false;
    public static final int BUTTON_BACK = 3;
    public static final int BUTTON_CANCEL = 2;
    public static final int BUTTON_OK = 1;
    private String[] assetsTypefaces = new String[0];
    public boolean boldText;
    private ImageView btnAlignment;
    private ImageView btnColor;
    private ImageView btnTextBold;
    private ImageView btnTextItalic;
    private ImageView btnTextStrike;
    private ImageView btnTextUnderline;
    public int colorCounter = 0;
    public LinearLayout colorLayout;
    public int defaultAlignment;
    public int defaultColor = 0x000000;
    public Typeface defaultTypeface;
    public int fontCounter = -1;
    private String fontDirectory;
    public boolean italicText;
    private String mAction1Title;
    private String mAction2Title;
    private String mAlertTitle;
    private TextView mBtnAction1;
    private TextView mBtnAction2;
    private String mEditText;
    public SmartInputAlertListener mListener;
    public int[] meterialColors = new int[]{-16777216, -769226, -1499549, -54125, -6543440, -10011977, -12627531, -14575885, -16537100, -16728876, -16738680, -11751600, -7617718, -3285959, -5317, -16121, -26624, -8825528, -10453621, -6381922, -1};
    private int[] resourceTypefaces;
    public EditText smartEditText;
    public boolean strikeText;
    public TextView tvFontView;
    public Typeface[] typefaces;
    public boolean underlineText;

    public int toggleAlignmentView(int i) {
        if (i == 49) {
            return 53;
        } else if (i == 53) {
            return 51;
        } else {
            return i == 51 ? 49 : i;
        }
    }

    static int access$1108(SmartInputAlert smartInputAlert) {
        int i = smartInputAlert.colorCounter++;
        return i;
    }

    static int access$1508(SmartInputAlert smartInputAlert) {
        int i = smartInputAlert.fontCounter++;
        return i;
    }

    @SuppressLint("WrongConstant")
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.setContentView(R.layout.alert_smartinput);
        ((TextView) this.findViewById(R.id.txtTitle)).setText(this.mAlertTitle);
        EditText editText = (EditText) this.findViewById(R.id.smartInput);
        this.smartEditText = editText;
//        editText.setTextColor(this.defaultColor);

        this.smartEditText.setTypeface(this.defaultTypeface);
        String str = this.mEditText;
        if (str != null) {
            this.smartEditText.setText(str);
        }

        this.mBtnAction1 = (TextView) this.findViewById(R.id.btnAction1);
        this.mBtnAction2 = (TextView) this.findViewById(R.id.btnAction2);
        this.activeResultButton();
        this.mBtnAction1.setText(this.mAction1Title);
        this.mBtnAction1.setOnClickListener(v -> {
            if (SmartInputAlert.this.smartEditText.getText().length() > 0) {
                if (SmartInputAlert.this.mListener != null) {
                    SmartInput smartInput = new SmartInput(SmartInputAlert.this.smartEditText.getText().toString(), SmartInputAlert.this.defaultColor, SmartInputAlert.this.defaultTypeface);
                    smartInput.setBoldText(SmartInputAlert.this.boldText);
                    smartInput.setItalicText(SmartInputAlert.this.italicText);
                    smartInput.setUnderlineText(SmartInputAlert.this.underlineText);
                    smartInput.setStrikeThruText(SmartInputAlert.this.strikeText);
                    smartInput.setGravity(SmartInputAlert.this.defaultAlignment);
                    SmartInputAlert.this.mListener.result(1, smartInput);
                }

                SmartInputAlert.this.dismiss();
            } else {
                SmartInputAlert.this.smartEditText.startAnimation(SmartInputAlert.this.shakeEdit());
            }
        });
//        String str2 = this.mAction2Title;
        String str2 = getContext().getString(R.string.cancel);
        if (str2 != null) {
            this.mBtnAction2.setText(str2);
            this.mBtnAction2.setOnClickListener(v -> {
                if (SmartInputAlert.this.mListener != null) {
                    SmartInputAlert.this.mListener.result(2, new SmartInput(SmartInputAlert.this.smartEditText.getText().toString(), SmartInputAlert.this.defaultColor, SmartInputAlert.this.defaultTypeface));
                }

                SmartInputAlert.this.dismiss();
            });
        } else {
            this.mBtnAction2.setVisibility(8);
        }

        ImageView ImageView = (ImageView) this.findViewById(R.id.btnAlignment);
        this.btnAlignment = ImageView;
        ImageView.setOnClickListener(v -> {
            SmartInputAlert smartInputAlert = SmartInputAlert.this;
            smartInputAlert.defaultAlignment = smartInputAlert.toggleAlignmentView(smartInputAlert.smartEditText.getGravity());
            SmartInputAlert.this.updateAlignmentView();
        });
        this.updateAlignmentView();
        LinearLayout linearLayout = (LinearLayout) this.findViewById(R.id.colorLayout);
        this.colorLayout = linearLayout;
        linearLayout.setBackgroundColor(this.defaultColor);
        ImageView ImageView2 = (ImageView) this.findViewById(R.id.btnTextColor);

        this.btnColor = ImageView2;
        ImageView2.setOnClickListener(v -> {
            AlertDialog dialog = ColorPickerDialogBuilder.with(getContext())
                    .setTitle(R.string.choose_color)
                    .initialColor(this.defaultColor)
                    .wheelType(ColorPickerView.WHEEL_TYPE.FLOWER)
                    .setOnColorSelectedListener(new OnColorSelectedListener() {
                        public void onColorSelected(int selectedColor) {
                            SmartInputAlert smartInputAlert = SmartInputAlert.this;
                            smartInputAlert.defaultColor = selectedColor;
                            SmartInputAlert.this.colorLayout.setBackgroundColor(SmartInputAlert.this.defaultColor);
                            SmartInputAlert.this.smartEditText.setTextColor(SmartInputAlert.this.defaultColor);
                        }
                    })
                    .setPositiveButton(R.string.OK, new ColorPickerClickListener() {
                        public void onClick(DialogInterface dialog, int selectedColor, Integer[] allColors) {
                        }
                    }).setNegativeButton(R.string.cancel, new OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                        }
                    }).build();

            dialog.show();
        });
        if (this.fontDirectory != null) {
            try {
                String[] list = this.getContext().getAssets().list(this.fontDirectory);
                this.assetsTypefaces = list;
                int length = list.length + this.resourceTypefaces.length;
                this.typefaces = new Typeface[length];

                for (int i = 0; i < length; ++i) {
                    if (i < this.assetsTypefaces.length) {
                        this.typefaces[i] = Typeface.createFromAsset(this.getContext().getAssets(), this.fontDirectory.concat("/").concat(this.assetsTypefaces[i]));
                    } else {
                        this.typefaces[i] = ResourcesCompat.getFont(this.getContext(), this.resourceTypefaces[i - this.assetsTypefaces.length]);
                    }
                }
            } catch (IOException var14) {
                this.smartEditText.setText(var14.toString());
            }
        }

        if (this.assetsTypefaces.length == 0) {
            this.typefaces = new Typeface[this.resourceTypefaces.length];

            for (int i2 = 0; i2 < this.resourceTypefaces.length; ++i2) {
                this.typefaces[i2] = ResourcesCompat.getFont(this.getContext(), this.resourceTypefaces[i2]);
            }
        }

        if (this.typefaces.length == 0) {
            this.findViewById(R.id.fontsLayout).setVisibility(8);
        }

        TextView textView = (TextView) this.findViewById(R.id.btnTextFont);
        this.tvFontView = textView;
        textView.setTypeface(this.defaultTypeface);
        if (this.defaultTypeface.equals(ResourcesCompat.getFont(this.getContext(), R.font.font_2))) {
            this.tvFontView.setText("اج");
            this.smartEditText.setHint("یہاں سے لکھنا شروع کریں۔۔۔");
        } else {
            this.tvFontView.setText("A");
            this.smartEditText.setHint(getContext().getString(R.string.Start_typing_here));
        }

        this.tvFontView.setOnClickListener(v -> {
            if (SmartInputAlert.this.typefaces.length > 0) {
                SmartInputAlert.access$1508(SmartInputAlert.this);
                if (SmartInputAlert.this.fontCounter == SmartInputAlert.this.typefaces.length) {
                    SmartInputAlert.this.fontCounter = -1;
                    SmartInputAlert.this.defaultTypeface = Typeface.DEFAULT;
                } else {
                    SmartInputAlert smartInputAlert = SmartInputAlert.this;
                    smartInputAlert.defaultTypeface = smartInputAlert.typefaces[SmartInputAlert.this.fontCounter];
                }

                if (SmartInputAlert.this.defaultTypeface.equals(ResourcesCompat.getFont(SmartInputAlert.this.getContext(), R.font.font_2))) {
                    SmartInputAlert.this.tvFontView.setText("اج");
                    SmartInputAlert.this.smartEditText.setHint("یہاں سے لکھنا شروع کریں۔۔۔");
                } else {
                    SmartInputAlert.this.tvFontView.setText("A");
                    SmartInputAlert.this.smartEditText.setHint(getContext().getString(R.string.Start_typing_here));
                }

                SmartInputAlert.this.tvFontView.setTypeface(SmartInputAlert.this.defaultTypeface);
                SmartInputAlert.this.smartEditText.setTypeface(SmartInputAlert.this.defaultTypeface, SmartInputAlert.this.getBoldItalicTextStyle());
            }
        });
        ImageView ImageView3 = (ImageView) this.findViewById(R.id.btnTextBold);
        this.btnTextBold = ImageView3;
        ImageView3.setOnClickListener(v -> {
            SmartInputAlert smartInputAlert = SmartInputAlert.this;
            smartInputAlert.boldText = !smartInputAlert.boldText;
            SmartInputAlert.this.smartEditText.setTypeface(SmartInputAlert.this.defaultTypeface, SmartInputAlert.this.getBoldItalicTextStyle());
            if (SmartInputAlert.this.boldText) {
                ImageView3.setColorFilter(getContext().getColor(R.color.main));
            } else {
                ImageView3.setColorFilter(getContext().getColor(R.color.sub));
            }
        });
        if (this.boldText) {
            ((LinearLayout) this.findViewById(R.id.boldTextLayout)).addView(new View(this.getContext()));
            this.smartEditText.setTypeface(this.defaultTypeface, this.getBoldItalicTextStyle());
        }

        ImageView ImageView4 = (ImageView) this.findViewById(R.id.btnTextItalic);
        this.btnTextItalic = ImageView4;
        ImageView4.setOnClickListener(v -> {
            SmartInputAlert smartInputAlert = SmartInputAlert.this;
            smartInputAlert.italicText = !smartInputAlert.italicText;
            SmartInputAlert.this.smartEditText.setTypeface(SmartInputAlert.this.defaultTypeface, SmartInputAlert.this.getBoldItalicTextStyle());
            if (SmartInputAlert.this.italicText) {
                ImageView4.setColorFilter(getContext().getColor(R.color.main));
            } else {
                ImageView4.setColorFilter(getContext().getColor(R.color.sub));
            }
        });
        if (this.italicText) {
            ((LinearLayout) this.findViewById(R.id.italicTextLayout)).addView(new View(this.getContext()));
            this.smartEditText.setTypeface(this.defaultTypeface, this.getBoldItalicTextStyle());
        }

        ImageView ImageView5 = (ImageView) this.findViewById(R.id.btnTextUnderline);
        this.btnTextUnderline = ImageView5;
        ImageView5.setOnClickListener(v -> {
            SmartInputAlert smartInputAlert = SmartInputAlert.this;
            smartInputAlert.underlineText = !smartInputAlert.underlineText;
            SmartInputAlert.this.smartEditText.setPaintFlags(SmartInputAlert.this.smartEditText.getPaintFlags() ^ 8);
            if (SmartInputAlert.this.underlineText) {
                ImageView5.setColorFilter(getContext().getColor(R.color.main));
            } else {
                ImageView5.setColorFilter(getContext().getColor(R.color.sub));
            }
        });
        if (this.underlineText) {
            ((LinearLayout) this.findViewById(R.id.underlineTextLayout)).addView(new View(this.getContext()));
            EditText editText2 = this.smartEditText;
            editText2.setPaintFlags(8 ^ editText2.getPaintFlags());
        }

        ImageView ImageView6 = (ImageView) this.findViewById(R.id.btnTextStrike);
        this.btnTextStrike = ImageView6;
        ImageView6.setOnClickListener(v -> {
            SmartInputAlert smartInputAlert = SmartInputAlert.this;
            smartInputAlert.strikeText = !smartInputAlert.strikeText;
            SmartInputAlert.this.smartEditText.setPaintFlags(SmartInputAlert.this.smartEditText.getPaintFlags() ^ 16);
            if (SmartInputAlert.this.strikeText) {
                ImageView6.setColorFilter(getContext().getColor(R.color.main));
            } else {
                ImageView6.setColorFilter(getContext().getColor(R.color.sub));
            }
        });
        if (this.strikeText) {
            ((LinearLayout) this.findViewById(R.id.strikeThruTextLayout)).addView(new View(this.getContext()));
            EditText editText3 = this.smartEditText;
            editText3.setPaintFlags(editText3.getPaintFlags() ^ 16);
        }

        this.setOnDismissListener(new OnDismissListener() {
            public void onDismiss(DialogInterface dialogInterface) {
            }
        });
        this.setCancelable(false);
    }

    public int getBoldItalicTextStyle() {
        if (this.boldText && this.italicText) {
            return 3;
        } else if (this.boldText) {
            return 1;
        } else {
            return this.italicText ? 2 : 0;
        }
    }

    public TranslateAnimation shakeEdit() {
        TranslateAnimation translateAnimation = new TranslateAnimation(0.0F, 0.0F, 0.0F, 20.0F);
        translateAnimation.setDuration(500L);
        translateAnimation.setInterpolator(new CycleInterpolator(3.0F));
        return translateAnimation;
    }

    @SuppressLint("WrongConstant")
    private void closeKeyboard() {
        View currentFocus = this.getCurrentFocus();
        if (currentFocus != null) {
            ((InputMethodManager) this.getContext().getSystemService("input_method")).hideSoftInputFromWindow(currentFocus.getWindowToken(), 0);
        }

    }

    public void onBackPressed() {
        SmartInputAlertListener smartInputAlertListener = this.mListener;
        if (smartInputAlertListener != null) {
            smartInputAlertListener.result(3, new SmartInput(this.smartEditText.getText().toString(), this.defaultColor, this.defaultTypeface));
        }

    }

    private void activeResultButton() {
        this.mBtnAction1.setEnabled(true);
        this.mBtnAction2.setEnabled(true);
    }

    public void updateAlignmentView() {
        int i = this.defaultAlignment;
        if (i == 49) {
            this.btnAlignment.setImageResource(R.drawable.ic_align_white);
        } else if (i == 51) {
            this.btnAlignment.setImageResource(R.drawable.ic_format_align_left);
        } else if (i == 53) {
            this.btnAlignment.setImageResource(R.drawable.ic_format_align_right);
        }

        this.smartEditText.setGravity(this.defaultAlignment);
    }

    @SuppressLint("ResourceType")
    public SmartInputAlert(Context context, SmartInput smartInput, String str, String str2, String str3, SmartInputAlertListener smartInputAlertListener) {
        super(context, 16974128);
        int w = (int) (context.getResources().getDisplayMetrics().widthPixels * 0.7);
        int h = ViewGroup.LayoutParams.WRAP_CONTENT;
        SmartInputAlert.this.getWindow().setLayout(w, h);
        this.resourceTypefaces = new int[]{R.font.font_1, R.font.font_2};
        this.typefaces = new Typeface[0];
        this.fontDirectory = smartInput.getFontDirectory();
        this.mEditText = smartInput.getText();
        this.defaultColor = smartInput.getTextColor();
        this.defaultTypeface = smartInput.getTypeface();
        this.defaultAlignment = smartInput.getGravity();
        this.boldText = smartInput.isBoldText();
        this.italicText = smartInput.isItalicText();
        this.underlineText = smartInput.isUnderlineText();
        this.strikeText = smartInput.isStrikeThruText();
        this.mAlertTitle = str;
        this.mAction1Title = str2;
        this.mAction2Title = str3;
        this.mListener = smartInputAlertListener;
    }

    public static void select(Context context, SmartInput smartInput, String str, String str2, String str3, SmartInputAlertListener smartInputAlertListener) {
        (new SmartInputAlert(context, smartInput, str, str2, str3, smartInputAlertListener)).show();
    }

    @SuppressLint("ResourceType")
    public static void input(Context context, SmartInput smartInput, String str, SmartInputAlertListener smartInputAlertListener) {
        select(context, smartInput, str, context.getString(17039370), context.getString(17039360), smartInputAlertListener);
    }

    @SuppressLint("ResourceType")
    public static void edit(Context context, SmartInput smartInput, String str, SmartInputAlertListener smartInputAlertListener) {
        select(context, smartInput, str, context.getString(17039370), context.getString(17039360), smartInputAlertListener);
    }

    public interface SmartInputAlertListener {
        void result(int var1, SmartInput var2);
    }
}

