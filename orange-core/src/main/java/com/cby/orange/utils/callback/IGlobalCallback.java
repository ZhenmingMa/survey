package com.cby.orange.utils.callback;

import android.support.annotation.Nullable;

/**
 * Created by baiyanfang on 2018/1/9.
 */

public interface IGlobalCallback <T>{
    void executeCallback(@Nullable T args);
}
