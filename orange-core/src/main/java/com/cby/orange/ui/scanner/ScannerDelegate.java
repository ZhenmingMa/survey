package com.cby.orange.ui.scanner;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.cby.orange.delegate.OrangeDelegate;
import com.cby.orange.ui.camera.RequestCodes;
import com.cby.orange.utils.callback.CallbackManager;
import com.cby.orange.utils.callback.CallbackType;
import com.cby.orange.utils.callback.IGlobalCallback;

import me.dm7.barcodescanner.zbar.Result;
import me.dm7.barcodescanner.zbar.ZBarScannerView;

/**
 * Created by baiyanfang on 2018/1/17.
 */

public class ScannerDelegate extends OrangeDelegate implements ZBarScannerView.ResultHandler {

    private ScanView mScanView = null;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (mScanView == null) {
            mScanView = new ScanView(getContext());
        }
        mScanView.setAutoFocus(true);
        mScanView.setResultHandler(this);
    }

    @Override
    public Object setLayout() {
        return mScanView;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {

    }

    @Override
    public void onResume() {
        super.onResume();
        if (mScanView != null) {
            mScanView.startCamera();
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        if (mScanView != null) {
            mScanView.stopCameraPreview();
            mScanView.stopCamera();
        }
    }

    @Override
    public void handleResult(Result result) {
        if (result!=null){
            final String qrCode = result.getContents();
            final IGlobalCallback<String> callback = CallbackManager
                    .getInstance().getCallback(CallbackType.ON_SCAN);
            if (callback != null){
                callback.executeCallback(qrCode);
            }
        }
       getSupportDelegate().pop();
    }


}
