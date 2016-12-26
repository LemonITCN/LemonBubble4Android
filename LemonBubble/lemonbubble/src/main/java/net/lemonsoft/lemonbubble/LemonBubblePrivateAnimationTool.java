package net.lemonsoft.lemonbubble;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
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
                System.out.println("width: " + currentWidth);
                System.out.println("height: " + currentWidth);
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
}
