package com.example.weioule.dialogdemo.common.matedialog;

import android.content.Context;
import android.graphics.Color;
import android.view.Gravity;
import android.view.View;
import android.widget.LinearLayout;

import com.nineoldandroids.animation.ObjectAnimator;


/**
 * Dialog like Material Design Dialog
 */
public class MaterialDialog extends BaseAlertDialog<MaterialDialog> {

    public MaterialDialog(Context context) {
        super(context);
        titleTextColor = Color.parseColor("#DE000000");
        titleTextSize_SP = 20f;
        contentTextColor = Color.parseColor("#DE000000");
        contentTextSize_SP = 16f;
        leftBtnTextColor = Color.parseColor("#383838");
        rightBtnTextColor = Color.parseColor("#eb2127");
        middleBtnTextColor = Color.parseColor("#00796B");
        content = "您确定要删除尾号为7561的中国银行信用卡吗?";

        showAnim(new BaseAnimatorSet() {
            @Override
            public void setAnimation(View view) {
                ObjectAnimator animator1 = ObjectAnimator.ofFloat(view, "scaleX", 0f, 0.2f, 0.5f, 0.8f, 1f);
                ObjectAnimator animator2 = ObjectAnimator.ofFloat(view, "scaleY", 0f, 0.2f, 0.5f, 0.8f, 1f);
                animatorSet.playTogether(animator1, animator2);
            }
        });

        dismissAnim(new BaseAnimatorSet() {
            @Override
            public void setAnimation(View view) {
                ObjectAnimator animator1 = ObjectAnimator.ofFloat(view, "scaleX", 1f, 0.8f, 0.5f, 0.2f, 0f);
                ObjectAnimator animator2 = ObjectAnimator.ofFloat(view, "scaleY", 1f, 0.8f, 0.5f, 0.2f, 0f);
                animatorSet.playTogether(animator1, animator2);
            }
        });
    }

    @Override
    public View onCreateView() {
        /** title */
        tv_title.setGravity(Gravity.CENTER_VERTICAL);
        tv_title.setPadding(dp2px(20), dp2px(20), dp2px(20), dp2px(0));
        tv_title.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
        ll_container.addView(tv_title);

        /** content */
        tv_content.setPadding(dp2px(20), dp2px(20), dp2px(20), dp2px(20));
        tv_content.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
        ll_container.addView(tv_content);

        /** btns */
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
    public void setUiBeforShow() {
        super.setUiBeforShow();
        /** set background color and corner radius */
        float radius = dp2px(cornerRadius_DP);
        ll_container.setBackgroundDrawable(CornerUtils.cornerDrawable(bgColor, radius));
        tv_btn_left.setBackgroundDrawable(CornerUtils.btnSelector(radius, bgColor, btnPressColor, -2));
        tv_btn_right.setBackgroundDrawable(CornerUtils.btnSelector(radius, bgColor, btnPressColor, -2));
        tv_btn_middle.setBackgroundDrawable(CornerUtils.btnSelector(radius, bgColor, btnPressColor, -2));
    }
}
