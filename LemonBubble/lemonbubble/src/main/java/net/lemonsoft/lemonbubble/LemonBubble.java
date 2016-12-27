package net.lemonsoft.lemonbubble;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathMeasure;
import android.graphics.RectF;

import net.lemonsoft.lemonbubble.enums.LemonBubbleLayoutStyle;
import net.lemonsoft.lemonbubble.enums.LemonBubbleLocationStyle;
import net.lemonsoft.lemonbubble.interfaces.LemonBubblePaintContext;

/**
 * 柠檬泡泡控件 - 这个类里面都是静态方法，为的是方便使用者的全局调用
 * Created by LiuRi on 2016/12/25.
 */

public class LemonBubble {

    /**
     * 获取展示一个对号的泡泡控件
     *
     * @return 带有对号的泡泡信息对象
     */
    public static LemonBubbleInfo getRightBubbleInfo() {
        LemonBubbleInfo info = new LemonBubbleInfo();
        info.setLayoutStyle(LemonBubbleLayoutStyle.ICON_TOP_TITLE_BOTTOM);
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
        return info;
    }

    /**
     * 展示一个对号的泡泡控件
     *
     * @param context 上下文对象
     * @param title   显示的标题
     */
    public static void showRight(Context context, String title) {
        LemonBubbleInfo bubbleInfo = getRightBubbleInfo();
        bubbleInfo.setTitle(title);
        LemonBubbleView.defaultBubbleView().showBubbleInfo(context, bubbleInfo);
    }

    /**
     * 展示一个对号的泡泡控件，并在指定的时间后关闭
     *
     * @param context       上下文对象
     * @param title         显示的标题
     * @param autoCloseTime 自动关闭的时间，单位ms
     */
    public static void showRight(Context context, String title, int autoCloseTime) {
        LemonBubbleInfo bubbleInfo = getRightBubbleInfo();
        bubbleInfo.setTitle(title);
        LemonBubbleView.defaultBubbleView().showBubbleInfo(context, bubbleInfo, autoCloseTime);
    }

    /**
     * 获取展示一个叉号的错误提示的泡泡控件
     *
     * @return 带有叉号的错误信息的泡泡信息对象
     */
    public static LemonBubbleInfo getErrorBubbleInfo() {
        final LemonBubbleInfo info = new LemonBubbleInfo();
        info.setLayoutStyle(LemonBubbleLayoutStyle.ICON_LEFT_TITLE_RIGHT);
        info.setLocationStyle(LemonBubbleLocationStyle.BOTTOM);
        info.setProportionOfDeviation(0.01f);
        info.setBubbleSize(180, 80);
        info.setIconColor(Color.argb(255, 255, 48, 48));
        info.setIconAnimation(new LemonBubblePaintContext() {
            @Override
            public void paint(Canvas canvas, float playProgress) {
                int aimA = (info.getIconColor() & 0xff000000) >>> 24;
                int aimR = (info.getIconColor() & 0x00ff0000) >> 16;
                int aimG = (info.getIconColor() & 0x0000ff00) >> 8;
                int aimB = (info.getIconColor() & 0x000000ff);
                Paint paint = new Paint();
                paint.setStyle(Paint.Style.STROKE);
                paint.setColor(Color.argb((int) (aimA * 0.1), aimR, aimG, aimB));
                paint.setStrokeWidth(8);
                canvas.drawCircle(canvas.getWidth() / 2, canvas.getHeight() / 2, canvas.getWidth() / 2 - 4, paint);
                paint.setColor(info.getIconColor());
                Path path = new Path();
                path.addArc(new RectF(4, 4, canvas.getWidth() - 4, canvas.getHeight() - 4), -43, -272);
                path.lineTo((float) (canvas.getWidth() * 0.35),
                        (float) (canvas.getHeight() * 0.35));
                Path disPath = new Path();
                PathMeasure measure = new PathMeasure();
                measure.setPath(path, false);
                measure.getSegment((float) Math.max(0, playProgress - 0.16) * measure.getLength(), playProgress * measure.getLength(), disPath, true);
                canvas.drawPath(disPath, paint);

                Path pathRight = new Path();
                pathRight.addArc(new RectF(4, 4, canvas.getWidth() - 4, canvas.getHeight() - 4), -128, 261);
                pathRight.lineTo((float) (canvas.getWidth() * 0.65),
                        (float) (canvas.getHeight() * 0.35));
                Path disPathRight = new Path();
                PathMeasure measureRight = new PathMeasure();
                measureRight.setPath(pathRight, false);
                measureRight.getSegment((float) Math.max(0, playProgress - 0.16) * measureRight.getLength(), playProgress * measureRight.getLength(), disPathRight, true);
                canvas.drawPath(disPathRight, paint);
            }
        });
        return info;
    }

    /**
     * 展示一个叉号的带有错误提示的泡泡控件
     *
     * @param context 上下文对象
     * @param title   显示的标题
     */
    public static void showError(Context context, String title) {
        LemonBubbleInfo bubbleInfo = getErrorBubbleInfo();
        bubbleInfo.setTitle(title);
        LemonBubbleView.defaultBubbleView().showBubbleInfo(context, bubbleInfo);
    }

    /**
     * 展示一个叉号的带有错误提示的泡泡控件
     *
     * @param context       上下文对象
     * @param title         显示的标题
     * @param autoCloseTime 自动关闭的时间，单位ms
     */
    public static void showError(Context context, String title, int autoCloseTime) {
        LemonBubbleInfo bubbleInfo = getErrorBubbleInfo();
        bubbleInfo.setTitle(title);
        LemonBubbleView.defaultBubbleView().showBubbleInfo(context, bubbleInfo, autoCloseTime);
    }

    /**
     * 获取展示一个无限循环转动的等待提示的泡泡控件
     *
     * @return 带有无限循环转动的等待提示信息的泡泡信息对象
     */
    public static LemonBubbleInfo getRoundProgressBubbleInfo() {
        final LemonBubbleInfo info = new LemonBubbleInfo();
        info.setIconColor(Color.argb(255, 70, 123, 220));
        info.setIconAnimationRepeat(true);
        info.setFrameAnimationTime(1500);
        info.setIconAnimation(new LemonBubblePaintContext() {
            @Override
            public void paint(Canvas canvas, float playProgress) {
                int aimA = (info.getIconColor() & 0xff000000) >>> 24;
                int aimR = (info.getIconColor() & 0x00ff0000) >> 16;
                int aimG = (info.getIconColor() & 0x0000ff00) >> 8;
                int aimB = (info.getIconColor() & 0x000000ff);
                Paint paint = new Paint();
                paint.setStyle(Paint.Style.STROKE);
                paint.setColor(Color.argb((int) (aimA * 0.1), aimR, aimG, aimB));
                paint.setStrokeWidth(8);
                canvas.drawCircle(canvas.getWidth() / 2, canvas.getHeight() / 2, canvas.getWidth() / 2 - 4, paint);
                paint.setColor(info.getIconColor());
                Path path = new Path();
                path.addArc(new RectF(4, 4, canvas.getWidth() - 4, canvas.getHeight() - 4), 0, -360);
                Path disPath = new Path();
                PathMeasure measure = new PathMeasure();
                measure.setPath(path, false);
                // 截取线段起到渐长渐短的效果：进度的2次方 作为起点，根号2的进度作为终点
                measure.getSegment((float) (Math.pow(playProgress, 2) * measure.getLength()), (float) (Math.sqrt(playProgress) * measure.getLength()), disPath, true);
                canvas.drawPath(disPath, paint);
            }
        });
        return info;
    }

    /**
     * 获取展示一个无限循环转动的等待提示的泡泡控件
     *
     * @return 带有无限循环转动的等待提示信息的泡泡信息对象
     */
    public static void showRoundProgress(Context context, String title) {
        LemonBubbleInfo bubbleInfo = getRoundProgressBubbleInfo();
        bubbleInfo.setTitle(title);
        LemonBubbleView.defaultBubbleView().showBubbleInfo(context, bubbleInfo);
    }

}

