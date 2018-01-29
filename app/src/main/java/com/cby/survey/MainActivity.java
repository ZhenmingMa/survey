package com.cby.survey;

import android.os.Bundle;

import com.cby.orange.activites.ProxyActivity;
import com.cby.orange.delegate.OrangeDelegate;

public class MainActivity extends ProxyActivity {


    @Override
    public OrangeDelegate setRootDelegate() {
        return new MainDelegate();
    }

}
