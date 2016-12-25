package net.lemonsoft.lemonbubble;

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
    void setSize(View view, int widthDp, int heightDp) {
        view.setLayoutParams(
                new RelativeLayout.LayoutParams(_DP(widthDp), _DP(heightDp)));
    }

    /**
     * 设置控件的位置
     *
     * @param view 要设置位置的控件对象
     * @param x    水平x坐标
     * @param y    垂直y坐标
     */
    void setLocation(View view, int x, int y) {
        view.setX(_DP(x));
        view.setY(_DP(y));
    }
}
