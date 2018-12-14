package com.example.yuan.okhttp01.view;
//抽取view实现接口
public interface IView<T> {
    //请求成功返回的数据
    void showResponseData(T data);
}
