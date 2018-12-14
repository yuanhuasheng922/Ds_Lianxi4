package com.example.yuan.week3_lianxi.view;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.yuan.week3_lianxi.R;
import com.example.yuan.week3_lianxi.bean.UserBean;
import com.example.yuan.week3_lianxi.adapter.GridAdapter;
import com.example.yuan.week3_lianxi.adapter.LinearAdapter;
import com.example.yuan.week3_lianxi.apis.Apis;
import com.example.yuan.week3_lianxi.presenter.IPresenterImple;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity implements IView ,View.OnClickListener {

    private XRecyclerView linear;
    private LinearAdapter linearAdapter;
    private int mPage=1;
    private IPresenterImple presenterImple;
    //判断
    private boolean isShow= false;
    private RecyclerView grid;
    private GridAdapter gridAdapter;
    private TextView ename;
    private TextView zonghe;
    private TextView countt;
    private TextView price;
    private TextView change;
    private Button sousuo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        zonghe = findViewById(R.id.synthesize);
        ename = findViewById(R.id.edname);
        findViewById(R.id.synthesize).setOnClickListener(this);
        countt = findViewById(R.id.countt);
        findViewById(R.id.countt).setOnClickListener(this);
        price = findViewById(R.id.price);
        findViewById(R.id.price).setOnClickListener(this);
        change = findViewById(R.id.change);
        findViewById(R.id.change).setOnClickListener(this);
        sousuo = findViewById(R.id.sousuo);
        findViewById(R.id.sousuo).setOnClickListener(this);
        presenterImple = new IPresenterImple(this);
        linear = findViewById(R.id.linear);
        grid = findViewById(R.id.grid);



        //点击切换
        change.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isShow)
                {
                    linear.setVisibility(View.VISIBLE);
                    grid.setVisibility(View.GONE);
                    isShow=false;
                }
                else
                {
                    linear.setVisibility(View.GONE);
                    grid.setVisibility(View.VISIBLE);
                    isShow=true;
                }
            }
        });

        //设备管理器
        LinearLayoutManager layoutManager=new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        linear.setLayoutManager(layoutManager);
        //适配器
        linearAdapter = new LinearAdapter(this);
        linear.setAdapter(linearAdapter);
        //是否开启刷新加载
        linear.setPullRefreshEnabled(true);
        linear.setLoadingMoreEnabled(true);
        linear.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                mPage=1;
                shuju();
            }

            @Override
            public void onLoadMore() {
                shuju();
            }


        });
        shuju();
    //点击跳转
        this.linearAdapter.setonClickListener(
                new LinearAdapter.Click() {
                    @Override
                    public void onSuccess(int position) {
                        Intent intent = new Intent(MainActivity.this, Main2Activity.class);
                        intent.putExtra("name",position+"");
                        startActivity(intent);
                    }
                });

        GridLayoutManager gridLayoutManager=new GridLayoutManager(this,2);
        gridLayoutManager.setOrientation(OrientationHelper.VERTICAL);
        grid.setLayoutManager(gridLayoutManager);

        gridAdapter = new GridAdapter(this);
        grid.setAdapter(gridAdapter);


    }



    public void    shuju() {
        Map<String ,String> params=new HashMap<>();
        params.put("keywords","手机");
        params.put("mPage",mPage + "");
       presenterImple.getRequest(Apis.TYPE_TEXT,UserBean.class,params);
    }
    @Override
    public void getRequest(Object data) {
        if (data instanceof UserBean)

        {
            UserBean userBean= (UserBean) data;
            if (mPage==1)
            {
                linearAdapter.setmData(userBean.getData());
            }
            else
            {
               linearAdapter.addmData(userBean.getData());
            }
            mPage++;
            linear.refreshComplete();
            linear.loadMoreComplete();
        }
        UserBean userBean= (UserBean) data;
        gridAdapter.setmData(userBean.getData());
    }

    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            //搜索
            case R.id.sousuo:
                mPage=1;
                Map<String,String> params=new HashMap<>();
                String name = ename.getText().toString();
                params.put("keywords",name);

                presenterImple.getRequest(Apis.TYPE_TEXT,UserBean.class,params);
                break;
                //销量   没有销量,
            case R.id.countt:
                mPage=1;
                Map<String,String> params1=new HashMap<>();
                params1.put("sort",0+"");
                params1.put("keywords","手机");
                params1.put("page",mPage+"");
                presenterImple.getRequest(Apis.TYPE_TEXT,UserBean.class,params1);
                break;
            case R.id.price:
                //价格
                mPage=1;
                Map<String,String> params3=new HashMap<>();
                params3.put("sort",2+"");
                params3.put("keywords","手机");
                params3.put("page",mPage+"");
                presenterImple.getRequest(Apis.TYPE_TEXT,UserBean.class,params3);
                break;
        }
    }
}
