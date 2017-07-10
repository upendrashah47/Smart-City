package com.us.smartcity.knwur_city;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;


import com.us.smartcity.Traffic_detail.LightSignals;

/**
 * Created by Bhaskar on 29-10-2015.
 */
public class TrafficDetail extends ListFragment {

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        String[] values = new String[]{"Traffic Light Signals",
                "Traffic Police Hand Signals (Manual)",
                "Mandatory Signals",
                "Cautionary Signs",
                "Informatory Signs",
                "Road Markings",};
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(),
                android.R.layout.simple_list_item_1, values);
        setListAdapter(adapter);
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        // TODO implement some logic
        switch (position) {
            case 0:


                // Launching new Activity on selecting single List Item
                Intent i = new Intent(getActivity().getApplicationContext(), LightSignals.class);
                // sending data to new activity
                getActivity().startActivity(i);
                break;
        }

    }

}

