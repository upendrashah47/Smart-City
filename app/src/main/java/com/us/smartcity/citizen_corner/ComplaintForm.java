package com.us.smartcity.citizen_corner;


import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.us.smartcity.R;
import com.us.smartcity.ui.CitizenFragment;

import java.io.IOException;
import java.util.List;
import java.util.Locale;


/**
 * Created by Bhaskar on 24-10-2015.
 */
public class ComplaintForm extends AppCompatActivity implements AdapterView.OnItemSelectedListener {


    private static final int SELECT_FILE = 1;
    private static final int TAKE_PICK = 1;
    Spinner spinner1, spinner2;
    Button sub, can;
    ImageView iv1, iv2, iv3;
    EditText comp_desc, cont_pers, address1, mob_no;
    String comp_desc_s, cont_pers_s, address1_s, mob_no_s;

    String lat = "", lon = "";
    Button btnShowAddress;
    TextView tvAddress;

    private String selectedImagePath;
    private static Bitmap userImage1, userImage2, userImage3;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.complaintform);

        can = (Button) findViewById(R.id.cancle);
        btnShowAddress = (Button) findViewById(R.id.btnShowAddress);

        btnShowAddress.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                LocationManager locationManager = (LocationManager) ComplaintForm.this.getSystemService(Context.LOCATION_SERVICE);
                // Define a listener that responds to location updates
                LocationListener locationListener = new LocationListener() {
                    public void onLocationChanged(Location location) {
                        // Called when a new location is found by the network location provider.
                        lat = Double.toString(location.getLatitude());
                        lon = Double.toString(location.getLongitude());

                        tvAddress = (TextView) findViewById(R.id.tvAddress);
                        tvAddress.setText(GetAddress(lat, lon));
                    }

                    @Override
                    public void onStatusChanged(String provider, int status, Bundle extras) {

                    }

                    @Override
                    public void onProviderEnabled(String provider) {

                    }

                    @Override
                    public void onProviderDisabled(String provider) {

                    }
                };
                // Register the listener with the Location Manager to receive location updates
                locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 0, 0, locationListener);

            }
        });

        sub = (Button) findViewById(R.id.submit);
        sub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                comp_desc = (EditText) findViewById(R.id.comp_desc);
                cont_pers = (EditText) findViewById(R.id.cont_pers);
                mob_no = (EditText) findViewById(R.id.mob_no);

                comp_desc_s = comp_desc.getText().toString();
                cont_pers_s = cont_pers.getText().toString();
                mob_no_s = mob_no.getText().toString();

                if (comp_desc_s.equals("") || cont_pers_s.equals("") || mob_no_s.equals("")) {
                    if (comp_desc_s.equals("")) {
                        Toast.makeText(ComplaintForm.this, "Enter Complaint Description",
                                Toast.LENGTH_LONG).show();

                    } else if (cont_pers_s.equals("")) {
                        Toast.makeText(ComplaintForm.this, "Enter Contact Person",
                                Toast.LENGTH_LONG).show();

                    } else if (mob_no_s.equals("")) {
                        Toast.makeText(ComplaintForm.this, "Enter Mobile Number",
                                Toast.LENGTH_LONG).show();

                    }

                } else {
                    AlertDialog.Builder alertDialog = new AlertDialog.Builder(ComplaintForm.this);
                    // Setting Dialog Title
                    alertDialog.setTitle("Confirm Complaint...");
                    // Setting Dialog Message
                    alertDialog.setMessage("Are you sure?");
                    // Setting Icon to Dialog
                    // Setting Positive "Yes" Button
                    alertDialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            // Write your code here to invoke YES event
                            Toast.makeText(getApplicationContext(), "We Recevied Your Complaint ", Toast.LENGTH_SHORT).show();
                            Intent i = new Intent(getApplicationContext(), CitizenFragment.class);
                            startActivity(i);
                        }
                    });

                    // Setting Negative "NO" Button
                    alertDialog.setNegativeButton("Cancle", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            // Write your code here to invoke NO event
                            dialog.cancel();
                        }
                    });

                    // Showing Alert Message
                    alertDialog.show();

                }
            }
        });

        TextView txtProduct = (TextView) findViewById(R.id.complaint_name);

        Intent i = getIntent();
        // getting attached intent data
        String product = i.getStringExtra("product");
        // displaying selected product name
        txtProduct.setText(product);

        if (savedInstanceState != null) {
            userImage1 = savedInstanceState.getParcelable("userImage1");
        }

        spinner1 = (Spinner) findViewById(R.id.sector);
        spinner2 = (Spinner) findViewById(R.id.area);
        iv1 = (ImageView) findViewById(R.id.imageView1);
        iv2 = (ImageView) findViewById(R.id.imageView2);
        iv3 = (ImageView) findViewById(R.id.imageView3);

        iv1.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);


                startActivityForResult(intent, 1);

            }
        });

        iv2.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);


                startActivityForResult(intent, 2);
            }
        });
        iv3.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);


                startActivityForResult(intent, 3);
            }
        });

        can.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                Intent i2 = new Intent(getApplicationContext(), CitizenFragment.class);
                startActivity(i2);
            }
        });
        spinner1.setOnItemSelectedListener(this);
    }

    private String GetAddress(String lat, String lon) {
        final Geocoder geocoder = new Geocoder(this, Locale.ENGLISH);
        String ret = "";

        try {
            List<Address> addresses = geocoder.getFromLocation(Double.parseDouble(lat), Double.parseDouble(lon), 1);
            if (addresses != null) {
                Address returnedAddress = addresses.get(0);
                StringBuilder strReturnedAddress = new StringBuilder("Address:\n");
                for (int i = 0; i < returnedAddress.getMaxAddressLineIndex(); i++) {
                    strReturnedAddress.append(returnedAddress.getAddressLine(i)).append("\n");
                }
                ret = strReturnedAddress.toString();
            } else {
                ret = "No Address returned!";
            }
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            ret = "Can't get Address!";
        }
        return ret;
    }


    @Override
    public void onItemSelected(AdapterView<?> parent, View arg1, int pos, long arg3) {
        parent.getItemAtPosition(pos);

        if (pos == 0) {

            ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.west, android.R.layout.simple_spinner_item);
            spinner2.setAdapter(adapter);
        } else if (pos == 1) {
            ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.East, android.R.layout.simple_spinner_item);
            spinner2.setAdapter(adapter);
        } else if (pos == 2) {
            ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.south, android.R.layout.simple_spinner_item);

            spinner2.setAdapter(adapter);

        } else if (pos == 3) {
            ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.north, android.R.layout.simple_spinner_item);
            spinner2.setAdapter(adapter);

        } else if (pos == 4) {
            ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.central, android.R.layout.simple_spinner_item);

            spinner2.setAdapter(adapter);

        } else if (pos == 5) {
            ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.new_west, android.R.layout.simple_spinner_item);
            spinner2.setAdapter(adapter);

        }

    }

    @Override
    public void onNothingSelected(AdapterView<?> arg0) {

    }


    @Override
    protected void onActivityResult(int requestCode,
                                    int resultCode, Intent data) {

        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {

            case 1:

                if (resultCode == RESULT_OK) {
                    userImage1 = (Bitmap) data.getExtras().get("data");
                    Bundle extras = data.getExtras();
                    userImage1 = (Bitmap) extras.get("data");
                    iv1.setImageBitmap(userImage1);
                    //if resultCode Case// 1
                }


                break;
            case 2:
                if (resultCode == RESULT_OK) {
                    userImage2 = (Bitmap) data.getExtras().get("data");
                    Bundle extras = data.getExtras();
                    userImage2 = (Bitmap) extras.get("data");
                    iv2.setImageBitmap(userImage2);

                }//if resultCode Case 2


                break;

            case 3:
                if (resultCode == RESULT_OK) {
                    userImage3 = (Bitmap) data.getExtras().get("data");
                    Bundle extras = data.getExtras();
                    userImage3 = (Bitmap) extras.get("data");
                    iv3.setImageBitmap(userImage3);

                }/*if resultCode Case 3
                else if (resultCode == RESULT_OK) {
                    Uri selectedImageUri = data.getData();
                    String[] projection = { MediaStore.MediaColumns.DATA };
                    CursorLoader cursorLoader = new CursorLoader(this,selectedImageUri, projection, null, null,
                            null);
                    Cursor cursor =cursorLoader.loadInBackground();
                    int column_index = cursor.getColumnIndexOrThrow(MediaStore.MediaColumns.DATA);
                    cursor.moveToFirst();
                    String selectedImagePath = cursor.getString(column_index);

                    BitmapFactory.Options options = new BitmapFactory.Options();
                    options.inJustDecodeBounds = true;
                    BitmapFactory.decodeFile(selectedImagePath, options);
                    final int REQUIRED_SIZE = 200;
                    int scale = 1;
                    while (options.outWidth / scale / 2 >= REQUIRED_SIZE
                            && options.outHeight / scale / 2 >= REQUIRED_SIZE)
                        scale *= 2;
                    options.inSampleSize = scale;
                    options.inJustDecodeBounds = false;
                    userImage3 = BitmapFactory.decodeFile(selectedImagePath, options);
                    iv3.setImageBitmap(userImage3);
                }*/

                break;

        }//Switch

    }//onActivityResult
}









