package com.boylab.fragmentdemo.base;

import android.util.Log;
import android.widget.Toast;

import androidx.annotation.StringRes;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.boylab.fragmentdemo.R;
import com.boylab.fragmentdemo.fragment.FragmentTwo;

import java.lang.reflect.Type;
import java.util.ArrayList;

/**
 * 专门管理Fragment加载、回退
 */
public class FragmentHold extends AppCompatActivity {

    private final int container = R.id.container;
    private ArrayList<BaseFragment> fragments = new ArrayList<>();// back fragment list.
    private BaseFragment fragment;// current fragment.

    /**
     * 加载Fragment页面（默认压栈）
     * @param fragment
     * @param addToBackStack
     */
    public void add(BaseFragment fragment, boolean addToBackStack) {
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.add(container, fragment);
        if (addToBackStack) {
            ft.addToBackStack(null);
        } else {
            removePrevious();
        }

        ft.commitAllowingStateLoss();
        getSupportFragmentManager().executePendingTransactions();

        fragments.add(fragment);
        setFragment();
    }

    /**
     * 用replace方式，加载Fragment页面（默认压栈）
     * replace()、add()最终都是调用 doAddOp()方法
     * @param fragment
     * @param addToBackStack
     */
    public void replace(BaseFragment fragment, boolean addToBackStack) {
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(container, fragment);
        if (addToBackStack) {
            ft.addToBackStack(null);
        } else {
            removePrevious();
        }
        ft.commitAllowingStateLoss();
        getSupportFragmentManager().executePendingTransactions();

        fragments.add(fragment);
        setFragment();
    }

    /**
     * remove current fragment and back to front fragment.
     */
    public void pop(){
        removePrevious();
        setFragment();
        getSupportFragmentManager().popBackStack();
    }

    /**
     * 弹出到指定压栈页面（注意：如果Class没有，将退回栈顶）
     * @param clazz
     */
    public void popTo(Class clazz){
        if (fragments != null && fragments.size() > 1) {
            if (!fragment.getClass().equals(clazz)){
                pop();
                popTo(clazz);
            }
        }
    }

    /**
     * 弹出到栈顶
     */
    public void popToTop() {
        if (fragments != null && fragments.size() > 1) {
            pop();
            popToTop();
        }
    }

    /**
     * get amount of fragment.
     * @return
     */
    public int getFragmentNum() {
        return fragments != null ? fragments.size() : 0;
    }

    /**
     * get the current fragment.
     * @return
     */
    public BaseFragment getFragment() {
        return fragment;
    }

    /**
     * set current fragment.
     */
    private void setFragment() {
        if (fragments != null && fragments.size() > 0) {
            fragment = fragments.get(fragments.size() - 1);
        } else {
            fragment = null;
        }
    }

    /**
     * remove previous fragment
     */
    private void removePrevious() {
        if (fragments != null && fragments.size() > 0) {
            fragments.remove(fragments.size() - 1);
        }
    }

    /**
     * clear fragment list
     */
    private void clearFragments() {
        if (fragments != null) {
            fragments.clear();
        }
    }

    /**
     * remove all fragment from back stack.
     */
    protected void popAllFragment() {
        clearFragments();
        setFragment();
        getSupportFragmentManager().popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);
    }


    /**
     * 显示Toast
     * @param resId
     */
    public void showToast(@StringRes int resId) {
        showToast(getResources().getString(resId));
    }

    public void showToast(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }
}
