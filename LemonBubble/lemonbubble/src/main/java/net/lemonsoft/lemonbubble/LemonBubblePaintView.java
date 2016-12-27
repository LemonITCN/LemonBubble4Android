package net.lemonsoft.lemonbubble;

import android.animation.Animator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.view.View;
import android.widget.ImageView;

/**
 * 泡泡中的动画与帧图片展示控件
 * Created by LiuRi on 2016/12/24.
 */

public class LemonBubblePaintView extends ImageView {

    // 保存泡泡信息对象
    private LemonBubbleInfo _bubbleInfo;
    // 控制动画播放进度的数值动画器
    private ValueAnimator _playProgressValueAnimator;
    // 动画播放进度存储变量，0-1之间的浮点数
    private float _playProgressValue;

    public LemonBubblePaintView(final Context context) {
        super(context);
        if (_playProgressValueAnimator != null)
            _playProgressValueAnimator.end();
        _playProgressValueAnimator = ValueAnimator.ofFloat(0, 1);
        _playProgressValueAnimator.setDuration(600);
        _playProgressValueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                _playProgressValue = (float) valueAnimator.getAnimatedValue();
                postInvalidate();// 刷新界面
            }
        });
        this.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                LemonBubble.showError(context, "支付完了", 3000);
            }
        });
    }

    public void setBubbleInfo(LemonBubbleInfo bubbleInfo) {
        _playProgressValueAnimator.end();
        _bubbleInfo = bubbleInfo;
        _playProgressValueAnimator.setRepeatCount(_bubbleInfo.isIconAnimationRepeat() ? Integer.MAX_VALUE : 0);
        _playProgressValueAnimator.start();
        _playProgressValueAnimator.setDuration(bubbleInfo.getFrameAnimationTime());
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (_bubbleInfo != null &&
                _bubbleInfo.getIconAnimation() != null &&
                (_bubbleInfo.getIconArray() == null || _bubbleInfo.getIconArray().size() == 0))// 判断非空指针才进行操作
            _bubbleInfo.getIconAnimation().paint(canvas, _playProgressValue);// 调用绘制函数开始绘制
    }

}
