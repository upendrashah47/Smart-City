package com.us.smartcity.uc;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.TextView;

import com.us.smartcity.utils.Utils;


public class EspTextView extends TextView {
    public EspTextView(Context context) {
        super(context);
        this.setTypeface(Utils.getFont(context,
                Integer.parseInt(this.getTag().toString())));
    }

    public EspTextView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        this.setTypeface(Utils.getFont(context,
                Integer.parseInt(this.getTag().toString())));
    }

    public EspTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.setTypeface(Utils.getFont(context,
                Integer.parseInt(this.getTag().toString())));
    }
}