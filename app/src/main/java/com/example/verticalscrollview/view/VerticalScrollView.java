package com.example.verticalscrollview.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ScrollView;

/**
 * Created by 张野 on 2020/3/23.
 * 用于垂直滑动的ScrollView
 * 包含了滑动方向判断，是否滑动到底部判断，是否处于滑动判断
 * 是否滑动到顶部可以用getScrollY()==0作为判断依据，所以不单独提供判断
 * 可以用于解决滑动冲突
 */

public class VerticalScrollView extends ScrollView {

    public boolean isScrollBottom = false;//是否滑动到底部
    public int scrollOrientation = 0;//滑动方向
    public static final int ORIENTATION_PULL_UP = -1;//上拉
    public static final int ORIENTATION_PULL_DOWN = 1;//下拉
    private float startY = 0;
    public boolean isScrolling = false;//是否处于滑动状态


    public VerticalScrollView(Context context) {
        super(context);
    }

    public VerticalScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public VerticalScrollView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public VerticalScrollView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    @Override
    protected void onScrollChanged(int l, int t, int oldl, int oldt) {
        View view = (View)getChildAt(getChildCount()-1);
        int d = view.getBottom();
        d -= (getHeight()+getScrollY());
        if(d==0)
        {
            isScrollBottom = true;
        }else{
            isScrollBottom = false;
        }
        super.onScrollChanged(l,t,oldl,oldt);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {

        switch (ev.getAction()){
            case  MotionEvent.ACTION_DOWN:
                startY = ev.getY();
                break;
            case  MotionEvent.ACTION_MOVE:
                if(Math.abs(ev.getY()-startY)>40&&ev.getY()>startY){
                    scrollOrientation = ORIENTATION_PULL_DOWN;
                }else if(Math.abs(ev.getY()-startY)>40&&ev.getY()<startY){
                    scrollOrientation = ORIENTATION_PULL_UP;
                }
                isScrolling = true;
                break;
            case MotionEvent.ACTION_UP:
                scrollOrientation = 0;
                isScrolling = false;
                break;
        }
        return super.dispatchTouchEvent(ev);
    }
}
