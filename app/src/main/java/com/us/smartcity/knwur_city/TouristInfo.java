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
public class TouristInfo extends ListFragment {

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        String[] values = new String[]{"Gandhi Ashram",
                "Kankaria Lake",
                "Sidi Saiyyed Masjid",
                "Manek Chowk",
                "Lal Darwaja",
                "Vastrapur Lake",
                "Motera Stadium(Sardar Patel Stadium)",
                "Jama Masjid",
                "Lalbhai Dalpatbhai Museum",
                "Rani ni Hajiro"};
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
        }
    }
}

