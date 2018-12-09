package com.example.yuan.login_disanfang.fragent;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.yuan.login_disanfang.R;
import com.example.yuan.login_disanfang.view.CodeActivity;
import com.example.yuan.login_disanfang.view.LoginActivity;

import java.lang.ref.WeakReference;

import cn.bingoogolapple.qrcode.zxing.QRCodeEncoder;

public class MyFragment extends Fragment {

    private Button scan;
    private ImageView ImageView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.myfragment,container,false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        scan = view.findViewById(R.id.scan);
        ImageView = view.findViewById(R.id.qrcode);

        createQRcode();
        scan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(),CodeActivity.class));
            }
        });
    }

    private void checkPermission()
    {
        if (Build.VERSION.SDK_INT>=Build.VERSION_CODES.M)
        {
            if (ContextCompat.checkSelfPermission(getContext(), Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.CAMERA},100);
            }
            else
            {
                startActivity(new Intent(getActivity(),CodeActivity.class));
            }
        }

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode==100&&grantResults[0]==PackageManager.PERMISSION_GRANTED)
        {
            startActivity(new Intent(getActivity(),CodeActivity.class));
        }
    }

    private void createQRcode() {
        String name = ((LoginActivity) getActivity()).name();
        QRTask qrTask=new QRTask(getActivity(),ImageView,name);
        qrTask.execute(name);
    }

    static class QRTask extends AsyncTask<String,Void,Bitmap>{
        private WeakReference<Context> mContext;
        private WeakReference<ImageView> mImageview;

        public QRTask(Context context,ImageView imageView,String name) {
            mContext=new WeakReference<>(context);
            mImageview=new WeakReference<>(imageView);
        }

        @Override
        protected Bitmap doInBackground(String... strings) {

            String str=strings[0];
            if (TextUtils.isEmpty(str))
            {
                return  null;
            }
            int size=300;
            return QRCodeEncoder.syncEncodeQRCode(str,size);
        }

        @Override
        protected void onPostExecute(Bitmap bitmap) {
            super.onPostExecute(bitmap);
            if (bitmap!=null)
            {
                mImageview.get().setImageBitmap(bitmap);
            }
            else
            {
                Toast.makeText(mContext.get(),"生成失败",Toast.LENGTH_SHORT).show();
            }
        }
    };
}
