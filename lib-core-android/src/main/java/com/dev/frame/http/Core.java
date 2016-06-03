package com.dev.frame.http;

import android.content.Context;
import android.text.TextUtils;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.dev.frame.util.DevtUtil;


import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Devt on 16/6/1.
 * Email:devt@foxmail.com
 */
public final class Core {
    private static final String TAG = Core.class.getSimpleName();
    private static RequestQueue mRequestQueue;
    private int mStatusCode;
    private Core(){}

    private static class CoreHolder{
        public static Core instance = new Core();
    }

    public static RequestQueue getRequestQueue(Context context) {
        if (mRequestQueue == null) {
            mRequestQueue = Volley.newRequestQueue(context);
        }
        return mRequestQueue;
    }

    public static Core getInstance(Context context){
        mRequestQueue = getRequestQueue(context);
        return CoreHolder.instance;
    }
    /**
     * String 请求
     * @param method
     * @param url
     * @param requestData
     * @param callBack
     */
    public void request(final int method, final String url, final HashMap<String,String > requestData, final HttpCallBack callBack, final String requstTag){
        StringRequest  stringRequest = new StringRequest(method, url,
              new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    callBack.onSuccess(DevtUtil.fixEncoding(response));
                }
              },
              new Response.ErrorListener(){
                    @Override
                    public void onErrorResponse(VolleyError error){
                       callBack.onFailure(mStatusCode,DevtUtil.fixEncoding(error.getMessage()));
                    }
              }){

                    @Override
                    protected Map<String, String> getParams() throws AuthFailureError {
                        return requestData;
                    }

                    @Override
                    protected Response<String> parseNetworkResponse(NetworkResponse response) {
                        mStatusCode = response.statusCode;
                        return super.parseNetworkResponse(response);

                    }

                    @Override
                    public int getMethod() {
                        return super.getMethod();
                    }
        };
        addToRequestQueue(stringRequest,requstTag);
    }



    /**
     * 添加请求到队列
     * @param req
     * @param tag
     * @param <T>
     */
    public <T> void addToRequestQueue(Request<T> req, String tag) {
        req.setTag(TextUtils.isEmpty(tag) ? TAG : tag);
        mRequestQueue.add(req);
    }

    /**
     * 添加请求到队列
     * @param req
     * @param <T>
     */
    public  <T> void addToRequestQueue(Request<T> req) {
        req.setTag(TAG);
        mRequestQueue.add(req);
    }

    /**
     * 通过tag 取消请求
     * @param tag
     */
    public  void cancelPendingRequests(Object tag) {
        if (mRequestQueue != null) {
            mRequestQueue.cancelAll(tag);
        }
    }



}
