package net.lemonsoft.lemonbubble.samples;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import net.lemonsoft.lemonbubble.LemonBubble;
import net.lemonsoft.lemonbubble.LemonBubbleInfo;
import net.lemonsoft.lemonbubble.LemonBubbleView;
import net.lemonsoft.lemonbubble.enums.LemonBubbleLayoutStyle;
import net.lemonsoft.lemonbubble.enums.LemonBubbleLocationStyle;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;

public class MainActivity extends Activity {

    private SizeTool _PST = SizeTool.getPrivateSizeTool();

    private LinearLayout button1;
    private LinearLayout button2;
    private LinearLayout button3;
    private LinearLayout button4;
    private LinearLayout button5;
    private LinearLayout button6;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        button1 = (LinearLayout) findViewById(R.id.btn1);
        button2 = (LinearLayout) findViewById(R.id.btn2);
        button3 = (LinearLayout) findViewById(R.id.btn3);
        button4 = (LinearLayout) findViewById(R.id.btn4);
        button5 = (LinearLayout) findViewById(R.id.btn5);
        button6 = (LinearLayout) findViewById(R.id.btn6);

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LemonBubble.showRight(MainActivity.this, "这是一个成功的提示", 2000);
            }
        });

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LemonBubble.showError(MainActivity.this, "这是一个失败的提示", 2000);
            }
        });

        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LemonBubble.showRoundProgress(MainActivity.this, "请求中...");
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        LemonBubble.showRight(MainActivity.this, "请求成功", 2000);
                    }
                }, 2000);
            }
        });

        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LemonBubbleInfo myInfo = LemonBubble.getRoundProgressBubbleInfo();
                myInfo.setLocationStyle(LemonBubbleLocationStyle.BOTTOM);
                myInfo.setLayoutStyle(LemonBubbleLayoutStyle.ICON_LEFT_TITLE_RIGHT);
                myInfo.setTitle("正在删除");
                myInfo.setBubbleSize(200, 50);
                myInfo.setProportionOfDeviation(0.1f);
                LemonBubble.showBubbleInfo(MainActivity.this, myInfo);
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        LemonBubble.showRight(MainActivity.this, "删除成功", 2000);
                    }
                }, 2000);
            }
        });

        button5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LemonBubbleInfo frameInfo = new LemonBubbleInfo();
                List<Bitmap> icons = new ArrayList<Bitmap>();
                icons.add(BitmapFactory.decodeResource(getResources(), R.mipmap.lkbubble1));
                icons.add(BitmapFactory.decodeResource(getResources(), R.mipmap.lkbubble2));
                icons.add(BitmapFactory.decodeResource(getResources(), R.mipmap.lkbubble3));
                icons.add(BitmapFactory.decodeResource(getResources(), R.mipmap.lkbubble4));
                icons.add(BitmapFactory.decodeResource(getResources(), R.mipmap.lkbubble5));
                icons.add(BitmapFactory.decodeResource(getResources(), R.mipmap.lkbubble6));
                icons.add(BitmapFactory.decodeResource(getResources(), R.mipmap.lkbubble7));
                icons.add(BitmapFactory.decodeResource(getResources(), R.mipmap.lkbubble8));
                frameInfo.setIconArray(icons);
                frameInfo.setFrameAnimationTime(150);
                frameInfo.setTitle("正在加载中...");
                frameInfo.setTitleColor(Color.DKGRAY);
                LemonBubble.showBubbleInfo(MainActivity.this, frameInfo);
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        LemonBubble.showError(MainActivity.this, "加载失败", 2000);
                    }
                }, 2000);
            }
        });

        button6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LemonBubbleInfo iconInfo = new LemonBubbleInfo();
                List<Bitmap> icon = new ArrayList<Bitmap>();
                icon.add(BitmapFactory.decodeResource(getResources(), R.mipmap.icon_icon));
                iconInfo.setIconArray(icon);
                iconInfo.setLocationStyle(LemonBubbleLocationStyle.TOP);
                iconInfo.setLayoutStyle(LemonBubbleLayoutStyle.ICON_LEFT_TITLE_RIGHT);
                iconInfo.setTitle("飞行模式已开启");
                iconInfo.setProportionOfDeviation(0.05f);
                iconInfo.setBubbleSize(300, 60);
                LemonBubble.showBubbleInfo(MainActivity.this, iconInfo, 2000);
            }
        });
    }

}
