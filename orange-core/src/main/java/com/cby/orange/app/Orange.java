package com.cby.orange.app;

import android.content.Context;
import android.os.Handler;

import java.util.HashMap;

/**
 *
 * Created by Ma on 2017/11/22.
 */

public final class Orange {

//    public static Configurator init(Context context){
//        getConfigurators().put(ConfigKeys.APPLICATION_CONTEXT.name(),context.getApplicationContext());
//        return Configurator.getInstance();
//    }
//
    public static HashMap<Object,Object> getConfigurators(){
        return Configurator.getInstance().getOrangeConfigs();
    }
//
//    public static Object getConfigurator(){
//        return Configurator.getInstance().getOrangeConfigs().get();
//    }


    public static Configurator init(Context context){

        Configurator.getInstance().getOrangeConfigs().put(ConfigKeys.APPLICATION_CONTEXT,context.getApplicationContext());
        return Configurator.getInstance();
    }

    public static Configurator getConfigurator() {
        return Configurator.getInstance();
    }

    public static <T> T getConfiguration(Object key) {
        return getConfigurator().getConfiguration(key);
    }

    public static Context getApplicationContext() {
        return getConfiguration(ConfigKeys.APPLICATION_CONTEXT);
    }

    public static Handler getHandler() {
        return getConfiguration(ConfigKeys.HANDLER);
    }

}
