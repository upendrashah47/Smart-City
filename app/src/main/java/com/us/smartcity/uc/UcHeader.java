package com.us.smartcity.uc;

import android.app.Activity;
import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.us.smartcity.R;


public class UcHeader extends RelativeLayout implements View.OnClickListener {
    public Context context;
    public ImageView imgBack, imgClose;
    public EspTextView txtTitle;

    public UcHeader(Context context) {
        super(context);
        this.context = context;
        init();
    }

    public UcHeader(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        init();
    }

    public void init() {
        View view = LayoutInflater.from(context).inflate(R.layout.uc_header, this, true);
        imgBack = (ImageView) view.findViewById(R.id.imgBack);
        imgClose = (ImageView) view.findViewById(R.id.imgClose);
        txtTitle = (EspTextView) view.findViewById(R.id.txtTitle);

        imgBack.setOnClickListener(this);
        imgClose.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.imgBack:
                ((Activity) context).finish();
                break;
            case R.id.imgClose:

                break;
        }
    }
}