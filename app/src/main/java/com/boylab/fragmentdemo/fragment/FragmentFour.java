package com.boylab.fragmentdemo.fragment;

import android.os.Bundle;
import android.view.View;
import com.boylab.fragmentdemo.R;
import com.boylab.fragmentdemo.base.BaseFragment;
import com.boylab.fragmentdemo.view.ActionView;

public class FragmentFour extends BaseFragment implements ActionView.IActionClick {

    private ActionView actionView;

    @Override
    protected int setRootView() {
        return R.layout.fragment_four;
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
        add(new FragmentFive(), true);
    }

    @Override
    public void onFront02() {
        add(new FragmentFive(), false);
    }

    @Override
    public void onFront03() {
        replace(new FragmentFive(), true);
    }

    @Override
    public void onFront04() {
        replace(new FragmentFive(), false);
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
         * Fragment回调参数居然可以隔页面回调数据
         */
        Bundle bundle = new Bundle();
        bundle.putString("key", "hello four");
        setFragmentResult("twoResult", bundle);
        pop();
    }
}