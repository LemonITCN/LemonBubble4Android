package net.lemonsoft.lemonbubble;

import android.app.Fragment;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathMeasure;
import android.graphics.RectF;

import net.lemonsoft.lemonbubble.enums.LemonBubbleLayoutStyle;
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
        final LemonBubbleInfo info = new LemonBubbleInfo();
        info.setLayoutStyle(LemonBubbleLayoutStyle.ICON_TOP_TITLE_BOTTOM);
        info.setIconColor(Color.argb(255, 0, 205, 0));
        info.setIconAnimation(new LemonBubblePaintContext() {
            @Override
            public void paint(Canvas canvas, float playProgress) {
                int aimA = (info.getIconColor() & 0xff000000) >>> 24;
                int aimR = (info.getIconColor() & 0x00ff0000) >> 16;
                int aimG = (info.getIconColor() & 0x0000ff00) >> 8;
                int aimB = (info.getIconColor() & 0x000000ff);
                Paint paint = new Paint();
                paint.setStyle(Paint.Style.STROKE);
                // 设置外侧的浅色圆形为图标颜色的0.1倍透明度
                paint.setColor(Color.argb((int) (aimA * 0.1), aimR, aimG, aimB));
                paint.setStrokeWidth(8);
                // 绘制外侧的完整浅色圆形
                canvas.drawCircle(canvas.getWidth() / 2, canvas.getHeight() / 2, canvas.getWidth() / 2 - 4, paint);
                paint.setColor(info.getIconColor());
                Path path = new Path();
                path.addArc(new RectF(4, 4, canvas.getWidth() - 4, canvas.getHeight() - 4), 67, -225);
                // 画对号第一笔
                path.lineTo((float) (canvas.getWidth() * 0.42),
                        (float) (canvas.getHeight() * 0.68));
                // 画对号第二笔
                path.lineTo((float) (canvas.getWidth() * 0.75),
                        (float) (canvas.getHeight() * 0.35));
                Path disPath = new Path();
                PathMeasure measure = new PathMeasure();
                measure.setPath(path, false);
                // 根据实验，发现对号的路径长度占总长度的26%，所以下面减去0.26
                measure.getSegment((float) Math.max(0, playProgress - 0.26) * measure.getLength(), playProgress * measure.getLength(), disPath, true);
                disPath.rLineTo(0, 0);// 解决在android4.4.4以下版本导致的无法绘制path的bug
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
     * 展示一个对号的泡泡控件
     *
     * @param fragment 要判断是否处于显示状态的fragment
     * @param title    显示的标题
     */
    public static void showRight(Fragment fragment, String title) {
        LemonBubbleInfo bubbleInfo = getRightBubbleInfo();
        bubbleInfo.setTitle(title);
        LemonBubbleView.defaultBubbleView().showBubbleInfo(fragment, bubbleInfo);
    }

    /**
     * 展示一个对号的泡泡控件
     *
     * @param fragment 要判断是否处于显示状态的fragment
     * @param title    显示的标题
     */
    public static void showRight(android.support.v4.app.Fragment fragment, String title) {
        LemonBubbleInfo bubbleInfo = getRightBubbleInfo();
        bubbleInfo.setTitle(title);
        LemonBubbleView.defaultBubbleView().showBubbleInfo(fragment, bubbleInfo);
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
     * 展示一个对号的泡泡控件，并在指定的时间后关闭
     *
     * @param fragment      要判断是否处于显示状态的fragment
     * @param title         显示的标题
     * @param autoCloseTime 自动关闭的时间，单位ms
     */
    public static void showRight(Fragment fragment, String title, int autoCloseTime) {
        LemonBubbleInfo bubbleInfo = getRightBubbleInfo();
        bubbleInfo.setTitle(title);
        LemonBubbleView.defaultBubbleView().showBubbleInfo(fragment, bubbleInfo, autoCloseTime);
    }

    /**
     * 展示一个对号的泡泡控件，并在指定的时间后关闭
     *
     * @param fragment      要判断是否处于显示状态的fragment
     * @param title         显示的标题
     * @param autoCloseTime 自动关闭的时间，单位ms
     */
    public static void showRight(android.support.v4.app.Fragment fragment, String title, int autoCloseTime) {
        LemonBubbleInfo bubbleInfo = getRightBubbleInfo();
        bubbleInfo.setTitle(title);
        LemonBubbleView.defaultBubbleView().showBubbleInfo(fragment, bubbleInfo, autoCloseTime);
    }

    /**
     * 获取展示一个叉号的错误提示的泡泡控件
     *
     * @return 带有叉号的错误信息的泡泡信息对象
     */
    public static LemonBubbleInfo getErrorBubbleInfo() {
        final LemonBubbleInfo info = new LemonBubbleInfo();
        info.setIconColor(Color.argb(255, 255, 48, 48));
        info.setIconAnimation(new LemonBubblePaintContext() {
            @Override
            public void paint(Canvas canvas, float playProgress) {
                // 此部分代码的基本含义和第一段对号的含义基本一致，请参考上面
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
                disPath.rLineTo(0, 0);// 解决在android4.4.4以下版本导致的无法绘制path的bug
                canvas.drawPath(disPath, paint);

                Path pathRight = new Path();
                pathRight.addArc(new RectF(4, 4, canvas.getWidth() - 4, canvas.getHeight() - 4), -128, 261);
                pathRight.lineTo((float) (canvas.getWidth() * 0.65),
                        (float) (canvas.getHeight() * 0.35));
                Path disPathRight = new Path();
                PathMeasure measureRight = new PathMeasure();
                measureRight.setPath(pathRight, false);
                measureRight.getSegment((float) Math.max(0, playProgress - 0.16) * measureRight.getLength(), playProgress * measureRight.getLength(), disPathRight, true);
                disPathRight.rLineTo(0, 0);// 解决在android4.4.4以下版本导致的无法绘制path的bug
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
     * @param fragment 要判断是否处于显示状态的fragment
     * @param title    显示的标题
     */
    public static void showError(Fragment fragment, String title) {
        LemonBubbleInfo bubbleInfo = getErrorBubbleInfo();
        bubbleInfo.setTitle(title);
        LemonBubbleView.defaultBubbleView().showBubbleInfo(fragment, bubbleInfo);
    }

    /**
     * 展示一个叉号的带有错误提示的泡泡控件
     *
     * @param fragment 要判断是否处于显示状态的fragment
     * @param title    显示的标题
     */
    public static void showError(android.support.v4.app.Fragment fragment, String title) {
        LemonBubbleInfo bubbleInfo = getErrorBubbleInfo();
        bubbleInfo.setTitle(title);
        LemonBubbleView.defaultBubbleView().showBubbleInfo(fragment, bubbleInfo);
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
     * 展示一个叉号的带有错误提示的泡泡控件
     *
     * @param fragment      要判断是否处于显示状态的fragment
     * @param title         显示的标题
     * @param autoCloseTime 自动关闭的时间，单位ms
     */
    public static void showError(Fragment fragment, String title, int autoCloseTime) {
        LemonBubbleInfo bubbleInfo = getErrorBubbleInfo();
        bubbleInfo.setTitle(title);
        LemonBubbleView.defaultBubbleView().showBubbleInfo(fragment, bubbleInfo, autoCloseTime);
    }

    /**
     * 展示一个叉号的带有错误提示的泡泡控件
     *
     * @param fragment      要判断是否处于显示状态的fragment
     * @param title         显示的标题
     * @param autoCloseTime 自动关闭的时间，单位ms
     */
    public static void showError(android.support.v4.app.Fragment fragment, String title, int autoCloseTime) {
        LemonBubbleInfo bubbleInfo = getErrorBubbleInfo();
        bubbleInfo.setTitle(title);
        LemonBubbleView.defaultBubbleView().showBubbleInfo(fragment, bubbleInfo, autoCloseTime);
    }

    /**
     * 获取展示一个无限循环转动的等待提示的泡泡控件
     *
     * @return 带有无限循环转动的等待提示信息的泡泡信息对象
     */
    public static LemonBubbleInfo getRoundProgressBubbleInfo() {
        final LemonBubbleInfo info = new LemonBubbleInfo();
        info.setBubbleSize(160, 160);
        info.setMaskColor(Color.argb(180, 0, 0, 0));
        info.setIconColor(Color.argb(255, 70, 123, 220));
        info.setIconAnimationRepeat(true);
        info.setFrameAnimationTime(1500);
        info.setIconAnimation(new LemonBubblePaintContext() {
            @Override
            public void paint(Canvas canvas, float playProgress) {
                // 此部分代码的基本含义和第一段对号的含义基本一致，请参考上面
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
                disPath.rLineTo(0, 0);// 解决在android4.4.4以下版本导致的无法绘制path的bug
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

    /**
     * 获取展示一个无限循环转动的等待提示的泡泡控件
     *
     * @return 带有无限循环转动的等待提示信息的泡泡信息对象
     */
    public static void showRoundProgress(Fragment fragment, String title) {
        LemonBubbleInfo bubbleInfo = getRoundProgressBubbleInfo();
        bubbleInfo.setTitle(title);
        LemonBubbleView.defaultBubbleView().showBubbleInfo(fragment, bubbleInfo);
    }

    /**
     * 获取展示一个无限循环转动的等待提示的泡泡控件
     *
     * @return 带有无限循环转动的等待提示信息的泡泡信息对象
     */
    public static void showRoundProgress(android.support.v4.app.Fragment fragment, String title) {
        LemonBubbleInfo bubbleInfo = getRoundProgressBubbleInfo();
        bubbleInfo.setTitle(title);
        LemonBubbleView.defaultBubbleView().showBubbleInfo(fragment, bubbleInfo);
    }

    /**
     * 展示泡泡控件
     *
     * @param context    上下文对象
     * @param bubbleInfo 泡泡信息描述对象
     */
    public static void showBubbleInfo(Context context, LemonBubbleInfo bubbleInfo) {
        LemonBubbleView.defaultBubbleView().showBubbleInfo(context, bubbleInfo);
    }

    /**
     * 展示泡泡控件
     *
     * @param fragment   要判断是否处于显示状态的fragment
     * @param bubbleInfo 泡泡信息描述对象
     */
    public static void showBubbleInfo(Fragment fragment, LemonBubbleInfo bubbleInfo) {
        LemonBubbleView.defaultBubbleView().showBubbleInfo(fragment, bubbleInfo);
    }

    /**
     * 展示泡泡控件
     *
     * @param fragment   要判断是否处于显示状态的fragment
     * @param bubbleInfo 泡泡信息描述对象
     */
    public static void showBubbleInfo(android.support.v4.app.Fragment fragment, LemonBubbleInfo bubbleInfo) {
        LemonBubbleView.defaultBubbleView().showBubbleInfo(fragment, bubbleInfo);
    }

    /**
     * 展示泡泡控件并在指定的时间后关闭
     *
     * @param context       上下文对象
     * @param bubbleInfo    泡泡信息描述对象
     * @param autoCloseTime 自动关闭的时间
     */
    public static void showBubbleInfo(Context context, LemonBubbleInfo bubbleInfo, int autoCloseTime) {
        LemonBubbleView.defaultBubbleView().showBubbleInfo(context, bubbleInfo, autoCloseTime);
    }

    /**
     * 展示泡泡控件并在指定的时间后关闭
     *
     * @param fragment      要判断是否处于显示状态的fragment
     * @param bubbleInfo    泡泡信息描述对象
     * @param autoCloseTime 自动关闭的时间
     */
    public static void showBubbleInfo(Fragment fragment, LemonBubbleInfo bubbleInfo, int autoCloseTime) {
        LemonBubbleView.defaultBubbleView().showBubbleInfo(fragment, bubbleInfo, autoCloseTime);
    }

    /**
     * 展示泡泡控件并在指定的时间后关闭
     *
     * @param fragment      要判断是否处于显示状态的fragment
     * @param bubbleInfo    泡泡信息描述对象
     * @param autoCloseTime 自动关闭的时间
     */
    public static void showBubbleInfo(android.support.v4.app.Fragment fragment, LemonBubbleInfo bubbleInfo, int autoCloseTime) {
        LemonBubbleView.defaultBubbleView().showBubbleInfo(fragment, bubbleInfo, autoCloseTime);
    }

    /**
     * 隐藏当前正在显示的泡泡控件
     */
    public static void hide() {
        LemonBubbleView.defaultBubbleView().hide();
    }

    /**
     * 强制关闭当前正在显示的泡泡控件
     */
    public static void forceHide() {
        LemonBubbleView.defaultBubbleView().forceHide();
    }

}

