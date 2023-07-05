package com.boylab.fragmentdemo.base;

import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

import androidx.annotation.StringRes;

public class BaseActivity extends FragmentHold {

    //记录用户首次点击返回键的时间
    private long firstTime = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Window window = getWindow();
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
//        window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
//        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS //
//                | WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
//        window.setStatusBarColor(Color.TRANSPARENT);
//        window.setNavigationBarColor(Color.TRANSPARENT);
    }

    /**
     * 监听返回动作
     */
    @Override
    public void onBackPressed() {
        //super.onBackPressed();
        if (getFragmentNum() <= 1) {
            if (isFastBack()) {
                finish();
            }
        } else {
            pop();
        }
    }

    /**
     * 是否双击返回退出
     *
     * @return
     */
    private boolean isFastBack() {
        long secondTime = System.currentTimeMillis();
        if (secondTime - firstTime > 1000) {
            showToast("再按一次退出程序");
            firstTime = secondTime;
            return false;
        } else {
            return true;
        }
    }

}
