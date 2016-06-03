package com.dev.frame.base;

/**
 * Created by Devt on 16/6/1.
 * Email:devt@foxmail.com
 */
public interface LocalCallBackInterface {
    public void success(int bizCode, String responseMessage);
    public void error(int bizCode, String errorMessage);
}

