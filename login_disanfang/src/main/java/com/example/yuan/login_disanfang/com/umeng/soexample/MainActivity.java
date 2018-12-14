package com.example.yuan.login_disanfang.com.umeng.soexample;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.yuan.login_disanfang.R;
import com.example.yuan.login_disanfang.bean.LoginBean;
import com.example.yuan.login_disanfang.presenter.IPresenterImple;
import com.example.yuan.login_disanfang.view.IView;
import com.example.yuan.login_disanfang.view.LoginActivity;
import com.example.yuan.login_disanfang.view.ZhuceActivity;
import com.umeng.socialize.ShareAction;
import com.umeng.socialize.UMAuthListener;
import com.umeng.socialize.UMShareAPI;
import com.umeng.socialize.UMShareListener;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.umeng.socialize.media.UMImage;

import java.util.Map;

public class MainActivity extends AppCompatActivity implements IView,View.OnClickListener {

    private EditText editTextphone;
    private EditText editTextpwd;
    private CheckBox checkBoxjizhu;
    private CheckBox checkBoxzidong;
    private ImageView imageViewqq;
    private ImageView imageViewfenxaing;
    private IPresenterImple presenterImple;
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;
    private Button button_login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();

    }

    private void initView() {
        editTextphone = findViewById(R.id.editText_phone);
        editTextpwd = findViewById(R.id.editText_pwd);
        checkBoxjizhu = findViewById(R.id.checkBox_jizhu);
        checkBoxzidong = findViewById(R.id.checkBox_zidong);
        imageViewqq = findViewById(R.id.image_qq);
        imageViewfenxaing = findViewById(R.id.image_fenxiang);
        button_login = findViewById(R.id.button_login);
        findViewById(R.id.button_login).setOnClickListener(this);
        findViewById(R.id.button_zhuce).setOnClickListener(this);
        findViewById(R.id.image_fenxiang).setOnClickListener(this);
        findViewById(R.id.image_qq).setOnClickListener(this);

        editTextpwd.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                button_login.setEnabled((s.length()>=6));

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        //第三方



        //得到presenter实例
        presenterImple = new IPresenterImple(this);

        //得到shard
        sharedPreferences = getSharedPreferences("User", MODE_MULTI_PROCESS);
        editor = sharedPreferences.edit();
        boolean jizhuchecked = sharedPreferences.getBoolean("jizhuchecked", false);
        if (jizhuchecked)
        {
            String phone = sharedPreferences.getString("phone", null);
            String pwd = sharedPreferences.getString("pwd", null);
            editTextphone.setText(phone);
            editTextpwd.setText(pwd);
            checkBoxzidong.setChecked(true);
        }
        boolean zidongchecked = sharedPreferences.getBoolean("zidongchecked", false);
            if (zidongchecked)
            {
                String phone = editTextphone.getText().toString();
                String pwd = editTextpwd.getText().toString();
                checkBoxjizhu.setChecked(true);

                presenterImple.getData("http://120.27.23.105/user/login?mobile="+phone+"&password="+pwd,null,LoginBean.class);
            }

            checkBoxzidong.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    if (isChecked)
                    {
                        checkBoxjizhu.setChecked(true);
                    }
                    else
                    {
                        editor.clear();
                        editor.commit();
                    }
                }
            });

    }
    //第三方
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        UMShareAPI.get(this).onActivityResult(requestCode,resultCode,data);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id)
        {
            case R.id.button_login:
                String phone = editTextphone.getText().toString();
                String pwd = editTextpwd.getText().toString();
                if (checkBoxjizhu.isChecked())
                {
                    //如果选中就存值
                    editor.putString("phone",phone);
                    editor.putString("pwd",pwd);
                    editor.putBoolean("jizhuchecked",true);
                    editor.commit();
                }
                else
                {
                    editor.clear();
                    editor.commit();
                }
                if (checkBoxzidong.isChecked())
                {
                    editor.putBoolean("zidongchecked",true);
                    editor.commit();
                }



                presenterImple.getData("http://120.27.23.105/user/login?mobile="+phone+"&password="+pwd,null,LoginBean.class);

                break;
            case R.id.button_zhuce:
                Intent intent=new Intent(MainActivity.this,ZhuceActivity.class);
                startActivity(intent);
                break;

            case R.id.image_fenxiang:
                UMImage image=new UMImage(MainActivity.this,R.drawable.umeng_socialize_qq);
                new ShareAction(MainActivity.this)
                        .withText("hello")
                        .withMedia(image)
                        .setDisplayList(SHARE_MEDIA.QQ)
                        .setCallback(umAuthListener)
                        .open();
                break;
            case R.id.image_qq:
                //获得umshadreapi实例
                UMShareAPI umShareAPI=UMShareAPI.get(MainActivity.this);
                //开始登录
                umShareAPI.getPlatformInfo(MainActivity.this, SHARE_MEDIA.QQ, new UMAuthListener() {
                    @Override
                    public void onStart(SHARE_MEDIA share_media) {
                        //登录成跳转
                        Intent intent=new Intent(MainActivity.this,LoginActivity.class);
                        startActivity(intent);
                    }

                    @Override
                    public void onComplete(SHARE_MEDIA share_media, int i, Map<String, String> map) {

                    }

                    @Override
                    public void onError(SHARE_MEDIA share_media, int i, Throwable throwable) {

                    }

                    @Override
                    public void onCancel(SHARE_MEDIA share_media, int i) {

                    }
                });


                break;


                default:
                    break;
        }
    }
    //分享开始的回调
    private UMShareListener umAuthListener=new UMShareListener() {

        @Override
        public void onStart(SHARE_MEDIA share_media) {

        }

        @Override
        public void onResult(SHARE_MEDIA share_media) {

        }

        @Override
        public void onError(SHARE_MEDIA share_media, Throwable throwable) {

        }

        @Override
        public void onCancel(SHARE_MEDIA share_media) {

        }
    };

    @Override
    public void getData(Object data) {
        LoginBean loginBean= (LoginBean) data;
        String code = loginBean.getCode();
        if (code.equals("0"))
        {
            Toast.makeText(this,"成功",Toast.LENGTH_SHORT).show();
            Intent intent=new Intent(MainActivity.this,LoginActivity.class);
            intent.putExtra("username",loginBean.getData().getUsername());
            startActivity(intent);

        }
        else
        {
            Toast.makeText(this,"账号或密码错误",Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenterImple.xiaohui();
    }
}
