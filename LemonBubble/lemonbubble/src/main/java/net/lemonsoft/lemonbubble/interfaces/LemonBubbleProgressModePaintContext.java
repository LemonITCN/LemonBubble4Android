package net.lemonsoft.lemonbubble.interfaces;

import android.graphics.Canvas;

/**
 * 当泡泡控件显示为进度条模式的时候来需要调用的接口，通过此接口中传入的进度值等信息进行绘制进度条样式
 * Created by LiuRi on 2016/12/24.
 */

public interface LemonBubbleProgressModePaintContext {
    /**
     * 绘制方法
     *
     * @param canvas          要绘制图形的画布
     * @param playProgress    当前动画播放的进度
     * @param currentProgress 当前应该显示的进度条进度
     */
    void paint(Canvas canvas, float playProgress, float currentProgress);
}
