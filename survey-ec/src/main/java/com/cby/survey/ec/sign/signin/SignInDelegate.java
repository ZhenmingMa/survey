package com.cby.survey.ec.sign.signin;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.view.View;

import com.cby.orange.delegate.OrangeDelegate;
import com.cby.survey.ec.R;
import com.cby.survey.ec.R2;
import com.cby.survey.ec.sign.TabBean;
import com.cby.survey.ec.sign.TabPagerAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * 登陆页面
 * Created by baiyanfang on 2018/1/30.
 */

public class SignInDelegate extends OrangeDelegate {

    @BindView(R2.id.tab_layout)
    TabLayout mTabLayout = null;
    @BindView(R2.id.view_pager)
    ViewPager mViewPager = null;


    @Override
    public Object setLayout() {
        return R.layout.delegate_sign_in;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {
        initTabLayout();
        initViewPager();
        setTitle("登陆");
    }

    private void initTabLayout(){
        mTabLayout.setTabMode(TabLayout.MODE_FIXED);
        mTabLayout.setBackgroundColor(Color.WHITE);
        mTabLayout.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
        mTabLayout.setVerticalFadingEdgeEnabled(true);
        mTabLayout.setSelectedTabIndicatorColor(ContextCompat.getColor(getContext(),R.color.app_main));
        mTabLayout.setTabTextColors(Color.BLACK, ContextCompat.getColor(getContext(),R.color.app_main));
        mTabLayout.setupWithViewPager(mViewPager);
    }

    private void  initViewPager(){
        List<TabBean> tab_beans = new ArrayList<>();
        tab_beans.add(new TabBean("账号登陆",new NumberSignInDelegate().create(this)));
        tab_beans.add(new TabBean("快速登陆",new QuickSignInDelegate().create(this)));
        TabPagerAdapter adapter = new TabPagerAdapter(getFragmentManager(),tab_beans);
        mViewPager.setAdapter(adapter);
    }

}
