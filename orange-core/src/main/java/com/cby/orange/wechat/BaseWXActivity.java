package com.cby.orange.wechat;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.tencent.mm.opensdk.openapi.IWXAPIEventHandler;

/**
 * Created by baiyanfang on 2017/12/19.
 */

public abstract class BaseWXActivity extends AppCompatActivity implements IWXAPIEventHandler {

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState, @Nullable PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);

        //必须写在OnCreate中
        OrangeWeChat.getInstance().getWXAPI().handleIntent(getIntent(),this);
    }

    //保证在各种情况下都运行
    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        setIntent(intent);
        OrangeWeChat.getInstance().getWXAPI().handleIntent(getIntent(),this);
    }
}
