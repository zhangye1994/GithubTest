package com.example.verticalscrollview.adapter.account;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.verticalscrollview.R;

import java.util.List;

/**
 * Created by 张野 on 2020/3/21.
 */

public class SlidingTwoAdapter extends BaseQuickAdapter<Integer,BaseViewHolder> {

    public SlidingTwoAdapter(int layoutResId, List<Integer> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, Integer item) {
    }

    @Override
    public void onBindViewHolder(BaseViewHolder holder, int positions) {
        holder.setText(R.id.tv_item_sliding_two_rcv,"滑动冲突2RCV_item"+positions);
    }
}
