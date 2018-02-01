package com.cby.survey.ec.sign;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.cby.orange.app.AccountManager;
import com.cby.survey.ec.database.DataBaseManager;
import com.cby.survey.ec.database.UserProfile;
import com.cby.survey.ec.jsonhandler.JsonHandler;

import org.greenrobot.greendao.database.Database;

import java.text.MessageFormat;

/**
 * Created by baiyanfang on 2018/1/30.
 */

public class SignHandler {

    public static void onSignIn(String response, ISignListener listener) {

        JsonHandler handler = JsonHandler.create(response);
        final int code = handler.getCode();

        if (code == 0) {
            JSONObject profilesJson = handler.getDataObject();

            final String id = profilesJson.getString("id");
            final String token = profilesJson.getString("token");
            final long phone = profilesJson.getLong("phone");
            final long time = profilesJson.getLong("time");
            final long birthday = profilesJson.getLong("birthday");
            final String location = profilesJson.getString("location");
            final String occupation = profilesJson.getString("occupation");
            final String income = profilesJson.getString("income");
            final String hobby = profilesJson.getString("hobby");
            final String sex = profilesJson.getString("sex");

            UserProfile userProfile = new UserProfile(id, token, phone, time, birthday, location, occupation, income, hobby, sex);
            DataBaseManager.getInstance().getDao().insert(userProfile);

            //保存登陆状态
            AccountManager.setSignState(true);
            listener.onSignInSuccess();

        } else {
            AccountManager.setSignState(false);
            final String message = handler.getMessage();
            listener.onSignInError(message);
        }


    }
}
