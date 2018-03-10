# General Frame

通用开发框架

1. fixed 65k(65535) method limitation

2. RxJava & RxAndroid

3. Retrofit (Upstream & Downstream)

4.


//TBC......................

RecyclerView左滑：http://www.jianshu.com/p/f2a9b860858e

ExpandableListView、ListView左滑：http://www.jianshu.com/p/a00073b55f77

RecyclerView左滑，不需要继承特地的adapter，不需要使用重写过的RecyclerView，只需要一个类即可。

以QQ的左滑为参考设计的，与SwipeRefreshLayout无冲突。

使用方法如下：
  
  1、recyclerView.addOnItemTouchListener(new SwipeItemLayout.OnSwipeItemTouchListener(this));
  
  2、item layout文件
    以SwipeItemLayout为item的root view，添加两个ViewGroup，第一个为main部分，即显示在中心，第二个为menu部分，即显示在右侧
  
  3、click、long click点击效果以及listener
    将item里的child当作普通的view来对待即可。使用setOnClickListener以及setOnLongClickListener。至于显示效果，通常怎么设置，这里也怎么设置。
  
RecyclerView与ListView左滑效果图：
