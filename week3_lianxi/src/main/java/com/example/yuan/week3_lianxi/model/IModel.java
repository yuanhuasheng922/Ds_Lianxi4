package com.example.yuan.week3_lianxi.model;

import com.example.yuan.week3_lianxi.callback.MyCallback;

import java.util.Map;

public interface IModel {
    void getRequest(String url, Class clazz, MyCallback callback);
    void getRequest(String url, Class clazz, Map<String,String>params, MyCallback callback);
}
