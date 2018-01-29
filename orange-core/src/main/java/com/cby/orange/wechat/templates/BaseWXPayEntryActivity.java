package com.cby.orange.wechat.templates;

import com.cby.orange.ui.loader.OrangeLoader;
import com.cby.orange.utils.log.OrangeLogger;
import com.cby.orange.wechat.BaseWXActivity;
import com.tencent.mm.opensdk.constants.ConstantsAPI;
import com.tencent.mm.opensdk.modelbase.BaseReq;
import com.tencent.mm.opensdk.modelbase.BaseResp;

/**
 * Created by baiyanfang on 2018/1/4.
 */

public abstract class BaseWXPayEntryActivity extends BaseWXActivity {

    private static final int WX_PAY_SUCCESS = 0;
    private static final int WX_PAY_FAIL = -1;
    private static final int WX_PAY_CANCEL = -2;

    protected abstract void onPaySuccess();
    protected abstract void onPayFail();
    protected abstract void onPayCancel();

    @Override
    public void onResp(BaseResp baseResp) {
        OrangeLogger.e("wechat",baseResp.getType()+"");
        OrangeLogger.e("wechat",baseResp.errCode+"");
        OrangeLogger.e("wechat",baseResp.errStr+"");

        if (baseResp.getType() == ConstantsAPI.COMMAND_PAY_BY_WX){
            switch (baseResp.errCode){
                case WX_PAY_SUCCESS:
                    onPaySuccess();
                    break;
                case WX_PAY_FAIL:
                    onPayFail();
                    break;
                case WX_PAY_CANCEL:
                    onPayCancel();
                    break;
                default:
                    break;
            }
        }
    }
}
