package com.boylab.fragmentdemo.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;

import com.boylab.fragmentdemo.R;

public class ActionView extends RelativeLayout implements View.OnClickListener {

    private Button btn_01, btn_03, btn_05, btn_07;
    private Button btn_02, btn_04, btn_06, btn_08;
    private IActionClick iActionClick =null;

    public ActionView(Context context) {
        this(context, null);
    }

    public ActionView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ActionView(Context context, AttributeSet attrs, int defStyleAttr) {
        this(context, attrs, defStyleAttr, 0);
    }

    public ActionView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        initView();
    }

    private void initView() {
        LayoutInflater.from(getContext()).inflate(R.layout.layout_action_view, this);
        btn_01 = findViewById(R.id.btn_01);
        btn_03 = findViewById(R.id.btn_03);
        btn_05 = findViewById(R.id.btn_05);
        btn_07 = findViewById(R.id.btn_07);

        btn_02 = findViewById(R.id.btn_02);
        btn_04 = findViewById(R.id.btn_04);
        btn_06 = findViewById(R.id.btn_06);
        btn_08 = findViewById(R.id.btn_08);

        btn_01.setOnClickListener(this);
        btn_03.setOnClickListener(this);
        btn_05.setOnClickListener(this);
        btn_07.setOnClickListener(this);

        btn_02.setOnClickListener(this);
        btn_04.setOnClickListener(this);
        btn_06.setOnClickListener(this);
        btn_08.setOnClickListener(this);
    }

    public void setiActionClick(IActionClick iActionClick) {
        this.iActionClick = iActionClick;
    }

    @Override
    public void onClick(View v) {
        if (iActionClick == null){
            return;
        }
        if(v.getId() == R.id.btn_01){
            iActionClick.onFront01();
        }else if(v.getId() == R.id.btn_03){
            iActionClick.onFront02();
        }else if(v.getId() == R.id.btn_05){
            iActionClick.onFront03();
        }else if(v.getId() == R.id.btn_07){
            iActionClick.onFront04();
        }else if(v.getId() == R.id.btn_02){
            iActionClick.onBack01();
        }else if(v.getId() == R.id.btn_04){
            iActionClick.onBack02();
        }else if(v.getId() == R.id.btn_06){
            iActionClick.onBack03();
        }else if(v.getId() == R.id.btn_08){
            iActionClick.onBack04();
        }
    }

    public interface IActionClick{
        void onFront01();
        void onFront02();
        void onFront03();
        void onFront04();
        void onBack01();
        void onBack02();
        void onBack03();
        void onBack04();
    }

}
