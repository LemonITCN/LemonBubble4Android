package net.lemonsoft.lemonbubble.interfaces;

import android.graphics.Canvas;

/**
 * 正式绘制动画的接口
 */
public interface LemonBubblePaintContext {
    /**
     * 绘制方法
     *
     * @param canvas       要绘制图形的画布
     * @param playProgress 当前动画播放的进度
     */
    void paint(Canvas canvas, float playProgress);
}
