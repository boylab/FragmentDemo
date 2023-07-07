package com.boylab.fragmentdemo.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.FragmentResultListener;

import com.boylab.fragmentdemo.R;
import com.boylab.fragmentdemo.SecondActivity;
import com.boylab.fragmentdemo.base.BaseFragment;
import com.boylab.fragmentdemo.bean.ResultContract;
import com.boylab.fragmentdemo.view.ActionView;

public class FragmentTwo extends BaseFragment implements ActionView.IActionClick, View.OnClickListener {

    private ActionView actionView;
    private Button btn_Fragment, btn_Activity;
    private TextView text_Fragment, text_Activity;

    @Override
    protected int setRootView() {
        return R.layout.fragment_two;
    }

    @Override
    protected void initView(View rootView) {
        actionView = rootView.findViewById(R.id.actionView);
        actionView.setiActionClick(this);

        btn_Fragment = rootView.findViewById(R.id.btn_Fragment);
        btn_Activity = rootView.findViewById(R.id.btn_Activity);
        text_Fragment = rootView.findViewById(R.id.text_Fragment);
        text_Activity = rootView.findViewById(R.id.text_Activity);
        btn_Fragment.setOnClickListener(this);
        btn_Activity.setOnClickListener(this);
    }

    @Override
    protected void initData() {

    }

    @Override
    public void onFront01() {
        add(new FragmentThree(), true);
    }

    @Override
    public void onFront02() {
        add(new FragmentThree(), false);
    }

    @Override
    public void onFront03() {
        replace(new FragmentThree(), true);
    }

    @Override
    public void onFront04() {
        replace(new FragmentThree(), false);
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

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btn_Fragment){
            addForResult(new FragmentThree(), "twoResult", new FragmentResultListener() {
                @Override
                public void onFragmentResult(@NonNull String requestKey, @NonNull Bundle result) {
                    Log.i(">>>boylab>>>", "onFragmentResult: "+requestKey);
                    text_Fragment.setText("requestKey = "+requestKey);
                    text_Fragment.append("\n");
                    text_Fragment.append("key = "+result.getString("key"));

                }
            });
        }else if (v.getId() == R.id.btn_Activity){
            /**
             * Fragment 跳转 Activity 并获得回调
             */
            Intent intent = new Intent(getContext(), SecondActivity.class);
            activityLauncher.launch(intent);
        }
    }

    private ActivityResultLauncher activityLauncher = registerForActivityResult(new ResultContract(), new ActivityResultCallback<ResultContract.ResultBean>(){

        @Override
        public void onActivityResult(ResultContract.ResultBean result) {
            //Log.i(">>>boylab>>>", "onActivityResult: result = "+result);
            if (result != null){
                text_Activity.setText(result.toString());
            }
        }
    });
}