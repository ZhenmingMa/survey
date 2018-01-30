package com.cby.survey.ec.launcher;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.AdapterView;

import com.bigkoo.convenientbanner.ConvenientBanner;
import com.bigkoo.convenientbanner.listener.OnItemClickListener;
import com.cby.orange.app.AccountManager;
import com.cby.orange.app.IUserchecker;
import com.cby.orange.delegate.OrangeDelegate;
import com.cby.orange.ui.launcher.ILauncherListener;
import com.cby.orange.ui.launcher.LauncherHolderCreator;
import com.cby.orange.ui.launcher.OnLauncherFinishTag;
import com.cby.orange.ui.launcher.ScrollLauncherTag;
import com.cby.orange.utils.storage.OrangePreference;
import com.cby.survey.ec.R;

import java.util.ArrayList;

/**
 * Created by baiyanfang on 2018/1/30.
 */

public class LauncherScrollDelegate extends OrangeDelegate implements OnItemClickListener{
    private ConvenientBanner<Integer> convenientBanner = null;
    private static final ArrayList<Integer> INTEGERS = new ArrayList<>();

    private ILauncherListener mILauncherListener;

    private void initBanner(){
        INTEGERS.add(R.drawable.launcher01);
        INTEGERS.add(R.drawable.launcher02);
        INTEGERS.add(R.drawable.launcher03);
        convenientBanner.setPages( new LauncherHolderCreator(),INTEGERS)
                .setOnItemClickListener(this)
                .setCanLoop(false);

    }
    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        if (activity instanceof ILauncherListener) {
            this.mILauncherListener = (ILauncherListener) activity;
        }
    }
    @Override
    public Object setLayout() {
        convenientBanner = new ConvenientBanner<>(getContext());
        return convenientBanner;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {
        initBanner();
    }

    @Override
    public void onItemClick(int i) {
        //如果点击的是最后一个
        if (i == INTEGERS.size()-1){
            OrangePreference.setAppFlag(ScrollLauncherTag.HAS_FIRST_LAUNCHER_APP.name(),true);
            //检查用户是否登陆了app
            AccountManager.checkAccount(new IUserchecker() {
                @Override
                public void onSign() {
                    if (mILauncherListener != null){
                        mILauncherListener.onLauncherFinish(OnLauncherFinishTag.SIGNED);
                    }
                }

                @Override
                public void onNotSign() {
                    if (mILauncherListener != null){
                        mILauncherListener.onLauncherFinish(OnLauncherFinishTag.NOT_SIGNED);
                    }
                }
            });
        }
    }
}
