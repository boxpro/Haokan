package com.dev.frame.base;

import android.app.Application;

/**
 * Created by Devt on 16/6/1.
 * Email:devt@foxmail.com
 */
public class DevtApplication extends Application {
    private static final String TAG = DevtApplication.class.getSimpleName();
    private static DevtApplication mInstance;

    @Override
    public void onCreate() {
        super.onCreate();
        mInstance = this;
    }

    public static synchronized DevtApplication getInstance() {
        return mInstance;
    }





}
