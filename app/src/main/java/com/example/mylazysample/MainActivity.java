package com.example.mylazysample;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mylazysample.adapter.MainPagerAdapter;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {
    @BindView(R.id.viewpager)
    ViewPager viewpager;
    @BindView(R.id.rl_home)
    RelativeLayout rlHome;
    @BindView(R.id.rl_work)
    RelativeLayout rlWork;
    @BindView(R.id.rl_task)
    RelativeLayout rlTask;
    @BindView(R.id.rl_message)
    RelativeLayout rlMessage;

    @BindView(R.id.rl_ldys)
    RelativeLayout rl_ldys;

    private long mExitTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        init();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }


    // 监听手机上的BACK键
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            // 判断两次点击的时间间隔（默认设置为2秒）
            if ((System.currentTimeMillis() - mExitTime) > 2000) {
                Toast.makeText(this, "是否退出应用", Toast.LENGTH_SHORT).show();
                mExitTime = System.currentTimeMillis();
            } else {
                finish();
//                System.exit(0);
                super.onBackPressed();
            }

            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    private void init() {
        FragmentManager fm = getSupportFragmentManager();
        MainPagerAdapter mPagerAdapter = new MainPagerAdapter(fm);
        mPagerAdapter.setCount(5);
        viewpager.setAdapter(mPagerAdapter);
        viewpager.setCurrentItem(0); //设置当前页是第一页

    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
    }


    @OnClick({R.id.rl_home, R.id.rl_work, R.id.rl_task, R.id.rl_message,R.id.rl_ldys})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.rl_home:
                viewpager.setCurrentItem(0);
                break;
            case R.id.rl_work:
                viewpager.setCurrentItem(1);
                break;
            case R.id.rl_task:
                viewpager.setCurrentItem(2);

                break;
            case R.id.rl_message:
                viewpager.setCurrentItem(3);
                break;

            case R.id.rl_ldys:
                viewpager.setCurrentItem(4);
                break;
        }
    }


}

