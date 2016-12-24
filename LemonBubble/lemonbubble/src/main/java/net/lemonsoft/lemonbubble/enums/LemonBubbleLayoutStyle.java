package net.lemonsoft.lemonbubble.enums;

/**
 * 泡泡控件中的布局样式枚举
 * Created by LiuRi on 2016/12/24.
 */

public enum LemonBubbleLayoutStyle {

    /**
     * 图标在上，标题文字在下
     */
    ICON_TOP_TITLE_BOTTOM(0),
    /**
     * 图标在下，标题文字在上
     */
    ICON_BOTTOM_TITLE_TOP(3),
    /**
     * 图标在左，标题文字在右
     */
    ICON_LEFT_TITLE_RIGHT(1),
    /**
     * 图标在右，标题文字在左
     */
    ICON_RIGHT_TITLE_LEFT(4),
    /**
     * 只显示图标
     */
    ICON_ONLY(2),
    /**
     * 只显示标题文字
     */
    TITLE_ONLY(5);

    private int value;

    public int getValue() {
        return value;
    }

    LemonBubbleLayoutStyle(int value) {
        this.value = value;
    }

}
