package com.cby.survey.ec.main.index.survey;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.AppCompatTextView;
import android.text.format.DateUtils;
import android.view.View;

import com.alibaba.fastjson.JSON;
import com.cby.orange.delegate.OrangeDelegate;
import com.cby.orange.ui.recycler.MultipleItemEntity;
import com.cby.orange.ui.recycler.MultipleteFields;
import com.cby.orange.utils.log.OrangeLogger;
import com.cby.survey.ec.R;
import com.cby.survey.ec.R2;
import com.cby.survey.ec.main.index.IndexItemFields;
import com.joanzapata.iconify.widget.IconTextView;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by baiyanfang on 2018/2/6.
 */

public class SurveyDelegate extends OrangeDelegate {

    @BindView(R2.id.survey_name)
    AppCompatTextView name = null;

    @BindView(R2.id.surver_time)
    AppCompatTextView time = null;

    @BindView(R2.id.demand_sex1)
    AppCompatTextView sex = null;

    @BindView(R2.id.demand_age1)
    AppCompatTextView age = null;

    @BindView(R2.id.demand_location1)
    AppCompatTextView location = null;

    @BindView(R2.id.surver_number)
    AppCompatTextView number = null;

    @BindView(R2.id.survey_reminder)
    AppCompatTextView reminder = null;

    @BindView(R2.id.survey_invite)
    AppCompatTextView invite = null;

    @BindView(R2.id.icon_survey_back)
    IconTextView back = null;

    @BindView(R2.id.survey_state)
    AppCompatTextView state = null;

    @BindView(R2.id.survey_endTime)
    AppCompatTextView endTime = null;

    @OnClick(R2.id.survey_bt)
    void goSurveyDetail(){
        getSupportDelegate().startWithPop(new SurveyDetailDelegate());
    }

    private MultipleItemEntity entity;

    public SurveyDelegate() {
    }






    public static SurveyDelegate create(MultipleItemEntity entity) {
        SurveyDelegate delegate = new SurveyDelegate();
        delegate.entity = entity;
        return delegate;
    }

    @Override
    public Object setLayout() {
        return R.layout.delegate_survey;
    }

    @Override
    public void onLazyInitView(@Nullable Bundle savedInstanceState) {
        super.onLazyInitView(savedInstanceState);

        OrangeLogger.e("test", JSON.toJSONString(entity));
        final String nameStr = entity.getField(MultipleteFields.NAME);
        final String timeStr = "发布时间:" + entity.getField(IndexItemFields.CREATETIME);
        final String age_Str = "要求:" + entity.getField(IndexItemFields.AGE)+",";
        final String sex_Str = entity.getField(IndexItemFields.SEX)+",";
        final String location_Str = entity.getField(IndexItemFields.CITY);
        final String number_Str = "题量:" + entity.getField(IndexItemFields.QUESTIONS) + "道";
        final String reminder_Str = "剩余:" + entity.getField(IndexItemFields.COUNT) + "席位";
        final String endTime_Str = "截止时间:" + entity.getField(IndexItemFields.ENDTIME);

        name.setText(nameStr);
        time.setText(timeStr);
        age.setText(age_Str);
        sex.setText(sex_Str);
        location.setText(location_Str);
        number.setText(number_Str);
        reminder.setText(reminder_Str);
        state.setText("状态:" + "运行中");
        endTime.setText(endTime_Str);
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {

    }


}
