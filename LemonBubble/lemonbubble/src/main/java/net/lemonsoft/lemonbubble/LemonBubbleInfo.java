package net.lemonsoft.lemonbubble;

import android.app.Fragment;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import net.lemonsoft.lemonbubble.enums.LemonBubbleLayoutStyle;
import net.lemonsoft.lemonbubble.enums.LemonBubbleLocationStyle;
import net.lemonsoft.lemonbubble.interfaces.LemonBubbleMaskOnTouchContext;
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
    private int bubbleWidth = LemonBubbleGlobal.bubbleWidth;
    /**
     * 泡泡控件的高
     */
    private int bubbleHeight = LemonBubbleGlobal.bubbleHeight;
    /**
     * 泡泡控件的圆角半径
     */
    private int cornerRadius = LemonBubbleGlobal.cornerRadius;
    /**
     * 图文布局属性
     */
    private LemonBubbleLayoutStyle layoutStyle = LemonBubbleGlobal.layoutStyle;
    /**
     * 自定义图标动画
     */
    private LemonBubblePaintContext iconAnimation = LemonBubbleGlobal.iconAnimation;
    /**
     * 自定义图标动画是否重复播放
     */
    private boolean isIconAnimationRepeat = LemonBubbleGlobal.isIconAnimationRepeat;
    /**
     * 进度被改变的回调
     */
    private LemonBubbleProgressModePaintContext onProgressChanged = LemonBubbleGlobal.onProgressChanged;
    /**
     * 图标数组，如果该数组为空或者该对象为null，那么显示自定义动画，如果图标为一张，那么固定显示那个图标，大于一张的时候显示图片帧动画
     */
    private List<Bitmap> iconArray = LemonBubbleGlobal.iconArray;
    /**
     * 要显示的标题
     */
    private String title = LemonBubbleGlobal.title;
    /**
     * 帧动画时间间隔，单位ms
     * 如果当前为自定义动画模式，那么该时间为自定义动画的单次变换时间
     */
    private int frameAnimationTime = LemonBubbleGlobal.frameAnimationTime;
    /**
     * 图标占比 0 - 1，图标控件的边长占高度的比例
     */
    private float proportionOfIcon = LemonBubbleGlobal.proportionOfIcon;
    /**
     * 间距占比 0 - 1，图标控件和标题控件之间距离占整个控件的比例（如果横向布局那么就相当于宽度，纵向布局相当于高度）
     */
    private float proportionOfSpace = LemonBubbleGlobal.proportionOfSpace;
    /**
     * 内边距占比 0 - 1，整个泡泡控件的内边距，x最终为左右的内边距（左右内边距以宽度算最终的像素值）
     */
    private float proportionOfPaddingX = LemonBubbleGlobal.proportionOfPaddingX;
    /**
     * 内边距占比 0 - 1，整个泡泡控件的内边距，y最终为上下的内边距（上下边距以高度算最终的像素值）
     */
    private float proportionOfPaddingY = LemonBubbleGlobal.proportionOfPaddingY;
    /**
     * 位置样式
     */
    private LemonBubbleLocationStyle locationStyle = LemonBubbleGlobal.locationStyle;
    /**
     * 泡泡控件显示时偏移，当位置样式为上中的时候，偏移值是向下移动，当位置样式为底部时候，偏移值是向上移动
     */
    private float proportionOfDeviation = LemonBubbleGlobal.proportionOfDeviation;
    /**
     * 是否展示蒙版，展示蒙版后，显示泡泡控件时会产生一个蒙版层来拦截所有其他控件的点击事件
     */
    private boolean isShowMaskView = LemonBubbleGlobal.isShowMaskView;
    /**
     * 蒙版颜色
     */
    private int maskColor = LemonBubbleGlobal.maskColor;
    /**
     * 泡泡控件的背景色
     */
    private int backgroundColor = LemonBubbleGlobal.backgroundColor;
    /**
     * 图标渲染色
     */
    private int iconColor = LemonBubbleGlobal.iconColor;
    /**
     * 标题文字颜色
     */
    private int titleColor = LemonBubbleGlobal.titleColor;
    /**
     * 标题字体大小
     */
    private int titleFontSize = LemonBubbleGlobal.titleFontSize;
    /**
     * 蒙版被点击的回调
     */
    private LemonBubbleMaskOnTouchContext onMaskTouchContext = LemonBubbleGlobal.onMaskTouchContext;
    /**
     * 是否显示状态栏
     */
    private boolean showStatusBar = LemonBubbleGlobal.showStatusBar;
    /**
     * 状态栏的颜色
     */
    private int statusBarColor = LemonBubbleGlobal.statusBarColor;

    public String getTitle() {
        return title;
    }

    public LemonBubbleInfo setTitle(String title) {
        this.title = title;
        return this;
    }

    public int getBubbleWidth() {
        return bubbleWidth;
    }

    public LemonBubbleInfo setBubbleWidth(int bubbleWidth) {
        this.bubbleWidth = bubbleWidth;
        return this;
    }

    public int getBubbleHeight() {
        return bubbleHeight;
    }

    public LemonBubbleInfo setBubbleHeight(int bubbleHeight) {
        this.bubbleHeight = bubbleHeight;
        return this;
    }

    public LemonBubbleInfo setBubbleSize(int width, int height) {
        setBubbleWidth(width);
        setBubbleHeight(height);
        return this;
    }

    public int getCornerRadius() {
        return cornerRadius;
    }

    public LemonBubbleInfo setCornerRadius(int cornerRadius) {
        this.cornerRadius = cornerRadius;
        return this;
    }

    public LemonBubbleLayoutStyle getLayoutStyle() {
        return layoutStyle;
    }

    public LemonBubbleInfo setLayoutStyle(LemonBubbleLayoutStyle layoutStyle) {
        this.layoutStyle = layoutStyle;
        return this;
    }

    public LemonBubblePaintContext getIconAnimation() {
        return iconAnimation;
    }

    public LemonBubbleInfo setIconAnimation(LemonBubblePaintContext iconAnimation) {
        this.iconAnimation = iconAnimation;
        return this;
    }

    public boolean isIconAnimationRepeat() {
        return isIconAnimationRepeat;
    }

    public LemonBubbleInfo setIconAnimationRepeat(boolean iconAnimationRepeat) {
        isIconAnimationRepeat = iconAnimationRepeat;
        return this;
    }

    public LemonBubbleProgressModePaintContext getOnProgressChanged() {
        return onProgressChanged;
    }

    public LemonBubbleInfo setOnProgressChanged(LemonBubbleProgressModePaintContext onProgressChanged) {
        this.onProgressChanged = onProgressChanged;
        return this;
    }

    public List<Bitmap> getIconArray() {
        return iconArray;
    }

    public LemonBubbleInfo setIconArray(List<Bitmap> iconArray) {
        this.iconArray = iconArray;
        return this;
    }

    public int getFrameAnimationTime() {
        return frameAnimationTime;
    }

    public LemonBubbleInfo setFrameAnimationTime(int frameAnimationTime) {
        this.frameAnimationTime = frameAnimationTime;
        return this;
    }

    public float getProportionOfIcon() {
        return proportionOfIcon;
    }

    public LemonBubbleInfo setProportionOfIcon(float proportionOfIcon) {
        this.proportionOfIcon = proportionOfIcon;
        return this;
    }

    public float getProportionOfSpace() {
        return proportionOfSpace;
    }

    public LemonBubbleInfo setProportionOfSpace(float proportionOfSpace) {
        this.proportionOfSpace = proportionOfSpace;
        return this;
    }

    public float getProportionOfPaddingX() {
        return proportionOfPaddingX;
    }

    public LemonBubbleInfo setProportionOfPaddingX(float proportionOfPaddingX) {
        this.proportionOfPaddingX = proportionOfPaddingX;
        return this;
    }

    public float getProportionOfPaddingY() {
        return proportionOfPaddingY;
    }

    public LemonBubbleInfo setProportionOfPaddingY(float proportionOfPaddingY) {
        this.proportionOfPaddingY = proportionOfPaddingY;
        return this;
    }

    public LemonBubbleLocationStyle getLocationStyle() {
        return locationStyle;
    }

    public LemonBubbleInfo setLocationStyle(LemonBubbleLocationStyle locationStyle) {
        this.locationStyle = locationStyle;
        return this;
    }

    public float getProportionOfDeviation() {
        return proportionOfDeviation;
    }

    public LemonBubbleInfo setProportionOfDeviation(float proportionOfDeviation) {
        this.proportionOfDeviation = proportionOfDeviation;
        return this;
    }

    public boolean isShowMaskView() {
        return isShowMaskView;
    }

    public LemonBubbleInfo setShowMaskView(boolean showMaskView) {
        isShowMaskView = showMaskView;
        return this;
    }

    public int getMaskColor() {
        return maskColor;
    }

    public LemonBubbleInfo setMaskColor(int maskColor) {
        this.maskColor = maskColor;
        return this;
    }

    public int getBackgroundColor() {
        return backgroundColor;
    }

    public LemonBubbleInfo setBackgroundColor(int backgroundColor) {
        this.backgroundColor = backgroundColor;
        return this;
    }

    public int getIconColor() {
        return iconColor;
    }

    public LemonBubbleInfo setIconColor(int iconColor) {
        this.iconColor = iconColor;
        return this;
    }

    public int getTitleColor() {
        return titleColor;
    }

    public LemonBubbleInfo setTitleColor(int titleColor) {
        this.titleColor = titleColor;
        return this;
    }

    public int getTitleFontSize() {
        return titleFontSize;
    }

    public LemonBubbleInfo setTitleFontSize(int titleFontSize) {
        this.titleFontSize = titleFontSize;
        return this;
    }

    public LemonBubbleMaskOnTouchContext getOnMaskTouchContext() {
        return onMaskTouchContext;
    }

    public LemonBubbleInfo setOnMaskTouchContext(LemonBubbleMaskOnTouchContext onMaskTouchContext) {
        this.onMaskTouchContext = onMaskTouchContext;
        return this;
    }

    public boolean isShowStatusBar() {
        return showStatusBar;
    }

    public LemonBubbleInfo setShowStatusBar(boolean showStatusBar) {
        this.showStatusBar = showStatusBar;
        return this;
    }

    public int getStatusBarColor() {
        return statusBarColor;
    }

    public LemonBubbleInfo setStatusBarColor(int statusBarColor) {
        this.statusBarColor = statusBarColor;
        return this;
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
            case CENTER:// 当控件设置属性为屏幕居中的时候
                y = (int) ((_PST.screenHeightDp() - bubbleHeight) / 2.0);// 计算屏幕居中
                break;
            case BOTTOM:// 当位置属性设置为在屏幕底部的时候
                y = _PST.screenHeightDp() - bubbleHeight;
        }
        y += (locationStyle != LemonBubbleLocationStyle.BOTTOM ? 1 : -1) *
                (proportionOfDeviation * _PST.screenHeightDp());// 根据当前的位置属性 是上面还是中间还是下面判断便宜方向并加上偏移的值
        // 应用位置
        _PAT.setLocation(view, (int) ((_PST.screenWidthDp() - bubbleWidth) / 2.0), y);
        // 应用尺寸
        _PAT.setSize(view, bubbleWidth, bubbleHeight);
    }

    int getLineHeight(TextView textView) {
        Paint.FontMetrics fontMetrics = textView.getPaint().getFontMetrics();
        return _PST.pxToDp((int) (fontMetrics.descent - fontMetrics.top)) + 2;
    }

    /**
     * 获取指定的textV的行高
     *
     * @param textView 要获取的textView的行高
     * @return textView的每行的高度
     */
    int getTitleHeight(TextView textView, int viewWidth) {
        int widthMeasureSpec = View.MeasureSpec.makeMeasureSpec(_PST.dpToPx(viewWidth), View.MeasureSpec.AT_MOST);
        int heightMeasureSpec = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED);
        textView.measure(widthMeasureSpec, heightMeasureSpec);
        return _PST.pxToDp(textView.getMeasuredHeight());
    }

    /**
     * 获取textView的文字宽
     *
     * @param textView 要查询文字宽的标签控件
     * @return 获取到的文字宽度数值
     */
    int getTitleWidth(TextView textView) {
        return Math.min(
                (int) (bubbleWidth * (1 - proportionOfPaddingX * 2 - proportionOfSpace) - bubbleHeight * proportionOfIcon),
                _PST.pxToDp((int) textView.getPaint().measureText(textView.getText().toString()))
        );
    }

    /**
     * 计算泡泡控件中的图标和标题控件的位置和大小，并进行应用赋值
     *
     * @param paintView 图标和动画显示内容控件
     * @param titleView 标题标签控件
     */
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
        int titleHeight = getLineHeight(titleView);
        int iconX, titleX, iconY, titleY;
        iconX = titleX = baseX;
        iconY = titleY = baseY;
        titleView.setText(title);// 应用设置的标题文字
        titleView.setTextSize(titleFontSize);// 应用预先设置的字体

        switch (layoutStyle) {
            case ICON_TOP_TITLE_BOTTOM: {// 图上文下
                titleView.setLayoutParams(new RelativeLayout.LayoutParams(_DP(bubbleContentWidth),
                        RelativeLayout.LayoutParams.WRAP_CONTENT));
                titleView.postInvalidate();// 即时刷新
                titleHeight = getTitleHeight(titleView, titleWidth);
                int contentHeight = (int) (iconWidth + bubbleContentHeight * proportionOfSpace + titleHeight);
                iconX = baseX + (bubbleContentWidth - iconWidth) / 2;
                iconY = baseY + (bubbleContentHeight - contentHeight) / 2;
                titleY = (int) (iconY + iconWidth + bubbleContentHeight * proportionOfSpace);
                titleX = (int) ((baseX) + (bubbleContentWidth - titleWidth) / 2.0);
                break;
            }
            case ICON_BOTTOM_TITLE_TOP: {// 图下文上
                titleView.setLayoutParams(new RelativeLayout.LayoutParams(_DP(bubbleContentWidth),
                        RelativeLayout.LayoutParams.WRAP_CONTENT));
                titleView.postInvalidate();// 即时刷新
                titleHeight = getTitleHeight(titleView, titleWidth);
                int contentHeight = (int) (iconWidth + bubbleContentHeight * proportionOfSpace + titleHeight);
                titleY = (int) (baseY + (bubbleContentHeight - contentHeight) / 2.0);
                titleX = (int) (baseX + (bubbleContentWidth - titleWidth) / 2.0);
                iconX = (int) (baseX + (bubbleContentWidth - iconWidth) / 2.0);
                iconY = (int) (titleY + titleHeight + bubbleContentHeight * proportionOfSpace);
                break;
            }
            case ICON_LEFT_TITLE_RIGHT: {// 图左文右
                titleWidth = getTitleWidth(titleView);
                titleView.postInvalidate();// 即时刷新
                int contentWidth = (int) (iconWidth + bubbleContentWidth * proportionOfSpace + getTitleWidth(titleView));
                iconX = (int) (baseX + (bubbleContentWidth - contentWidth) / 2.0);
                iconY = (int) (baseY + (bubbleContentHeight - iconWidth) / 2.0);
                titleX = (int) (iconX + iconWidth + bubbleContentWidth * proportionOfSpace);
                titleY = (int) (baseY + (bubbleContentHeight - titleHeight) / 2.0);
                break;
            }
            case ICON_RIGHT_TITLE_LEFT: {// 图右文左
                titleWidth = getTitleWidth(titleView);
                titleView.postInvalidate();// 即时刷新
                int contentWidth = (int) (iconWidth + bubbleContentWidth * proportionOfSpace + getTitleWidth(titleView));
                titleX = (int) (baseX + (bubbleContentWidth - contentWidth) / 2.0);
                titleY = (int) (baseY + (bubbleContentHeight - titleHeight) / 2.0);
                iconX = (int) (titleX + getTitleWidth(titleView) + bubbleContentWidth * proportionOfSpace);
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
                titleHeight = getTitleHeight(titleView, titleWidth);
                iconX = iconY = iconWidth = 0;
                titleX = baseX;
                titleY = (int) (baseY + (bubbleContentHeight - getTitleHeight(titleView, titleWidth)) / 2.0);
                break;
            }
        }
        _PAT.setLocation(paintView, iconX, iconY);
        _PAT.setSize(paintView, iconWidth, iconWidth);
        _PAT.setLocation(titleView, titleX, titleY);
        _PAT.setSize(titleView, titleWidth, titleHeight);
    }

    /**
     * 展示这个泡泡控件，并且在指定的时间后关闭
     *
     * @param context       要显示在哪个Activity
     * @param autoCloseTime 自动关闭的时间
     */
    public void show(Context context, int autoCloseTime) {
        LemonBubble.showBubbleInfo(context, this, autoCloseTime);
    }

    /**
     * 展示这个泡泡控件，并且在指定的时间后关闭
     *
     * @param fragment      要判断是否处于显示状态的fragment
     * @param autoCloseTime 自动关闭的时间
     */
    public void show(Fragment fragment, int autoCloseTime) {
        LemonBubble.showBubbleInfo(fragment, this, autoCloseTime);
    }

    /**
     * 展示这个泡泡控件，并且在指定的时间后关闭
     *
     * @param fragment      要判断是否处于显示状态的fragment
     * @param autoCloseTime 自动关闭的时间
     */
    public void show(android.support.v4.app.Fragment fragment, int autoCloseTime) {
        LemonBubble.showBubbleInfo(fragment, this, autoCloseTime);
    }

    /**
     * 展示这个跑酷控件
     *
     * @param context 要显示在哪个Activity
     */
    public void show(Context context) {
        LemonBubble.showBubbleInfo(context, this);
    }

    /**
     * 展示这个跑酷控件
     *
     * @param fragment 要判断是否处于显示状态的fragment
     */
    public void show(Fragment fragment) {
        LemonBubble.showBubbleInfo(fragment, this);
    }

    /**
     * 展示这个跑酷控件
     *
     * @param fragment 要判断是否处于显示状态的fragment
     */
    public void show(android.support.v4.app.Fragment fragment) {
        LemonBubble.showBubbleInfo(fragment, this);
    }

}

