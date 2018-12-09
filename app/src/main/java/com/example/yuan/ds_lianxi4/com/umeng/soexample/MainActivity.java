package com.example.yuan.ds_lianxi4.com.umeng.soexample;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.yuan.ds_lianxi4.R;
import com.umeng.socialize.ShareAction;
import com.umeng.socialize.UMAuthListener;
import com.umeng.socialize.UMShareAPI;
import com.umeng.socialize.UMShareListener;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.umeng.socialize.media.UMImage;

import java.util.Map;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }
    /**
     * 添加回调
     * @param requestCode
     * @param resultCode
     * @param data
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        UMShareAPI.get(this).onActivityResult(requestCode,resultCode,data);
    }

    private void initView() {

        Button fenxiang=findViewById(R.id.fenxiang);
        Button login=findViewById(R.id.login);
        fenxiang.setOnClickListener(this);
        login.setOnClickListener(this);

    }


    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id)
        {
            case R.id.fenxiang:
                //分享用的图片
                UMImage image=new UMImage(MainActivity.this,R.drawable.umeng_socialize_qq);
                new ShareAction(MainActivity.this)
                        .withText("hello")
                        .withMedia(image)
                        .setDisplayList(SHARE_MEDIA.QQ)
                        .setCallback(shareListener)
                        .open();
                break;
            case R.id.login:
                //获得UMShareAPI实例
                UMShareAPI umShareAPI=UMShareAPI.get(MainActivity.this);
                //开始登录
                //第一个参数：上下文
                //第二个参数，登录哪种平台
                //第三个参数，添加回调
                umShareAPI.getPlatformInfo(MainActivity.this, SHARE_MEDIA.QQ, new UMAuthListener() {
                    //开始登录回调
                    @Override
                    public void onStart(SHARE_MEDIA share_media) {
                        Log.i("dd", "UMAuthListener onStart");

                    }
                    //登录完成
                    @Override
                    public void onComplete(SHARE_MEDIA share_media, int i, Map<String, String> map) {
                        Log.i("dd", "UMAuthListener onComplete");
                    }

                    @Override
                    public void onError(SHARE_MEDIA share_media, int i, Throwable throwable) {
                        Log.i("dd", "UMAuthListener onError" + throwable.getLocalizedMessage());
                    }
                        //登录失败
                    @Override
                    public void onCancel(SHARE_MEDIA share_media, int i) {
                        Log.i("dd", "UMAuthListener onCancel");
                    }
                });
                break;
                default:
                    break;

        }
    }
    //分享开始的回调
    private UMShareListener shareListener=new UMShareListener() {

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
}
