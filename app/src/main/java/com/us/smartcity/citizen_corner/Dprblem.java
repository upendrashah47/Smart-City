package com.us.smartcity.citizen_corner;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import com.us.smartcity.R;
import com.us.smartcity.citizen_corner.drainagepop.DrainDialog;
import com.us.smartcity.citizen_corner.garbagepop.GarbageDialog;
import com.us.smartcity.citizen_corner.roadpop.RoadDialog;
import com.us.smartcity.citizen_corner.waterpop.WaterDialog;

public class Dprblem extends Fragment {
    public ArrayAdapter<String> gridAdapter1;
    gridView gridAdapter;
    Intent i;

    public Dprblem() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.dprblm, container, false);
        GridView grid=(GridView)rootView.findViewById(R.id.gridView1);

        final String[] mainMenuArray = {
                "Water",
                "Garbage",
                "Drainage",
                "Roads",
                "Street Light",
                "Stray Animals",
                "Food Adulteration",
                "Fallen Tree",
                "Illegal Construction",
                "Public Toilet",
                "Dead Animals",
                "E-waste"

        } ;
        int[] imageId = {
                R.drawable.watericon,

                R.drawable.garbage,

                R.drawable.drainage,

                R.drawable.ic_road,

                R.drawable.streetlgt,

                R.drawable.strayanim,

                R.drawable.foodadult,

                R.drawable.fallentree,

                R.drawable.illegalconstruction,

                R.drawable.toilets,

                R.drawable.deadanim,

                R.drawable.ewaste,
        };

        gridAdapter = new gridView(getActivity(), mainMenuArray, imageId);
        grid.setAdapter(gridAdapter);


        final FragmentManager fm =getFragmentManager();
        final WaterDialog p=new WaterDialog();
        final GarbageDialog g=new GarbageDialog();
        final DrainDialog d=new DrainDialog();
        final RoadDialog r=new RoadDialog();

        grid.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                switch (position) {
                    case 0:
                        p.show(fm,"Subcategories");
                        break;
                    case 1:
                        g.show(fm,"Subcategories");
                        break;
                    case 2:
                        d.show(fm, "Subcategories");
                        break;
                    case 3:
                        r.show(fm, "Subcategories");
                        break;
                    case 4:
                        i = new Intent(getActivity().getApplicationContext(), ComplaintForm.class);
                        // sending data to new activity
                        i.putExtra("product","Street Light");
                        getActivity().startActivity(i);
                        break;
                    case 5:
                        i = new Intent(getActivity().getApplicationContext(), ComplaintForm.class);
                        // sending data to new activity
                        i.putExtra("product","Stray Animals");
                        getActivity().startActivity(i);
                        break;
                    case 6:
                        i = new Intent(getActivity().getApplicationContext(), ComplaintForm.class);
                        // sending data to new activity
                        i.putExtra("product","Food Adulteration");
                        getActivity().startActivity(i);
                        break;
                    case 7:
                        i = new Intent(getActivity().getApplicationContext(), ComplaintForm.class);
                        // sending data to new activity
                        i.putExtra("product","Fallen Tree");
                        getActivity().startActivity(i);
                        break;
                    case 8:
                        i = new Intent(getActivity().getApplicationContext(), ComplaintForm.class);
                        // sending data to new activity
                        i.putExtra("product","Illegal Construction");
                        getActivity().startActivity(i);
                        break;
                    case 9:
                        i = new Intent(getActivity().getApplicationContext(), ComplaintForm.class);
                        // sending data to new activity
                        i.putExtra("product","Public Toilet");
                        getActivity().startActivity(i);
                        break;
                    case 10:
                        i = new Intent(getActivity().getApplicationContext(), ComplaintForm.class);
                        // sending data to new activity
                        i.putExtra("product","Dead Animals");
                        getActivity().startActivity(i);
                        break;
                    case 11:
                        i = new Intent(getActivity().getApplicationContext(), ComplaintForm.class);
                        // sending data to new activity
                        i.putExtra("product", "E-waste");
                        getActivity().startActivity(i);
                        break;
                }
            }
        });
        return rootView;

    }




    public class gridView extends BaseAdapter{
        private Context mContext;
        private final String[] mainMenuArray;
        private final int[] Imageid;
        public gridView(Context c,String[] mainMenuArray,int[] Imageid ) {
            mContext = c;
            this.Imageid = Imageid;
            this.mainMenuArray = mainMenuArray;
        }
        @Override
        public int getCount() {
            // TODO Auto-generated method stub
            return mainMenuArray.length;
        }
        @Override
        public Object getItem(int position) {
            // TODO Auto-generated method stub
            return null;
        }
        @Override
        public long getItemId(int position) {
            // TODO Auto-generated method stub
            return 0;
        }
        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            // TODO Auto-generated method stub
            View grid;
            LayoutInflater inflater = (LayoutInflater) mContext
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            if (convertView == null) {
                grid = new View(mContext);
                grid = inflater.inflate(R.layout.griddprblem, null);

            } else {
                grid = (View) convertView;
            }
            TextView textView = (TextView) grid.findViewById(R.id.tvTitle);
            ImageView imageView = (ImageView)grid.findViewById(R.id.ivIcon);
            textView.setText(mainMenuArray[position]);
            imageView.setImageResource(Imageid[position]);
            return grid;
        }
    }
}