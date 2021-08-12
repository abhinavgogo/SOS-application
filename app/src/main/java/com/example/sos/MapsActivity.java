package com.example.sos;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.FragmentActivity;

import android.Manifest;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.util.Log;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.example.sos.databinding.ActivityMapsBinding;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;

    private LocationManager locationManager;
    private LocationListener locationListener;
    private final long MIN_TIME = 300000;
    private final long MIN_DIST = 5;

    private LatLng latLng;

    private ActivityMapsBinding binding;

    SharedPreferences sharedPreferences;
    private static final String SHARED_PREF_NAME = "mypref";
    private static final String KEY_NUM = "phno";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMapsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.LOCATION_HARDWARE}, PackageManager.PERMISSION_GRANTED);
        ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.SEND_SMS}, PackageManager.PERMISSION_GRANTED);
        ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, PackageManager.PERMISSION_GRANTED);
        ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.INTERNET},PackageManager.PERMISSION_GRANTED);


//        //Access shared preferences here
//        sharedPreferences = getSharedPreferences("NumberStorage", Context.MODE_PRIVATE);
//        int phoneNumbers[]= {sharedPreferences.getInt("Number1",0),sharedPreferences.getInt("Number2",0)
//                ,sharedPreferences.getInt("Number3",0)};



    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        LatLng sydney = new LatLng(-34, 151);
        mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));

        locationListener = location -> {
            try {
                latLng = new LatLng(location.getLatitude(),location.getLongitude());
                mMap.addMarker(new MarkerOptions().position(latLng).title("MY LOCATION!"));
                mMap.moveCamera(CameraUpdateFactory.newLatLng(latLng));




                String myLatitude = String.valueOf(location.getLatitude());
                String myLongitude = String.valueOf(location.getLongitude());
                String message = "SOS!\nThis is a call for help sending my coordinates \n"+"Latitude = "+myLatitude + "\nLongtitude = "+myLongitude
                        +"\nPS: this is only for my project";

                sharedPreferences = getSharedPreferences(SHARED_PREF_NAME,MODE_PRIVATE);

                //String phno[10];

                String phno = sharedPreferences.getString(KEY_NUM,null);

//                if(phno!=null) {
                    SmsManager smsManager = SmsManager.getDefault();
                    smsManager.sendTextMessage(String.valueOf(phno), null, message, null, null);
                    Toast.makeText(MapsActivity.this, "SOS SENT", Toast.LENGTH_SHORT).show();
                //}
//                else {
//                    Toast.makeText(MapsActivity.this,"No Numbers selected",Toast.LENGTH_LONG).show();
//                }

                //String phnos[] = {"9606161388","8861103260"};







                //Access shared preferences here
//                sharedPreferences = getSharedPreferences("NumberStorage", Context.MODE_PRIVATE);
//                int phoneNumbers[]= {sharedPreferences.getInt("Number1",0),sharedPreferences.getInt("Number2",0)
//                        ,sharedPreferences.getInt("Number3",0)};



//                for(int i=0;i<= phoneNumbers.length;i++) {
//                    SmsManager smsManager = SmsManager.getDefault();
//                    smsManager.sendTextMessage(String.valueOf(phoneNumbers[i]),null, message, null, null);
//                }
                /*
                for(int i=0;i<= phoneNumbers.length;i++) {
                    SmsManager smsManager = SmsManager.getDefault();
                    smsManager.sendTextMessage(phoneNumbers[i], null, message, null, null);
                    Toast.makeText(MapsActivity.this, "SOS SENT", Toast.LENGTH_SHORT).show();
                }
                */

                /*
                    SmsManager smsManager = SmsManager.getDefault();
                    smsManager.sendTextMessage(phoneNumber1,null,message,null,null);
                    Toast.makeText(MapsActivity.this, "SOS SENT", Toast.LENGTH_SHORT).show();
                */
            }
            catch (Exception e){
                e.printStackTrace();
            }
        };
        locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);
        try {
            locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, MIN_TIME,MIN_DIST,locationListener);
            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER,MIN_TIME,MIN_DIST,locationListener);
        }
        catch (SecurityException e){
            e.printStackTrace();
        }

    }
}