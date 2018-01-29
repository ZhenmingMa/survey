package com.cby.orange.net.rx;

import com.cby.orange.app.Orange;
import com.cby.orange.utils.storage.OrangePreference;

import java.io.IOException;


import io.reactivex.Observable;
import io.reactivex.functions.Consumer;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by baiyanfang on 2018/1/2.
 */

public class AddCookieInterceptor implements Interceptor {

    @Override
    public Response intercept(Chain chain) throws IOException {
        final Request.Builder builder = chain.request().newBuilder();
        Observable
                .just(OrangePreference.getCustomAppProfile("cookie"))
                .subscribe(new Consumer<String>() {
                    @Override
                    public void accept(String s) throws Exception {
                        //给原生api请求附带上webview拦截下来的Cookie
                        builder.addHeader("Cookie",s);
                    }
                });
        return chain.proceed(builder.build());
    }
}
