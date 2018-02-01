package com.cby.survey.ec.sign;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.cby.orange.delegate.OrangeDelegate;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by baiyanfang on 2018/1/30.
 */

public class TabPagerAdapter extends FragmentStatePagerAdapter {

    private List<TabBean> TAB_BEANS = new ArrayList<>();

    public TabPagerAdapter(FragmentManager fm, List<TabBean> tab_beans) {
        super(fm);
        this.TAB_BEANS = tab_beans;
    }

    @Override
    public Fragment getItem(int position) {
        return TAB_BEANS.get(position).getContent();
    }

    @Override
    public int getCount() {
        return TAB_BEANS.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return TAB_BEANS.get(position).getTitle();
    }
}
