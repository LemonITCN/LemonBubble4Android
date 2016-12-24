package net.lemonsoft.lemonbubble;

import android.content.Context;
import android.graphics.Canvas;
import android.widget.ImageView;

/**
 * 泡泡中的动画与帧图片展示控件
 * Created by LiuRi on 2016/12/24.
 */

public class LemonBubblePaintView extends ImageView {

    // 保存泡泡信息对象
    private LemonBubbleInfo _bubbleInfo;

    public LemonBubblePaintView(Context context) {
        super(context);
    }

    public void setBubbleInfo(LemonBubbleInfo bubbleInfo) {
        _bubbleInfo = bubbleInfo;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (_bubbleInfo != null)// 判断非空指针才进行操作
            _bubbleInfo.getIconAnimation().paint(canvas);// 调用绘制函数开始绘制
    }

}
