package com.cby.orange.app;

import android.app.Activity;
import android.app.Application;
import android.os.Handler;
import android.support.annotation.NonNull;

import com.blankj.utilcode.util.Utils;
import com.cby.orange.delegate.web.event.Event;
import com.cby.orange.delegate.web.event.EventManager;
import com.joanzapata.iconify.IconFontDescriptor;
import com.joanzapata.iconify.Iconify;
import com.orhanobut.logger.AndroidLogAdapter;
import com.orhanobut.logger.Logger;

import java.util.ArrayList;
import java.util.HashMap;

import okhttp3.Interceptor;

/**
 * Created by Ma on 2017/11/22.
 */

public class Configurator {

    private static final HashMap<Object, Object> ORANGE_CONFIGS = new HashMap<>();
    private static final ArrayList<IconFontDescriptor> ICONS = new ArrayList<>();
    private static final ArrayList<Interceptor> INTERCEPTORS = new ArrayList<>();

    private static final Handler HANDLER = new Handler();

    public Configurator() {

        ORANGE_CONFIGS.put(ConfigKeys.CONFIG_READY, false);
        ORANGE_CONFIGS.put(ConfigKeys.HANDLER, HANDLER);
    }

    public static Configurator getInstance() {
        return Holder.INSTANCE;
    }

    public final HashMap<Object, Object> getOrangeConfigs() {
        return ORANGE_CONFIGS;
    }

    private static class Holder {
        private static final Configurator INSTANCE = new Configurator();
    }

    public final void configure() {

        initIcons();
        initLogger();
        ORANGE_CONFIGS.put(ConfigKeys.CONFIG_READY, true);
        Utils.init((Application) Orange.getApplicationContext());
    }

    public final Configurator withApiHost(String host) {
        ORANGE_CONFIGS.put(ConfigKeys.API_HOST, host);
        return this;
    }

    private void checkConfiguration() {
        final boolean isReady = (boolean) ORANGE_CONFIGS.get(ConfigKeys.CONFIG_READY);
        if (!isReady) {
            throw new RuntimeException("Configuration is not ready,call configure");
        }
    }


    @SuppressWarnings("unchecked")
    final <T> T getConfiguration(Object key) {
        checkConfiguration();
        final Object value = ORANGE_CONFIGS.get(key);
        if (value == null) {
            throw new NullPointerException(key.toString() + "IS NULL");
        }
        return (T) ORANGE_CONFIGS.get(key);
    }


    private void initIcons() {
        if (ICONS.size() > 0) {
            final Iconify.IconifyInitializer initializer = Iconify.with(ICONS.get(0));
            for (int i = 1; i < ICONS.size(); i++) {
                initializer.with(ICONS.get(i));
            }
        }
    }

    private void initLogger() {
        Logger.addLogAdapter(new AndroidLogAdapter());
    }

    public Configurator withIcon(IconFontDescriptor descriptor) {
        ICONS.add(descriptor);
        return this;
    }

    public Configurator withInterceptor(Interceptor interceptor) {
        INTERCEPTORS.add(interceptor);
        ORANGE_CONFIGS.put(ConfigKeys.INTERCEPTOR, INTERCEPTORS);
        return this;
    }

    public Configurator withInterceptors(ArrayList<Interceptor> interceptors) {
        INTERCEPTORS.addAll(interceptors);
        ORANGE_CONFIGS.put(ConfigKeys.INTERCEPTOR, INTERCEPTORS);
        return this;
    }

    public Configurator withWeChatAppId(String weChatAppId) {
        ORANGE_CONFIGS.put(ConfigKeys.WECHAT_APP_ID, weChatAppId);
        return this;
    }

    public Configurator withWeChatAppSecret(String weChatAppSecret) {
        ORANGE_CONFIGS.put(ConfigKeys.WECHAT_APP_SECRET, weChatAppSecret);
        return this;
    }

    public Configurator withActivity(Activity activity) {
        ORANGE_CONFIGS.put(ConfigKeys.ACTIVITY, activity);
        return this;
    }

    public Configurator withJavascriptInterface(@NonNull String name) {
        ORANGE_CONFIGS.put(ConfigKeys.JAVASCRIPT_INTERFACE, name);
        return this;
    }

    public Configurator withWebEvent(@NonNull String name, @NonNull Event event) {
        final EventManager manager = EventManager.getInstance();
        manager.addEvent(name, event);
        return this;
    }

    //浏览器加载的host
    public Configurator withWebHost(String host) {
        ORANGE_CONFIGS.put(ConfigKeys.WEB_HOST, host);
        return this;
    }
}
