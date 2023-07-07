package com.boylab.fragmentdemo;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;

import com.boylab.fragmentdemo.base.BaseActivity;
import com.boylab.fragmentdemo.fragment.FragmentOne;

public class MainActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        add(new FragmentOne(),true);
    }
}