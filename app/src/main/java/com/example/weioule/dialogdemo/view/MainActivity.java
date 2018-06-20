package com.example.weioule.dialogdemo.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.example.weioule.dialogdemo.R;
import com.example.weioule.dialogdemo.common.matedialog.BaseAlertDialog;
import com.example.weioule.dialogdemo.common.matedialog.MaterialDialog;


/**
 * author weioule
 * Create on 2018/6/20.

  在App的开发中，提示框是经常用到的一个技术点。因为样式与之前不一样，所以就自定义写一个，今天自定义的提示框，跟之前差不多都是直接继承至Dialog，
  但是没有使用XML布局，都是动态创建View和画的背景，另外还支持添加动画。
  下面来看看效果图：

  在matedialog包下的是一些抽取的Base类和工具类，MaterialDialog是比较灵活的可以定制其布局样式和动画支持的dialog，在onCreateView函数中创建你所需要定制的布局即可。
  不需要动画就直接忽略不设置。
  而AbsAlertDialog与ComAlertDialog就一个单独的类，直接copy即可不需要带那么多附加的东西。方便但不能定制，比较合布局统一的项目。
  二者的区别在于AbsAlertDialog支持设置显示隐藏动画，ComAlertDialog不支持，不过大部分的提示框基本都不存在动画效果。
  使用时方法在项目里Activity中的showDialogh函数里，文案大小、颜色或背景等都可以在外部传入。设置按钮点击回调后可在其回调方法里进行你的流程操作。
 */

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ((TextView) findViewById(R.id.activity_name)).setText(getActName());

        findViewById(R.id.show_dialog).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDialog();
            }
        });

        findViewById(R.id.do_next).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity();
            }
        });
    }

    protected void showDialog() {
        final MaterialDialog dialog = new MaterialDialog(MainActivity.this);
        BaseAlertDialog.OnBtnClickL onBtnClickL = new BaseAlertDialog.OnBtnClickL() {
            @Override
            public void onBtnClick() {
                System.out.println("******************* 取消");
                dialog.dismiss();
            }
        };

        BaseAlertDialog.OnBtnClickL onBtnClickM = new BaseAlertDialog.OnBtnClickL() {
            @Override
            public void onBtnClick() {
                System.out.println("******************* 确定");
                dialog.dismiss();
            }
        };
        dialog.setOnBtnClickL(onBtnClickL, onBtnClickM);
        dialog.setCanceledOnTouchOutside(false);
        dialog.show();
    }

    protected void startActivity() {
        startActivity(new Intent(MainActivity.this, SecondActivity.class));
    }

    protected String getActName() {
        return "MainActivity";
    }
}
