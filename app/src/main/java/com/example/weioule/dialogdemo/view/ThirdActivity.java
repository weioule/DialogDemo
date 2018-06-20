package com.example.weioule.dialogdemo.view;

import android.content.Intent;
import android.graphics.Color;

import com.example.weioule.dialogdemo.common.ComAlertDialog;

/**
 * author weioule
 * Create on 2018/6/20.
 */
public class ThirdActivity extends MainActivity {
    @Override
    protected void showDialog() {
        final ComAlertDialog dialog = new ComAlertDialog(ThirdActivity.this);
        dialog.setTitleTextColor(Color.parseColor("#DE000000"));
        dialog.setTitleTextSize(20f);
        dialog.setContentTextColor(Color.parseColor("#DE000000"));
        dialog.setContentTextSize(16f);
        dialog.setBtnTextColor(Color.parseColor("#383838"), Color.parseColor("#eb2127"), Color.parseColor("#00796B"));
        dialog.setContent("您确定要删除尾号为7561的中国银行信用卡吗?");
        dialog.setCanceledOnTouchOutside(false);

        ComAlertDialog.OnBtnClickL onBtnClickL = new ComAlertDialog.OnBtnClickL() {
            @Override
            public void onBtnClick() {
                System.out.println("******************* 取消");
                dialog.dismiss();
            }
        };

        ComAlertDialog.OnBtnClickL onBtnClickM = new ComAlertDialog.OnBtnClickL() {
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
        startActivity(new Intent(ThirdActivity.this, MainActivity.class).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP));
    }

    @Override
    protected String getActName() {
        return "ThirdActivity";
    }
}
