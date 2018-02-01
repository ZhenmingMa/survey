package com.cby.survey.ec.sign;

/**
 * 
 * Created by baiyanfang on 2018/1/30.
 */

public interface ISignListener {

    void onSignInSuccess();
    void onSignInError(String message);
    void onSignUpSuccess();
    void onSignUpError();

}
