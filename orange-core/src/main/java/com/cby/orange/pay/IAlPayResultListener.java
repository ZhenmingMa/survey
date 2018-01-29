package com.cby.orange.pay;

/**
 * Created by baiyanfang on 2018/1/4.
 */

public interface IAlPayResultListener {

    void onPaySuccess();

    void onPaying();

    void onPayFail();

    void onPayCancel();

    void onPayConnectError();
}
