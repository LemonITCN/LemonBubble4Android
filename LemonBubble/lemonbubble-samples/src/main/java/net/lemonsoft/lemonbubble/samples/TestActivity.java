package net.lemonsoft.lemonbubble.samples;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import net.lemonsoft.lemonbubble.LemonBubble;

public class TestActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        LemonBubble.showRoundProgress(this, "hello");
        LemonBubble.hide();
//        finish();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        LemonBubble.forceHide();
    }
}
