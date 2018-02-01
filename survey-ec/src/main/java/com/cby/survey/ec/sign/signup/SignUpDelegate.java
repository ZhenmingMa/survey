package com.cby.survey.ec.sign.signup;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.cby.orange.delegate.OrangeDelegate;
import com.cby.survey.ec.R;
import com.cby.survey.ec.R2;

import butterknife.OnClick;

/**
 * 注册页面
 * Created by baiyanfang on 2018/1/30.
 */

public class SignUpDelegate extends OrangeDelegate{


    @OnClick(R2.id.tv_sign_up_sign_in)
    void onClickBack(){
        getSupportDelegate().pop();
    }

    @OnClick(R2.id.icon_sign_up_back)
    void onClickIconBack(){
        getSupportDelegate().pop();
    }

    @Override
    public Object setLayout() {
        return R.layout.delegate_sign_up;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {

    }
}
