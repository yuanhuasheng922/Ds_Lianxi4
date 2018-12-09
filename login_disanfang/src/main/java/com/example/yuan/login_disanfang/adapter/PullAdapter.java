package com.example.yuan.login_disanfang.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.yuan.login_disanfang.R;
import com.example.yuan.login_disanfang.bean.ImageBean;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.ArrayList;
import java.util.List;

public class PullAdapter extends BaseAdapter {
    private Context mContext;
    private List<ImageBean.DataBean> mDatas;

    public PullAdapter(Context mContext) {
        this.mContext = mContext;
        mDatas=new ArrayList<>();
    }
    //刷新
    public void setmDatas(List<ImageBean.DataBean> datas) {
        mDatas.clear();
        mDatas.addAll(datas);
        notifyDataSetChanged();
    }
    //加载
    public void addmDatas(List<ImageBean.DataBean> datas) {
        mDatas.addAll(datas);
        notifyDataSetChanged();
    }
    @Override
    public int getCount() {
        return mDatas.size();
    }

    @Override
    public Object getItem(int position) {
        return mDatas.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder=null;
        if (convertView==null)
        {
            holder=new ViewHolder();
            convertView=LayoutInflater.from(mContext).inflate(R.layout.pull,parent,false);
            holder.ima=convertView.findViewById(R.id.ima);
            holder.text=convertView.findViewById(R.id.text);
            convertView.setTag(holder);
        }
        else
        {
            holder= (ViewHolder) convertView.getTag();
        }
        holder.text.setText(mDatas.get(position).getTitle());
        DisplayImageOptions options=new DisplayImageOptions.Builder()
                .cacheOnDisk(true)
                .cacheInMemory(true)
                .build();
        ImageLoader.getInstance().displayImage(mDatas.get(position).getThumbnail_pic_s03(),holder.ima,options);
        return convertView;
    }
    class ViewHolder{
        TextView text;
        ImageView ima;
    }
}
