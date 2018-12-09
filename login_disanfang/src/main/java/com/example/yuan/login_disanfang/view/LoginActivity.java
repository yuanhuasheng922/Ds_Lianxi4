package com.example.yuan.login_disanfang.view;

import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.yuan.login_disanfang.R;
import com.example.yuan.login_disanfang.adapter.ViewPagerAdapter;

public class LoginActivity extends AppCompatActivity {

    private TabLayout tabLayout;
    private ViewPager viewPager;
    private String username;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
           
        initView();

    }

    private void initView() {
        tabLayout = findViewById(R.id.table);
        viewPager = findViewById(R.id.viewpager);
        //适配器
        viewPager.setAdapter(new ViewPagerAdapter(getSupportFragmentManager()));
        //tableat关联viewpager
        tabLayout.setupWithViewPager(viewPager);
        Intent intent = getIntent();
        username = intent.getStringExtra("username");
    }

    public String name()
    {
        return username;
    }
}
