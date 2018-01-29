package com.cby.orange.wechat;

import android.app.Activity;

import com.cby.orange.app.ConfigKeys;
import com.cby.orange.app.Orange;
import com.cby.orange.wechat.callbacks.IWeChatSignInCallcback;
import com.tencent.mm.opensdk.modelmsg.SendAuth;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;

/**
 * Created by baiyanfang on 2017/12/19.
 */

public class OrangeWeChat {

    public final static String APPID = Orange.getConfiguration(ConfigKeys.WECHAT_APP_ID);
    public final static String APPSECRET = Orange.getConfiguration(ConfigKeys.WECHAT_APP_SECRET);
    private final IWXAPI WXAPI;
    private IWeChatSignInCallcback mSignInCallback = null;

    private OrangeWeChat() {
        final Activity activity = Orange.getConfiguration(ConfigKeys.ACTIVITY);
        /**
         * 是否验证
         */
        WXAPI = WXAPIFactory.createWXAPI(activity, APPID, false);
        WXAPI.registerApp(APPID);
    }

    private static final class Holder {
        private static final OrangeWeChat INSTANCE = new OrangeWeChat();
    }

    public static OrangeWeChat getInstance() {
        return Holder.INSTANCE;
    }

    public final IWXAPI getWXAPI() {
        return WXAPI;
    }

    public OrangeWeChat onSignInSuccess(IWeChatSignInCallcback mSignInCallback){
        this.mSignInCallback = mSignInCallback;
        return this;
    }

    public IWeChatSignInCallcback getmSignInCallcback(){
        return mSignInCallback;
    }

    public final void signIn() {
        final SendAuth.Req req = new SendAuth.Req();
        req.scope = "snsapi_userinfo";
        req.state = "random_state";
        WXAPI.sendReq(req);
    }
}
