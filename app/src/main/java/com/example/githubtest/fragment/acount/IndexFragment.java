package com.example.githubtest.fragment.acount;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.verticalscrollview.R;
import com.example.verticalscrollview.adapter.account.IndexRcvAdapter;
import com.example.verticalscrollview.adapter.account.IndexVpAdapter;
import com.example.verticalscrollview.databinding.FragmentIndexBinding;
import com.example.verticalscrollview.fragment.BaseFragment;

import java.util.ArrayList;

/**
 * Created by 张野 on 2020/3/20.
 */

public class IndexFragment extends BaseFragment {
    FragmentIndexBinding binding;
    private int index;
    private ArrayList<Integer> rcvItems = new ArrayList<>();
    public static IndexFragment newInstance(int index){
        IndexFragment mainFragment = new IndexFragment();
        Bundle args = new Bundle();
        args.putInt("index",index);
        mainFragment.setArguments(args);
        return mainFragment;
    }
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(LayoutInflater.from(mA), R.layout.fragment_index, container, false);
        initData();
        return binding.getRoot();
    }
    private void initData(){
        index = getArguments().getInt("index",index);
        binding.vpIndex.setAdapter(new IndexVpAdapter(mA));
        for (int i = 0;i<30;i++){
            rcvItems.add(i);
        }
        RecyclerView.LayoutManager layoutManager = new StaggeredGridLayoutManager(1, LinearLayoutManager.VERTICAL);
        binding.rcvIndex.setLayoutManager(layoutManager);
        binding.rcvIndex.setAdapter(new IndexRcvAdapter(R.layout.item_index_recyclerview,rcvItems));
    }
}
