package com.boylab.fragmentdemo.fragment;

import android.os.Bundle;
import android.view.View;

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
        mActivity.addContent(new FragmentTwo(), true);
    }

    @Override
    public void onFront02() {
        mActivity.replaceContent(new FragmentTwo(), true);
    }

    @Override
    public void onFront03() {

    }

    @Override
    public void onBack01() {
        mActivity.backTopFragment();
    }

    @Override
    public void onBack02() {

    }

    @Override
    public void onBack03() {

    }
}