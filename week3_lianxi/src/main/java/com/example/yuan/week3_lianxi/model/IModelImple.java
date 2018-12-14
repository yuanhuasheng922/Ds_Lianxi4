package com.example.yuan.week3_lianxi.model;

import com.example.yuan.week3_lianxi.callback.ICallback;
import com.example.yuan.week3_lianxi.callback.MyCallback;
import com.example.yuan.week3_lianxi.okhttps.OkHttps;

import java.util.Map;

public class IModelImple implements IModel {
    @Override
    public void getRequest(String url, Class clazz, final MyCallback callback) {
        OkHttps.getInstance().getEnqueue(url, clazz, new ICallback() {
            @Override
            public void onsuccess(Object obj) {
                callback.getRequest(obj);
            }

            @Override
            public void onfail(Exception e) {
                callback.getRequest(e);
            }
        });
    }

    @Override
    public void getRequest(String url, Class clazz, Map<String, String> params, final MyCallback callback) {
            OkHttps.getInstance().postEnqueue(url, clazz, params, new ICallback() {
    @Override
    public void onsuccess(Object obj) {
        callback.getRequest(obj);
    }

    @Override
    public void onfail(Exception e) {
        callback.getRequest(e.getMessage());
    }
});
    }
}
