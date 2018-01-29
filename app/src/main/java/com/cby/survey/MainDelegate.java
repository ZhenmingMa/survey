package com.cby.survey;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;


import com.cby.orange.delegate.OrangeDelegate;
import com.cby.orange.pay.FastPay;

import butterknife.OnClick;


/**
 * Created by baiyanfang on 2018/1/29.
 */

public class MainDelegate extends OrangeDelegate {

    @OnClick(R2.id.my_tv)
    void click(){
        FastPay.create(this).setOrderID(1).beginPayDialog();
    }
    @Override
    public Object setLayout() {
        return R.layout.activity_main;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {

    }
}
