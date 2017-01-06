# LemonBubble4Android
> 作者：1em0nsOft - LiuRi
>
> 版本号：1.0.4
>
> 简介：这是一个完全Made in China的炫酷弹出指示层Android版本（-_-#意思就是还有iOS的），他能让你快速的自定义任何样式的弹出框。

> 最新更新记录：
>
> 支持.点语法无限修改BubbleInfo~
>
> ```java
> LemonBubble.getRightBubbleInfo()// 增加无限点语法修改bubbleInfo的特性
>                         .setTitle("这是一个成功的提示")
>                         .setTitleFontSize(12)
>                         .show(MainActivity.this, 2000);
> ```

- 废话不多说，先看看图，来~

![效果图](https://raw.githubusercontent.com/1em0nsOft/LemonBubble4Android/master/Resource/LemonBubble.gif)

- 感觉怎么样呢？^_^ 光看图感觉到时还挺不错的，那怎么集成到项目中呢？来来，使用Gradle，首先在你的Project build.gradle文件中（allprojects ->repositories节点）加入如下代码：

```
allprojects {
    repositories {
        jcenter()
        // 加入下面这行
        maven { url 'https://jitpack.io' }
    }
}
```

然后在你的Module（xxx e.g:app） build.gradle中（dependencies节点）加入如下代码：

```
dependencies {
    // ...  你的其他依赖
    // 然后加入下面这行
    compile 'com.github.1em0nsOft:LemonBubble4Android:1.0.4'
}
```

最后重新build一下就可以啦。

接下来，我们验证一下我们是否集成成功，随便找一个Activity，在onCreate方法里面我们加上如下一行代码：

```
LemonBubble.showRight(this, "集成成功！", 2000);
```

运行一下，可以看到如下界面，说明我们集成成功咯！

![集成成功](https://raw.githubusercontent.com/1em0nsOft/LemonBubble4Android/master/Resource/example-run01.jpg)

LemonBubble默认自带了三种泡泡样式，带有一个绿色的对号的成功泡泡，带有一个红色X错号的错误泡泡，带有蓝色无限旋转的等待控件，你可以使用如下三种方式调用他们：

```
LemonBubble.showRight(this, "成功啦！", 2000);
LemonBubble.showError(this, "出错啦", 2000);
LemonBubble.showRoundProgress(this, "等待中...");
```

上面三个方法中，showRight和showError可以通过传入的第三个参数来控制泡泡显示的时间，单位ms。当你弹出了一个泡泡控件之后你也可以随时使用`LemonBubble.hide()`进行关闭当前正在显示的泡泡控件。

如果你想自定义样式的话，你只需要新建一个LemonBubbleInfo对象，然后对其进行修改属性即可，你也可以分别通过

```
LemonBubble.getRightBubbleInfo()
LemonBubble.getErrorBubbleInfo()
LemonBubble.getRoundProgressBubbleInfo()
```

三个方法来获取我们预先为您写好的包含正确、错误、等待信息的LemonBubbleInfo对象，然后通过修改其属性的方式来快速自定义自己的泡泡控件，比如，我们现在通过如下代码自定义泡泡信息对象：

```
// 获取默认的正确信息的泡泡信息对象
LemonBubbleInfo myInfo = LemonBubble.getRightBubbleInfo();
// 设置图标在左侧，标题在右侧
myInfo.setLayoutStyle(LemonBubbleLayoutStyle.ICON_LEFT_TITLE_RIGHT);
// 设置泡泡控件在底部
myInfo.setLocationStyle(LemonBubbleLocationStyle.BOTTOM);
// 设置泡泡控件的动画图标颜色为蓝色
myInfo.setIconColor(Color.BLUE);
// 设置泡泡控件的尺寸，单位dp
myInfo.setBubbleSize(200, 80);
// 设置泡泡控件的偏移比例为整个屏幕的0.01,
myInfo.setProportionOfDeviation(0.01f);
// 设置泡泡控件的标题
myInfo.setTitle("自定义泡泡控件");
// 展示自定义的泡泡控件，并显示2s后关闭
LemonBubble.showBubbleInfo(this, myInfo, 2000);
```

一顿乱改，我们运行一下程序，发现泡泡控件已经按我们修改的样式显示出来啦：

![图片描述](https://raw.githubusercontent.com/1em0nsOft/LemonBubble4Android/master/Resource/example-run02.jpg)

怎么样，是不是很简单？快来体验一下吧~