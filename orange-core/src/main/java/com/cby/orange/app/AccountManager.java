package com.cby.orange.app;

import com.cby.orange.utils.storage.OrangePreference;

/**
 * Created by baiyanfang on 2017/12/15.
 */

public class AccountManager {

    private enum SignTag{
        SIGN_TAG
    }


    //保存用户登陆状态，登陆后调用
    public static void setSignState(boolean state){
        OrangePreference.setAppFlag(SignTag.SIGN_TAG.name(),state);
    }

    private static boolean isSignIn(){
        return OrangePreference.getAppFlag(SignTag.SIGN_TAG.name());
    }

    public static void checkAccount(IUserchecker iUserchecker){
        if (isSignIn())
            iUserchecker.onSign();
        else
            iUserchecker.onNotSign();
    }
}
