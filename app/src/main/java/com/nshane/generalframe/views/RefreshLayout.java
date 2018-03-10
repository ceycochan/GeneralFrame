package com.nshane.generalframe.views;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.os.Handler;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.nshane.generalframe.R;

/**
 * Created by bryan on 2018-3-10.
 */

@SuppressLint({"InflateParams", "HandlerLeak"})
public class RefreshLayout extends LinearLayout {
    @SuppressLint("NewApi")
    public RefreshLayout(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(context);
    }

    public RefreshLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public RefreshLayout(Context context) {
        super(context);
        init(context);
    }

    /**
     * 方法名: init
     * <p>
     * 功能描述:初始化
     *
     * @param context 上下文对象
     * @return void
     * <p>
     * </br>throws
     */
    private void init(Context context) {
        this.context = context;
        refreshView = LayoutInflater.from(context).inflate(
                R.layout.view_refreshlayout, null);
        addView(refreshView);
        refreshImageView = (ImageView) refreshView
                .findViewById(R.id.iv_refresh);
        refreshTextView = (TextView) refreshView
                .findViewById(R.id.tv_refresh);
        /**
         * 设置布局监听,当布局完完成了以后,首先要隐藏头部
         */
        ViewTreeObserver observer = getViewTreeObserver();
        observer.addOnPreDrawListener(new ViewTreeObserver.OnPreDrawListener() {
            @Override
            public boolean onPreDraw() {
                if (refreshView.getHeight() > tmpHeight) {
                    tmpHeight = refreshView.getHeight();
                    setPadding(0, -tmpHeight, 0, 0);
                }
                return true;
            }
        });
        setBackgroundColor(Color.BLACK);
    }

    private Context context;// 上下文对象
    private View refreshView;// 刷新的Layout
    private ScrollView scrollView;// 滚动视图
    private ImageView refreshImageView;// 刷新图片
    private TextView refreshTextView;// 刷新提示
    private int startY = 0;// 开始点
    private int moveDistance = 0;// 移动的距离
    private int tmpHeight = 0;// 刷新的View的等矿山
    private boolean flag = false;// 是否可以下拉刷新.默认无法下拉刷新,需要设置
    private boolean ifMove = false;// 是否有移动
    private RefreshDataListener listener;

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:// 手指按下
                startY = (int) ev.getRawY();
                break;
            case MotionEvent.ACTION_MOVE:// 手指移动
                if (flag) {// 只有设置为true的时候才可以下拉刷新
                    ifMove = true;// 手指有移动
                    moveDistance = (int) (ev.getRawY() - startY);
                    if (scrollView.getScrollY() == 0 && moveDistance > 50) {// ScrollView滚动到了顶部
                        if (moveDistance > refreshView.getHeight()) {// 下拉的高度超过的了下拉刷新Layout的高度,就提示可以放手了
                            refreshImageView.setImageResource(R.mipmap.ic_launcher);
                            refreshTextView.setText("松手刷新");
                        }
                        setPadding(0, -tmpHeight + (int) ((moveDistance - 50) / 4),
                                0, 0);
                        return true;// 吸收掉手势
                    }
                } else {// 如果不为true,需要得到当前移动点作为初始点(这个主要为了当View一开始没有滚动到顶部,但是在下拉的过程中滚动了顶部所做的处理)
                    startY = (int) ev.getRawY();
                }
                break;
            case MotionEvent.ACTION_UP:// 手指离开
                if (ifMove) {// 必须移动(判断这个已经默认可以下拉刷新)
                    ifMove = false;// 重置为没有移动
                    if (moveDistance > tmpHeight) {// 如果下拉的高度超过了Layout的高度,就显示正在刷新,并开始执行刷新
                        refreshImageView.setImageResource(R.mipmap.ic_launcher);
                        refreshTextView.setText("正在刷新...");
                        setPadding(0, 0, 0, 0);
                        listener.startReLoadData();
                        stop = false;// 开启刷新动画
                        handler.sendEmptyMessage(10087);// 开启刷新动画
                    } else {// 下拉高度不足,重新隐藏界面
                        setPadding(0, -refreshView.getHeight(), 0, 0);
                    }
                    startY = 0;// 把接触点重置为0
                    moveDistance = 0;// 把距离重新设置为0
                    return true;// 吸收掉手势
                }
                moveDistance = 0;// 把距离重新设置为0
                startY = 0;// 把接触点重置为0
                break;
        }
        return super.dispatchTouchEvent(ev);
    }

    /**
     * 方法名: setOnRefreshDataListener
     * <p>
     * 功能描述:设置监听
     *
     * @param listener   监听回调
     * @param scrollView 要刷新的ScrollView
     * @return void
     * <p>
     * </br>throws
     */
    public void setOnRefreshDataListener(RefreshDataListener listener,
                                         ScrollView scrollView) {
        this.listener = listener;
        this.scrollView = scrollView;
    }

    /**
     * 方法名: refreshDataFinish
     * <p>
     * 功能描述:数据完成,可调用次方法,隐藏刷新Layout
     *
     * @return void
     * <p>
     * </br>throws
     */
    public void refreshDataFinish() {
        handler.sendEmptyMessage(10088);// 停止动画
        refreshImageView.setImageResource(R.mipmap.ic_launcher);
        refreshTextView.setText("刷新完成");
        handler.sendEmptyMessageDelayed(10086, 2000);
    }

    /**
     * 方法名: refreshDataError
     * <p>
     * 功能描述:刷新失败
     *
     * @return void
     * <p>
     * </br>throws
     */
    public void refreshDataError() {
        handler.sendEmptyMessage(10088);// 停止动画
        refreshImageView.setImageResource(R.mipmap.ic_launcher);
        refreshTextView.setText("刷新失败");
        handler.sendEmptyMessageDelayed(10086, 2000);
    }

    /**
     * 方法名: refreshDataError
     * <p>
     * 功能描述:刷新失败
     *
     * @param content 刷新失败的提示内容
     * @return void
     * <p>
     * </br>throws
     */
    public void refreshDataError(String content) {
        handler.sendEmptyMessage(10088);// 停止动画
        refreshImageView.setImageResource(R.mipmap.ic_launcher);
        refreshTextView.setText(content);
        handler.sendEmptyMessageDelayed(10086, 2500);
    }

    /**
     * 方法名: setFlag
     * <p>
     * 功能描述:设置是否禁止下拉刷新
     *
     * @param flag
     * @return void
     * <p>
     * </br>throws
     */
    public void setFlag(boolean flag) {
        this.flag = flag;
    }

    /**
     * @类名:RefreshDataListener
     * @功能描述:刷新数据监听接口
     * @作者:XuanKe'Huang
     * @时间:2014-10-15 下午2:42:20
     * @Copyright 2014
     */
    public interface RefreshDataListener {
        /**
         * 方法名: startReLoadData
         * <p>
         * 功能描述:开始刷新数据
         *
         * @return void
         * <p>
         * </br>throws
         */
        public void startReLoadData();
    }

    private boolean stop = false;// 停止动画
    private Handler handler = new Handler() {
        public void handleMessage(android.os.Message msg) {
            switch (msg.what) {
                case 10088:// 刷新完成,停止动画
                    stop = true;
                    refreshImageView.clearAnimation();
                case 10087:// 正在刷新,启动动画
                    if (!stop) {
                        Animation animation = new RotateAnimation(0, 360,
                                Animation.RELATIVE_TO_SELF, 0.5f,
                                Animation.RELATIVE_TO_SELF, 0.5f);
                        animation.setDuration(2000);
                        refreshImageView.startAnimation(animation);
                        handler.sendEmptyMessageDelayed(10087, 2000);
                    }
                    break;
                case 10086:// 隐藏界面
                    stop = false;
                    refreshImageView.setImageResource(R.mipmap.ic_launcher);
                    refreshTextView.setText("下拉刷新");
                    setPadding(0, -refreshView.getHeight(), 0, 0);// 重新隐藏下拉刷新界面
                    scrollView.scrollTo(0, 0);
                    break;
            }
        }

        ;
    };
}
