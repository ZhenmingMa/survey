package com.cby.survey.ec.main.index;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.cby.orange.delegate.bottom.BottomItemDelegate;
import com.cby.survey.ec.R;
import com.cby.survey.ec.R2;


import butterknife.BindView;

/**
 * Created by baiyanfang on 2018/2/1.
 */

public class IndexDelegate extends BottomItemDelegate {

    @BindView(R2.id.srl_index)
    SwipeRefreshLayout mRefreshLayout = null;

    @BindView(R2.id.rv_index)
    RecyclerView mRecyclerView = null;

    private IndexRefreshHandler mRefreshHandler;

    @Override
    public Object setLayout() {
        return R.layout.delegate_index;
    }

    @Override
    public void onLazyInitView(@Nullable Bundle savedInstanceState) {
        super.onLazyInitView(savedInstanceState);
        initSwipeRefreshLayout();
        initRecycleView();
        mRefreshHandler.firstPage();
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {
        setTitle("首页");
        mRefreshHandler = IndexRefreshHandler.create(mRefreshLayout, mRecyclerView, new IndexDataConvert(), getContext());

    }

    private void initSwipeRefreshLayout() {
        mRefreshLayout.setColorSchemeResources(android.R.color.holo_blue_bright, android.R.color.holo_red_light);
        mRefreshLayout.setProgressViewOffset(true, 120, 300);
    }

    private void initRecycleView() {
        LinearLayoutManager manager = new LinearLayoutManager(getContext());
        mRecyclerView.setLayoutManager(manager);
        mRecyclerView.addOnItemTouchListener(new IndexItemClickListener(this));
    }


}
