package com.cby.orange.net.interceptors;

import android.support.annotation.RawRes;

import com.cby.orange.utils.FileUtil;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.Protocol;
import okhttp3.Response;
import okhttp3.ResponseBody;

/**
 * Created by Ma on 2017/11/29.
 */

public class DebugInterceptor extends BaseInterceptor {

    private final String DEBUG_URL;
    private final int DEBUG_ROW_ID;

    public DebugInterceptor(String debug_url, int debug_row_id) {
        this.DEBUG_URL = debug_url;
        this.DEBUG_ROW_ID = debug_row_id;
    }

    private Response getReponse(Chain chain,String json){
        return new Response.Builder()
                .code(200)
                .addHeader("Content-Type","application/json")
                .body(ResponseBody.create(MediaType.parse("application/json"),json))
                .message("OK")
                .request(chain.request())
                .protocol(Protocol.HTTP_1_1)
                .build();
    }

    private Response debugResponse(Chain chain,@RawRes int rawId){
        final String json = FileUtil.getRawFile(rawId);
        return getReponse(chain,json);

    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        final String url = chain.request().url().toString();
        if (url.contains(DEBUG_URL)){
            return debugResponse(chain,DEBUG_ROW_ID);
        }
        return chain.proceed(chain.request());
    }
}
