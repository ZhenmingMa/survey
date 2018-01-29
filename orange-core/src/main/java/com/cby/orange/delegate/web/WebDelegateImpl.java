package com.cby.orange.delegate.web;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.cby.orange.delegate.web.chromeclient.WebChromeClientImpl;
import com.cby.orange.delegate.web.client.WebViewClientImpl;
import com.cby.orange.delegate.web.route.RouteKeys;
import com.cby.orange.delegate.web.route.Router;
import com.cby.orange.utils.log.OrangeLogger;

/**
 * Created by baiyanfang on 2017/12/28.
 */

public class WebDelegateImpl extends WebDelegate {

    private IPageLoadListener mIPageLoadListener = null;

    public static WebDelegateImpl create(String url) {
        OrangeLogger.e("WebDelegateImpl_Creat",url);
        final Bundle args = new Bundle();
        args.putString(RouteKeys.URL.name(), url);
        final WebDelegateImpl delegate = new WebDelegateImpl();
        delegate.setArguments(args);
        return delegate;
    }

    public void setIPageLoadListener(IPageLoadListener iPageLoadListener) {
        this.mIPageLoadListener = iPageLoadListener;
    }

    @Override
    public Object setLayout() {
        return getmWebView();
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {
        if (getUrl() != null) {
            OrangeLogger.e("url",getUrl());
            //用原生的方式模拟web跳转并进行页面加载
            Router.getInstance().loadPage(this,getUrl());

        }
    }

    @Override
    public IWebViewInitializer setInitializer() {
        return this;
    }

    @Override
    public WebView initWebView(WebView webView) {
        return new WebViewInitializer().createWebView(webView);
    }

    @Override
    public WebViewClient initWebViewClient() {
        final WebViewClientImpl client = new WebViewClientImpl(this);
        client.setIPageLoadListener(mIPageLoadListener);
        return client;
    }

    @Override
    public WebChromeClient initWebChromeClient() {
        return new WebChromeClientImpl();
    }
}
