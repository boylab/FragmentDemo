package com.boylab.fragmentdemo.fragment;

import android.os.Bundle;
import android.view.View;

import com.boylab.fragmentdemo.R;
import com.boylab.fragmentdemo.base.BaseFragment;
import com.boylab.fragmentdemo.view.ActionView;

public class FragmentThree extends BaseFragment implements ActionView.IActionClick {

    private ActionView actionView;

    @Override
    protected int setRootView() {
        return R.layout.fragment_three;
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
        add(new FragmentFour(), true);
    }

    @Override
    public void onFront02() {
        add(new FragmentFour(), false);
    }

    @Override
    public void onFront03() {
        replace(new FragmentFour(), true);
    }

    @Override
    public void onFront04() {
        replace(new FragmentFour(), false);
    }

    @Override
    public void onBack01() {
        pop();
    }

    @Override
    public void onBack02() {
        popTo(FragmentTwo.class);
    }

    @Override
    public void onBack03() {
        popToTop();
    }

    @Override
    public void onBack04() {
        /**
         * Fragment返回上一页面传参
         */
        Bundle bundle = new Bundle();
        bundle.putString("key", "hello three");
        setFragmentResult("twoResult", bundle);
        pop();
    }
}