package com.cby.orange.pay;

import android.app.Activity;
import android.app.AlertDialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.AsyncTask;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.cby.orange.R;
import com.cby.orange.app.ConfigKeys;
import com.cby.orange.app.Orange;
import com.cby.orange.delegate.OrangeDelegate;
import com.cby.orange.net.RestClient;
import com.cby.orange.net.callback.ISuccess;
import com.cby.orange.ui.loader.OrangeLoader;
import com.cby.orange.utils.log.OrangeLogger;
import com.cby.orange.wechat.OrangeWeChat;
import com.tencent.mm.opensdk.modelpay.PayReq;
import com.tencent.mm.opensdk.openapi.IWXAPI;

/**
 * Created by baiyanfang on 2018/1/4.
 */

public class FastPay implements View.OnClickListener {

    //设置支付回调监听
    private IAlPayResultListener mIAlPayResultListener = null;
    private Activity mActivity;

    private AlertDialog mDialog = null;
    private int mOrderId = -1;

    private FastPay(OrangeDelegate delegate) {
        this.mActivity = delegate.getProxyActivity();
        this.mDialog = new AlertDialog.Builder(delegate.getContext()).create();
    }

    public static FastPay create(OrangeDelegate delegate) {
        return new FastPay(delegate);
    }

    public FastPay SetPayResultListener(IAlPayResultListener iAlPayResultListener){
        this.mIAlPayResultListener = iAlPayResultListener;
        return this;
    }

    public FastPay setOrderID(int orderID){
        this.mOrderId = orderID;
        return this;
    }

    public void beginPayDialog() {
        mDialog.show();
        final Window window = mDialog.getWindow();
        if (window != null) {
            window.setContentView(R.layout.dialog_pay_panel);
            window.setGravity(Gravity.BOTTOM);
            window.setWindowAnimations(R.style.anim_panel_up_for_bottom);
            window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            //设置属性
            final WindowManager.LayoutParams params = window.getAttributes();
            params.width = WindowManager.LayoutParams.MATCH_PARENT;
            params.flags = WindowManager.LayoutParams.FLAG_DIM_BEHIND;
            window.setAttributes(params);
            window.findViewById(R.id.btn_dialog_pay_alipay).setOnClickListener(this);
            window.findViewById(R.id.btn_dialog_pay_wechat).setOnClickListener(this);
            window.findViewById(R.id.btn_dialog_pay_cancel).setOnClickListener(this);
        }
    }

    private final void alPay(int orderID) {
        final String signUrl = "index.php";
        //获取签名字符串
        RestClient.builder()
                .url(signUrl)
                .loader(mActivity)
                .success(new ISuccess() {
                    @Override
                    public void onSuccess(String response) {

//                        String paySign = JSONObject.parseObject(response).getString("result");
                        String paySign = "123123123";
                        OrangeLogger.e("paysign",paySign);
                        //必须异步调用客户端接口支付接口
                        final PayAsyncTask payAsyncTask = new PayAsyncTask(mActivity,mIAlPayResultListener);
                        payAsyncTask.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR,paySign);
                    }
                })
                .build()
                .post();
    }

    private final void weChatPay(int orderID) {
        OrangeLoader.stopLoading();
        final String weChatPrePayUrl = "index.php";

        //生成微信api
        final IWXAPI iwxapi = OrangeWeChat.getInstance().getWXAPI();
        final String appId = Orange.getConfiguration(ConfigKeys.WECHAT_APP_ID);

        iwxapi.registerApp(OrangeWeChat.APPID);

        RestClient.builder()
                .url(weChatPrePayUrl)
                .success(new ISuccess() {
                    @Override
                    public void onSuccess(String response) {
//                        final JSONObject result = JSON.parseObject(response).getJSONObject("result");
//                        final String prepayId = result.getString("prepayid");
//                        final String partnerId = result.getString("partnerid");
//                        final String packageValue = result.getString("package");
//                        final String timestamp = result.getString("timestamp");
//                        final String nonceStr = result.getString("noncestr");
//                        final String paySign = result.getString("sign");

                        final String prepayId = "1900000109";
                        final String partnerId ="WX1217752501201407033233368018";
                        final String packageValue = "Sign=WXPay";
                        final String timestamp = "1412000000";
                        final String nonceStr = "5K8264ILTKCH16CQ2502SI8ZNMTM67VS";
                        final String paySign = "C380BEC2BFD727A4B6845133519F3AD6";

                        final PayReq payReq = new PayReq();
                        payReq.appId = appId;
                        payReq.prepayId = prepayId;
                        payReq.partnerId = partnerId;
                        payReq.packageValue = packageValue;
                        payReq.timeStamp = timestamp;
                        payReq.nonceStr = nonceStr;
                        payReq.sign = paySign;

                        iwxapi.sendReq(payReq);
                    }
                })
                .build()
                .post();
    }

    @Override
    public void onClick(View view) {
        final int id = view.getId();
        if (id == R.id.btn_dialog_pay_alipay) {
            alPay(mOrderId);
            mDialog.cancel();
        }
        if (id == R.id.btn_dialog_pay_wechat) {
            weChatPay(mOrderId);
            mDialog.cancel();
        }
        if (id == R.id.btn_dialog_pay_cancel) {
            mDialog.cancel();
        }


    }
}
