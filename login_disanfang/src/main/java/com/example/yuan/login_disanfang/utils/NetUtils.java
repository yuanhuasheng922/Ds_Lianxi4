package com.example.yuan.login_disanfang.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

//网络请求

public  class NetUtils {


    public static String getData(String strUrl){
        String result=null;
        try {
            URL url=new URL(strUrl);
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            httpURLConnection.setConnectTimeout(5000);
            httpURLConnection.setReadTimeout(5000);
            int responseCode = httpURLConnection.getResponseCode();
            if (responseCode==200)
            {
                result =  stream2String(httpURLConnection.getInputStream());
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return result;
    }

    private static String stream2String(InputStream inputStream) throws IOException {
        InputStreamReader inputStreamReader=new InputStreamReader(inputStream);
        BufferedReader bufferedReader=new BufferedReader(inputStreamReader);
        StringBuilder stringBuilder=new StringBuilder();
        for (String temp=bufferedReader.readLine();temp!=null;temp=bufferedReader.readLine())
        {
            stringBuilder.append(temp);
        }
        return stringBuilder.toString();
    }


}
