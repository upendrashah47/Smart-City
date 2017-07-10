package com.us.smartcity.ui;

/**
 * Created by Bhaskar on 02-11-2015.
 */

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import com.us.smartcity.Home.HomeAdapter;
import com.us.smartcity.R;
import com.us.smartcity.download.BirthForm;
import com.us.smartcity.download.DeathForm;
import com.us.smartcity.download.MrgForm;

public class DownloadFormActivity extends AppCompatActivity {
    Button button;
    String[] text = {
            "  Birth Certificate Application Form",
            "  Death Certificate Application Form",
            "  Marriage Certificate Application Form",
            "  constructors Form",
            "  Heritage cell Certificate Application Form"
    };
    Integer[] imgs = {
            R.drawable.marriageform,
            R.drawable.marriageform,
            R.drawable.marriageform,
            R.drawable.marriageform,
            R.drawable.marriageform
    };
    ListView list;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.download_form_activity);


        HomeAdapter adapter = new HomeAdapter(DownloadFormActivity.this, text, imgs);
        list = (ListView) findViewById(R.id.listView3);
        list.setAdapter(adapter);
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {


            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                switch (position) {
                    case 0:
                        Intent i = new Intent(getApplicationContext(), BirthForm.class);
                        startActivity(i);
                        break;
                    case 1:
                        Intent j = new Intent(getApplicationContext(), DeathForm.class);
                        startActivity(j);
                        break;
                    case 2:
                        Intent k = new Intent(getApplicationContext(), MrgForm.class);
                        startActivity(k);
                        break;
                    case 3:
                        Intent l = new Intent(getApplicationContext(), MrgForm.class);
                        startActivity(l);
                        break;

                }
            }
        });

    }
}