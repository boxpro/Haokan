package com.devt.haokan.view.view;

import android.annotation.TargetApi;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;


import com.dev.frame.base.BaseActivity;
import com.dev.frame.base.LocalCallBackInterface;
import com.dev.frame.base.LocalDataInteface;
import com.devt.haokan.R;
import com.devt.haokan.mock.MockData;
import com.devt.haokan.view.adapter.ContentAdapter;


import java.util.HashMap;

import butterknife.BindView;

public class MainActivity extends BaseActivity implements LocalCallBackInterface,LocalDataInteface {
    private static  final  String URl = "http://www.weather.com.cn/data/cityinfo/101010100.html";
    private static final String urlImg = "http://c.hiphotos.baidu.com/image/h%3D200/sign=7b991b465eee3d6d3dc680cb73176d41/96dda144ad3459829813ed730bf431adcaef84b1.jpg";
    private static final int  GETMESSAGE = 1;
    private ContentAdapter contentAdapter;
    ActionBar actionBar;

    @BindView(R.id.recycler_view_show)
    RecyclerView recyclerView;

    @Override
    protected int initLayout() {
        return R.layout.activity_main;
    }

    @Override
    protected void initExtendData(){
       requestTag = MainActivity.class.getSimpleName();
    }

    @Override
    protected void initExtendInterface() {
        setInterface(this,this);
    }

    @Override
    protected void initView() {
        actionBar = getSupportActionBar();
       // actionBar.hide();
//        actionBar.setTitle("图片");
//        actionBar.setSubtitle("超级图片");
//        actionBar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#33000000")));
//        actionBar.setSplitBackgroundDrawable(new ColorDrawable(Color.parseColor("#33000000")));

    }

    @Override
    protected void initEvent() {
        configRecycleView();
    }


    @Override
    public void error(int bizCode, String errorMessage) {
        switch (bizCode){
            case GETMESSAGE:
                Log.d("result",errorMessage);
                break;
        }

    }

    @Override
    public void success(int bizCode, String responseMessage) {
        switch (bizCode) {
            case GETMESSAGE:

                break;
            }


    }

    @Override
    public HashMap<String, String> setRequestDatas(int bizCode) {
        switch (bizCode){
            case GETMESSAGE:
            break;
        }
        return null;
    }

    //相关的业务
    @TargetApi(Build.VERSION_CODES.M)
    private void configRecycleView(){
        if (recyclerView ==null)return;
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        contentAdapter = new ContentAdapter(mContext, MockData.getDatas());
        recyclerView.setAdapter(contentAdapter);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getPointerCount()==2){
                    recyclerView.scrollToPosition(0);
                }
                return false;
            }
        });

    }


}
