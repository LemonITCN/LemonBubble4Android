package net.lemonsoft.lemonbubble.samples;

import android.app.Activity;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.os.Bundle;

import net.lemonsoft.lemonbubble.LemonBubbleInfo;
import net.lemonsoft.lemonbubble.LemonBubblePaintContext;
import net.lemonsoft.lemonbubble.LemonBubbleView;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        LemonBubbleInfo info = new LemonBubbleInfo();
        info.setIconAnimation(new LemonBubblePaintContext() {
            @Override
            public void paint(Canvas canvas) {
                Paint paint = new Paint();
                paint.setStyle(Paint.Style.STROKE);
                paint.setColor(Color.argb(30, 0, 205, 0));
                paint.setStrokeWidth(8);
                canvas.drawCircle(canvas.getWidth() / 2, canvas.getHeight() / 2, canvas.getWidth() / 2 - 4, paint);
                paint.setColor(Color.argb(255, 0, 205, 0));
                canvas.drawArc(new RectF(4, 4, canvas.getWidth() - 4, canvas.getHeight() - 4), 67, -225, false, paint);
                canvas.drawLines(new float[]{
                        (float) (1 - Math.cos(Math.toRadians(22))) * canvas.getWidth() / 2,
                        (float) (1 - Math.cos(Math.toRadians(68))) * canvas.getHeight() / 2,
                        (float) (canvas.getWidth() * 0.42),
                        (float) (canvas.getHeight() * 0.68),
                        (float) (canvas.getWidth() * 0.42),
                        (float) (canvas.getHeight() * 0.68),
                        (float) (canvas.getWidth() * 0.75),
                        (float) (canvas.getHeight() * 0.35)
                }, paint);
                float p1Length = (float) (225.0 / 360.0 * (2 * Math.PI * canvas.getWidth() / 2));
//                float p2Length = (float)

            }
        });
        LemonBubbleView.set_defaultContext(this);
        LemonBubbleView.defaultBubbleView().showBubbleInfo(info);

    }
}
