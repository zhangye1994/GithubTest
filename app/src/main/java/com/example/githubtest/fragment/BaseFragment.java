package com.example.githubtest.fragment;

import android.content.Context;
import android.support.v4.app.Fragment;

import com.example.verticalscrollview.activity.BaseActivity;


/**
 * Created by 张野 on 2020/3/20.
 */

public class BaseFragment extends Fragment {
    public BaseActivity mA ;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mA = (BaseActivity) getActivity();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }
}
