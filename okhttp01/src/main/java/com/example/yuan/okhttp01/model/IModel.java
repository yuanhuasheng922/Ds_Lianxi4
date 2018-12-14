package com.example.yuan.okhttp01.model;

import com.example.yuan.okhttp01.callback.MyCallBack;

import java.util.Map;

//Model 接口
public interface IModel {
    //封装方法
    //1.接口地址2.如果是post请求的话要传参数3.处理完保存带接口
    void requestData(String url, String params, Class clazz, MyCallBack callBack);
    //post
    void getRequest(String dataUrl, Map<String ,String> params,Class clazz,MyCallBack callBack);
}
