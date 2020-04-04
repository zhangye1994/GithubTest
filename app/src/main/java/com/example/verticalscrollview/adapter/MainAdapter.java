package com.example.verticalscrollview.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.verticalscrollview.fragment.BaseFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 张野 on 2020/3/20.
 */

public class MainAdapter extends FragmentPagerAdapter {
    private List<BaseFragment> fragments = new ArrayList<>();
    public MainAdapter(FragmentManager fm, List<BaseFragment> fragments) {
        super(fm);
        this.fragments=fragments;
    }
    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);
    }

    @Override
    public int getCount() {
        return fragments.size();
    }
}
