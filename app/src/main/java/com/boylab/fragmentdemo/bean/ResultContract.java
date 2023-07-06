package com.boylab.fragmentdemo.bean;

import android.content.Context;
import android.content.Intent;

import androidx.activity.result.contract.ActivityResultContract;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class ResultContract extends ActivityResultContract<Intent, ResultContract.ResultBean> {

    @NonNull
    @Override
    public Intent createIntent(@NonNull Context context, Intent input) {
        return input;
    }

    @Override
    public ResultBean parseResult(int resultCode, @Nullable Intent intent) {
        String value = intent.getStringExtra("value");
        return new ResultBean(resultCode, value);
    }


    public class ResultBean {

        private int resultCode;
        private String value;

        public ResultBean() {
        }

        public ResultBean(int resultCode, String value) {
            this.resultCode = resultCode;
            this.value = value;
        }

        public int getResultCode() {
            return resultCode;
        }

        public void setResultCode(int resultCode) {
            this.resultCode = resultCode;
        }

        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }

        @Override
        public String toString() {
            return "ResultBean{" +
                    "resultCode=" + resultCode +
                    ", value='" + value + '\'' +
                    '}';
        }
    }
}
