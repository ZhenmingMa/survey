package com.cby.orange.net.download;

import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;

import com.cby.orange.app.Orange;
import com.cby.orange.net.callback.IRequest;
import com.cby.orange.net.callback.ISuccess;
import com.cby.orange.utils.FileUtil;

import java.io.File;
import java.io.InputStream;

import okhttp3.ResponseBody;

/**
 *
 * Created by Ma on 2017/11/29.
 */

public class SaveFileTask extends AsyncTask<Object,Void,File> {

    private final IRequest IREQUEST;
    private final ISuccess SUCCESS;

    public SaveFileTask(IRequest IREQUEST, ISuccess SUCCESS) {
        this.IREQUEST = IREQUEST;
        this.SUCCESS = SUCCESS;
    }

    @Override
    protected File doInBackground(Object... params) {

        String downloadDir = (String) params[0];
        String extension = (String) params[1];
        final ResponseBody body = (ResponseBody) params[2];
        final String name = (String) params[3];
        final InputStream is = body.byteStream();
        if (downloadDir == null||downloadDir.isEmpty()){
            downloadDir = "down_loads";
        }
        if (extension == null || extension.isEmpty()){
            extension = "";
        }
        if (name == null){
            return FileUtil.writeToDisk(is,downloadDir,extension.toUpperCase(),extension);
        }else {
            return FileUtil.writeToDisk(is,downloadDir,name);
        }
    }

    @Override
    protected void onPostExecute(File file) {
        super.onPostExecute(file);
        if (SUCCESS != null){
            SUCCESS.onSuccess(file.getPath());
        }
        if (IREQUEST !=null){
            IREQUEST.onRequestEnd();
        }
        autoInstallAPK(file);
    }

    private void autoInstallAPK(File file){
        if (FileUtil.getExtension(file.getPath()).equals("apk")){
            final Intent install = new Intent();
            install.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            install.setAction(Intent.ACTION_VIEW);
            install.setDataAndType(Uri.fromFile(file),"application/vnd.android.package-archive");
            Orange.getApplicationContext().startActivity(install);

        }
    }

}
