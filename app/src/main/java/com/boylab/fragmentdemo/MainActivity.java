package com.boylab.fragmentdemo;

import android.os.Bundle;

import com.boylab.fragmentdemo.base.BaseActivity;
import com.boylab.fragmentdemo.fragment.FragmentOne;

public class MainActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        addContent(new FragmentOne(),true);
    }
}