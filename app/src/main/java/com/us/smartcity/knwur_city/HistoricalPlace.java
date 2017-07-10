package com.us.smartcity.knwur_city;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.us.smartcity.ui.KnowYourCityDetailActivity;

/**
 * Created by Bhaskar on 31-10-2015.
 */
public class HistoricalPlace extends ListFragment {

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        String[] values = new String[]{"Adalaj ni Vav",
                "Kankaria",
                "Sabarmati Ashram",
                "Nagina Wadi",
                "Vastrapur Lake",
                "Jama Masjid",
                "Teen Darwaza",
                "Nehru Bridge",
                "Rani no Hajiro",
                "Sarkhej",
                "Sidi Bashir Mosque",
                "Sidi Saiyyed Mosque",
                "Sanskar Kendra"};
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(),
                android.R.layout.simple_list_item_1, values);
        setListAdapter(adapter);
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        // TODO implement some logic
        switch (position) {
            case 0:
                Intent i = new Intent(getActivity().getApplicationContext(), KnowYourCityDetailActivity.class);
                getActivity().startActivity(i);
                break;

            case 1:
                Intent j = new Intent(getActivity().getApplicationContext(), KnowYourCityDetailActivity.class);
                getActivity().startActivity(j);
                break;

            case 2:
                Intent k = new Intent(getActivity().getApplicationContext(), KnowYourCityDetailActivity.class);
                getActivity().startActivity(k);
                break;
        }

    }
}






