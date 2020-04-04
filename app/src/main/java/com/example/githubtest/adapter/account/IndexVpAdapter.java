package com.example.githubtest.adapter.account;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.verticalscrollview.R;

import java.util.ArrayList;

/**
 * Created by 张野 on 2020/3/21.
 */

public class IndexVpAdapter extends PagerAdapter {
    ArrayList<View> views = new ArrayList<>();
    private Context context;
    LayoutInflater inflater ;
    public IndexVpAdapter(Context context){
        this.context = context;
        inflater = LayoutInflater.from(context);
        for(int i = 0;i<4;i++){
            View view = inflater.inflate(R.layout.item_index_viewpager,null);
            TextView textView = view.findViewById(R.id.tv_item_vp);
            textView.setText("首页item"+(i+1));
            views.add(view);
        }
    }

    @Override
    public int getCount() {
        return views.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        container.addView(views.get(position));
        return views.get(position);
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView(views.get(position));
    }
}
