package com.cby.orange.wechat.templates;


import com.cby.orange.activites.ProxyActivity;
import com.cby.orange.delegate.OrangeDelegate;
import com.cby.orange.wechat.BaseWXActivity;
import com.cby.orange.wechat.BaseWXEntryActivity;
import com.cby.orange.wechat.OrangeWeChat;

public class WXEntryTemplate extends BaseWXEntryActivity{

    @Override
    protected void onResume() {
        super.onResume();
        finish();
        overridePendingTransition(0,0);
    }

    @Override
    protected void onSignSuccess(String userInfo) {
        OrangeWeChat.getInstance().getmSignInCallcback().onSignInSuccess(userInfo);
    }
}
