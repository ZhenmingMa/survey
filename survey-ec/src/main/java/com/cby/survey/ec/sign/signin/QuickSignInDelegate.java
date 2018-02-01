package com.cby.survey.ec.sign.signin;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputEditText;
import android.support.v7.widget.AppCompatTextView;
import android.view.View;
import android.widget.Toast;

import com.cby.orange.delegate.OrangeDelegate;
import com.cby.orange.net.rx.MyObservable;
import com.cby.orange.net.rx.RxRestClient;
import com.cby.survey.ec.R;
import com.cby.survey.ec.R2;
import com.cby.survey.ec.sign.ISignListener;
import com.cby.survey.ec.sign.SignHandler;
import com.cby.survey.ec.sign.signup.SignUpDelegate;

import butterknife.BindView;
import butterknife.OnClick;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by baiyanfang on 2018/1/31.
 */

public class QuickSignInDelegate extends OrangeDelegate {

    private OrangeDelegate mRootDelegate = null;

    private ISignListener mISignListener = null;

    @BindView(R2.id.edit_quick_sign_in_phone)
    TextInputEditText mPhone = null;
    @BindView(R2.id.edit_quick_sign_in_check_code)
    TextInputEditText mCheckCode = null;

    //获取验证码
    @OnClick(R2.id.tv_login_check_code)
    void onClickGetCheckCode(){

    }


    @OnClick(R2.id.tv_quick_sign_in)
    void onClickSignIn(){
        final String phone = mPhone.getText().toString();
        RxRestClient.builder()
                .url("loginByPhone")
                .params("phone",phone)
                .loader(getContext())
                .build()
                .post()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new MyObservable<String>(getContext()) {
                    @Override
                    public void onNext(String s) {
                        SignHandler.onSignIn(s,mISignListener);
                    }
                });

    }

    @OnClick(R2.id.tv_quick_sign_in_sign_up)
    void OnClickGoSignUp(){
        mRootDelegate.getSupportDelegate().start(new SignUpDelegate());
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        if (activity instanceof ISignListener){
            this.mISignListener = (ISignListener) activity;
        }
    }

    public  QuickSignInDelegate create(OrangeDelegate orangeDelegate){
        QuickSignInDelegate delegate = new QuickSignInDelegate();
        delegate.mRootDelegate = orangeDelegate;
        return delegate;
    }

    @Override
    public Object setLayout() {
        return R.layout.delegate_quick_sign_in;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {

    }
}
