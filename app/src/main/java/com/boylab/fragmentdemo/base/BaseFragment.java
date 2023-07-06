package com.boylab.fragmentdemo.base;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.StringRes;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentResultListener;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleEventObserver;
import androidx.lifecycle.LifecycleOwner;

public abstract class BaseFragment extends Fragment {
    protected BaseActivity mActivity;

    protected abstract int setRootView();

    protected abstract void initView(View rootView);

    protected abstract void initData();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(setRootView(), container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView(view);
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        //requireActivity() 返回的是宿主activity
        requireActivity().getLifecycle().addObserver(new LifecycleEventObserver() {
            @Override
            public void onStateChanged(@NonNull LifecycleOwner source, @NonNull Lifecycle.Event event) {
                if (event.getTargetState() == Lifecycle.State.CREATED){
                    //在这里任你飞翔
                    Log.i(">>>boylab>>>", "onStateChanged: 什么时候执行2");
                    mActivity = (BaseActivity) requireActivity();
                    initData();

                    requireActivity().getLifecycle().removeObserver(this);  //这里是删除观察者
                }
            }
        });
    }

    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
    }


    @Override
    public void onStop() {
        super.onStop();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    /***
     * 以下是Fragment操作方法
     * @param fragment
     */

    public void add(BaseFragment fragment) {
        mActivity.add(fragment, true);
    }

    public void add(BaseFragment fragment, boolean addToBackStack) {
        mActivity.add(fragment, addToBackStack);
    }

    public void addForResult(BaseFragment fragment, String requestKey, FragmentResultListener listener) {
        mActivity.addForResult(fragment, requestKey, listener);
    }

    public void replace(BaseFragment fragment) {
        mActivity.replace(fragment, true);
    }

    public void replace(BaseFragment fragment, boolean addToBackStack) {
        mActivity.replace(fragment, addToBackStack);
    }

    public void replaceForResult(BaseFragment fragment, String requestKey, FragmentResultListener listener) {
        mActivity.replaceForResult(fragment, requestKey, listener);
    }

    public void setFragmentResult(@NonNull String requestKey, @NonNull Bundle result){
        mActivity.getSupportFragmentManager().setFragmentResult(requestKey, result);
    }

    public void pop(){
        mActivity.pop();
    }

    public void popTo(Class clazz){
        mActivity.popTo(clazz);
    }

    public void popToTop() {
        mActivity.popToTop();
    }

    public int getFragmentNum() {
        return mActivity.getFragmentNum();
    }

    public BaseFragment getFragment() {
        return mActivity.getmFragment();
    }

    /**
     * 显示Toast
     * @param resId
     */
    public void showToast(@StringRes int resId) {
        showToast(getResources().getString(resId));
    }

    public void showToast(String msg) {
        Toast.makeText(getContext(), msg, Toast.LENGTH_SHORT).show();
    }

}
