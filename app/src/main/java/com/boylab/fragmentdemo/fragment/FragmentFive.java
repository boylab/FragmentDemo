package com.boylab.fragmentdemo.fragment;

import android.view.View;

import com.boylab.fragmentdemo.R;
import com.boylab.fragmentdemo.base.BaseFragment;
import com.boylab.fragmentdemo.view.ActionView;


public class FragmentFive extends BaseFragment implements ActionView.IActionClick {

    private ActionView actionView;

    @Override
    protected int setRootView() {
        return R.layout.fragment_five;
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
        add(new FragmentSix(), true);
    }

    @Override
    public void onFront02() {
        add(new FragmentSix(), false);
    }

    @Override
    public void onFront03() {
        replace(new FragmentSix(), true);
    }

    @Override
    public void onFront04() {
        replace(new FragmentSix(), false);
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

    }
}