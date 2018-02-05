package com.cby.survey.ec.main.account;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.cby.orange.delegate.OrangeDelegate;
import com.cby.orange.delegate.bottom.BottomItemDelegate;
import com.cby.survey.ec.R;

/**
 * Created by baiyanfang on 2018/2/1.
 */

public class AccountDelegate extends BottomItemDelegate {
    @Override
    public Object setLayout() {
        return R.layout.delegate_account;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {

    }
}
