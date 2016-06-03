package com.dev.frame.base;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;


import com.dev.frame.http.Core;
import com.dev.frame.http.HttpCallBack;
import com.dev.frame.http.HttpMethod;

import java.util.HashMap;

import butterknife.ButterKnife;


/**
 * Created by Devt on 16/6/1.
 * Email:devt@foxmail.com
 */
public abstract class BaseActivity extends AppCompatActivity {
    private static final String TAG = BaseActivity.class.getSimpleName();
    private static final  int  SUCCESS = 1; //成功
    private static final  int  FAILTURE = 0; //失败
    private static final  String STATUS_CODE ="STATUS_CODE";
    private static final  String RESULT = "RESULT";
    protected Context applicationContext;
    protected Context mContext ;
    protected static  String requestTag ="defaut";
    protected static LocalCallBackInterface localCallBackInterface;
    protected static LocalDataInteface localDataInteface;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContext();
        setContentView(initLayout());
        setButterknife();
        initView();
        initEvent();
        initExtendData();
        initExtendInterface();

    }

    protected abstract int  initLayout();
    protected abstract void initView();
    protected abstract void initEvent();
    protected abstract void initExtendData();
    protected abstract void initExtendInterface();

    protected final void setInterface(LocalCallBackInterface localCallBack,LocalDataInteface localData){
        localCallBackInterface = localCallBack;
        localDataInteface = localData;
    }

    protected void request(int bizCode, String url, HttpMethod method){
        HashMap<String,String> parameters = localDataInteface.setRequestDatas(bizCode);
        final Bundle bundle = new Bundle();
        bundle.putInt("bizCode",bizCode);
        Core.getInstance(applicationContext).request(method.ordinal(), url, parameters, new HttpCallBack() {
            @Override
            public void onSuccess(String t) {
                bundle.putInt(STATUS_CODE,SUCCESS);
                bundle.putString(RESULT,t);
                setMessage(bundle);
            }
            @Override
            public void onFailure(int errorNo, String strMsg) {
                bundle.putInt(STATUS_CODE,FAILTURE);
                bundle.putString(RESULT,strMsg);
                setMessage(bundle);
            }

        },requestTag);

    }


    private void setMessage(Bundle data){
        Message message = handler.obtainMessage();
        message.setData(data);
        handler.sendMessage(message);
    }

    private static Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            int bizCode =  msg.getData().getInt("bizCode");
            int statusCode = msg.getData().getInt(STATUS_CODE);

            if (SUCCESS == statusCode){
                localCallBackInterface.success(bizCode,msg.getData().getString(RESULT));
            }else{
                localCallBackInterface.error(bizCode,msg.getData().getString(RESULT));
            }
        }
    };


    private void setContext(){
        applicationContext = getApplicationContext();
        mContext = this;

    }

    private void setButterknife(){
       ButterKnife.bind(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        handler.removeCallbacksAndMessages(null);
        Core.getInstance(applicationContext).cancelPendingRequests(requestTag);
    }
}
