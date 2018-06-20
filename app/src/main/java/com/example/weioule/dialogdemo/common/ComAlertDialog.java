package com.example.weioule.dialogdemo.common;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.StateListDrawable;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.util.Log;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.TextView;


/**
 * author weioule
 * Create on 2018/6/20.
 * 这是不支持设置动画的dialog
 */
public class ComAlertDialog extends Dialog {
    /**
     * (DisplayMetrics)设备密度
     */
    protected DisplayMetrics dm;
    /**
     * dialog width scale(宽度比例)
     */
    protected float widthScale = 1;
    /**
     * dialog height scale(高度比例)
     */
    protected float heightScale;
    /**
     * max height(最大高度)
     */
    protected float maxHeight;
    /**
     * container layout to control dialog height(用于控制对话框高度)
     */
    protected LinearLayout ll_control_height;
    /**
     * context(上下文)
     */
    protected Context context;
    /**
     * top container(最上层容器)
     */
    protected LinearLayout ll_top;
    /**
     * container layout
     */
    protected LinearLayout ll_container;
    /**
     * title
     */
    protected TextView tv_title;
    /**
     * title content(标题)
     */
    protected String title;
    /**
     * title textcolor(标题颜色)
     */
    protected int titleTextColor;
    /**
     * title textsize(标题字体大小,单位sp)
     */
    protected float titleTextSize_SP;
    /**
     * enable title show(是否显示标题)
     */
    protected boolean isTitleShow = true;
    /**
     * content
     */
    protected TextView tv_content;
    /**
     * content text
     */
    protected String content;
    /**
     * show gravity of content(正文内容显示位置)
     */
    protected int contentGravity = Gravity.CENTER_VERTICAL;
    /**
     * content textcolor(正文字体颜色)
     */
    protected int contentTextColor;
    /**
     * content textsize(正文字体大小)
     */
    protected float contentTextSize_SP;
    /**
     * num of btns, [1,3]
     */
    protected int btnNum = 2;
    /**
     * btn layout
     */
    protected LinearLayout ll_btns;
    /**
     * btns
     */
    protected TextView tv_btn_left;
    protected TextView tv_btn_right;
    protected TextView tv_btn_middle;
    /**
     * btn text(按钮内容)
     */
    protected String btnLeftText = "取消";
    protected String btnRightText = "确定";
    protected String btnMiddleText = "继续";
    /**
     * btn textcolor(按钮字体颜色)
     */
    protected int leftBtnTextColor;
    protected int rightBtnTextColor;
    protected int middleBtnTextColor;
    /**
     * btn textsize(按钮字体大小)
     */
    protected float leftBtnTextSize_SP = 15f;
    protected float rightBtnTextSize_SP = 15f;
    protected float middleBtnTextSize_SP = 15f;
    /**
     * btn press color(按钮点击颜色)
     * #E3E3E3,#85D3EF,#ffcccccc,#E3E3E3
     */
    protected int btnPressColor = Color.parseColor("#E3E3E3");
    /**
     * left btn click listener(左按钮接口)
     */
    protected ComAlertDialog.OnBtnClickL onBtnLeftClickL;
    /**
     * middle btn click listener(中间钮接口)
     */
    protected ComAlertDialog.OnBtnClickL onBtnMiddleClickL;
    /**
     * right btn click listener(右按钮接口)
     */
    protected ComAlertDialog.OnBtnClickL onBtnRightClickL;
    /**
     * corner radius,dp(圆角程度,单位dp)
     */
    protected float cornerRadius_DP = 3;
    /**
     * background color(背景颜色)
     */
    protected int bgColor = Color.parseColor("#ffffff");
    /**
     * enable dismiss outside dialog(设置点击对话框以外区域,是否dismiss)
     */
    protected boolean cancel;


    /**
     * method execute order:
     * show:constrouctor---show---oncreate---onStart---onAttachToWindow
     * dismiss:dismiss---onDetachedFromWindow---onStop
     *
     * @param context
     */
    public ComAlertDialog(Context context) {
        super(context);
        setDialogTheme();
        this.context = context;
        setWidthScale(0.88f);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        dm = context.getResources().getDisplayMetrics();
        ll_top = new LinearLayout(context);
        ll_top.setGravity(Gravity.CENTER);

        ll_control_height = new LinearLayout(context);
        ll_control_height.setOrientation(LinearLayout.VERTICAL);

        ll_control_height.addView(onCreateView());
        ll_top.addView(ll_control_height);

        maxHeight = dm.heightPixels - StatusBarUtils.getHeight(context);
        setContentView(ll_top, new ViewGroup.LayoutParams(dm.widthPixels, (int) maxHeight));

        ll_top.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (cancel) {
                    dismiss();
                }
            }
        });
    }

    public View onCreateView() {
        ll_container = new LinearLayout(context);
        ll_container.setOrientation(LinearLayout.VERTICAL);
        /** title */
        tv_title = new TextView(context);
        tv_title.setGravity(Gravity.CENTER_VERTICAL);
        tv_title.setPadding(dp2px(20), dp2px(20), dp2px(20), dp2px(0));
        tv_title.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
        ll_container.addView(tv_title);

        /** content */
        tv_content = new TextView(context);
        tv_content.setPadding(dp2px(20), dp2px(20), dp2px(20), dp2px(20));
        tv_content.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
        ll_container.addView(tv_content);

        /** btn */
        tv_btn_left = new TextView(context);
        tv_btn_left.setGravity(Gravity.CENTER);

        tv_btn_middle = new TextView(context);
        tv_btn_middle.setGravity(Gravity.CENTER);

        tv_btn_right = new TextView(context);
        tv_btn_right.setGravity(Gravity.CENTER);

        /** btn_layout */
        ll_btns = new LinearLayout(context);
        ll_btns.setOrientation(LinearLayout.HORIZONTAL);
        ll_btns.setGravity(Gravity.RIGHT);
        ll_btns.addView(tv_btn_left);
        ll_btns.addView(tv_btn_middle);
        ll_btns.addView(tv_btn_right);

        tv_btn_left.setPadding(dp2px(15), dp2px(8), dp2px(15), dp2px(8));
        tv_btn_right.setPadding(dp2px(15), dp2px(8), dp2px(15), dp2px(8));
        tv_btn_middle.setPadding(dp2px(15), dp2px(8), dp2px(15), dp2px(8));
        ll_btns.setPadding(dp2px(20), dp2px(0), dp2px(10), dp2px(10));

        ll_container.addView(ll_btns);
        return ll_container;
    }

    @Override
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        setUiBeforShow();

        int width;
        if (widthScale == 0) {
            width = ViewGroup.LayoutParams.WRAP_CONTENT;
        } else {
            width = (int) (dm.widthPixels * widthScale);
        }

        int height;
        if (heightScale == 0) {
            height = ViewGroup.LayoutParams.WRAP_CONTENT;
        } else if (heightScale == 1) {
            height = ViewGroup.LayoutParams.MATCH_PARENT;
        } else {
            height = (int) (maxHeight * heightScale);
        }

        ll_control_height.setLayoutParams(new LinearLayout.LayoutParams(width, height));
    }

    public void setUiBeforShow() {
        /** title */
        tv_title.setVisibility(isTitleShow ? View.VISIBLE : View.GONE);
        tv_title.setText(TextUtils.isEmpty(title) ? "温馨提示" : title);
        tv_title.setTextColor(titleTextColor);
        tv_title.setTextSize(TypedValue.COMPLEX_UNIT_SP, titleTextSize_SP);

        /** content */
        tv_content.setGravity(contentGravity);
        tv_content.setText(content);
        tv_content.setTextColor(contentTextColor);
        tv_content.setTextSize(TypedValue.COMPLEX_UNIT_SP, contentTextSize_SP);
        tv_content.setLineSpacing(0, 1.3f);

        /**btns*/
        tv_btn_left.setText(btnLeftText);
        tv_btn_right.setText(btnRightText);
        tv_btn_middle.setText(btnMiddleText);

        tv_btn_left.setTextColor(leftBtnTextColor);
        tv_btn_right.setTextColor(rightBtnTextColor);
        tv_btn_middle.setTextColor(middleBtnTextColor);

        tv_btn_left.setTextSize(TypedValue.COMPLEX_UNIT_SP, leftBtnTextSize_SP);
        tv_btn_right.setTextSize(TypedValue.COMPLEX_UNIT_SP, rightBtnTextSize_SP);
        tv_btn_middle.setTextSize(TypedValue.COMPLEX_UNIT_SP, middleBtnTextSize_SP);

        float radius = dp2px(cornerRadius_DP);
        ll_container.setBackgroundDrawable(ComAlertDialog.CornerUtils.cornerDrawable(bgColor, radius));
        tv_btn_left.setBackgroundDrawable(ComAlertDialog.CornerUtils.btnSelector(radius, bgColor, btnPressColor, -2));
        tv_btn_right.setBackgroundDrawable(ComAlertDialog.CornerUtils.btnSelector(radius, bgColor, btnPressColor, -2));
        tv_btn_middle.setBackgroundDrawable(ComAlertDialog.CornerUtils.btnSelector(radius, bgColor, btnPressColor, -2));

        if (btnNum == 1) {
            tv_btn_left.setVisibility(View.GONE);
            tv_btn_right.setVisibility(View.GONE);
        } else if (btnNum == 2) {
            tv_btn_middle.setVisibility(View.GONE);
        }

        tv_btn_left.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onBtnLeftClickL != null) {
                    onBtnLeftClickL.onBtnClick();
                } else {
                    dismiss();
                }
            }
        });

        tv_btn_right.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onBtnRightClickL != null) {
                    onBtnRightClickL.onBtnClick();
                } else {
                    dismiss();
                }
            }
        });

        tv_btn_middle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onBtnMiddleClickL != null) {
                    onBtnMiddleClickL.onBtnClick();
                } else {
                    dismiss();
                }
            }
        });
    }

    /**
     * set title text(设置标题内容) @return MaterialDialog
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * set title textcolor(设置标题字体颜色)
     */
    public void setTitleTextColor(int titleTextColor) {
        this.titleTextColor = titleTextColor;
    }

    /**
     * set title textsize(设置标题字体大小)
     */
    public void setTitleTextSize(float titleTextSize_SP) {
        this.titleTextSize_SP = titleTextSize_SP;
    }

    /**
     * enable title show(设置标题是否显示)
     */
    public void setIsTitleShow(boolean isTitleShow) {
        this.isTitleShow = isTitleShow;
    }

    /**
     * set content text(设置正文内容)
     */
    public void setContent(String content) {
        this.content = content;
    }

    /**
     * set content gravity(设置正文内容,显示位置)
     */
    public void setContentGravity(int contentGravity) {
        this.contentGravity = contentGravity;
    }

    /**
     * set content textcolor(设置正文字体颜色)
     */
    public void setContentTextColor(int contentTextColor) {
        this.contentTextColor = contentTextColor;
    }

    /**
     * set content textsize(设置正文字体大小,单位sp)
     */
    public void setContentTextSize(float contentTextSize_SP) {
        this.contentTextSize_SP = contentTextSize_SP;
    }

    /**
     * set btn text(设置按钮文字内容)
     * btnTexts size 1, middle
     * btnTexts size 2, left right
     * btnTexts size 3, left right middle
     */
    public void setBtnNum(int btnNum) {
        if (btnNum < 1 || btnNum > 3) {
            throw new IllegalStateException("btnNum is [1,3]!");
        }
        this.btnNum = btnNum;
    }

    /**
     * set btn text(设置按钮文字内容)
     * btnTexts size 1, middle
     * btnTexts size 2, left right
     * btnTexts size 3, left right middle
     */
    public void setBtnText(String... btnTexts) {
        if (btnTexts.length < 1 || btnTexts.length > 3) {
            throw new IllegalStateException(" range of param btnTexts length is [1,3]!");
        }

        if (btnTexts.length == 1) {
            this.btnMiddleText = btnTexts[0];
        } else if (btnTexts.length == 2) {
            this.btnLeftText = btnTexts[0];
            this.btnRightText = btnTexts[1];
        } else if (btnTexts.length == 3) {
            this.btnLeftText = btnTexts[0];
            this.btnRightText = btnTexts[1];
            this.btnMiddleText = btnTexts[2];
        }
    }

    /**
     * set btn textcolor(设置按钮字体颜色)
     * btnTextColors size 1, middle
     * btnTextColors size 2, left right
     * btnTextColors size 3, left right middle
     */
    public void setBtnTextColor(int... btnTextColors) {
        if (btnTextColors.length < 1 || btnTextColors.length > 3) {
            throw new IllegalStateException(" range of param textColors length is [1,3]!");
        }

        if (btnTextColors.length == 1) {
            this.middleBtnTextColor = btnTextColors[0];
        } else if (btnTextColors.length == 2) {
            this.leftBtnTextColor = btnTextColors[0];
            this.rightBtnTextColor = btnTextColors[1];
        } else if (btnTextColors.length == 3) {
            this.leftBtnTextColor = btnTextColors[0];
            this.rightBtnTextColor = btnTextColors[1];
            this.middleBtnTextColor = btnTextColors[2];
        }
    }

    /**
     * set btn textsize(设置字体大小,单位sp)
     * btnTextSizes size 1, middle
     * btnTextSizes size 2, left right
     * btnTextSizes size 3, left right middle
     */
    public void setBtnTextSize(float... btnTextSizes) {
        if (btnTextSizes.length < 1 || btnTextSizes.length > 3) {
            throw new IllegalStateException(" range of param btnTextSizes length is [1,3]!");
        }

        if (btnTextSizes.length == 1) {
            this.middleBtnTextSize_SP = btnTextSizes[0];
        } else if (btnTextSizes.length == 2) {
            this.leftBtnTextSize_SP = btnTextSizes[0];
            this.rightBtnTextSize_SP = btnTextSizes[1];
        } else if (btnTextSizes.length == 3) {
            this.leftBtnTextSize_SP = btnTextSizes[0];
            this.rightBtnTextSize_SP = btnTextSizes[1];
            this.middleBtnTextSize_SP = btnTextSizes[2];
        }
    }

    /**
     * set btn press color(设置按钮点击颜色)
     */
    public void setBtnPressColor(int btnPressColor) {
        this.btnPressColor = btnPressColor;
    }

    /**
     * set corner radius (设置圆角程度)
     */
    public void setCornerRadius(float cornerRadius_DP) {
        this.cornerRadius_DP = cornerRadius_DP;
    }

    /**
     * set backgroud color(设置背景色)
     */
    public void setBgColor(int bgColor) {
        this.bgColor = bgColor;
    }

    /**
     * set window dim or not(设置背景是否昏暗)
     */
    public void dimEnabled(boolean isDimEnabled) {
        if (isDimEnabled) {
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
        } else {
            getWindow().clearFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
        }
    }

    /**
     * set btn click listener(设置按钮监听事件)
     * onBtnClickLs size 1, middle
     * onBtnClickLs size 2, left right
     * onBtnClickLs size 3, left right middle
     */
    public void setOnBtnClickL(ComAlertDialog.OnBtnClickL... onBtnClickLs) {
        if (onBtnClickLs.length < 1 || onBtnClickLs.length > 3) {
            throw new IllegalStateException(" range of param onBtnClickLs length is [1,3]!");
        }

        if (onBtnClickLs.length == 1) {
            this.onBtnMiddleClickL = onBtnClickLs[0];
        } else if (onBtnClickLs.length == 2) {
            this.onBtnLeftClickL = onBtnClickLs[0];
            this.onBtnRightClickL = onBtnClickLs[1];
        } else if (onBtnClickLs.length == 3) {
            this.onBtnLeftClickL = onBtnClickLs[0];
            this.onBtnRightClickL = onBtnClickLs[1];
            this.onBtnMiddleClickL = onBtnClickLs[2];
        }
    }

    /**
     * set dialog width scale:0-1(设置对话框宽度,占屏幕宽的比例0-1)
     */
    public void setWidthScale(float widthScale) {
        this.widthScale = widthScale;
    }

    /**
     * set dialog height scale:0-1(设置对话框高度,占屏幕宽的比例0-1)
     */
    public void setHeightScale(float heightScale) {
        this.heightScale = heightScale;
    }

    @Override
    public void setCanceledOnTouchOutside(boolean cancel) {
        this.cancel = cancel;
        super.setCanceledOnTouchOutside(cancel);
    }

    /**
     * dp to px
     */
    protected int dp2px(float dp) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dp * scale + 0.5f);
    }

    /**
     * set dialog theme(设置对话框主题)
     */
    private void setDialogTheme() {
        requestWindowFeature(Window.FEATURE_NO_TITLE);// android:windowNoTitle
        getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));// android:windowBackground
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);// android:backgroundDimEnabled默认是true的
    }

    public interface OnBtnClickL {
        void onBtnClick();
    }


    /**
     * Utils to get corner drawable
     */
    public static class CornerUtils {
        public static Drawable cornerDrawable(final int bgColor, float cornerradius) {
            final GradientDrawable bg = new GradientDrawable();
            bg.setCornerRadius(cornerradius);
            bg.setColor(bgColor);

            return bg;
        }

        public static Drawable cornerDrawable(final int bgColor, float[] cornerradius) {
            final GradientDrawable bg = new GradientDrawable();
            bg.setCornerRadii(cornerradius);
            bg.setColor(bgColor);

            return bg;
        }

        /**
         * set btn selector with corner drawable for special position
         */
        public static StateListDrawable btnSelector(float radius, int normalColor, int pressColor, int postion) {
            StateListDrawable bg = new StateListDrawable();
            Drawable normal = null;
            Drawable pressed = null;

            if (postion == 0) {// left btn
                normal = cornerDrawable(normalColor, new float[]{0, 0, 0, 0, 0, 0, radius, radius});
                pressed = cornerDrawable(pressColor, new float[]{0, 0, 0, 0, 0, 0, radius, radius});
            } else if (postion == 1) {// right btn
                normal = cornerDrawable(normalColor, new float[]{0, 0, 0, 0, radius, radius, 0, 0});
                pressed = cornerDrawable(pressColor, new float[]{0, 0, 0, 0, radius, radius, 0, 0});
            } else if (postion == -1) {// only one btn
                normal = cornerDrawable(normalColor, new float[]{0, 0, 0, 0, radius, radius, radius, radius});
                pressed = cornerDrawable(pressColor, new float[]{0, 0, 0, 0, radius, radius, radius, radius});
            } else if (postion == -2) {// for material dialog
                normal = cornerDrawable(normalColor, radius);
                pressed = cornerDrawable(pressColor, radius);
            }

            bg.addState(new int[]{-android.R.attr.state_pressed}, normal);
            bg.addState(new int[]{android.R.attr.state_pressed}, pressed);
            return bg;
        }
    }

    /**
     * StatusBar Utils handle with special FlymeOS4.x/Android4.4.4
     * (状态栏工具,处理魅族FlymeOS4.x/Android4.4.4)
     */
    public static class StatusBarUtils {
        public static int getHeight(Context context) {
            int statusBarHeight = 0;
            int resourceId = context.getResources().getIdentifier("status_bar_height", "dimen", "android");
            if (resourceId > 0) {
                statusBarHeight = context.getResources().getDimensionPixelSize(resourceId);
            }
            Log.d(StatusBarUtils.class.getSimpleName(), "statusBarHeight--->" + statusBarHeight);
            if (isFlymeOs4x()) {
                return 2 * statusBarHeight;
            }

            return statusBarHeight;
        }

        public static boolean isFlymeOs4x() {
            String sysVersion = android.os.Build.VERSION.RELEASE;
            if ("4.4.4".equals(sysVersion)) {
                String sysIncrement = android.os.Build.VERSION.INCREMENTAL;
                String displayId = android.os.Build.DISPLAY;
                if (!TextUtils.isEmpty(sysIncrement)) {
                    return sysIncrement.contains("Flyme_OS_4");
                } else {
                    return displayId.contains("Flyme OS 4");
                }
            }
            return false;
        }
    }

}

