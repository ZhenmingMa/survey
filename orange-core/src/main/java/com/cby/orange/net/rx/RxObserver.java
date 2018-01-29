package com.cby.orange.net.rx;

import android.os.Handler;

import com.cby.orange.ui.loader.LoaderStyle;
import com.cby.orange.ui.loader.OrangeLoader;
import com.cby.orange.utils.log.OrangeLogger;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * Created by baiyanfang on 2017/12/14.
 */

public class RxObserver implements Observer<String> {

    private static final Handler HANDLER = new Handler();

    private final LoaderStyle LOADER_STYLE;

    public RxObserver(LoaderStyle loaderStyle) {
        this.LOADER_STYLE = loaderStyle;
    }

    @Override
    public void onSubscribe(Disposable disposable) {
        if (disposable.isDisposed()){
            OrangeLogger.e("","isDisposed");
        }else {
            OrangeLogger.e("","Disposed");
        }
    }

    @Override
    public void onNext(String s) {
        OrangeLogger.e("","next");
        stopLoading();
    }

    @Override
    public void onError(Throwable throwable) {
        OrangeLogger.e("","error");
        stopLoading();
    }

    @Override
    public void onComplete() {
        OrangeLogger.e("","complete");
        stopLoading();
    }


    private void stopLoading(){
        if (LOADER_STYLE!=null){
            HANDLER.postDelayed(new Runnable() {
                @Override
                public void run() {
                    OrangeLoader.stopLoading();
                }
            },500);
        }
    }
}
