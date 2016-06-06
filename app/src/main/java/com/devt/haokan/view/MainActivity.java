package com.devt.haokan.view;

import android.app.Fragment;
import android.support.v7.app.ActionBar;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;


import com.dev.frame.base.BaseActivity;
import com.dev.frame.base.LocalCallBackInterface;
import com.dev.frame.base.LocalDataInteface;
import com.devt.haokan.R;
import com.devt.haokan.view.adapter.ContentAdapter;


import java.util.HashMap;

import butterknife.BindView;

public class MainActivity extends BaseActivity implements LocalCallBackInterface,LocalDataInteface {
    private static  final  String URl = "http://www.weather.com.cn/data/cityinfo/101010100.html";
    private static final String urlImg = "http://c.hiphotos.baidu.com/image/h%3D200/sign=7b991b465eee3d6d3dc680cb73176d41/96dda144ad3459829813ed730bf431adcaef84b1.jpg";
    private static final int  GETMESSAGE = 1;
    private ContentAdapter contentAdapter;




    ActionBar actionBar;
//    @BindView(R.id.recycler_view_show)
//    RecyclerView recyclerView;

    @BindView(R.id.main_content)
    FrameLayout mainConent;
    @BindView(R.id.action_bar1)
    RelativeLayout relativeLayout_home;
    @BindView(R.id.action_bar2)
    RelativeLayout relativeLayout_recommend;
    @BindView(R.id.action_bar3)
    RelativeLayout relativeLayout_collect;
    @BindView(R.id.action_bar4)
    RelativeLayout relativeLayout_me;

    @Override
    protected int initLayout() {
        return R.layout.activity_main;
    }

    @Override
    protected void initExtendData(){
       requestTag = MainActivity.class.getSimpleName();
       fragmentManager = getFragmentManager();
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
        //configRecycleView();
        configBottomTab();
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
//    @TargetApi(Build.VERSION_CODES.M)
//    private void configRecycleView(){
//        if (recyclerView ==null)return;
//        recyclerView.setLayoutManager(new LinearLayoutManager(this));
//        contentAdapter = new ContentAdapter(mContext, MockData.getDatas());
//        recyclerView.setAdapter(contentAdapter);
//        recyclerView.setItemAnimator(new DefaultItemAnimator());
//        recyclerView.setOnTouchListener(new View.OnTouchListener() {
//            @Override
//            public boolean onTouch(View v, MotionEvent event) {
//                if (event.getPointerCount()==2){
//                    recyclerView.scrollToPosition(0);
//                }
//                return false;
//            }
//        });
//
//    }

    /**
     * 配置底部按钮
     */
    private void configBottomTab(){
        relativeLayout_home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                switchFragment(new BlankFragmentA() ,R.id.main_content);
            }
        });
        relativeLayout_recommend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BlankFragmentB fragment = new BlankFragmentB();
                switchFragment(fragment,R.id.main_content);
            }
        });
        relativeLayout_collect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BlankFragmentC fragment = new BlankFragmentC();
                switchFragment(fragment,R.id.main_content);
            }
        });
        relativeLayout_me.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BlankFragmentD fragment = new BlankFragmentD();
                switchFragment(fragment,R.id.main_content);
            }
        });
    }


}
