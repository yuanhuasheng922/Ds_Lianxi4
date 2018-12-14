package com.example.yuan.week3_lianxi.view;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.yuan.week3_lianxi.R;
import com.example.yuan.week3_lianxi.apis.Apis;
import com.example.yuan.week3_lianxi.bean.ImageBean;
import com.example.yuan.week3_lianxi.bean.UserBean;
import com.example.yuan.week3_lianxi.presenter.IPresenter;
import com.example.yuan.week3_lianxi.presenter.IPresenterImple;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.loader.ImageLoaderInterface;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Main2Activity extends AppCompatActivity implements IView{

    private TextView textprice;
    private TextView textname;
    private Banner banner;
    private String name;
    private IPresenter presenterImple;
    private List<String> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
            //找控件
        textprice = findViewById(R.id.prices);
        textname = findViewById(R.id.names);
        banner = findViewById(R.id.banner);
        //p层实例
        presenterImple = new IPresenterImple(this);
        //数组
        list = new ArrayList<>();
        //接受传过来的值
        Intent intent = getIntent();
        name = intent.getStringExtra("name");

        //设置banner样式
        banner.setBannerStyle(BannerConfig.NOT_INDICATOR);
        banner.setImageLoader(new ImageLoaderInterface<ImageView>() {

            @Override
            public void displayImage(Context context, Object path, ImageView imageView) {
                Glide.with(Main2Activity.this).load(path).into(imageView);
            }

            @Override
            public ImageView createImageView(Context context) {
                ImageView imageView = new ImageView(context);
                imageView.setScaleType(ImageView.ScaleType.FIT_XY);
                return imageView;
            }
        });
        loadData();
    }

    private void loadData() {
        Map<String,String> params=new HashMap<>();
        params.put("pid",name);
        presenterImple.getRequest(Apis.TYPE_TITLE,ImageBean.class,params);
    }

    @Override
    public void getRequest(Object data) {
        if(data instanceof ImageBean){
            ImageBean detailBean= (ImageBean) data;
            String[] split = detailBean.getData().getImages().split("\\|");
            for (int i=0;i<split.length;i++){
                list.add(split[i]);
            }
            banner.setImages(list);
            banner.start();
            textname.setText(detailBean.getData().getTitle());
            textprice.setText(detailBean.getData().getPrice()+"");
            textprice.setTextColor(Color.RED);

        }
    }
}
