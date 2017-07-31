package com.us.smartcity.uc;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.Button;

import com.us.smartcity.utils.Utils;


public class EspButton extends Button {

    public EspButton(Context context) {
        super(context);
        this.setTypeface(Utils.getFont(context, Integer.parseInt(this.getTag().toString())));
    }

    public EspButton(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        this.setTypeface(Utils.getFont(context, Integer.parseInt(this.getTag().toString())));
    }

    public EspButton(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.setTypeface(Utils.getFont(context, Integer.parseInt(this.getTag().toString())));
    }
}