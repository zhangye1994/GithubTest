package com.example.githubtest.fragment.acount;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.verticalscrollview.R;
import com.example.verticalscrollview.databinding.FragmentEmptyBinding;
import com.example.verticalscrollview.fragment.BaseFragment;


/**
 * Created by 张野 on 2020/3/21.
 */

public class EmptyFragment extends BaseFragment {
    FragmentEmptyBinding binding;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_empty,container,false);
        return binding.getRoot();
    }
}
