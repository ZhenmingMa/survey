package com.cby.survey.ec.main.index;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.blankj.utilcode.util.TimeUtils;
import com.cby.orange.ui.recycler.DataConverter;
import com.cby.orange.ui.recycler.ItemType;
import com.cby.orange.ui.recycler.MultipleItemEntity;
import com.cby.orange.ui.recycler.MultipleteFields;
import com.cby.orange.utils.log.OrangeLogger;

import org.w3c.dom.Entity;

import java.util.ArrayList;
import java.util.Date;

/**
 * Created by baiyanfang on 2018/2/1.
 */

public class IndexDataConvert extends DataConverter {
    @Override
    public ArrayList<MultipleItemEntity> convert() {

        final JSONArray array = JSONObject.parseObject(getmJsonData()).getJSONObject("data").getJSONArray("content");

        final int size = array.size();
        for (int i = 0; i < size; i++) {
            JSONObject data = array.getJSONObject(i);

            final int id = data.getInteger("id");
            final String name = data.getString("name");
            final double bonus = data.getDouble("bonus");
            final String age = "年龄:" + data.getString("age");
            final String city = "坐标:" + data.getString("city");
            final String recent = data.getString("recent");
            final int questions = data.getInteger("questions");
            final String count = data.getString("count");

            final Date createTime = data.getDate("createTime");
            final Date endTime = data.getDate("createTime");

            final String createTimeStr = TimeUtils.date2String(createTime);

            final String endTimeStr = TimeUtils.date2String(endTime);

            final int sex = data.getInteger("sex");
            final String sexStr;

            if (sex == 1) {
                sexStr = "性别:" + "男";
            } else {
                sexStr = "性别:" + "女";
            }

            MultipleItemEntity entity = MultipleItemEntity.builder()
                    .setField(MultipleteFields.ITEM_TYPE, IndexItemType.SURVEY_ITEM)
                    .setField(MultipleteFields.ID, id)
                    .setField(MultipleteFields.NAME, name)
                    .setField(IndexItemFields.AGE, age)
                    .setField(IndexItemFields.BONUS, bonus + "")
                    .setField(IndexItemFields.SEX, sexStr)
                    .setField(IndexItemFields.CITY, city)
                    .setField(IndexItemFields.RECENT, recent)
                    .setField(IndexItemFields.QUESTIONS, questions)
                    .setField(IndexItemFields.COUNT, count)
                    .setField(IndexItemFields.CREATETIME, createTimeStr)
                    .setField(IndexItemFields.ENDTIME, endTimeStr)
                    .build();
            ENTITYS.add(entity);
        }
        OrangeLogger.e("test", JSON.toJSONString(ENTITYS));
        return ENTITYS;
    }
}
