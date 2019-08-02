package com.example.mylazysample.adapter;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.example.mylazysample.fragment.Fragment0;
import com.example.mylazysample.fragment.Fragment1;
import com.example.mylazysample.fragment.Fragment2;
import com.example.mylazysample.fragment.Fragment3;
import com.example.mylazysample.fragment.Fragment4;


/**
 * Created by RF
 * on 2018/1/9.
 */

public class MainPagerAdapter extends FragmentStatePagerAdapter {
    private int mTabCount;

    public MainPagerAdapter(FragmentManager fm) {
        super(fm);
        this.mTabCount = 0;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return Fragment0.newInstance();
            case 1:
                return Fragment1.newInstance();
            case 2:
                return Fragment2.newInstance();
            case 3:
                return Fragment3.newInstance();
            case 4:
                return Fragment4.newInstance();

            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return mTabCount;
    }

    public void setCount(int count) {
        mTabCount = count;
    }
}
