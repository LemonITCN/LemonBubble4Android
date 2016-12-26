package net.lemonsoft.lemonbubble.samples;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import net.lemonsoft.lemonbubble.LemonBubble;

public class TestActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        Button button = (Button) findViewById(R.id.rightButton);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LemonBubble.showRight(TestActivity.this, "支付成功");
            }
        });
    }
}
