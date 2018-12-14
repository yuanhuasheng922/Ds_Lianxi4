package com.example.yuan.week3_lianxi.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.yuan.week3_lianxi.R;
import com.example.yuan.week3_lianxi.bean.UserBean;

import java.util.ArrayList;
import java.util.List;

public class GridAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{

    private Context mContext;
    private List<UserBean.DataBean> mDatas;

    public GridAdapter(Context mContext) {
        this.mContext = mContext;
        mDatas=new ArrayList<>();
    }
    public void setmData(List<UserBean.DataBean> data) {

        mDatas.clear();
        mDatas.addAll(data);
        notifyDataSetChanged();
    }
    public void addmData(List<UserBean.DataBean> data) {

        mDatas.addAll(data);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LinearAdapter.ViewHolder viewHolder=null;

        View view=LayoutInflater.from(mContext).inflate(R.layout.grid_item,viewGroup,false);
        return new ViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        GridAdapter.ViewHolder holder= (GridAdapter.ViewHolder) viewHolder;
        holder.jiage.setText("价格:"+mDatas.get(i).getPrice()+"");
        // holder.price.setText((int) mDatas.get(i).getPrice());
        holder.riqi.setText("上市时间:"+mDatas.get(i).getCreatetime());
        holder.name.setText(mDatas.get(i).getTitle());

        String images = mDatas.get(i).getImages();
        String[] split = images.split("\\|");
        Glide.with(mContext).load(split[0]).into(holder.imag);
    }

    @Override
    public int getItemCount() {
        return mDatas.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private TextView name,jiage,riqi;
        private final ImageView imag;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.name);
            riqi = itemView.findViewById(R.id.riqi);
            jiage = itemView.findViewById(R.id.jiage);
            imag = itemView.findViewById(R.id.imag);
        }
    }

}
