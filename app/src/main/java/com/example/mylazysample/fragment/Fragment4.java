package com.example.mylazysample.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.mylazysample.R;
import com.example.mylazysample.SecondActivity;
import com.example.mylazysample.base.BaseFragment;
import com.example.mylazysample.base.LazyFragment;

/**
 * Created by RF
 * on 2018/1/8.
 */

public class Fragment4 extends LazyFragment {
    private static final String TAG = "Fragment";
    private TextView textView;
    public static Fragment4 newInstance() {
        Bundle args = new Bundle();
        Fragment4 fragment = new Fragment4();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected void initView(View rootView) {
        textView = rootView.findViewById(R.id.text);
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), SecondActivity.class));
            }
        });
    }

    @Override
    public void onFragmentResume() {
        super.onFragmentResume();
        Log.e(TAG, "onFragmentPause=Fragment4=真正的更新界面");
    }

    @Override
    public void onFragmentPause() {
        super.onFragmentPause();
        Log.e(TAG, "onFragmentPause=Fragment4=停止一切操作");
    }
    @Override
    public int getLayoutRes() {
        return R.layout.fragment4;
    }
}
