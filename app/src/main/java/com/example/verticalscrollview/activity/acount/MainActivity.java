package com.example.verticalscrollview.activity.acount;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.DecelerateInterpolator;

import com.example.verticalscrollview.R;
import com.example.verticalscrollview.activity.BaseActivity;
import com.example.verticalscrollview.adapter.MainAdapter;
import com.example.verticalscrollview.databinding.ActivityMainBinding;
import com.example.verticalscrollview.fragment.BaseFragment;
import com.example.verticalscrollview.fragment.acount.EmptyFragment;
import com.example.verticalscrollview.fragment.acount.IndexFragment;
import com.example.verticalscrollview.fragment.acount.SlidingTwoFragment;

import net.lucode.hackware.magicindicator.MagicIndicator;
import net.lucode.hackware.magicindicator.ViewPagerHelper;
import net.lucode.hackware.magicindicator.buildins.UIUtil;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.CommonNavigator;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.CommonNavigatorAdapter;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.IPagerIndicator;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.IPagerTitleView;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.indicators.LinePagerIndicator;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.titles.ColorTransitionPagerTitleView;

import java.util.ArrayList;

public class MainActivity extends BaseActivity {

    private ViewPager mViewPager;
    ArrayList<String> mTitleDataList;
    ActivityMainBinding binding;
    MainAdapter mainAdapter;
    ArrayList<BaseFragment> fragments;
    ArrayList<ColorTransitionPagerTitleView> transitionPagerTitleViews = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        initData();
        setContentVp();
    }
    private void initData(){
        mViewPager = binding.vpContent;
        fragments = new ArrayList<>();
        mTitleDataList = new ArrayList<>();
        mTitleDataList.add("空白页面1");
        mTitleDataList.add("滑动冲突测试1");
        mTitleDataList.add("滑动冲突测试2");
        fragments.add(new EmptyFragment());
        fragments.add(IndexFragment.newInstance(1));
        fragments.add(SlidingTwoFragment.newInstance());
        mainAdapter = new MainAdapter(getSupportFragmentManager(),fragments);
        mViewPager.setAdapter(mainAdapter);
    }
    public void setContentVp(){
        MagicIndicator magicIndicator = binding.magicIndicator;
        CommonNavigator commonNavigator = new CommonNavigator(this);
        commonNavigator.setScrollPivotX(0.65f);
        commonNavigator.setAdjustMode(true);
        commonNavigator.setAdapter(new CommonNavigatorAdapter() {

            @Override
            public int getCount() {
                return mTitleDataList == null ? 0 : mTitleDataList.size();
            }

            @Override
            public IPagerTitleView getTitleView(Context context, final int index) {

                ColorTransitionPagerTitleView colorTransitionPagerTitleView = new ColorTransitionPagerTitleView(context);
                colorTransitionPagerTitleView.setNormalColor(Color.GRAY);
                colorTransitionPagerTitleView.setText(mTitleDataList.get(index));
                if(index == 0){
                    colorTransitionPagerTitleView.setSelectedColor(Color.GREEN);
                    colorTransitionPagerTitleView.setTextSize(20);
                }else{
                    colorTransitionPagerTitleView.setSelectedColor(Color.BLACK);
                    colorTransitionPagerTitleView.setTextSize(15);
                }
                colorTransitionPagerTitleView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        mViewPager.setCurrentItem(index);
                    }
                });
                transitionPagerTitleViews.add(colorTransitionPagerTitleView);
                return colorTransitionPagerTitleView;
            }

            @Override
            public IPagerIndicator getIndicator(Context context) {
                LinePagerIndicator indicator = new LinePagerIndicator(context);
                indicator.setMode(LinePagerIndicator.MODE_EXACTLY);
                indicator.setForegroundGravity(Gravity.CENTER);
                indicator.setLineHeight(UIUtil.dip2px(context, 6));
                indicator.setLineWidth(UIUtil.dip2px(context, 10));
                indicator.setRoundRadius(UIUtil.dip2px(context, 3));
                indicator.setStartInterpolator(new AccelerateInterpolator());
                indicator.setEndInterpolator(new DecelerateInterpolator(2.0f));
                indicator.setColors(Color.parseColor("#00c853"));

//                WrapPagerIndicator indicator = new WrapPagerIndicator(context);
//                indicator.setFillColor(Color.parseColor("#ebe4e3"));
                return indicator;

            }
        });
        magicIndicator.setNavigator(commonNavigator);
        ViewPagerHelper.bind(magicIndicator,mViewPager);


        binding.vpContent.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                transitionPagerTitleViews.get(position).setSelectedColor(Color.GREEN);
                transitionPagerTitleViews.get(position).setTextSize(20);
                for (int index = 0;index<transitionPagerTitleViews.size();index++){
                    if(index!=position){
                        transitionPagerTitleViews.get(index).setTextSize(15);
                    }
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        switch (ev.getAction()){
            case MotionEvent.ACTION_MOVE:
                binding.magicIndicator.setBackgroundColor(Color.RED);
                for (int index = 0;index<transitionPagerTitleViews.size();index++){
                    transitionPagerTitleViews.get(index).setBackgroundColor(Color.RED);
                }
                break;
            case MotionEvent.ACTION_UP:
                binding.magicIndicator.setBackgroundColor(Color.WHITE);
                for (int index = 0;index<transitionPagerTitleViews.size();index++){
                    transitionPagerTitleViews.get(index).setBackgroundColor(Color.WHITE);
                }
                break;
        }
        return super.dispatchTouchEvent(ev);
    }
}
