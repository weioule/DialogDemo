package com.example.weioule.dialogdemo.view;

import android.content.Intent;
import android.graphics.Color;
import android.view.View;

import com.example.weioule.dialogdemo.common.AbsAlertDialog;
import com.nineoldandroids.animation.ObjectAnimator;


/**
 * author weioule
 * Create on 2018/6/20.
 */
public class SecondActivity extends MainActivity {

    @Override
    protected void showDialog() {
        final AbsAlertDialog dialog = new AbsAlertDialog(SecondActivity.this);
        dialog.setTitleTextColor(Color.parseColor("#DE000000"));
        dialog.setTitleTextSize(20f);
        dialog.setContentTextColor(Color.parseColor("#DE000000"));
        dialog.setContentTextSize(16f);
        dialog.setBtnTextColor(Color.parseColor("#383838"), Color.parseColor("#eb2127"), Color.parseColor("#00796B"));
        dialog.setContent("您确定要删除尾号为7561的中国银行信用卡吗?");
        dialog.setCanceledOnTouchOutside(false);

        dialog.showAnim(new AbsAlertDialog.BaseAnimatorSet() {
            @Override
            public void setAnimation(View view) {
                ObjectAnimator animator1 = ObjectAnimator.ofFloat(view, "scaleX", 0f, 0.2f, 0.5f, 0.8f, 1f);
                ObjectAnimator animator2 = ObjectAnimator.ofFloat(view, "scaleY", 0f, 0.2f, 0.5f, 0.8f, 1f);
                animatorSet.playTogether(animator1, animator2);
            }
        });
        dialog.dismissAnim(new AbsAlertDialog.BaseAnimatorSet() {
            @Override
            public void setAnimation(View view) {
                ObjectAnimator animator1 = ObjectAnimator.ofFloat(view, "scaleX", 1f, 0.8f, 0.5f, 0.2f, 0f);
                ObjectAnimator animator2 = ObjectAnimator.ofFloat(view, "scaleY", 1f, 0.8f, 0.5f, 0.2f, 0f);
                animatorSet.playTogether(animator1, animator2);
            }
        });

        AbsAlertDialog.OnBtnClickL onBtnClickL = new AbsAlertDialog.OnBtnClickL() {
            @Override
            public void onBtnClick() {
                System.out.println("******************* 取消");
                dialog.dismiss();
            }
        };

        AbsAlertDialog.OnBtnClickL onBtnClickM = new AbsAlertDialog.OnBtnClickL() {
            @Override
            public void onBtnClick() {
                System.out.println("******************* 确定");
                dialog.dismiss();
            }
        };
        dialog.setOnBtnClickL(onBtnClickL, onBtnClickM);
        dialog.show();
    }

    @Override
    protected void startActivity() {
        startActivity(new Intent(SecondActivity.this, ThirdActivity.class));
    }

    @Override
    protected String getActName() {
        return "SecondActivity";
    }
}
