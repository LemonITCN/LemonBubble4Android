package net.lemonsoft.lemonbubble.interfaces;

import net.lemonsoft.lemonbubble.LemonBubbleInfo;
import net.lemonsoft.lemonbubble.LemonBubbleView;

/**
 * LemonBubble的生命周期支持
 * Created by LiuRi on 2017/2/21.
 */

public interface LemonBubbleLifeCycleDelegate {

    /**
     * LemonBubble将要被显示
     */
    void willShow(LemonBubbleView bubbleView, LemonBubbleInfo bubbleInfo);

    /**
     * LemonBubble已经被显示完毕
     */
    void alreadyShow(LemonBubbleView bubbleView, LemonBubbleInfo bubbleInfo);

    /**
     * LemonBubble即将被关闭
     */
    void willHide(LemonBubbleView bubbleView, LemonBubbleInfo bubbleInfo);

    /**
     * LemonBubble已经被关闭
     */
    void alreadyHide(LemonBubbleView bubbleView, LemonBubbleInfo bubbleInfo);

    abstract class Adapter implements LemonBubbleLifeCycleDelegate {
        @Override
        public void willShow(LemonBubbleView bubbleView, LemonBubbleInfo bubbleInfo) {

        }

        @Override
        public void alreadyShow(LemonBubbleView bubbleView, LemonBubbleInfo bubbleInfo) {

        }

        @Override
        public void willHide(LemonBubbleView bubbleView, LemonBubbleInfo bubbleInfo) {

        }

        @Override
        public void alreadyHide(LemonBubbleView bubbleView, LemonBubbleInfo bubbleInfo) {

        }

    }

}
