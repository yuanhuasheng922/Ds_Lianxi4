package com.example.yuan.login_disanfang.model;

import android.os.AsyncTask;

import com.example.yuan.login_disanfang.callback.MyCllback;
import com.example.yuan.login_disanfang.utils.NetUtils;
import com.google.gson.Gson;

import java.io.IOException;

public class IModleImple implements IModle {

    //json解析
    public <T> T getData(String strUrl,String param,Class clazz) {
      return (T) new Gson().fromJson(NetUtils.getData(strUrl),clazz);
    }

    @Override
    public void getData(final String strUrl, final String param, final Class clazz, final MyCllback cllback) {
        new AsyncTask<String,Void,Object>(){
            @Override
            protected Object doInBackground(String... strings) {
                return getData(strUrl,param,clazz);
            }

            @Override
            protected void onPostExecute(Object o) {
                cllback.getData(o);
            }
        }.execute(strUrl);
    }
}
