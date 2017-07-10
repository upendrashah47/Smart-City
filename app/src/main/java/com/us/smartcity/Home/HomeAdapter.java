package com.us.smartcity.Home;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.us.smartcity.R;

import java.util.ArrayList;


/**
 * Created by Bhaskar on 27-02-2016.
 */
public class HomeAdapter extends ArrayAdapter<String> {

    private final Activity context;
    private final String[] text;
    private final Integer[] imgs;
    LayoutInflater inflater;

    public HomeAdapter(Activity context,
                       String[] text, Integer[] imgs) {
        super(context, R.layout.card_dwnldfrm, text);
        this.context = context;
        this.text = text;
        this.imgs = imgs;
        inflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);

    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View vi = convertView;
        vi = inflater.inflate(R.layout.card_dwnldfrm, parent, false);
        TextView txtTitle = (TextView) vi.findViewById(R.id.info_text);

        ImageView imageView = (ImageView) vi.findViewById(R.id.some_page);
        txtTitle.setText(text[position]);

        imageView.setImageResource(imgs[position]);
        return vi;
    }
}


