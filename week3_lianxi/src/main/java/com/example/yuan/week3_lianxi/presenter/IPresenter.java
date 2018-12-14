package com.example.yuan.week3_lianxi.presenter;

import java.util.Map;

public interface IPresenter {
    void getRequest(String url,Class clazz);
    void getRequest(String url, Class clazz, Map<String,String> params);
}
