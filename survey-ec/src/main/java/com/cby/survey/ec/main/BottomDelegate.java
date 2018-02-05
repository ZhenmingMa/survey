package com.cby.survey.ec.main;

import android.support.v4.content.ContextCompat;

import com.cby.orange.delegate.bottom.BaseBottomDelegate;
import com.cby.orange.delegate.bottom.BottomItemDelegate;
import com.cby.orange.delegate.bottom.BottomTabBean;
import com.cby.orange.delegate.bottom.ItemBuilder;
import com.cby.survey.ec.R;
import com.cby.survey.ec.main.account.AccountDelegate;
import com.cby.survey.ec.main.index.IndexDelegate;
import com.cby.survey.ec.main.personal.PersonalDelegate;
import com.cby.survey.ec.main.shop.ShopDelegate;

import java.util.LinkedHashMap;

/**
 * Created by baiyanfang on 2018/1/29.
 */

public class BottomDelegate extends BaseBottomDelegate {
    @Override
    public LinkedHashMap<BottomTabBean, BottomItemDelegate> setItems(ItemBuilder builder) {
        LinkedHashMap<BottomTabBean, BottomItemDelegate>  items = new LinkedHashMap<>();
        items.put(new BottomTabBean("{fa-home}","我的"),new IndexDelegate());
        items.put(new BottomTabBean("{fa-rmb}","我的账户"),new AccountDelegate());
        items.put(new BottomTabBean("{fa-shopping-basket}","积分商城"),new ShopDelegate());
        items.put(new BottomTabBean("{fa-user}","个人中心"),new PersonalDelegate());
        return items;
    }

    @Override
    public int setIndexDelegate() {
        return 0;
    }

    @Override
    public int setClickedColor() {
        return ContextCompat.getColor(getContext(), R.color.app_main);
    }
}
