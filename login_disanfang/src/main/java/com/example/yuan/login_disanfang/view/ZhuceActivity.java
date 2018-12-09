package com.example.yuan.login_disanfang.view;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.yuan.login_disanfang.com.umeng.soexample.MainActivity;
import com.example.yuan.login_disanfang.R;
import com.example.yuan.login_disanfang.bean.ZhuceBean;
import com.example.yuan.login_disanfang.presenter.IPresenterImple;

public class ZhuceActivity extends AppCompatActivity implements IView,View.OnClickListener {

    private EditText zhucephone;
    private EditText zhucepwd;
    private Button zhuceclick;
    private IPresenterImple presenterImple;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zhuce);
        initView();
    }

    private void initView() {
        zhucephone = findViewById(R.id.zhucephone);
        zhucepwd = findViewById(R.id.zhucepwd);
        zhuceclick = findViewById(R.id.zhuceclick);
        zhuceclick.setOnClickListener(this);
        presenterImple = new IPresenterImple(this);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id)
        {
            case R.id.zhuceclick:
                String phone = zhucephone.getText().toString();
                String pwd = zhucepwd.getText().toString();
                presenterImple.getData("http://120.27.23.105/user/reg?mobile="+phone+"&password="+pwd,null,ZhuceBean.class);
                break;
                default:
                    break;
        }
    }

    @Override
    public void getData(Object data) {
        ZhuceBean zhuceBean= (ZhuceBean) data;
        String code = zhuceBean.getCode();
        if (code.equals("0"))
        {
            Toast.makeText(this,"注册成功",Toast.LENGTH_SHORT).show();
            Intent intent=new Intent(ZhuceActivity.this,MainActivity.class);
            startActivity(intent);
            finish();
        }
        else
        {
            Toast.makeText(this,"格式不正确,",Toast.LENGTH_SHORT).show();
        }
    }
}
