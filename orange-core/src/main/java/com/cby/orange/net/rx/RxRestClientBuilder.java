package com.cby.orange.net.rx;

import android.content.Context;

import com.cby.orange.ui.loader.LoaderStyle;

import java.io.File;
import java.util.WeakHashMap;

import okhttp3.MediaType;
import okhttp3.RequestBody;

/**
 * Created by Ma on 2017/11/28.
 */

public class RxRestClientBuilder {
    private String mUrl = null;
    private final WeakHashMap<String,Object> PARAMS = new WeakHashMap<>();
    private RequestBody mBody = null;
    private File mFile = null;
    private Context mContext = null;
    private LoaderStyle mloaderStyle = null;

    RxRestClientBuilder() {

    }

    public final RxRestClientBuilder url(String url){
        this.mUrl = url;
        return this;
    }

    public final RxRestClientBuilder params(WeakHashMap<String,Object> params){
        PARAMS.putAll(params);
        return this;
    }

    public final RxRestClientBuilder params(String key, Object value){
        PARAMS.put(key,value);
        return this;
    }

    public final RxRestClientBuilder file(File file){
        this.mFile = file;
        return this;
    }

    public final RxRestClientBuilder file(String file){
        this.mFile = new File(file);
        return this;
    }

    public final RxRestClientBuilder row(String row){
        this.mBody = RequestBody.create(MediaType.parse("application/json;charset=UTF-8"),row);
        return this;
    }


    public final RxRestClientBuilder loader(Context context, LoaderStyle loaderStyle){
        this.mContext = context;
        this.mloaderStyle = loaderStyle;
        return this;
    }

    public final RxRestClientBuilder loader(Context context){
        this.mContext = context;
        this.mloaderStyle = LoaderStyle.BallGridPulseIndicator;
        return this;
}
    public final RxRestClient build(){
        return new RxRestClient(mUrl,PARAMS, mBody,mFile,
                                mloaderStyle,mContext);
    }

}
