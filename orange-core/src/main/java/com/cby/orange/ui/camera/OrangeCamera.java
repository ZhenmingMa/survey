package com.cby.orange.ui.camera;

import android.net.Uri;

import com.cby.orange.delegate.PermissionCheckerDelegate;
import com.cby.orange.utils.FileUtil;

/**
 * 调用类
 * Created by baiyanfang on 2018/1/9.
 */

public class OrangeCamera {

    public static Uri createCropFile(){
        return Uri.parse
                (FileUtil.createFile("crop_image",
                        FileUtil.getFileNameByTime("IMG","jpg")).getPath());
    }

    public static void start(PermissionCheckerDelegate delegate){
        new CameraHandler(delegate).beginCameraDialog();
    }
}
