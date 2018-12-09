package com.example.yuan.login_disanfang.custom;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.AttributeSet;
import android.widget.TextView;

@SuppressLint("AppCompatCustomView")
public class CustomViewTextView extends TextView {
    public CustomViewTextView(Context context) {
        super(context);
    }

    public CustomViewTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public CustomViewTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public boolean isFocused() {
        return true;
    }
}
