package com.us.smartcity.citizen_corner.drainagepop;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.us.smartcity.R;

/**
 * Created by Bhaskar on 03-03-2016.
 */
public class CustomAdapter extends BaseAdapter {
    private Fragment activity;

    private Context c;
    int[] img;
    private String[] txt;

    public CustomAdapter(Context ctx, String[] txt, int[] img) {
        this.activity=activity;
        this.c = ctx;
        this.img = img;
        this.txt = txt;
    }

    @Override
    public int getCount() {
        return txt.length;
    }

    @Override
    public Object getItem(int pos) {
        return txt[pos];
    }

    @Override
    public long getItemId(int pos) {
        return pos;
    }

    public View getView(int pos, View convertView, ViewGroup parent) {
        // TODO Auto-generated method stub

        View grid;
        LayoutInflater inflater = (LayoutInflater)c.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        if (convertView == null) {
            grid = new View(c);
            grid = inflater.inflate(R.layout.griddrain, null);

        } else {
            grid = (View) convertView;
        }

        ImageView imageView = (ImageView)grid.findViewById(R.id.dIcon);

        imageView.setImageResource(img[pos]);
        TextView textView = (TextView) grid.findViewById(R.id.dTitle);
        textView.setText(txt[pos]);
        return grid;
    }
}
