package com.cby.survey.ec.sign.signin;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.cby.orange.delegate.OrangeDelegate;
import com.cby.survey.ec.R;
import com.cby.survey.ec.R2;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by baiyanfang on 2018/2/1.
 */

public class ForgetDelegate extends OrangeDelegate {

    @OnClick(R2.id.icon_back)
    void onClickBack(){
        getSupportDelegate().pop();
    }

    @Override
    public Object setLayout() {
        return R.layout.delegate_forget_password;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {

    }
}
