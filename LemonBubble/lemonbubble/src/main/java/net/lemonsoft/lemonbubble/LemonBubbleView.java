package net.lemonsoft.lemonbubble;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.RelativeLayout;
import android.widget.TextView;

/**
 * 柠檬泡泡控件
 * Created by LiuRi on 2016/12/23.
 */

public class LemonBubbleView {

    //泡泡控件的实体控件容器
    private Dialog _container;
    // 整个dialog全屏的布局容器
    private RelativeLayout _rootLayout;
    // 对context对象进行存储的变量
    private Context _context;
    // 对LemonBubblePrivateSizeTool的名称缩短变量，此变量仅为了让名称变短，代码整洁
    private LemonBubblePrivateSizeTool _PST = LemonBubblePrivateSizeTool.getPrivateSizeTool();
    // 对LemonBubblePrivateAnimationTool的名称缩短变量，此变量仅为了让名称变短，代码整洁
    private LemonBubblePrivateAnimationTool _PAT = LemonBubblePrivateAnimationTool.defaultPrivateAnimationTool();

    // 当前正在显示的泡泡控件的信息对象
    private LemonBubbleInfo _currentBubbleInfo;
    // 背景灰色半透明蒙版
    private View _backMaskView;
    // 包含弹出框真正内容的小布局面板
    private RelativeLayout _contentPanel;
    // 动画和帧图片显示的控件
    private LemonBubblePaintView _paintView;
    // 标题显示标签控件
    private TextView _titleView;
    // 记录连环动画当前播放的帧索引的变量
    private int _frameAnimationPlayIndex;

    // 用于存储单例对象的变量
    private static LemonBubbleView _defaultBubbleViewObject;
    private static Context _defaultContext;

    /**
     * 获取单例泡泡控件对象
     *
     * @return 单例泡泡控件实例对象
     */
    public static LemonBubbleView defaultBubbleView(Context context) {
        if (_defaultBubbleViewObject == null)
            _defaultBubbleViewObject = new LemonBubbleView(context);
        return _defaultBubbleViewObject;
    }

    /**
     * 获取单例泡泡控件对象 - 调用此方法前提是通过setDefaultContext方法设置了默认的context对象
     *
     * @return 单例泡泡控件实例对象
     */
    public static LemonBubbleView defaultBubbleView() {
        if (_defaultBubbleViewObject == null) {
            if (_defaultContext == null) {
                new Exception("Default _context not found!").printStackTrace();
                return null;
            } else
                _defaultBubbleViewObject = new LemonBubbleView(_defaultContext);
        }
        return _defaultBubbleViewObject;
    }

    public static void set_defaultContext(Context _defaultContext) {
        LemonBubbleView._defaultContext = _defaultContext;
    }

    private LemonBubbleView() {
    }

    public LemonBubbleView(Context context) {
        _context = context;
        _PST.setContext(context);// 初始化尺寸工具类
        initContainerAndRootLayout();
    }

    /**
     * 初始化容器与根视图布局
     */
    private void initContainerAndRootLayout() {
        _container = new Dialog(_context, android.R.style.Theme_NoTitleBar);// 创建对话框对象并设置无标题栏主题
        _rootLayout = new RelativeLayout(_context);// 实例化根布局对象
        Window window = _container.getWindow();
        if (window == null) {// 检测是否成功获取window对象
            // 如果为null那么不再继续进行，防止空指针异常
            new Exception("Get lemon bubble dialog's window error!").printStackTrace();
            return;
        }
        window.getDecorView().setPadding(0, 0, 0, 0);// 去掉系统默认的与屏幕边缘的内边距
        window.setBackgroundDrawableResource(android.R.color.transparent);// 设置背景透明
        window.addFlags(WindowManager.LayoutParams.FLAG_LAYOUT_IN_SCREEN);// 设置窗口全屏
        window.addFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);// 防止状态栏更新导致界面卡顿
        _container.setContentView(_rootLayout);// 把根视图与对话框相关联
        _container.setCanceledOnTouchOutside(false);// 设置背景点击关闭为true
        _container.setOnKeyListener(new DialogInterface.OnKeyListener() {// 禁止返回按钮返回
            public boolean onKey(DialogInterface dialog, int keyCode, KeyEvent event) {
                if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0)
                    return true;
                else
                    return false;
            }
        });
    }

    private int _DP(int dpValue) {
        return _PST.dpToPx(dpValue);
    }

    /**
     * 初始化公共的控件
     */
    private void initCommonView() {
        // 实例化灰色半透明蒙版控件
        _backMaskView = new View(_context);
        // 设置全屏宽
        _PAT.setSize(_backMaskView, _PST.screenWidthDp() + 1, _PST.screenHeightDp() + 1);// 为了防止强转导致的屏幕宽度缺失一小部分而+1
        // 设置半透明黑色
        _backMaskView.setBackgroundColor(Color.argb(100, 0, 0, 0));
        // _rootLayout.setAlpha(0);// 设置全透明，也就是默认不可见，后期通过动画改变来显示

        // 实例化内容面板控件
        _contentPanel = new RelativeLayout(_context);

        // 实例化绘图动画和帧图片显示的控件
        _paintView = new LemonBubblePaintView(_context);
        _paintView.setBackgroundColor(Color.RED);

        // 实例化标题显示标签控件
        _titleView = new TextView(_context);
        _titleView.setGravity(Gravity.CENTER);
        _titleView.setBackgroundColor(Color.BLUE);

        // 把所有控件添加到根视图上
        _rootLayout.addView(_backMaskView);// 半透明灰色背景
        _rootLayout.addView(_contentPanel);// 主内容面板
        _contentPanel.addView(_paintView);// 动画和帧图标显示控件放置到内容面板上
        _contentPanel.addView(_titleView);// 标题显示标签控件放置到内容面板上
    }

    /**
     * 根据泡泡信息对象初始化内容面板
     *
     * @param info 泡泡信息对象
     */
    private void initContentPanel(LemonBubbleInfo info) {
        if (info.getIconArray() == null || info.getIconArray().size() == 0) {
            // 显示自定义动画

        } else if (info.getIconArray().size() == 1) {
            // 显示单张图片
        } else {
            // 逐帧连环动画
        }
        // 初始化主内容面板的相关属性
        _contentPanel.setBackgroundColor(Color.WHITE);
        info.calBubbleViewContentPanelFrame(_contentPanel);
        info.calPaintViewAndTitleViewFrame(_paintView, _titleView);

        // 初始化绘图动画与帧图片展示的控件的相关属性
        _paintView.setBubbleInfo(info);// 传入泡泡信息对象
    }

    public void showBubbleInfo(LemonBubbleInfo bubbleInfo) {
        _currentBubbleInfo = bubbleInfo;// 现将泡泡信息对象保存起来
        initCommonView();// 初始化公共的控件
        initContentPanel(bubbleInfo);// 根据泡泡信息对象对正文内容面板进行初始化
        _container.show();
    }

}
