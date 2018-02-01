package com.cby.survey.ec.main;

import com.cby.orange.delegate.bottom.BaseBottomDelegate;
import com.cby.orange.delegate.bottom.BottomItemDelegate;
import com.cby.orange.delegate.bottom.BottomTabBean;
import com.cby.orange.delegate.bottom.ItemBuilder;

import java.util.LinkedHashMap;

/**
 * Created by baiyanfang on 2018/1/29.
 */

public class BottomDelegate extends BaseBottomDelegate {
    @Override
    public LinkedHashMap<BottomTabBean, BottomItemDelegate> setItems(ItemBuilder builder) {
        LinkedHashMap<BottomTabBean, BottomItemDelegate>  items = new LinkedHashMap<>();

        return items;
    }

    @Override
    public int setIndexDelegate() {
        return 0;
    }

    @Override
    public int setClickedColor() {
        return 0;
    }
}
