###  一、概述
我们都知道大部分后端返回给移动端的数据都是以Json数据返回的。有些时候如果我想直接显示网页上数据怎么办呢。可能我们都知道直接写`WebView`控件就可以了，但是webview显示网页的效果目前看来并不是很好，因为它要渲染，所有用户体验不好。那最好的办法就是我们把网页上的数据转化成自己的数据。下面就介绍一个神器。

### 二、jsoup
`jsoup`是解析html的神器，因为网页的上数据也是规律的，所以`jsoup`通过的它的可以解析成`Document`对象，通过`Document`对象可以取出我们想要的数据。下面列出jsoup一些相关的地址：

[jsoup官方文档](https://jsoup.org/cookbook/)
[jsoup中文文档](http://www.open-open.com/jsoup)
[Github地址](https://github.com/jhy/jsoup)

### 三、使用
首页先在AndroidStudio引用，如图：
![这里写图片描述](http://img.blog.csdn.net/20171111224516277?watermark/2/text/aHR0cDovL2Jsb2cuY3Nkbi5uZXQveGlhb3l1YW41MTE=/font/5a6L5L2T/fontsize/400/fill/I0JBQkFCMA==/dissolve/70/gravity/SouthEast)
注意：使用请放在子线程上，否则会报错，对了，别忘记加上网络权限。
前期工作准备完毕，接下来我们得找一个网页，当我们的数据源了。作为一个资深段友，那么我们就那内涵段子做我们的数据源吧。[内涵段子](http://neihanshequ.com/)。我们来分析它的html是怎么样的。如图：
![这里写图片描述](http://img.blog.csdn.net/20171111225050235?watermark/2/text/aHR0cDovL2Jsb2cuY3Nkbi5uZXQveGlhb3l1YW41MTE=/font/5a6L5L2T/fontsize/400/fill/I0JBQkFCMA==/dissolve/70/gravity/SouthEast)
可以看出它的内容是一个div下面的元素p里面的文本
那么我把div解析出来，在解析出div里面的p，在拿到p里面的内容，这样就可以解析出来。上代码：
![](http://img.blog.csdn.net/20171111225745488?watermark/2/text/aHR0cDovL2Jsb2cuY3Nkbi5uZXQveGlhb3l1YW41MTE=/font/5a6L5L2T/fontsize/400/fill/I0JBQkFCMA==/dissolve/70/gravity/SouthEast)
效果如下：
![](http://img.blog.csdn.net/20171111225825576?watermark/2/text/aHR0cDovL2Jsb2cuY3Nkbi5uZXQveGlhb3l1YW41MTE=/font/5a6L5L2T/fontsize/400/fill/I0JBQkFCMA==/dissolve/70/gravity/SouthEast)
我们可以出数据已经解析出来，在转化成我们自己的Bean类，就可以展示了。我们发现他这个内容其实是两个div嵌套，我们解析的是第一个div。那么我们直接解析第二个div是否也可以解析出来内容呢，我们来试试：
![](http://img.blog.csdn.net/20171111230413425?watermark/2/text/aHR0cDovL2Jsb2cuY3Nkbi5uZXQveGlhb3l1YW41MTE=/font/5a6L5L2T/fontsize/400/fill/I0JBQkFCMA==/dissolve/70/gravity/SouthEast)
你会发现得到同样的效果，可以肯定只要我们找到一个div标签就可以解析出来里面的内容。

有一种需求就是我们想解析例如`a`标签里面的`href`内容，这个时候我们就不能用.text方法了，得用.attr方法。具体代码如下：
![](http://img.blog.csdn.net/20171111230943544?watermark/2/text/aHR0cDovL2Jsb2cuY3Nkbi5uZXQveGlhb3l1YW41MTE=/font/5a6L5L2T/fontsize/400/fill/I0JBQkFCMA==/dissolve/70/gravity/SouthEast)
效果如下：
![](http://img.blog.csdn.net/20171111231107685?watermark/2/text/aHR0cDovL2Jsb2cuY3Nkbi5uZXQveGlhb3l1YW41MTE=/font/5a6L5L2T/fontsize/400/fill/I0JBQkFCMA==/dissolve/70/gravity/SouthEast)
解析到这，我相信所有都知道怎么取出该条内容点赞数、踩数、收藏数的了。代码如下：
![这里写图片描述](http://img.blog.csdn.net/20171111231935039?watermark/2/text/aHR0cDovL2Jsb2cuY3Nkbi5uZXQveGlhb3l1YW41MTE=/font/5a6L5L2T/fontsize/400/fill/I0JBQkFCMA==/dissolve/70/gravity/SouthEast)
还有一些评论数，大家按照这样方式解析一下。取到这些数据，其实我们就可以做成一个应用首页了。因为我们想要的数据都有了。

当然我们还有一些需求，就是我们我想要一些数据是经过我们修改的，就是我们要修改html里面的值。如下：
![](http://img.blog.csdn.net/20171111234939443?watermark/2/text/aHR0cDovL2Jsb2cuY3Nkbi5uZXQveGlhb3l1YW41MTE=/font/5a6L5L2T/fontsize/400/fill/I0JBQkFCMA==/dissolve/70/gravity/SouthEast)
效果如下：
![](http://img.blog.csdn.net/20171111235022860?watermark/2/text/aHR0cDovL2Jsb2cuY3Nkbi5uZXQveGlhb3l1YW41MTE=/font/5a6L5L2T/fontsize/400/fill/I0JBQkFCMA==/dissolve/70/gravity/SouthEast)
其余大家可以参考一下中文文档。恩，就先这样。
