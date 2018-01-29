package com.cby.orange.delegate.web;

import android.webkit.JavascriptInterface;

import com.alibaba.fastjson.JSON;
import com.cby.orange.delegate.web.event.Event;
import com.cby.orange.delegate.web.event.EventManager;
import com.cby.orange.utils.log.OrangeLogger;

/**
 *
 * Created by baiyanfang on 2017/12/28.
 */
final class OrangeWebInterface {

    private final WebDelegate DELEGATE;

    private OrangeWebInterface(WebDelegate delegate) {
        this.DELEGATE = delegate;
    }

    static OrangeWebInterface create(WebDelegate delegate) {
        return new OrangeWebInterface(delegate);
    }

    @JavascriptInterface
    public String event(String params) {
        final String action = JSON.parseObject(params).getString("action");
        final Event event = EventManager.getInstance().createEvent(action);
        OrangeLogger.d("WEB_EVENT",params);
        if (event != null) {
            event.setAction(action);
            event.setDelegate(DELEGATE);
            event.setContext(DELEGATE.getContext());
            event.setUrl(DELEGATE.getUrl());
            return event.execute(params);
        }
        return null;
    }
}
