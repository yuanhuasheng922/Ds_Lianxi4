package com.example.yuan.login_disanfang.model;

import com.example.yuan.login_disanfang.callback.MyCllback;

public interface IModle   {
    void getData(String strUrl, String param, Class clazz, MyCllback cllback);
}
