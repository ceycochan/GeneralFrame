package com.nshane.generalframe.adapters;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;

/**
 * Created by bryan on 2017-12-26.
 */

public class NoScrollViewPager extends ViewPager {

    /**
     * 主页下不可滑动的ViewPager
     */

    private boolean isScroll = false;

    public NoScrollViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
        // TODO Auto-generated constructor stub
    }

    public NoScrollViewPager(Context context) {
        super(context);
    }

    public void setNoScroll(boolean noScroll) {
        this.isScroll = noScroll;
    }

    @Override
    public void scrollTo(int x, int y) {
        super.scrollTo(x, y);
    }


    /**
     * 是否消费事件
     * 消费: 事件结束 or 不消费: 往父类空间传
     *
     */

    @Override
    public boolean onTouchEvent(MotionEvent arg0) {
        /* return false;//super.onTouchEvent(arg0); */


        //return false;// 可行,不消费,传给父控件
        //return true;// 可行,消费,拦截事件
        //super.onTouchEvent(ev); //不行,
        //虽然onInterceptTouchEvent中拦截了,
        //但是如果viewpage里面子控件不是viewgroup,还是会调用这个方法.


        if (isScroll)
            return super.onTouchEvent(arg0);
        else
            return true;
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent arg0) {

        // return false;//可行,不拦截事件,
        // return true;//不行,孩子无法处理事件
        //return super.onInterceptTouchEvent(ev);//不行,会有细微移动

        if (isScroll)
            return super.onInterceptTouchEvent(arg0);
        else
            return false;
    }

    @Override
    public void setCurrentItem(int item, boolean smoothScroll) {
        super.setCurrentItem(item, smoothScroll);
    }

    @Override
    public void setCurrentItem(int item) {
        super.setCurrentItem(item);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        //不拦截,否则子孩子都无法收到事件,一般这个自定义的时候都不作处理
        return super.dispatchTouchEvent(ev);
    }
}
