package com.cby.survey.ec.jsonhandler;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

/**
 * Created by baiyanfang on 2018/1/30.
 */

public class JsonHandler {

    private final JSONObject OBJ;

    private JsonHandler(String response) {
        this.OBJ = JSON.parseObject(response);
    }

    public static JsonHandler create(String response){
        JsonHandler jsonHandler = new JsonHandler(response);
        return jsonHandler;
    }


    public int getCode() {
        final int code = OBJ.getInteger("code");
        return code;
    }

    public String getMessage() {
        final String message = OBJ.getString("message");
        return message;
    }

    public JSONObject getDataObject() {
        final JSONObject data = OBJ.getJSONObject("data");
        return data;
    }

    public JSONArray getDataArray() {
        final JSONArray data = OBJ.getJSONArray("data");
        return data;
    }

}
