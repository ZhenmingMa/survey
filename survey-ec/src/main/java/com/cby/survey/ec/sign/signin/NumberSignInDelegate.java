package com.cby.survey.ec.sign.signin;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputEditText;
import android.util.Patterns;
import android.view.View;
import android.widget.Toast;

import com.cby.orange.delegate.OrangeDelegate;
import com.cby.orange.net.RestClient;
import com.cby.orange.net.callback.IError;
import com.cby.orange.net.callback.ISuccess;
import com.cby.orange.net.rx.MyObservable;
import com.cby.orange.net.rx.RxRestClient;
import com.cby.orange.ui.loader.LoaderStyle;
import com.cby.orange.ui.loader.OrangeLoader;
import com.cby.orange.utils.log.OrangeLogger;
import com.cby.survey.ec.R;
import com.cby.survey.ec.R2;
import com.cby.survey.ec.jsonhandler.JsonHandler;
import com.cby.survey.ec.sign.ISignListener;
import com.cby.survey.ec.sign.SignHandler;
import com.cby.survey.ec.sign.signup.SignUpDelegate;

import butterknife.BindView;
import butterknife.OnClick;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by baiyanfang on 2018/1/31.
 */

public class NumberSignInDelegate extends OrangeDelegate {

    private OrangeDelegate mRootDelegate = null;

    private ISignListener mISignListener = null;

    @BindView(R2.id.edit_sign_in_phone)
    TextInputEditText mPhone = null;
    @BindView(R2.id.edit_sign_in_password)
    TextInputEditText mPassword = null;

    @OnClick(R2.id.tv_number_sign_in)
    void onClickSignIn() {
        if (checkForm()) {
            RxRestClient.builder()
                    .url("login")
                    .params("phone", mPhone.getText().toString())
                    .params("password", mPassword.getText().toString())
                    .loader(getContext(), LoaderStyle.BallSpinFadeLoaderIndicator)
                    .build()
                    .post()
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new MyObservable<String>(getContext()) {
                        @Override
                        public void onNext(String s) {
                            SignHandler.onSignIn(s, mISignListener);
                        }

                    });
        }
    }

    @OnClick(R2.id.tv_number_sign_in_sign_up)
    void OnClickGoSignUp(){
        mRootDelegate.getSupportDelegate().start(new SignUpDelegate());
    }


    @OnClick(R2.id.tv_number_sign_in_forget)
    void onClickGoForget(){
        mRootDelegate.getSupportDelegate().start(new ForgetDelegate());
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        if (activity instanceof ISignListener)
            mISignListener = (ISignListener) activity;
    }

    public  NumberSignInDelegate create(OrangeDelegate orangeDelegate){
        NumberSignInDelegate delegate = new NumberSignInDelegate();
        delegate.mRootDelegate = orangeDelegate;
        return delegate;
    }

    @Override
    public Object setLayout() {
        return R.layout.delegate_number_sign_in;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {

    }

    private boolean checkForm() {
        String phone = mPhone.getText().toString();
        String password = mPassword.getText().toString();

        boolean isPass = true;

        if (phone.isEmpty() || phone.length() != 11) {
            mPhone.setError("手机号码错误");
            isPass = false;
        } else {
            mPhone.setError(null);
        }

        if (password.isEmpty() || password.length() < 6) {
            mPassword.setError("请输入至少6位数的密码");
            isPass = false;
        } else {
            mPassword.setError(null);
        }
        return isPass;

    }

}
