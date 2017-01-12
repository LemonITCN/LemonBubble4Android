package net.lemonsoft.lemonbubble;

import android.graphics.Bitmap;
import android.graphics.Color;

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

public class LemonBubbleGlobal {

    /**
     * 泡泡控件的宽
     */
    public static int bubbleWidth = 180;
    /**
     * 泡泡控件的高
     */
    public static int bubbleHeight = 120;
    /**
     * 泡泡控件的圆角半径
     */
    public static int cornerRadius = 8;
    /**
     * 图文布局属性
     */
    public static LemonBubbleLayoutStyle layoutStyle = LemonBubbleLayoutStyle.ICON_TOP_TITLE_BOTTOM;
    /**
     * 自定义图标动画
     */
    public static LemonBubblePaintContext iconAnimation = null;
    /**
     * 自定义图标动画是否重复播放
     */
    public static boolean isIconAnimationRepeat = false;
    /**
     * 进度被改变的回调
     */
    public static LemonBubbleProgressModePaintContext onProgressChanged = null;
    /**
     * 图标数组，如果该数组为空或者该对象为null，那么显示自定义动画，如果图标为一张，那么固定显示那个图标，大于一张的时候显示图片帧动画
     */
    public static List<Bitmap> iconArray = null;
    /**
     * 要显示的标题
     */
    public static String title = "LemonBubble";
    /**
     * 帧动画时间间隔，单位ms
     * 如果当前为自定义动画模式，那么该时间为自定义动画的单次变换时间
     */
    public static int frameAnimationTime = 500;
    /**
     * 图标占比 0 - 1，图标控件的边长占高度的比例
     */
    public static float proportionOfIcon = 0.675f;
    /**
     * 间距占比 0 - 1，图标控件和标题控件之间距离占整个控件的比例（如果横向布局那么就相当于宽度，纵向布局相当于高度）
     */
    public static float proportionOfSpace = 0.1f;
    /**
     * 内边距占比 0 - 1，整个泡泡控件的内边距，x最终为左右的内边距（左右内边距以宽度算最终的像素值）
     */
    public static float proportionOfPaddingX = 0.1f;
    /**
     * 内边距占比 0 - 1，整个泡泡控件的内边距，y最终为上下的内边距（上下边距以高度算最终的像素值）
     */
    public static float proportionOfPaddingY = 0.1f;
    /**
     * 位置样式
     */
    public static LemonBubbleLocationStyle locationStyle = LemonBubbleLocationStyle.CENTER;
    /**
     * 泡泡控件显示时偏移，当位置样式为上中的时候，偏移值是向下移动，当位置样式为底部时候，偏移值是向上移动
     */
    public static float proportionOfDeviation = 0f;
    /**
     * 是否展示蒙版，展示蒙版后，显示泡泡控件时会产生一个蒙版层来拦截所有其他控件的点击事件
     */
    public static boolean isShowMaskView = true;
    /**
     * 蒙版颜色
     */
    public static int maskColor = Color.argb(150, 0, 0, 0);
    /**
     * 泡泡控件的背景色
     */
    public static int backgroundColor = Color.argb((int) (255 * 0.8), 255, 255, 255);
    /**
     * 图标渲染色
     */
    public static int iconColor = Color.BLACK;
    /**
     * 标题文字颜色
     */
    public static int titleColor = Color.BLACK;
    /**
     * 标题字体大小
     */
    public static int titleFontSize = 11;
    /**
     * 蒙版被点击的回调
     */
    public static LemonBubbleMaskOnTouchContext onMaskTouchContext = null;
    /**
     * 是否显示状态栏
     */
    public static boolean showStatusBar = true;
    /**
     * 状态栏的颜色
     */
    public static int statusBarColor = Color.BLACK;

    /**
     * 是否使用Fragment显示状态检测功能
     * 如果开启，则如果在未被显示的Fragment中调用，弹框会自动被忽略
     * <p>
     * 测试阶段哦，如果发现检测的不准确，麻烦告诉我一声，liuri@lemonsoft.net
     */
    public static boolean useFragmentDisplayCheck = true;


}

