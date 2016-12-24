package net.lemonsoft.lemonbubble;

import android.graphics.Bitmap;

import net.lemonsoft.lemonbubble.enums.LemonBubbleLayoutStyle;
import net.lemonsoft.lemonbubble.enums.LemonBubbleLocationStyle;
import net.lemonsoft.lemonbubble.interfaces.LemonBubblePaintContext;
import net.lemonsoft.lemonbubble.interfaces.LemonBubbleProgressModePaintContext;

import java.util.List;

/**
 * 柠檬泡泡信息对象，再次对象中详细描述了泡泡控件显示的各种细节
 * Created by LiuRi on 2016/12/23.
 */

public class LemonBubbleInfo {

    /**
     * 泡泡控件的宽
     */
    private int bubbleWidth;
    /**
     * 泡泡控件的高
     */
    private int bubbleHeight;
    /**
     * 泡泡控件的圆角半径
     */
    private int cornerRadius;
    /**
     * 图文布局属性
     */
    private LemonBubbleLayoutStyle layoutStyle;
    /**
     * 自定义图标动画
     */
    private LemonBubblePaintContext iconAnimation;
    /**
     * 进度被改变的回调
     */
    private LemonBubbleProgressModePaintContext onProgressChanged;
    /**
     * 图标数组，如果该数组为空或者该对象为nil，那么显示自定义动画，如果图标为一张，那么固定显示那个图标，大于一张的时候显示图片帧动画
     */
    private List<Bitmap> iconArray;
    /**
     * 要显示的标题
     */
    private String title;
    /**
     * 帧动画时间间隔
     */
    private int frameAnimationTime;
    /**
     * 图标占比 0 - 1，图标控件的边长占高度的比例
     */
    private float proportionOfIcon;
    /**
     * 间距占比 0 - 1，图标控件和标题控件之间距离占整个控件的比例（如果横向布局那么就相当于宽度，纵向布局相当于高度）
     */
    private float proportionOfSpace;
    /**
     * 内边距占比 0 - 1，整个泡泡控件的内边距，x最终为左右的内边距（左右内边距以宽度算最终的像素值）
     */
    private int proportionOfPaddingX;
    /**
     * 内边距占比 0 - 1，整个泡泡控件的内边距，y最终为上下的内边距（上下边距以高度算最终的像素值）
     */
    private int proportionOfPaddingY;
    /**
     * 位置样式
     */
    private LemonBubbleLocationStyle locationStyle;
    /**
     * 泡泡控件显示时偏移，当位置样式为上中的时候，偏移值是向下移动，当位置样式为底部时候，偏移值是向上移动
     */
    private int proportionOfDeviation;
    /**
     * 是否展示蒙版，展示蒙版后，显示泡泡控件时会产生一个蒙版层来拦截所有其他控件的点击事件
     */
    private boolean isShowMaskView;
    /**
     * 蒙版颜色
     */
    private int maskColor;
    /**
     * 泡泡控件的背景色
     */
    private int backgroundColor;
    /**
     * 图标渲染色
     */
    private int iconColor;
    /**
     * 标题文字颜色
     */
    private int titleColor;
    /**
     * 标题字体大小
     */
    private int titleFontSize;

    public int getBubbleWidth() {
        return bubbleWidth;
    }

    public void setBubbleWidth(int bubbleWidth) {
        this.bubbleWidth = bubbleWidth;
    }

    public int getBubbleHeight() {
        return bubbleHeight;
    }

    public void setBubbleHeight(int bubbleHeight) {
        this.bubbleHeight = bubbleHeight;
    }

    public int getCornerRadius() {
        return cornerRadius;
    }

    public void setCornerRadius(int cornerRadius) {
        this.cornerRadius = cornerRadius;
    }

    public LemonBubbleLayoutStyle getLayoutStyle() {
        return layoutStyle;
    }

    public void setLayoutStyle(LemonBubbleLayoutStyle layoutStyle) {
        this.layoutStyle = layoutStyle;
    }

    public LemonBubblePaintContext getIconAnimation() {
        return iconAnimation;
    }

    public void setIconAnimation(LemonBubblePaintContext iconAnimation) {
        this.iconAnimation = iconAnimation;
    }

    public LemonBubbleProgressModePaintContext getOnProgressChanged() {
        return onProgressChanged;
    }

    public void setOnProgressChanged(LemonBubbleProgressModePaintContext onProgressChanged) {
        this.onProgressChanged = onProgressChanged;
    }

    public List<Bitmap> getIconArray() {
        return iconArray;
    }

    public void setIconArray(List<Bitmap> iconArray) {
        this.iconArray = iconArray;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getFrameAnimationTime() {
        return frameAnimationTime;
    }

    public void setFrameAnimationTime(int frameAnimationTime) {
        this.frameAnimationTime = frameAnimationTime;
    }

    public float getProportionOfIcon() {
        return proportionOfIcon;
    }

    public void setProportionOfIcon(float proportionOfIcon) {
        this.proportionOfIcon = proportionOfIcon;
    }

    public float getProportionOfSpace() {
        return proportionOfSpace;
    }

    public void setProportionOfSpace(float proportionOfSpace) {
        this.proportionOfSpace = proportionOfSpace;
    }

    public int getProportionOfPaddingX() {
        return proportionOfPaddingX;
    }

    public void setProportionOfPaddingX(int proportionOfPaddingX) {
        this.proportionOfPaddingX = proportionOfPaddingX;
    }

    public int getProportionOfPaddingY() {
        return proportionOfPaddingY;
    }

    public void setProportionOfPaddingY(int proportionOfPaddingY) {
        this.proportionOfPaddingY = proportionOfPaddingY;
    }

    public LemonBubbleLocationStyle getLocationStyle() {
        return locationStyle;
    }

    public void setLocationStyle(LemonBubbleLocationStyle locationStyle) {
        this.locationStyle = locationStyle;
    }

    public int getProportionOfDeviation() {
        return proportionOfDeviation;
    }

    public void setProportionOfDeviation(int proportionOfDeviation) {
        this.proportionOfDeviation = proportionOfDeviation;
    }

    public boolean isShowMaskView() {
        return isShowMaskView;
    }

    public void setShowMaskView(boolean showMaskView) {
        isShowMaskView = showMaskView;
    }

    public int getMaskColor() {
        return maskColor;
    }

    public void setMaskColor(int maskColor) {
        this.maskColor = maskColor;
    }

    public int getBackgroundColor() {
        return backgroundColor;
    }

    public void setBackgroundColor(int backgroundColor) {
        this.backgroundColor = backgroundColor;
    }

    public int getIconColor() {
        return iconColor;
    }

    public void setIconColor(int iconColor) {
        this.iconColor = iconColor;
    }

    public int getTitleColor() {
        return titleColor;
    }

    public void setTitleColor(int titleColor) {
        this.titleColor = titleColor;
    }

    public int getTitleFontSize() {
        return titleFontSize;
    }

    public void setTitleFontSize(int titleFontSize) {
        this.titleFontSize = titleFontSize;
    }
}

