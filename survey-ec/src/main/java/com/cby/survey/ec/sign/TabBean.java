package com.cby.survey.ec.sign;

import com.cby.orange.delegate.OrangeDelegate;

/**
 * Created by baiyanfang on 2018/1/31.
 */

public class TabBean {
    private String title;
    private OrangeDelegate content;

    public TabBean(String title, OrangeDelegate content) {
        this.title = title;
        this.content = content;
    }

    public String getTitle() {
        return title;
    }

    public OrangeDelegate getContent() {
        return content;
    }
}
