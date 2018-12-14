package com.example.yuan.week3_lianxi.presenter;

import com.example.yuan.week3_lianxi.callback.MyCallback;
import com.example.yuan.week3_lianxi.model.IModelImple;
import com.example.yuan.week3_lianxi.view.IView;

import java.util.Map;

public class IPresenterImple implements IPresenter {
    private IView mIView;
    private IModelImple mIMpdelimple;

    public IPresenterImple(IView mIView) {
        this.mIView = mIView;
        mIMpdelimple=new IModelImple();
    }

    @Override
    public void getRequest(String url, Class clazz) {
        mIMpdelimple.getRequest(url, clazz, new MyCallback() {
            @Override
            public void getRequest(Object data) {
                mIView.getRequest(data);
            }
        });
    }

    @Override
    public void getRequest(String url, Class clazz, Map<String, String> params) {
        mIMpdelimple.getRequest(url, clazz, params, new MyCallback() {
            @Override
            public void getRequest(Object data) {
             mIView.getRequest(data);
            }
        });
    }
}
