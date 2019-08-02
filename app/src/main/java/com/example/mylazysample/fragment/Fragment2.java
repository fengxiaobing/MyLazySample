package com.example.mylazysample.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.mylazysample.R;
import com.example.mylazysample.base.BaseFragment;
import com.example.mylazysample.base.LazyFragment;

/**
 * Created by RF
 * on 2018/1/8.
 */

public class Fragment2 extends LazyFragment {
    private static final String TAG = "Fragment";
    public static Fragment2 newInstance() {
        Bundle args = new Bundle();
        Fragment2 fragment = new Fragment2();
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public void onFragmentResume() {
        super.onFragmentResume();
        Log.e(TAG, "onFragmentPause=Fragment2=真正的更新界面");
    }

    @Override
    public void onFragmentPause() {
        super.onFragmentPause();
        Log.e(TAG, "onFragmentPause=Fragment2=停止一切操作");
    }



    @Override
    protected void initView(View rootView) {

    }

    @Override
    public int getLayoutRes() {
        return R.layout.fragment2;
    }
}
