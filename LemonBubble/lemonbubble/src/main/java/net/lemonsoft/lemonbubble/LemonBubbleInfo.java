package net.lemonsoft.lemonbubble;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

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

    private LemonBubblePrivateSizeTool _PST = LemonBubblePrivateSizeTool.getPrivateSizeTool();

    private LemonBubblePrivateAnimationTool _PAT = LemonBubblePrivateAnimationTool.defaultPrivateAnimationTool();

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
    private LemonBubbleLayoutStyle layoutStyle = LemonBubbleLayoutStyle.ICON_TOP_TITLE_BOTTOM;
    /**
     * 自定义图标动画
     */
    private LemonBubblePaintContext iconAnimation = null;
    /**
     * 进度被改变的回调
     */
    private LemonBubbleProgressModePaintContext onProgressChanged = null;
    /**
     * 图标数组，如果该数组为空或者该对象为null，那么显示自定义动画，如果图标为一张，那么固定显示那个图标，大于一张的时候显示图片帧动画
     */
    private List<Bitmap> iconArray = null;
    /**
     * 要显示的标题
     */
    private String title = "LemonBubble";
    /**
     * 帧动画时间间隔，单位ms
     */
    private int frameAnimationTime = 100;
    /**
     * 图标占比 0 - 1，图标控件的边长占高度的比例
     */
    private float proportionOfIcon = 0.675f;
    /**
     * 间距占比 0 - 1，图标控件和标题控件之间距离占整个控件的比例（如果横向布局那么就相当于宽度，纵向布局相当于高度）
     */
    private float proportionOfSpace = 0.1f;
    /**
     * 内边距占比 0 - 1，整个泡泡控件的内边距，x最终为左右的内边距（左右内边距以宽度算最终的像素值）
     */
    private float proportionOfPaddingX = 0.1f;
    /**
     * 内边距占比 0 - 1，整个泡泡控件的内边距，y最终为上下的内边距（上下边距以高度算最终的像素值）
     */
    private float proportionOfPaddingY = 0.1f;
    /**
     * 位置样式
     */
    private LemonBubbleLocationStyle locationStyle = LemonBubbleLocationStyle.CENTER;
    /**
     * 泡泡控件显示时偏移，当位置样式为上中的时候，偏移值是向下移动，当位置样式为底部时候，偏移值是向上移动
     */
    private float proportionOfDeviation = 0f;
    /**
     * 是否展示蒙版，展示蒙版后，显示泡泡控件时会产生一个蒙版层来拦截所有其他控件的点击事件
     */
    private boolean isShowMaskView = true;
    /**
     * 蒙版颜色
     */
    private int maskColor = Color.argb(50, 25, 25, 25);
    /**
     * 泡泡控件的背景色
     */
    private int backgroundColor = Color.argb((int) (255 * 0.8), 255, 255, 255);
    /**
     * 图标渲染色
     */
    private int iconColor = Color.BLACK;
    /**
     * 标题文字颜色
     */
    private int titleColor = Color.BLACK;
    /**
     * 标题字体大小
     */
    private int titleFontSize = 13;

    public LemonBubbleInfo(Context context) {
        _PST.setContext(context);
        bubbleWidth = 180;
        bubbleHeight = 120;
        cornerRadius = 3;
        System.out.println("hello nihao");
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

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

    public float getProportionOfPaddingX() {
        return proportionOfPaddingX;
    }

    public void setProportionOfPaddingX(float proportionOfPaddingX) {
        this.proportionOfPaddingX = proportionOfPaddingX;
    }

    public float getProportionOfPaddingY() {
        return proportionOfPaddingY;
    }

    public void setProportionOfPaddingY(float proportionOfPaddingY) {
        this.proportionOfPaddingY = proportionOfPaddingY;
    }

    public LemonBubbleLocationStyle getLocationStyle() {
        return locationStyle;
    }

    public void setLocationStyle(LemonBubbleLocationStyle locationStyle) {
        this.locationStyle = locationStyle;
    }

    public float getProportionOfDeviation() {
        return proportionOfDeviation;
    }

    public void setProportionOfDeviation(float proportionOfDeviation) {
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

    private int _DP(int value) {
        return LemonBubblePrivateSizeTool.getPrivateSizeTool().dpToPx(value);
    }

    /**
     * 根据当前泡泡信息对象中的属性计算并设置泡泡控件中内容面板的位置和大小
     *
     * @param view 泡泡控件中的内容信息面板控件
     */
    void calBubbleViewContentPanelFrame(View view) {
        int y = 0;
        switch (locationStyle) {
            case CENTER:
                y = (int) ((_PST.screenHeightDp() - bubbleHeight) / 2.0);
                break;
            case BOTTOM:
                y = _PST.screenHeightDp() - bubbleHeight;
        }
        y += (locationStyle != LemonBubbleLocationStyle.BOTTOM ? 1 : -1) *
                (proportionOfDeviation * _PST.screenHeightDp());
        _PAT.setLocation(view, (int) ((_PST.screenWidthDp() - bubbleWidth) / 2.0), y);
        _PAT.setSize(view, bubbleWidth, bubbleHeight);
    }

    void calPaintViewAndTitleViewFrame(LemonBubblePaintView paintView, TextView titleView) {
        int bubbleContentWidth = (int) (bubbleWidth * (1 - proportionOfPaddingX * 2));
        int bubbleContentHeight = (int) (bubbleHeight * (1 - proportionOfPaddingY * 2));
        int iconWidth = (int) (layoutStyle == LemonBubbleLayoutStyle.TITLE_ONLY ? 0 : bubbleContentHeight * proportionOfIcon);
        int baseX = (int) (bubbleWidth * proportionOfPaddingX);
        int baseY = (int) (bubbleHeight * proportionOfPaddingY);
        int titleWidth = (int) ((layoutStyle == LemonBubbleLayoutStyle.ICON_TOP_TITLE_BOTTOM ||
                layoutStyle == LemonBubbleLayoutStyle.ICON_BOTTOM_TITLE_TOP ||
                layoutStyle == LemonBubbleLayoutStyle.TITLE_ONLY) ?
                bubbleContentWidth :
                bubbleContentWidth * (1 - proportionOfSpace - iconWidth));
        int titleHeight = _DP(titleFontSize);
        int iconX, titleX, iconY, titleY;
        iconX = titleX = baseX;
        iconY = titleY = baseY;
        titleView.setText(title);

        switch (layoutStyle) {
            case ICON_TOP_TITLE_BOTTOM: {
                titleView.setLayoutParams(new RelativeLayout.LayoutParams(_DP(bubbleContentWidth),
                        RelativeLayout.LayoutParams.WRAP_CONTENT));
                titleView.postInvalidate();// 即时刷新
                int contentHeight = (int) (iconWidth + bubbleContentHeight * proportionOfSpace + titleView.getMeasuredHeight());
                iconX = baseX + (bubbleContentWidth - iconWidth) / 2;
                iconY = baseY + (bubbleContentHeight - contentHeight) / 2;
                titleY = (int) (iconY + iconWidth + bubbleContentHeight * proportionOfSpace);
                titleX = (int) ((baseX) + (bubbleContentWidth - titleWidth) / 2.0);
                break;
            }
            case ICON_BOTTOM_TITLE_TOP: {
                titleView.setLayoutParams(new RelativeLayout.LayoutParams(_DP(bubbleContentWidth),
                        RelativeLayout.LayoutParams.WRAP_CONTENT));
                titleView.postInvalidate();// 即时刷新
                int contentHeight = (int) (iconWidth + bubbleContentHeight * proportionOfSpace + titleView.getMeasuredHeight());
                titleY = (int) (baseY + (bubbleContentHeight - contentHeight) / 2.0);
                titleX = (int) (baseX + (bubbleContentWidth - titleWidth) / 2.0);
                iconX = (int) (baseX + (bubbleContentWidth - iconWidth) / 2.0);
                iconY = (int) (titleY + titleView.getMeasuredHeight() + bubbleContentHeight * proportionOfSpace);
                break;
            }
            case ICON_LEFT_TITLE_RIGHT: {
                titleView.setLayoutParams(new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT,
                        RelativeLayout.LayoutParams.WRAP_CONTENT));
                titleView.postInvalidate();// 即时刷新
                int contentWidth = (int) (iconWidth + bubbleContentWidth * proportionOfSpace + titleView.getMeasuredWidth());
                iconX = (int) (baseX + (bubbleContentWidth - contentWidth) / 2.0);
                iconY = (int) (baseY + (bubbleContentHeight - iconWidth) / 2.0);
                titleX = (int) (iconX + iconWidth + bubbleContentWidth * proportionOfSpace);
                titleY = (int) (baseY + (bubbleContentHeight - titleHeight) / 2.0);
                break;
            }
            case ICON_RIGHT_TITLE_LEFT: {
                titleView.setLayoutParams(new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT,
                        RelativeLayout.LayoutParams.WRAP_CONTENT));
                titleView.postInvalidate();// 即时刷新
                int contentWidth = (int) (iconWidth + bubbleContentWidth * proportionOfSpace + titleView.getMeasuredWidth());
                titleX = (int) (baseX + (bubbleContentWidth - contentWidth) / 2.0);
                titleY = (int) (baseY + (bubbleContentHeight - iconWidth) / 2.0);
                iconX = (int) (titleX + titleView.getMeasuredWidth() + bubbleContentWidth * proportionOfSpace);
                iconY = (int) (baseY + (bubbleContentHeight - iconWidth) / 2.0);
                break;
            }
            case ICON_ONLY: {
                titleX = titleY = titleWidth = titleHeight = 0;
                iconX = (int) (baseX + (bubbleContentWidth - iconWidth) / 2.0);
                iconY = (int) (baseY + (bubbleContentHeight - iconWidth) / 2.0);
                break;
            }
            case TITLE_ONLY: {
                titleView.setLayoutParams(new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT,
                        RelativeLayout.LayoutParams.WRAP_CONTENT));
                titleView.postInvalidate();// 即时刷新
                iconX = iconY = iconWidth = 0;
                titleX = (int) (baseX + (bubbleContentWidth - titleView.getMeasuredWidth()) / 2.0);
                titleY = (int) (baseY + (bubbleContentHeight - titleView.getMeasuredHeight()) / 2.0);
                break;
            }
        }
        _PAT.setLocation(paintView, iconX, iconY);
        _PAT.setSize(paintView, iconWidth, iconWidth);
        _PAT.setLocation(titleView, titleX, titleY);
        _PAT.setSize(titleView, titleWidth, titleHeight);
    }

}

