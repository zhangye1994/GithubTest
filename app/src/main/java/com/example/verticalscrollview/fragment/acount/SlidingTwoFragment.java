package com.example.verticalscrollview.fragment.acount;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.blankj.utilcode.util.LogUtils;
import com.example.verticalscrollview.R;
import com.example.verticalscrollview.adapter.account.SlidingTwoAdapter;
import com.example.verticalscrollview.adapter.account.SlidingTwoVpAdapter;
import com.example.verticalscrollview.databinding.FragmentSlidingTwoBinding;
import com.example.verticalscrollview.fragment.BaseFragment;
import com.example.verticalscrollview.view.VerticalScrollView;

import java.util.ArrayList;

/**
 * Created by 张野 on 2020/3/21.
 */

public class SlidingTwoFragment extends BaseFragment {
    private int SLIDING_MODE = 0;//0：优先RecyclerView的滑动，不做处理1：优先ScrollView的滑动
    ArrayList<Integer> items = new ArrayList<>();
    public static SlidingTwoFragment newInstance(){
        Bundle bundle = new Bundle();
        SlidingTwoFragment slidingTwoFragment = new SlidingTwoFragment();
        slidingTwoFragment.setArguments(bundle);
        return slidingTwoFragment;
    }

    FragmentSlidingTwoBinding binding;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_sliding_two,container,false);
        initData();
        initView();
        return binding.getRoot();
    }
    private void initData(){
        items.clear();
        for (int i = 0 ; i < 100;i++){
            items.add(i);
        }
    }
    private void initView(){
        binding.rcvSlidingTwo.setLayoutManager(new StaggeredGridLayoutManager(1, LinearLayoutManager.VERTICAL));
        /* binding.rcvSlidingTwo.setLayoutManager(new StaggeredGridLayoutManager(1, LinearLayoutManager.VERTICAL){
            @Override
            public boolean canScrollVertically() {
                return super.canScrollVertically();
            }
        });*/
        final LinearLayoutManager layoutManager = new LinearLayoutManager(mA);
        layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        binding.rcvSlidingTwo1.setLayoutManager(new StaggeredGridLayoutManager(10,layoutManager.getOrientation()));

        binding.rcvSlidingTwo.setAdapter(new SlidingTwoAdapter(R.layout.item_sliding_two_rcv,items));
        binding.rcvSlidingTwo1.setAdapter(new SlidingTwoAdapter(R.layout.item_sliding_two_rcv,items));
        binding.vpSlidingTwo.setAdapter(new SlidingTwoVpAdapter(mA));

//        binding.rcvSlidingTwo1.canScrollVertically(1);//false表示已经滚动到底部
//        binding.rcvSlidingTwo1.canScrollVertically(-1);//false表示已经滚动到顶部

//        binding.scrollView.setOnTouchListener(new View.OnTouchListener(){
//            @Override
//            public boolean onTouch(View arg0, MotionEvent arg1) {//返回true时会禁止scrollview的滑动
//                return true;
//            }
//        });

        binding.tvExchangeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.e("zhangye","OnClickListener");
                if(layoutManager.getOrientation()==LinearLayoutManager.VERTICAL){
                    Log.e("zhangye","LinearLayoutManager.HORIZONTAL");
                    binding.tvExchangeLayout.setText("横向滑动");
                    layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
                    binding.rcvSlidingTwo1.setLayoutManager(new StaggeredGridLayoutManager(10,layoutManager.getOrientation()));
                }else{
                    Log.e("zhangye","LinearLayoutManager.VERTICAL");
                    layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
                    binding.tvExchangeLayout.setText("竖向滑动");
                    binding.rcvSlidingTwo1.setLayoutManager(new StaggeredGridLayoutManager(1,layoutManager.getOrientation()){
                        @Override
                        public boolean canScrollVertically() {

//                            LogUtils.e("是否滚动到顶部："+binding.scrollView.getScrollY()+"<"+binding.rcvSlidingTwo1.getTop()+"=="+(binding.scrollView.getScrollY()<binding.rcvSlidingTwo1.getTop()));
//                            if(binding.scrollView.getScrollY()!=0&&!binding.scrollView.isScrollBottom&&SLIDING_MODE == 1){
//                                return false;
//                            }

                            LogUtils.e("是否滚动到底部："+binding.scrollView.isScrollBottom);
                            LogUtils.e("scrollView的滚动方向："+binding.scrollView.scrollOrientation);

                            if(SLIDING_MODE == 1&&binding.scrollView.scrollOrientation == VerticalScrollView.ORIENTATION_PULL_DOWN &&binding.scrollView.getScrollY() != 0){
                                return false;
                            }else if(SLIDING_MODE == 1&&binding.scrollView.scrollOrientation == VerticalScrollView.ORIENTATION_PULL_UP &&!binding.scrollView.isScrollBottom ){//应注意scrollview包含的内容小于屏幕高度时
                                return false;
                            }

                            return super.canScrollVertically();
                        }
                    });
                }
            }
        });
        binding.tvExchangeSliding.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(SLIDING_MODE == 0){
                    binding.tvExchangeSliding.setText("优先外部滑动");
                    SLIDING_MODE = 1;
                }else{
                    SLIDING_MODE = 0;
                    binding.tvExchangeSliding.setText("优先内部滑动");
                }
            }
        });

    }
}
