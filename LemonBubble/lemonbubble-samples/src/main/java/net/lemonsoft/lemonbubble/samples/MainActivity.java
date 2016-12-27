package net.lemonsoft.lemonbubble.samples;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;

import net.lemonsoft.lemonbubble.LemonBubble;
import net.lemonsoft.lemonbubble.LemonBubbleInfo;
import net.lemonsoft.lemonbubble.LemonBubbleView;

import java.util.Timer;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        RelativeLayout layout = new RelativeLayout(this);
        setContentView(layout);
        Button rightButton = new Button(this);
        rightButton.setLayoutParams(new RelativeLayout.LayoutParams(300, 300));
        rightButton.setX(100);
        rightButton.setY(500);
        rightButton.setBackgroundColor(Color.RED);
        rightButton.setText("right");
        layout.addView(rightButton);
        rightButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LemonBubble.showRoundProgress(MainActivity.this, "支付中...");
            }
        });

    }

}
