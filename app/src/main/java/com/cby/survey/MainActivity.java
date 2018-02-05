package com.cby.survey;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.widget.Toast;

import com.cby.orange.activites.ProxyActivity;
import com.cby.orange.app.Orange;
import com.cby.orange.delegate.OrangeDelegate;
import com.cby.orange.ui.launcher.ILauncherListener;
import com.cby.orange.ui.launcher.OnLauncherFinishTag;
import com.cby.survey.ec.launcher.LauncherDelegate;
import com.cby.survey.ec.main.BottomDelegate;
import com.cby.survey.ec.sign.ISignListener;
import com.cby.survey.ec.sign.signin.SignInDelegate;

import qiu.niorgai.StatusBarCompat;

public class MainActivity extends ProxyActivity implements ILauncherListener,ISignListener {

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //隐藏toolbar
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null){
            actionBar.hide();
        }
        Orange.getConfigurator().withActivity(this);
        StatusBarCompat.translucentStatusBar(this,true);
    }

    @Override
    public OrangeDelegate setRootDelegate() {
        return new LauncherDelegate();
    }

    @Override
    public void onLauncherFinish(OnLauncherFinishTag tag) {
        switch (tag){
            case SIGNED:
                getSupportDelegate().pop();
                getSupportDelegate().loadRootFragment(R.id.delegate_container,new BottomDelegate());
                break;
            case NOT_SIGNED:
                getSupportDelegate().startWithPop(new SignInDelegate());
                break;
            default:
                break;
        }
    }

    @Override
    public void onSignInSuccess() {
        Toast.makeText(this, "登陆成功", Toast.LENGTH_SHORT).show();
        getSupportDelegate().startWithPop(new BottomDelegate());
    }

    @Override
    public void onSignInError(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onSignUpSuccess() {

    }

    @Override
    public void onSignUpError() {

    }
}
