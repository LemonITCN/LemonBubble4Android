package net.lemonsoft.lemonbubble.interfaces;

import net.lemonsoft.lemonbubble.LemonBubbleInfo;
import net.lemonsoft.lemonbubble.LemonBubbleView;

/**
 * 柠檬泡泡控件的蒙版被触摸的回调上下文
 * Created by LiuRi on 2017/1/9.
 */

public interface LemonBubbleMaskOnTouchContext {

    void onTouch(LemonBubbleInfo bubbleInfo, LemonBubbleView bubbleView);

}
