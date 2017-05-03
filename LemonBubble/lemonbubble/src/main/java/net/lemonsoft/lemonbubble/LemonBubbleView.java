package net.lemonsoft.lemonbubble;

import android.animation.ValueAnimator;
import android.app.Dialog;
import android.app.Fragment;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.ImageFormat;
import android.os.Build;
import android.os.Handler;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.LinearInterpolator;
import android.webkit.WebView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import net.lemonsoft.lemonbubble.interfaces.LemonBubbleLifeCycleDelegate;

/**
 * 柠檬泡泡控件
 * Created by LiuRi on 2016/12/23.
 */

public class LemonBubbleView {

    // 泡泡控件的实体控件容器
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
    /**
     * 生命周期代理，可以通过生命周期代理来处理一些提示框显示或消失等节点的特殊事件
     */
    private LemonBubbleLifeCycleDelegate lifeCycleDelegate;

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
    public static synchronized LemonBubbleView defaultBubbleView(Context context) {
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
        _container = new Dialog(// 判断是否有状态栏
                _context,
                _currentBubbleInfo.isShowStatusBar() ?
                        android.R.style.Theme_NoTitleBar :
                        android.R.style.Theme_NoTitleBar_Fullscreen
        ) {
            @Override
            public void dismiss() {
                super.dismiss();
                if (lifeCycleDelegate != null)
                    lifeCycleDelegate.alreadyHide(LemonBubbleView.this, _currentBubbleInfo);
            }
        };// 创建对话框对象并设置无标题栏主题
        if (_currentBubbleInfo.isShowStatusBar()) {
            Window window = _container.getWindow();// 设置
            if (window != null && Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
                window.setStatusBarColor(_currentBubbleInfo.getStatusBarColor());
            }
        }
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
                if ((keyCode == KeyEvent.KEYCODE_HOME || keyCode == KeyEvent.KEYCODE_BACK) && event.getRepeatCount() == 0) {
                    return true;
                } else {
                    return false;
                }
            }
        });
    }

    /**
     * 初始化公共的控件
     */
    private void initCommonView() {
        // 实例化灰色半透明蒙版控件
        _backMaskView = new View(_context);
        _backMaskView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (_currentBubbleInfo.getOnMaskTouchContext() != null)
                    _currentBubbleInfo.getOnMaskTouchContext().onTouch(_currentBubbleInfo, LemonBubbleView.this);
            }
        });
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
            _framePlayIndexAnimator = ValueAnimator.ofInt(0, info.getIconArray().size());// 设置逐帧动画播放器的帧数范围为0 到为设置的图片数组中的元素数量 - 1
            _framePlayIndexAnimator.setDuration(info.getIconArray().size() * info.getFrameAnimationTime());// 设置逐帧动画播放时间为动画帧数 * 每帧的时间间隔
            _framePlayIndexAnimator.setRepeatCount(Integer.MAX_VALUE);// 设置重复次数为最大整数，为了不手动停止就一直循环
            _framePlayIndexAnimator.setInterpolator(new LinearInterpolator());
            _framePlayIndexAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                @Override
                public void onAnimationUpdate(ValueAnimator animation) {
                    // 逐帧修改图片，启动连环动画的效果
                    if (((int) animation.getAnimatedValue()) < info.getIconArray().size())
                        _paintView.setImageBitmap(info.getIconArray().get((Integer) animation.getAnimatedValue()));
                }
            });
            // 启动动画执行器
            _framePlayIndexAnimator.start();
        }
        // 设置根视图的透明度为1，不透明
        _PAT.setAlpha(_rootLayout, 1);
        // 动画改变到内容面板的背景颜色到预设值
        _PAT.setBackgroundColor(_contentPanel, info.getCornerRadius(), info.getBackgroundColor());
        // 设置内容面板的透明度为1，不透明
        _PAT.setAlpha(_contentPanel, 1);
        _titleView.setTextColor(_currentBubbleInfo.getTitleColor());
        // 设置蒙版色
        _PAT.setBackgroundColor(_backMaskView, 0, info.getMaskColor());
        // 调用泡泡控件信息对象中的方法来计算面板和图标标题等控件的位置和大小，并动画移动
        info.calBubbleViewContentPanelFrame(_contentPanel);
        info.calPaintViewAndTitleViewFrame(_paintView, _titleView);
    }

    /**
     * 判断制定的fragment当前是否被显示中
     *
     * @param fragment 要判断是否被显示的fragment
     * @return 是否显示的布尔值
     */
    private boolean isFragmentShowing(android.support.v4.app.Fragment fragment) {
        if (!LemonBubbleGlobal.useFragmentDisplayCheck)// 没有开启Fragment显示检测
            return true;
        if (!fragment.getUserVisibleHint())// ViewPager嵌套时还没有触发显示
            return false;
        if (fragment.isHidden())// 当前fragment被隐藏了
            return false;
        if (fragment.getActivity() == null)
            return false;
        return true;
    }

    /**
     * 判断制定的fragment当前是否被显示中
     *
     * @param fragment 要判断是否被显示的fragment
     * @return 是否显示的布尔值
     */
    private boolean isFragmentShowing(Fragment fragment) {
        if (!LemonBubbleGlobal.useFragmentDisplayCheck)// 没有开启Fragment显示检测
            return true;
        if (!fragment.getUserVisibleHint())// ViewPager嵌套时还没有触发显示
            return false;
        if (fragment.isHidden())// 当前fragment被隐藏了
            return false;
        if (fragment.getActivity() == null)
            return false;
        return true;
    }

    /**
     * 检测指定的fragment是否处于显示状态，如果是的话那么展示泡泡控件
     *
     * @param fragment   要判断是否显示的fragment
     * @param bubbleInfo 泡泡信息对象
     */
    public void showBubbleInfo(Fragment fragment, LemonBubbleInfo bubbleInfo) {
        if (isFragmentShowing(fragment))
            showBubbleInfo(fragment.getActivity(), bubbleInfo);
    }

    /**
     * 检测指定的fragment是否处于显示状态，如果是的话那么展示泡泡控件
     *
     * @param fragment   要判断是否显示的fragment
     * @param bubbleInfo 泡泡信息对象
     */
    public void showBubbleInfo(android.support.v4.app.Fragment fragment, LemonBubbleInfo bubbleInfo) {
        if (isFragmentShowing(fragment))
            showBubbleInfo(fragment.getActivity(), bubbleInfo);
    }

    /**
     * 检测指定的fragment是否处于显示状态，如果是的话那么展示泡泡控件，并在指定的时间后关闭
     *
     * @param fragment      要判断是否显示的fragment
     * @param bubbleInfo    泡泡信息对象
     * @param autoCloseTime 自动关闭的时间
     */
    public void showBubbleInfo(Fragment fragment, LemonBubbleInfo bubbleInfo, int autoCloseTime) {
        if (isFragmentShowing(fragment))
            showBubbleInfo(fragment.getActivity(), bubbleInfo, autoCloseTime);
    }

    /**
     * 检测指定的fragment是否处于显示状态，如果是的话那么展示泡泡控件，并在指定的时间后关闭
     *
     * @param fragment      要判断是否显示的fragment
     * @param bubbleInfo    泡泡信息对象
     * @param autoCloseTime 自动关闭的时间
     */
    public void showBubbleInfo(android.support.v4.app.Fragment fragment, LemonBubbleInfo bubbleInfo, int autoCloseTime) {
        if (isFragmentShowing(fragment))
            showBubbleInfo(fragment.getActivity(), bubbleInfo, autoCloseTime);
    }

    /**
     * 展示泡泡控件
     *
     * @param context    上下文对象
     * @param bubbleInfo 泡泡信息描述对象
     */
    public void showBubbleInfo(Context context, LemonBubbleInfo bubbleInfo) {
        if (lifeCycleDelegate != null)
            lifeCycleDelegate.willShow(this, bubbleInfo);
        if (_context != null && !_context.equals(context))
            haveInit = false;
        _currentBubbleInfo = bubbleInfo;// 现将泡泡信息对象保存起来
        autoInit(context);
        if (!isShow()) {// 如果已经显示，就不进行再弹出新的层
            _container.show();
        }
        initContentPanel(bubbleInfo);// 根据泡泡信息对象对正文内容面板进行初始化
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if (lifeCycleDelegate != null)
                    lifeCycleDelegate.alreadyShow(LemonBubbleView.this, _currentBubbleInfo);
            }
        }, 300);// 300是动画播放duration的默认时间
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
        }, autoCloseTime);// 延迟关闭
    }

    /**
     * 隐藏当前正在显示的泡泡控件
     */
    public void hide() {
        if (lifeCycleDelegate != null)
            lifeCycleDelegate.willHide(this, _currentBubbleInfo);
        _PAT.setAlpha(_rootLayout, 0);// 动画设置根视图不透明
        _PAT.setAlpha(_contentPanel, 0);// 动画设置内容面板不透明
        _PAT.setSize(_contentPanel, 0, 0);// 动画设置面板的大小为0，0
        _PAT.setSize(_paintView, 0, 0);// 动画设置图标动画控件的大小为0，0
        _PAT.setSize(_titleView, 0, 0);// 动画设置标题控件的大小为0，0
        _PAT.setLocation(_paintView, 0, 0);// 动画设置图标动画控件的坐标为0，0，可以让动画看起来更像是整体缩小
        _PAT.setLocation(_titleView, 0, 0);// 动画设置标题控件的坐标为0，0，可以让动画看起来更像是整体缩小
        // 把内容面板缩小至屏幕中间
        _PAT.setLocation(_contentPanel, _PST.screenWidthDp() / 2, _PST.screenHeightDp() / 2);
        setIsShow(false);// 设置当前的状态为不显示状态
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                _container.dismiss();
                haveInit = false;// 让其每次彻底关闭后在开启都重新创建对象，防止部分手机按返回键后再次弹出时候闪退
                // 如果哪位大神有更好的办法请联系我  liuri@lemonsoft.net
            }
        }, 300);// 待所有动画处理完毕后关闭根Dialog
    }

    /**
     * 强制关闭当前正在显示的泡泡控件
     */
    public void forceHide() {
        try {
            _container.dismiss();
        } catch (NullPointerException e) {
            System.err.println("未创建LemonBubble时调用了forceHide()，异常已经捕捉。");
        }
        this.haveInit = false;
    }

    public LemonBubbleLifeCycleDelegate getLifeCycleDelegate() {
        return lifeCycleDelegate;
    }

    public void setLifeCycleDelegate(LemonBubbleLifeCycleDelegate lifeCycleDelegate) {
        this.lifeCycleDelegate = lifeCycleDelegate;
    }
}
