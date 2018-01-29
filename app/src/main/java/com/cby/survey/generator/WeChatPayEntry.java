package com.cby.survey.generator;


import com.cby.orange.annotations.PayEntryGenerator;
import com.cby.orange.wechat.templates.WXPayEntryTemplate;

/**
 * Created by 傅令杰 on 2017/4/22
 */
@SuppressWarnings("unused")
@PayEntryGenerator(
        packageName = "com.cby.survey",
        payEntryTemplate = WXPayEntryTemplate.class
)
public interface WeChatPayEntry {
}
