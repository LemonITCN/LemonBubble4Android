package net.lemonsoft.lemonbubble;

import android.animation.ValueAnimator;
import android.graphics.Color;
import android.graphics.RectF;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.RoundRectShape;
import android.os.Build;
import android.view.View;
import android.widget.RelativeLayout;

/**
 * LemonBubble私有类，该类可以以动画的方式移动控件的位置、大小等外观属性
 * 开发者，请你不要在你的项目中尝试调用此类中的方法，你可以在LemonKit中找到更适合你的替代品
 * https://github.com/1em0nsOft/LemonKit4Android
 * Created by LiuRi on 2016/12/25.
 */

class LemonBubblePrivateAnimationTool {

    private static LemonBubblePrivateAnimationTool _privateAnimationTool;
    private static LemonBubblePrivateSizeTool _PST = LemonBubblePrivateSizeTool.getPrivateSizeTool();

    static synchronized LemonBubblePrivateAnimationTool defaultPrivateAnimationTool() {
        if (_privateAnimationTool == null)
            _privateAnimationTool = new LemonBubblePrivateAnimationTool();
        return _privateAnimationTool;
    }

    private int _DP(int value) {
        return LemonBubblePrivateSizeTool.getPrivateSizeTool().dpToPx(value);
    }

    /**
     * 对指定的控件设置尺寸大小
     *
     * @param view     要设置尺寸的控件
     * @param widthDp  宽度，单位dp
     * @param heightDp 高度，单位dp
     */
    void setSize(final View view, final int widthDp, final int heightDp) {
        final int startWidth = _PST.pxToDp(view.getLayoutParams() == null ? 0 : view.getLayoutParams().width);
        final int startHeight = _PST.pxToDp(view.getLayoutParams() == null ? 0 : view.getLayoutParams().height);
        final int subWidth = widthDp - startWidth;
        final int subHeight = heightDp - startHeight;
        ValueAnimator valueAnimator = ValueAnimator.ofFloat(0, 1);
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                int currentWidth = (int) ((float) animation.getAnimatedValue() * subWidth + startWidth);
                int currentHeight = (int) ((float) animation.getAnimatedValue() * subHeight + startHeight);
                view.setLayoutParams(
                        new RelativeLayout.LayoutParams(_DP(currentWidth), _DP(currentHeight)));
                view.postInvalidate();
            }
        });
        valueAnimator.start();
    }

    /**
     * 设置控件的位置
     *
     * @param view 要设置位置的控件对象
     * @param x    水平x坐标
     * @param y    垂直y坐标
     */
    void setLocation(final View view, int x, int y) {
        final int startX = _PST.pxToDp((int) (view.getX()));
        final int startY = _PST.pxToDp((int) (view.getY()));
        final int subX = x - startX;
        final int subY = y - startY;
        ValueAnimator valueAnimator = ValueAnimator.ofFloat(0, 1);
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                view.setX(_DP((int) ((float) animation.getAnimatedValue() * subX + startX)));
                view.setY(_DP((int) ((float) animation.getAnimatedValue() * subY + startY)));
                view.postInvalidate();
            }
        });
        valueAnimator.start();
    }

    /**
     * 渐变设置透明度
     *
     * @param view  设置透明度的控件
     * @param alpha 透明度的目标值
     */
    void setAlpha(final View view, float alpha) {
        ValueAnimator valueAnimator = ValueAnimator.ofFloat(view.getAlpha(), alpha);
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                view.setAlpha((float) animation.getAnimatedValue());
            }
        });
        valueAnimator.start();
    }

    /**
     * 动画改变背景颜色
     *
     * @param view  要改变背景颜色的控件
     * @param color 要改变成的目标背景颜色
     */
    void setBackgroundColor(final View view, final int cornerRadius, int color) {
        int startColor = Color.argb(0, 255, 255, 255);
        Drawable drawable = view.getBackground();
        if (drawable instanceof ColorDrawable)
            startColor = ((ColorDrawable) drawable).getColor();
        if (drawable instanceof ShapeDrawable)
            startColor = ((ShapeDrawable) drawable).getPaint().getColor();
        // 先算出原颜色的ARGB值
        final int startA = (startColor & 0xff000000) >>> 24;
        final int startR = (startColor & 0x00ff0000) >> 16;
        final int startG = (startColor & 0x0000ff00) >> 8;
        final int startB = (startColor & 0x000000ff);
        // 算出目标颜色的ARGB值
        int aimA = (color & 0xff000000) >>> 24;
        int aimR = (color & 0x00ff0000) >> 16;
        int aimG = (color & 0x0000ff00) >> 8;
        int aimB = (color & 0x000000ff);
        // 算颜色ARGB的差值
        final int subA = aimA - startA;
        final int subR = aimR - startR;
        final int subG = aimG - startG;
        final int subB = aimB - startB;
        ValueAnimator valueAnimator = ValueAnimator.ofFloat(0, 1);
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                // 根据进度来修改颜色
                int color = Color.argb(
                        (int) (startA + subA * (float) animation.getAnimatedValue()),
                        (int) (startR + subR * (float) animation.getAnimatedValue()),
                        (int) (startG + subG * (float) animation.getAnimatedValue()),
                        (int) (startB + subB * (float) animation.getAnimatedValue()));
                System.out.printf("a：%d   ,   r:%d   ,   g:%d   ,   b:%d  ---", (int) (startA + subA * (float) animation.getAnimatedValue()),
                        (int) (startR + subR * (float) animation.getAnimatedValue()),
                        (int) (startG + subG * (float) animation.getAnimatedValue()),
                        (int) (startB + subB * (float) animation.getAnimatedValue()));
//                if (cornerRadius == 0)
                view.setBackgroundColor(color);
                setCornerRadius(view, cornerRadius, color);
//                else
//                    setCornerRadius(view, cornerRadius, color);
            }
        });
        valueAnimator.start();
    }

    void setCornerRadius(View view, int radius, int color) {
        int borderWidth = 0;// 加边框后会出现空心圆角矩形的效果，所以设置为0
        float[] outerRadius = new float[8];
        float[] innerRadius = new float[8];
        for (int i = 0; i < 8; i++) {
            outerRadius[i] = radius + borderWidth;
            innerRadius[i] = radius;
        }
        ShapeDrawable shapeDrawable = // 创建图形drawable
                new ShapeDrawable(
                        // 创建圆角矩形
                        new RoundRectShape(outerRadius,
                                new RectF(borderWidth, borderWidth, borderWidth, borderWidth),
                                innerRadius));
        shapeDrawable.getPaint().setColor(color);// 使用指定的颜色绘制，即背景颜色
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            // 高版本SDK使用新的API
            view.setBackground(shapeDrawable);
        } else {
            view.setBackgroundDrawable(shapeDrawable);
        }
    }

}
