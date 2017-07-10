package com.us.smartcity.citizen_corner.waterpop;

import android.support.v4.app.DialogFragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageButton;

import com.us.smartcity.citizen_corner.ComplaintForm;
import com.us.smartcity.R;

/**
 * Created by Bhaskar on 03-03-2016.
 */
public class WaterDialog extends DialogFragment {
    Intent i;
    ImageButton closebtn;
    private boolean m_status;


    int[] img={R.drawable.insuffwater,R.drawable.nowater,R.drawable.contwater,R.drawable.waterleak};
    String[] txt={"Insufficient water","No Water Supply","Contaiminated Water","Water Leakage"};



    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.dialougewater,container );
        final GridView grid = (GridView) rootView.findViewById(R.id.waterdialog);



        getDialog().setTitle("Sub categories");

        CustomAdapter adapter=new CustomAdapter(getActivity(),txt,img);
        grid.setAdapter(adapter);

        grid.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                switch (position) {
                    case 0:

                        String product = parent.getItemAtPosition(position).toString();

                        // Launching new Activity on selecting single List Item
                        i = new Intent(getActivity().getApplicationContext(), ComplaintForm.class);
                        // sending data to new activity
                        i.putExtra("product", product);
                        startActivity(i);
                        break;
                    case 1:
                        String product1 = parent.getItemAtPosition(position).toString();

                        // Launching new Activity on selecting single List Item
                        i = new Intent(getActivity().getApplicationContext(), ComplaintForm.class);
                        // sending data to new activity
                        i.putExtra("product", product1);
                        startActivity(i);
                        break;
                    case 2:
                        String product2 = parent.getItemAtPosition(position).toString();

                        // Launching new Activity on selecting single List Item
                        i = new Intent(getActivity().getApplicationContext(), ComplaintForm.class);
                        // sending data to new activity
                        i.putExtra("product", product2);
                        startActivity(i);
                    case 3:
                        String product3 = parent.getItemAtPosition(position).toString();

                        // Launching new Activity on selecting single List Item
                        i = new Intent(getActivity().getApplicationContext(), ComplaintForm.class);
                        // sending data to new activity
                        i.putExtra("product", product3);
                        startActivity(i);
                        break;

                }
            }
        });

 return rootView;
    }
}
