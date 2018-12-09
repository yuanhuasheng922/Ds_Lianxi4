package com.example.yuan.login_disanfang.adapter;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.yuan.login_disanfang.fragent.FirstFragment;
import com.example.yuan.login_disanfang.fragent.GirlFragment;
import com.example.yuan.login_disanfang.fragent.MyFragment;

public class ViewPagerAdapter extends FragmentPagerAdapter {

    private String[] str=new String[]{"首页","我的","她的"};
    public ViewPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int i) {
        switch (i)
        {
            case 0:
                return new FirstFragment();
            case 1:
                return new MyFragment();
            case 2:
                return new GirlFragment();
        }
        return null;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return str[position];
    }

    @Override
    public int getCount() {
        return str.length;
    }
}
