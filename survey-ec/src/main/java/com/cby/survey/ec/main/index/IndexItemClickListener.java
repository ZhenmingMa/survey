package com.cby.survey.ec.main.index;

import android.view.View;

import com.cby.orange.delegate.OrangeDelegate;
import com.cby.orange.ui.recycler.MultipleItemEntity;
import com.cby.survey.ec.main.index.survey.SurveyDelegate;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.SimpleClickListener;

/**
 * Created by baiyanfang on 2018/2/6.
 */

public class IndexItemClickListener extends SimpleClickListener {

    private final OrangeDelegate DELEGATE;

    public IndexItemClickListener(OrangeDelegate delegate) {
        DELEGATE = delegate;
    }

    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
        DELEGATE.getParentDelegate().getSupportDelegate().start(SurveyDelegate.create((MultipleItemEntity) adapter.getData().get(position)));
    }

    @Override
    public void onItemLongClick(BaseQuickAdapter adapter, View view, int position) {

    }

    @Override
    public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {

    }

    @Override
    public void onItemChildLongClick(BaseQuickAdapter adapter, View view, int position) {

    }
}
