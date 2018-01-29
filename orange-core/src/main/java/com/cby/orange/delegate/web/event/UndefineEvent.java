package com.cby.orange.delegate.web.event;

import android.webkit.WebView;
import android.widget.Toast;

import com.cby.orange.utils.log.OrangeLogger;

/**
 * Created by baiyanfang on 2017/12/29.
 */

public class UndefineEvent extends Event {

    @Override
    public String execute(String params) {
        OrangeLogger.e("undefineEvent", params);

        return null;
    }
}
