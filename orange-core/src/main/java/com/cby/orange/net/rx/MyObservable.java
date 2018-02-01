package com.cby.orange.net.rx;

import android.content.Context;
import android.os.Handler;
import android.widget.Toast;

import com.cby.orange.R;
import com.cby.orange.ui.loader.OrangeLoader;
import com.cby.orange.utils.log.OrangeLogger;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * Created by baiyanfang on 2018/1/31.
 */

public abstract class MyObservable<T> implements Observer<T> {

    private Context mContext;

    public MyObservable(Context context) {
        super();
        this.mContext = context;
    }

    @Override
    public void onSubscribe(Disposable d) {

    }

    @Override
    public void onComplete() {
        OrangeLoader.stopLoading();
    }

    @Override
    public void onError(Throwable e) {
        OrangeLogger.e("net_error", e.getMessage());
        OrangeLoader.stopLoading();
        Toast.makeText(mContext, R.string.network_connettions_error, Toast.LENGTH_SHORT).show();

    }
}
