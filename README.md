# DialogDemo
这是一个自定义Dialog弹窗提示

 在App的开发中，提示框是经常用到的一个技术点。因为样式与之前不一样，所以就自定义写一个，今天自定义的提示框，跟之前差不多都是直接继承至Dialog，
  但是没有使用XML布局，都是动态创建View和画的背景，另外还支持添加动画。
  
  下面来看看效果图：
  
   &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
   ![image](https://github.com/weioule/DialogDemo/blob/master/app/img/001.png) 　
   &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
   ![image](https://github.com/weioule/DialogDemo/blob/master/app/img/002.png) 　

  matedialog包下的是一些抽取的Base类和工具类，MaterialDialog是比较灵活的可以定制其布局样式和动画支持的dialog，在onCreateView函数中创建你所需要定制的布局即可。不需要动画就直接忽略不设置。
  
  而AbsAlertDialog与ComAlertDialog就一个单独的类，直接copy即可不需要带那么多附加的东西。方便但不能定制，比较合布局统一的项目。
  二者的区别在于AbsAlertDialog支持设置显示隐藏动画，ComAlertDialog不支持，不过大部分的提示框基本都不存在动画效果。
  
  使用时方法在项目里Activity中的showDialogh函数里，文案大小、颜色或背景等都可以在外部传入。设置按钮点击回调后可在其回调方法里进行你的流程操作。

好了，就是这样，希望能帮助有需要的各位！
