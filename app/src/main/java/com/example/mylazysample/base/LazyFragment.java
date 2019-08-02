package com.example.mylazysample.base;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.mylazysample.R;

/**
 * Created by RF
 * on 2018/1/8.
 * 如果是与ViewPager一起使用，调用的是setUserVisibleHint  他会在所有的生命周期之前会调用
 * 如果是通过FragmentTransaction的show和hide的方法来控制显示，调用的是onHiddenChanged.
 * 针对初始就show的Fragment 为了触发onHiddenChanged事件 达到lazy效果 需要先hide再show
 */

public abstract class LazyFragment extends Fragment {
    private static final String TAG = "LazyFragment";
    View rootView;
    boolean isViewCreated = false; //判断view（fragment）是否已经创建
    boolean currentVisible = false;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Log.e(TAG, "onCreateView");
        if (rootView == null) {
            rootView = inflater.inflate(getLayoutRes(), container, false); //这里为什么是false
        }
        initView(rootView);
        isViewCreated = true;
        if (getUserVisibleHint() && !isHidden()) {
            dispatchUserVisibleHint(true);
        }
        return rootView;
    }

    //判断用户是否可见，与生命周期无关
    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        Log.e(TAG, "setUserVisibleHint" + isVisibleToUser);
        if (isViewCreated) {   //view创建之后才可以分发事件
            if (isVisibleToUser && !currentVisible) {  //fragment可见
                dispatchUserVisibleHint(true);
            } else if (!isVisibleToUser && currentVisible){  //不可见
                dispatchUserVisibleHint(false);
            }
        }

    }

    private void dispatchUserVisibleHint(boolean isVisible) {
        if(currentVisible == isVisible){
            return;
        }
        currentVisible = isVisible;
        if (isVisible) {
            onFragmentResume();
        } else {
            onFragmentPause();
        }
    }

    protected void onFragmentPause() {
        Log.e(TAG, "onFragmentResume" + "真正的pause");

    }

    protected void onFragmentResume() {
        Log.e(TAG, "onFragmentResume" + "真正的resume");


    }

    //FragmentTransaction 也与生命周期无关
    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        Log.e(TAG, "onHiddenChanged" + hidden);
        if(hidden){
            dispatchUserVisibleHint(true);
        }else {
            dispatchUserVisibleHint(false);
        }

    }

    //初始化view
    protected abstract void initView(View rootView);

    //设置xml布局
    public abstract int getLayoutRes();

    @Override
    public void onResume() {
        super.onResume();
        Log.e(TAG, "onResume");
        if (getUserVisibleHint() && !isHidden() && !currentVisible) {
            dispatchUserVisibleHint(true);
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.e(TAG, "onPause");
        if (getUserVisibleHint() && currentVisible) {
            dispatchUserVisibleHint(false);
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        Log.e(TAG, "onDestroyView");
        isViewCreated = false;
        currentVisible = false;
    }
}
