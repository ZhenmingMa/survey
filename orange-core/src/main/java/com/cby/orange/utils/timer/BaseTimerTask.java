package com.cby.orange.utils.timer;

import java.util.TimerTask;

/**
 * Created by fei on 2017/7/31.
 */

public class BaseTimerTask extends TimerTask {

    private ITimerListener mITimerListener=null;

    public BaseTimerTask(ITimerListener iTimerListener) {
        this.mITimerListener = iTimerListener;
    }

    @Override
    public void run() {
        if(mITimerListener!=null){
            mITimerListener.onTimer();
        }
    }
}
