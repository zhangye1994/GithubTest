package com.example.verticalscrollview.adapter.account;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.verticalscrollview.R;

import java.util.List;

/**
 * Created by 张野 on 2020/3/21.
 */

public class IndexRcvAdapter extends BaseQuickAdapter<Integer,BaseViewHolder> {
    public IndexRcvAdapter(int layoutResId, List data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, Integer item) {
        helper.setText(R.id.tv_item_vp,"RecyclerView的item"+item);
    }
}
