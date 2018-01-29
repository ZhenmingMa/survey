package com.cby.orange.delegate.web.client;

import android.graphics.Bitmap;
import android.os.Handler;
import android.webkit.CookieManager;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.cby.orange.app.ConfigKeys;
import com.cby.orange.app.Orange;
import com.cby.orange.delegate.web.IPageLoadListener;
import com.cby.orange.delegate.web.WebDelegate;
import com.cby.orange.delegate.web.route.Router;
import com.cby.orange.ui.loader.OrangeLoader;
import com.cby.orange.utils.log.OrangeLogger;
import com.cby.orange.utils.storage.OrangePreference;


/**
 * Created by baiyanfang on 2017/12/28.
 */

public class WebViewClientImpl extends WebViewClient {

    private final WebDelegate DELEGATE;
    private IPageLoadListener mIPageLoadListener = null;
    private static final Handler HANDLER = Orange.getHandler();

    public void setIPageLoadListener(IPageLoadListener iPageLoadListener) {
        this.mIPageLoadListener = iPageLoadListener;
    }

    public WebViewClientImpl(WebDelegate delegate) {
        this.DELEGATE = delegate;
    }


    @Override
    public boolean shouldOverrideUrlLoading(WebView view, String url) {
        OrangeLogger.d("shouldOverrideUrlLoading", url);
        return Router.getInstance().HandleWebUrl(DELEGATE, url);
    }

    //获取浏览器cookie
    private void syncCookie() {
        final CookieManager cookieManager = CookieManager.getInstance();
        /*
        注意这里和api请求的Cookie是不一样的，这个在网页中不可见
         */
        final String webHost = Orange.getConfiguration(ConfigKeys.WEB_HOST);
        if (webHost != null) {
           if (cookieManager.hasCookies()){
               final String cookieStr = cookieManager.getCookie(webHost);
               if (cookieStr != null && !cookieStr.equals("")) {
                   OrangePreference.addCustomAppProfile("cookie", cookieStr);
               }
           }
        }
    }

    @Override
    public void onPageStarted(WebView view, String url, Bitmap favicon) {
        super.onPageStarted(view, url, favicon);
        if (mIPageLoadListener != null) {
            mIPageLoadListener.onLoadStart();
        }
        OrangeLoader.showLoading(view.getContext());
    }

    @Override
    public void onPageFinished(WebView view, String url) {
        super.onPageFinished(view, url);
        syncCookie();
        if (mIPageLoadListener != null) {
            mIPageLoadListener.onLoadEnd();
        }
        HANDLER.postDelayed(new Runnable() {
            @Override
            public void run() {
                OrangeLoader.stopLoading();
            }
        }, 1000);

    }
}
