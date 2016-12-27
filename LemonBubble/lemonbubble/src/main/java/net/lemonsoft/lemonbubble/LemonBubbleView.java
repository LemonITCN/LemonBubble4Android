package net.lemonsoft.lemonbubble;

import android.animation.ValueAnimator;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Handler;
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
    // 当前是否被显示状态
    private boolean _isShow;
    // 记录连环动画当前播放的帧索引的变量
    private int _frameAnimationPlayIndex;
    // 帧动画播放指针切换动画器
    private ValueAnimator _framePlayIndexAnimator;

    // 是否已经初始化过了，避免重新创建控件
    private boolean haveInit = false;

    // 用于存储单例对象的变量
    private static LemonBubbleView _defaultBubbleViewObject;

    public boolean isShow() {
        return _isShow;
    }

    public synchronized void setIsShow(boolean isShow) {
        this._isShow = isShow;
    }

    /**
     * 获取单例泡泡控件对象
     *
     * @return 单例泡泡控件实例对象
     */
    public static LemonBubbleView defaultBubbleView(Context context) {
        if (_defaultBubbleViewObject == null)
            _defaultBubbleViewObject = new LemonBubbleView();
        return _defaultBubbleViewObject;
    }

    /**
     * 获取单例泡泡控件对象 - 调用此方法前提是通过setDefaultContext方法设置了默认的context对象
     *
     * @return 单例泡泡控件实例对象
     */
    public static synchronized LemonBubbleView defaultBubbleView() {
        if (_defaultBubbleViewObject == null) {
            _defaultBubbleViewObject = new LemonBubbleView();
        }
        return _defaultBubbleViewObject;
    }

    /**
     * 自动初始化
     *
     * @param context 上下文对象
     */
    private void autoInit(Context context) {
        _context = context;
        _PST.setContext(context);// 初始化尺寸工具类
        if (!haveInit) {
            initContainerAndRootLayout();// 初始化容器和根视图
            initCommonView();// 初始化公共的控件
            haveInit = true;
        }
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
                return keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0;
            }
        });
    }

    /**
     * 初始化公共的控件
     */
    private void initCommonView() {
        // 实例化灰色半透明蒙版控件
        _backMaskView = new View(_context);
        // 设置全屏宽
        _backMaskView.setLayoutParams(new RelativeLayout.LayoutParams(_PST.dpToPx(_PST.screenWidthDp()), _PST.dpToPx(_PST.screenHeightDp())));
        _rootLayout.setAlpha(0);// 设置全透明，也就是默认不可见，后期通过动画改变来显示

        // 实例化内容面板控件
        _contentPanel = new RelativeLayout(_context);
        _contentPanel.setX(_PST.dpToPx((int) (_PST.screenWidthDp() / 2.0)));
        _contentPanel.setY(_PST.dpToPx((int) (_PST.screenHeightDp() / 2.0)));

        // 实例化绘图动画和帧图片显示的控件
        _paintView = new LemonBubblePaintView(_context);

        // 实例化标题显示标签控件
        _titleView = new TextView(_context);
        _titleView.setX(0);
        _titleView.setY(0);
        _titleView.setGravity(Gravity.CENTER);

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
    private void initContentPanel(final LemonBubbleInfo info) {
        if (_framePlayIndexAnimator != null)
            _framePlayIndexAnimator.end();
        _paintView.setImageBitmap(null);
        _paintView.setBubbleInfo(null);
        if (info.getIconArray() == null || info.getIconArray().size() == 0) {
            // 显示自定义动画
            _paintView.setBubbleInfo(info);
        } else if (info.getIconArray().size() == 1) {
            // 显示单张图片
            _paintView.setImageBitmap(info.getIconArray().get(0));
        } else {
            // 逐帧连环动画
            _framePlayIndexAnimator = ValueAnimator.ofInt(0, info.getIconArray().size() - 1);
            _framePlayIndexAnimator.setDuration(info.getIconArray().size() * info.getFrameAnimationTime());
            _framePlayIndexAnimator.setRepeatCount(Integer.MAX_VALUE);
            _framePlayIndexAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                @Override
                public void onAnimationUpdate(ValueAnimator animation) {
                    _paintView.setImageBitmap(info.getIconArray().get((int) (animation.getAnimatedValue())));
                }
            });
            _framePlayIndexAnimator.start();
        }
        // 初始化主内容面板的相关属性
        _PAT.setAlpha(_rootLayout, 1);
        _PAT.setBackgroundColor(_contentPanel, info.getCornerRadius(), info.getBackgroundColor());
        info.calBubbleViewContentPanelFrame(_contentPanel);
        info.calPaintViewAndTitleViewFrame(_paintView, _titleView);
        _PAT.setAlpha(_contentPanel, 1);
        // 设置蒙版色
        _PAT.setBackgroundColor(_backMaskView, 0, info.getMaskColor());
    }

    /**
     * 展示泡泡控件
     *
     * @param context    上下文对象
     * @param bubbleInfo 泡泡信息描述对象
     */
    public void showBubbleInfo(Context context, LemonBubbleInfo bubbleInfo) {
        autoInit(context);
        _currentBubbleInfo = bubbleInfo;// 现将泡泡信息对象保存起来
        if (!isShow())// 如果已经显示，就不进行再弹出新的层
            _container.show();
        initContentPanel(bubbleInfo);// 根据泡泡信息对象对正文内容面板进行初始化
    }

    /**
     * 展示泡泡控件并在指定的时间后关闭
     *
     * @param context       上下文对象
     * @param bubbleInfo    泡泡信息描述对象
     * @param autoCloseTime 自动关闭的时间
     */
    public void showBubbleInfo(final Context context, final LemonBubbleInfo bubbleInfo, int autoCloseTime) {
        showBubbleInfo(context, bubbleInfo);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if (_currentBubbleInfo.hashCode() == bubbleInfo.hashCode())// 当前正在显示的泡泡信息对象没有改变
                    hide();
            }
        }, autoCloseTime);
    }

    /**
     * 隐藏当前正在显示的泡泡控件
     */
    public void hide() {
        _PAT.setAlpha(_rootLayout, 0);
        _PAT.setAlpha(_contentPanel, 0);
        _PAT.setSize(_contentPanel, 0, 0);
        _PAT.setSize(_paintView, 0, 0);
        _PAT.setSize(_titleView, 0, 0);
        _PAT.setLocation(_paintView, 0, 0);
        _PAT.setLocation(_titleView, 0, 0);
        _PAT.setLocation(_contentPanel, _PST.screenWidthDp() / 2, _PST.screenHeightDp() / 2);
        setIsShow(false);// 设置当前的状态为不显示状态
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                _container.dismiss();
            }
        }, 300);
    }

}
