package net.lemonsoft.lemonbubble.samples;

import android.app.Activity;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathMeasure;
import android.graphics.RectF;
import android.os.Bundle;

import net.lemonsoft.lemonbubble.LemonBubbleInfo;
import net.lemonsoft.lemonbubble.interfaces.LemonBubblePaintContext;
import net.lemonsoft.lemonbubble.LemonBubbleView;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        LemonBubbleInfo info = new LemonBubbleInfo();
        info.setIconAnimation(new LemonBubblePaintContext() {
            @Override
            public void paint(Canvas canvas, float playProgress) {
                Paint paint = new Paint();
                paint.setStyle(Paint.Style.STROKE);
                paint.setColor(Color.argb(30, 0, 205, 0));
                paint.setStrokeWidth(8);
                canvas.drawCircle(canvas.getWidth() / 2, canvas.getHeight() / 2, canvas.getWidth() / 2 - 4, paint);
                paint.setColor(Color.argb(255, 0, 205, 0));
                Path path = new Path();
                path.addArc(new RectF(4, 4, canvas.getWidth() - 4, canvas.getHeight() - 4), 67, -225);
                path.lineTo((float) (canvas.getWidth() * 0.42),
                        (float) (canvas.getHeight() * 0.68));
                path.lineTo((float) (canvas.getWidth() * 0.75),
                        (float) (canvas.getHeight() * 0.35));
                Path disPath = new Path();
                PathMeasure measure = new PathMeasure();
                measure.setPath(path, false);
                measure.getSegment((float) Math.max(0, playProgress - 0.26) * measure.getLength(), playProgress * measure.getLength(), disPath, true);
                canvas.drawPath(disPath, paint);
            }
        });
        LemonBubbleView.set_defaultContext(this);
        LemonBubbleView.defaultBubbleView().showBubbleInfo(info);
    }

}
