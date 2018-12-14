package com.example.yuan.okhttp01;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.provider.SyncStateContract;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.yuan.okhttp01.apis.Api;
import com.example.yuan.okhttp01.apis.SPUtils;
import com.example.yuan.okhttp01.bean.LoginBean;
import com.example.yuan.okhttp01.presenter.IPresenterImple;
import com.example.yuan.okhttp01.view.IView;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity implements IView, View.OnClickListener {

    private EditText name;
    private EditText pwd;
    private Button login;
    //private final int TYPE_LOGIN = 0;
    private IPresenterImple presenterImple;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        name = findViewById(R.id.name);
        pwd = findViewById(R.id.pwd);
        login = findViewById(R.id.login);
        login.setOnClickListener(this);

        //p层实例
        presenterImple = new IPresenterImple(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (presenterImple != null) {
            presenterImple.onDetach();
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.login:
                Map<String,String> params =new HashMap<>();
               String ptone = name.getText().toString();
                String pwdd = pwd.getText().toString();
                params.put("mobile",ptone);
                params.put("password",pwdd);
                presenterImple.getRequest(Api.URL_LOGIN,params,LoginBean.class);
                break;
        }
    }

//    private void checkPermission(int type_login) {
//        if (Build.VERSION.SDK_INT>=Build.VERSION_CODES.M)
//        {
//            if (ContextCompat.checkSelfPermission(this,Manifest.permission.INTERNET)!=PackageManager.PERMISSION_GRANTED)
//            {
//                ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.INTERNET},type_login);
//            }
//            else
//            {
//                startRequest(type_login);
//            }
//        }else
//        {
//            startRequest(type_login);
//        }
//    }

//    @Override
//    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
//        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
//        if (grantResults[0]==PackageManager.PERMISSION_GRANTED)
//        {
//            startRequest(requestCode);
//        }
//    }

//    private void startRequest(int type_login) {
//        switch (type_login)
//        {
//            case TYPE_LOGIN:
//               String strLogin = String.format(Api.URL_LOGIN,name.getText().toString(),pwd.getText().toString());
//                presenterImple.startRequest(strLogin,null,LoginBean.class);
//                break;
//        }
//    }

    @Override
    public void showResponseData(Object data) {

        if (data instanceof  LoginBean)
        {
            LoginBean loginBean= (LoginBean) data;
           if (loginBean.getCode().equals("0"))
           {
               Intent intent =new Intent(MainActivity.this,LoginActivity.class);
               startActivity(intent);
               Toast.makeText(this,"登录成功",Toast.LENGTH_SHORT).show();
           }
            {
                Toast.makeText(this,"登录失败",Toast.LENGTH_SHORT).show();
            }


        }
    }
}
