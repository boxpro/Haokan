package com.devt.haokan.mock;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Devt on 16/6/2.
 * Email:devt@foxmail.com
 */
public class MockData {
//    private static final String urlImg = "http://h.hiphotos.baidu.com/image/pic/item/024f78f0f736afc3065a7888b419ebc4b645128c.jpg";
    private static final String allImg = "http://image4.90e.com/image/960x600/";
    public static List<String> getDatas(){
        List<String> list = new ArrayList<>();
        for (int i =0;i<1000;i++){
            list.add(allImg+imgData(i));
        }
        return list;
    }

    private static String imgData(int i){
        return (i+1)+".jpg";
    }
}
