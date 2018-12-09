package com.example.yuan.login_disanfang.presenter;

import com.example.yuan.login_disanfang.callback.MyCllback;
import com.example.yuan.login_disanfang.model.IModleImple;
import com.example.yuan.login_disanfang.view.IView;

public class IPresenterImple implements IPresenter {
    //v层和m层的实例
    private IView mIView;
    private IModleImple mIModdleImple;

    public IPresenterImple(IView mIView) {
        this.mIView = mIView;
        mIModdleImple=new IModleImple();
    }

    @Override
    public void getData(String strUrl, String param, Class clazz) {
        mIModdleImple.getData(strUrl, param, clazz, new MyCllback() {
            @Override
            public void getData(Object data) {
                mIView.getData(data);
            }
        });
    }
    public void xiaohui()
    {
        mIView=null;
        mIModdleImple=null;
    }
}
