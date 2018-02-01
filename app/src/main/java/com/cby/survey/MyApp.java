package com.cby.survey;

import android.app.Application;

import com.cby.orange.app.Orange;
import com.cby.orange.icon.FontEcModule;
import com.cby.orange.net.interceptors.DebugInterceptor;
import com.cby.survey.ec.database.DataBaseManager;
import com.joanzapata.iconify.fonts.FontAwesomeModule;

/**
 * Created by baiyanfang on 2018/1/29.
 */

public class MyApp extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Orange.init(this)
                .withApiHost("http://101.200.53.112:8080/")
                .withWeChatAppId("123")
                .withWeChatAppSecret("123123")
                .withIcon(new FontAwesomeModule())
                .withIcon(new FontEcModule())
                .configure();

        DataBaseManager.getInstance().init(this);
    }
}
