package com.example.yuan.login_disanfang.fragent;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;

import com.example.yuan.login_disanfang.R;
import com.example.yuan.login_disanfang.adapter.PullAdapter;
import com.example.yuan.login_disanfang.bean.ImageBean;
import com.example.yuan.login_disanfang.presenter.IPresenterImple;
import com.example.yuan.login_disanfang.view.IView;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.loader.ImageLoaderInterface;

import java.util.ArrayList;
import java.util.List;

public class FirstFragment extends Fragment implements IView {

    private Banner banner;
    private IPresenterImple presenterImple;
    private PullToRefreshListView pull;
    private int mPage;
    private PullAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.firstfragment,container,false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        banner = view.findViewById(R.id.banner);
        pull = view.findViewById(R.id.pull);
        //拿到p层实例
        presenterImple = new IPresenterImple(this);
        //设置banner样式
        banner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR_TITLE_INSIDE);
        //设置图片加载器
        banner.setImageLoader(new ImageLoaderInterface<ImageView>() {
            @Override
            public void displayImage(Context context, Object path, ImageView imageView) {
                ImageBean.DataBean dataBean= (ImageBean.DataBean) path;
                ImageLoader.getInstance().displayImage(dataBean.getThumbnail_pic_s02(),imageView);
            }

            @Override
            public ImageView createImageView(Context context) {
                ImageView imageView=new ImageView(context);
                imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
                return imageView;
            }
        });
        initData();
        initDatas();
    }

    private void initDatas() {
            pull.setMode(PullToRefreshListView.Mode.BOTH);
            pull.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<ListView>() {
                @Override
                public void onPullDownToRefresh(PullToRefreshBase<ListView> refreshView) {
                    mPage=1;
                    initData();
                }

                @Override
                public void onPullUpToRefresh(PullToRefreshBase<ListView> refreshView) {
                    mPage++;
                    initData();
                }
            });
        initData();
    }

    private void initData() {
        presenterImple.getData("http://www.xieast.com/api/news/news.php",null,ImageBean.class);
        adapter = new PullAdapter(getActivity());
        pull.setAdapter(adapter);
    }

    @Override
    public void getData(Object data) {
        ImageBean imagee= (ImageBean) data;
        banner.setImages(imagee.getData());
        banner.setBannerTitles(getTitles(imagee));
        banner.start();

        if (imagee.getCode()==1)
        {
            if (mPage==1)
            {
                adapter.setmDatas(imagee.getData());
            }
            else
            {
                adapter.addmDatas(imagee.getData());
            }
            // mPage++;
            pull.onRefreshComplete();
        }
    }
    //设置标题名字
    private List<String> getTitles(ImageBean ima)
    {
        List<String> list=new ArrayList<>();
        for (ImageBean.DataBean bean : ima.getData())
        {

            list.add(bean.getTitle());
        }
        return list;
    }
}
