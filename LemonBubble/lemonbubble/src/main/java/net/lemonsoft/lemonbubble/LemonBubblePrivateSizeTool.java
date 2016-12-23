package net.lemonsoft.lemonbubble;

import android.content.Context;
import android.util.DisplayMetrics;
import android.view.WindowManager;

/**
 * LemonBubble内私有使用的尺寸工具类
 * Created by LiuRi on 2016/12/23.
 */

public class LemonBubblePrivateSizeTool {

    private float _density;
    private DisplayMetrics _metrics;

    private static LemonBubblePrivateSizeTool _privateSizeTool;

    public static LemonBubblePrivateSizeTool getPrivateSizeTool() {
        if (_privateSizeTool == null)
            _privateSizeTool = new LemonBubblePrivateSizeTool();
        return _privateSizeTool;
    }

    protected void setContext(Context context) {
        _density = context.getResources().getDisplayMetrics().density;
        _metrics = new DisplayMetrics();
        ((WindowManager) (context.getSystemService(Context.WINDOW_SERVICE))).getDefaultDisplay().getMetrics(_metrics);
    }

    /**
     * 换算dp到px
     *
     * @param dpValue dp的数值
     * @return 对应的px数值
     */
    protected int dpToPx(int dpValue) {
        return (int) (_density * dpValue + 0.5f);
    }

    /**
     * 换算px到dp
     *
     * @param pxValue px的数值
     * @return 对应的dp数值
     */
    protected int pxToDp(int pxValue) {
        return (int) (pxValue / _density + 0.5f);
    }

    /**
     * 获取屏幕的宽，单位dp
     *
     * @return 屏幕宽度dp值
     */
    protected int screenWidthDp() {
        return pxToDp(_metrics.widthPixels);
    }

    /**
     * 获取屏幕的高，单位dp
     *
     * @return 屏幕高度的dp值
     */
    protected int screenHeightDp() {
        return pxToDp(_metrics.heightPixels);
    }

}
