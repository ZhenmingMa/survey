package com.cby.survey.generator;


import com.cby.orange.annotations.EntryGenerator;
import com.cby.orange.wechat.templates.WXEntryTemplate;

/**
 * Created by 傅令杰 on 2017/4/22
 */

@SuppressWarnings("unused")
@EntryGenerator(
        packageName = "com.cby.survey",
        entryTemplate = WXEntryTemplate.class
)
public interface WeChatEntry {
}
