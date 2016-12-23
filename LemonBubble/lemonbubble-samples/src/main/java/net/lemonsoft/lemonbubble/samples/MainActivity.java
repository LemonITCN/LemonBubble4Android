package net.lemonsoft.lemonbubble.samples;

import android.app.Activity;
import android.os.Bundle;

import net.lemonsoft.lemonbubble.LemonBubbleInfo;
import net.lemonsoft.lemonbubble.LemonBubbleView;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        System.out.println("ok");
        LemonBubbleInfo info = new LemonBubbleInfo();
        LemonBubbleView.set_defaultContext(this);
        LemonBubbleView.defaultBubbleView().showBubbleInfo(info);

    }
}
