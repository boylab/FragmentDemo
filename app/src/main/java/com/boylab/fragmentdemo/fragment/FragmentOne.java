package com.boylab.fragmentdemo.fragment;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentResultListener;

import com.boylab.fragmentdemo.R;
import com.boylab.fragmentdemo.base.BaseFragment;
import com.boylab.fragmentdemo.view.ActionView;

public class FragmentOne extends BaseFragment implements ActionView.IActionClick {

    private ActionView actionView;

    @Override
    protected int setRootView() {
        return R.layout.fragment_one;
    }

    @Override
    protected void initView(View rootView) {
        actionView = rootView.findViewById(R.id.actionView);
        actionView.setiActionClick(this);
    }

    @Override
    protected void initData() {

    }

    @Override
    public void onFront01() {
        add(new FragmentTwo(), true);
    }

    @Override
    public void onFront02() {
        add(new FragmentTwo(), false);
    }

    @Override
    public void onFront03() {
        replace(new FragmentTwo(), true);
    }

    @Override
    public void onFront04() {
        replace(new FragmentTwo(), false);
    }

    @Override
    public void onBack01() {
        pop();
    }

    @Override
    public void onBack02() {
        showToast("无功能");
    }

    @Override
    public void onBack03() {
        popToTop();
    }

    @Override
    public void onBack04() {

    }
}