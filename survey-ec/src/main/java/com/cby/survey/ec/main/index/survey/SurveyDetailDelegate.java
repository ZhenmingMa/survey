package com.cby.survey.ec.main.index.survey;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.cby.orange.delegate.OrangeDelegate;
import com.cby.survey.ec.R;

/**
 * Created by baiyanfang on 2018/2/8.
 */

public class SurveyDetailDelegate extends OrangeDelegate {


    RecyclerView mRecyclerView = null;


    @Override
    public Object setLayout() {
        return R.layout.delegate_survey_detail;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {

    }
}
