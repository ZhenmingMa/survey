package com.cby.survey.generator;

import com.cby.orange.annotations.AppRegisterGenerator;
import com.cby.orange.wechat.templates.AppRegisterTemplate;

/**
 * Created by 傅令杰 on 2017/4/22
 */
@SuppressWarnings("unused")
@AppRegisterGenerator(
        packageName = "com.cby.survey",
        registerTemplate = AppRegisterTemplate.class
)
public interface AppRegister {
}
