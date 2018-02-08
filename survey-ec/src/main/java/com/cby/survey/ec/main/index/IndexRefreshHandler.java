package com.cby.survey.ec.main.index;

import android.content.Context;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.cby.orange.app.Orange;
import com.cby.orange.net.RestClient;
import com.cby.orange.net.callback.IError;
import com.cby.orange.net.callback.IFailure;
import com.cby.orange.net.callback.ISuccess;
import com.cby.orange.net.rx.MyObservable;
import com.cby.orange.net.rx.RxRestClient;
import com.cby.orange.ui.recycler.DataConverter;
import com.cby.orange.ui.recycler.MultipleRecyclerAdapter;
import com.cby.orange.ui.refresh.PagingBean;
import com.cby.orange.utils.log.OrangeLogger;
import com.cby.survey.ec.R;
import com.chad.library.adapter.base.BaseQuickAdapter;

import org.greenrobot.greendao.annotation.Index;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by baiyanfang on 2018/2/2.
 */

public class IndexRefreshHandler implements
        SwipeRefreshLayout.OnRefreshListener,
        BaseQuickAdapter.RequestLoadMoreListener {
    private final SwipeRefreshLayout REFRESH_LAYOUT;
    private final PagingBean BEAN;
    private final RecyclerView RECYCLERVIEW;
    private IndexAdapter mAdapter = null;
    private final DataConverter CONVERTER;
    private final Context CONTEXT;

    private final String URL = "getAllSurvey";

    public IndexRefreshHandler(SwipeRefreshLayout refresh_layout,
                               PagingBean bean,
                               RecyclerView recyclerview,
                               DataConverter converter, Context context) {
        this.REFRESH_LAYOUT = refresh_layout;
        this.BEAN = bean;
        this.RECYCLERVIEW = recyclerview;
        this.CONVERTER = converter;
        CONTEXT = context;
        REFRESH_LAYOUT.setOnRefreshListener(this);
    }

    public static IndexRefreshHandler create(SwipeRefreshLayout refresh_layout,
                                             RecyclerView recyclerview,
                                             DataConverter converter,
                                             Context context) {
        return new IndexRefreshHandler(refresh_layout, new PagingBean(), recyclerview, converter, context);
    }

    private void refresh() {
        REFRESH_LAYOUT.setRefreshing(true);
        BEAN.setTotal(0)
                .setPageSize(0)
                .setPageIndex(1)
                .setCurrentCount(0)
                .setDelayed(0);

        RxRestClient.builder()
                .url(URL)
                .params("page", 1)
                .build()
                .get()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new MyObservable<String>(CONTEXT) {
                    @Override
                    public void onNext(String s) {
                        OrangeLogger.e("test", s);
                        final JSONObject jsonObject = JSON.parseObject(s).getJSONObject("data");
                        BEAN.setTotal(jsonObject.getInteger("totalElements"))
                                .setPageSize(jsonObject.getInteger("size"));

                        //设置adapter
                        mAdapter.setNewData(new IndexDataConvert().setJsonData(s).convert());
                        BEAN.addIndex();
                        REFRESH_LAYOUT.setRefreshing(false);
                    }
                });

    }

    public void firstPage() {
        BEAN.setDelayed(1000);
        REFRESH_LAYOUT.setRefreshing(true);
        RxRestClient.builder()
                .url(URL)
                .params("page", 1)
                .build()
                .get()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new MyObservable<String>(CONTEXT) {
                    @Override
                    public void onNext(String s) {
                        final JSONObject jsonObject = JSON.parseObject(s).getJSONObject("data");
                        BEAN.setTotal(jsonObject.getInteger("totalElements"))
                                .setPageSize(jsonObject.getInteger("size"));
                        //设置adapter
                        OrangeLogger.e("",s);
                        mAdapter = new IndexAdapter(new IndexDataConvert().setJsonData(s).convert());
                        mAdapter.setOnLoadMoreListener(IndexRefreshHandler.this, RECYCLERVIEW);

                        View view = LayoutInflater.from(CONTEXT).inflate(R.layout.item_index_top, (ViewGroup) RECYCLERVIEW.getParent(), false);
                        mAdapter.addHeaderView(view, 0);
                        RECYCLERVIEW.setAdapter(mAdapter);
                        BEAN.addIndex();
                        REFRESH_LAYOUT.setRefreshing(false);
                    }
                });


    }

    private void paging() {
        final int pageSize = BEAN.getPageSize();
        final int currentCount = BEAN.getCurrentCount();
        final int total = BEAN.getTotal();
        final int index = BEAN.getPageIndex();

        if (mAdapter.getData().size() < pageSize || currentCount >= total) {
            mAdapter.loadMoreEnd();
        } else {
            RestClient.builder()
                    .url(URL)
                    .params("page", index)
                    .success(new ISuccess() {
                        @Override
                        public void onSuccess(String response) {
                            mAdapter.addData(new IndexDataConvert().setJsonData(response).convert());
                            //累加数量
                            BEAN.setCurrentCount(mAdapter.getData().size());
                            mAdapter.loadMoreComplete();
                            BEAN.addIndex();
                        }
                    })
                    .build()
                    .get();
        }
    }

    @Override
    public void onRefresh() {
        refresh();
    }

    @Override
    public void onLoadMoreRequested() {
        paging();
    }
}
