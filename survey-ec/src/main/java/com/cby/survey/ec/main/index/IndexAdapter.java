package com.cby.survey.ec.main.index;

import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.AppCompatTextView;

import com.bumptech.glide.Glide;
import com.cby.orange.ui.recycler.ItemType;
import com.cby.orange.ui.recycler.MultipleItemEntity;
import com.cby.orange.ui.recycler.MultipleRecyclerAdapter;
import com.cby.orange.ui.recycler.MultipleViewHolder;
import com.cby.orange.ui.recycler.MultipleteFields;
import com.cby.orange.utils.log.OrangeLogger;
import com.cby.survey.ec.R;

import java.util.List;

/**
 * Created by baiyanfang on 2018/2/1.
 */

public class IndexAdapter extends MultipleRecyclerAdapter {

    protected IndexAdapter(List<MultipleItemEntity> data) {
        super(data);
        addItemType(IndexItemType.SURVEY_ITEM, R.layout.item_index_survey);
    }

    @Override
    protected void convert(MultipleViewHolder holder, MultipleItemEntity entity) {
        super.convert(holder, entity);
        switch (holder.getItemViewType()) {
            case IndexItemType.SURVEY_ITEM:

                holder.setText(R.id.tv_index_item_name, (CharSequence) entity.getField(MultipleteFields.NAME));
                holder.setText(R.id.tv_index_item__time, (CharSequence) entity.getField(IndexItemFields.CREATETIME));
                holder.setText(R.id.tv_index_item_money, (CharSequence) entity.getField(IndexItemFields.BONUS));
                holder.setText(R.id.tv_index_item_require_age, (CharSequence) entity.getField(IndexItemFields.AGE));
                holder.setText(R.id.tv_index_item_require_sex, (CharSequence) entity.getField(IndexItemFields.SEX));
                holder.setText(R.id.tv_index_item_require_location, (CharSequence) entity.getField(IndexItemFields.CITY));

                break;
            default:
                break;
        }
    }
}
