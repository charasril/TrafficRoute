package com.example.win.trafficroute;

//import android.*;
//package com.example.akexorcist.googledirection.sample;


import android.Manifest;
import android.app.Activity;
import android.content.pm.PackageManager;
import android.database.sqlite.SQLiteDatabase;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;


/**
 * Created by win on 14/2/2560.
 */


public class SearchRouteList extends Activity implements OnMapReadyCallback,View.OnClickListener, TextView.OnEditorActionListener {
//   TabHost tabSearch,tabMap,tabHistList;
    EditText editTextStart ,editTextEnd;
    Button buttonSearch , buttonExit;
    ListView listViewHistList ;
//    private DatabaseHelper mHelper;
    private SQLiteDatabase mDatabase;
    private GoogleMap googleMap;
    private String serverKey = "AIzaSyDkxXWseLD9nGDV81y6DgBA1PLbwp5tzwU";
    private LatLng camera ;//= new LatLng(37.782437, -122.4281893);
    private LatLng origin ;//= new LatLng(37.7849569, -122.4068855);
    private LatLng destination = new LatLng(37.7814432, -122.4460177);
    private LocationManager locationManager;
    private Double startLatADouble = 0.0, startLngADouble = 0.0;
    private Double endLatADouble , endLngADouble;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.searchroutelist);

        editTextStart = (EditText) findViewById(R.id.editText_start);
        editTextEnd = (EditText) findViewById(R.id.editText_end);

        editTextStart.setOnEditorActionListener(this);
        editTextEnd.setOnEditorActionListener(this);

        buttonSearch = (Button) findViewById(R.id.button_search);
        buttonExit = (Button) findViewById(R.id.button_exit);
        buttonSearch.setOnClickListener(this);
        buttonExit.setOnClickListener(this);

        listViewHistList = (ListView) findViewById(R.id.listView_histlist);
//        mHelper = new DatabaseHelper(this);
//        mDatabase = mHelper.getWritableDatabase();

//        ((SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.fragment_map)).getMapAsync(this);

    }


    protected void onResume() {
        super.onResume();

        afterResume();
    }

    private void afterResume() {

        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        locationManager.removeUpdates(locationListener);
        //หาพิกัด
        Location networkLocation = myfindLocation(LocationManager.NETWORK_PROVIDER);
        if (networkLocation != null) {
            startLatADouble = networkLocation.getLatitude();
            startLngADouble = networkLocation.getLongitude();
        }

        Location gpsLocation = myfindLocation(LocationManager.GPS_PROVIDER);
        if (gpsLocation != null) {
            startLatADouble = gpsLocation.getLatitude();
            startLngADouble = gpsLocation.getLongitude();

        }
        Log.d("15FebV1", "Lat ==> " + startLatADouble);
        Log.d("15FebV1", "Lat ==> " + startLngADouble);


    } //after Resume


    public Location myfindLocation(String strProvider) {
        Location location = null;

        if (locationManager.isProviderEnabled(strProvider)) {

            if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                // TODO: Consider calling
                //    ActivityCompat#requestPermissions
                // here to request the missing permissions, and then overriding
                //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                //                                          int[] grantResults)
                // to handle the case where the user grants the permission. See the documentation
                // for ActivityCompat#requestPermissions for more details.
                return null;
            } //
            locationManager.requestLocationUpdates(strProvider, 1000, 10, locationListener);
            location = locationManager.getLastKnownLocation(strProvider);

        }
        return location;
    }
    //get Location
    public LocationListener locationListener = new LocationListener() {
        @Override
        public void onLocationChanged(Location location) {
            startLatADouble = location.getLatitude();
            startLngADouble = location.getLongitude();

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


    @Override
    //ทำการดแลเกี่ยวกัที่
    public void onMapReady(final GoogleMap googleMap) {
        this.googleMap = googleMap;

        //set up center map
        camera = new LatLng(startLatADouble, startLngADouble);

        googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(camera, 15));

        //การสร้าง marker ของ user ==> สร้างจุดเริมตินเป็น marker
        createMarkerUser();

        //get event click map
        googleMap.setOnMapClickListener(new GoogleMap.OnMapClickListener() {
            @Override
            public void onMapClick(LatLng latLng) {

                googleMap.clear();

                createMarkerUser(); //refresh marker

                createMakerDestination(latLng);

            } //onMapClick
        });

    }

    private void createMakerDestination(LatLng latLng) {

        googleMap.addMarker(new MarkerOptions()
                .position(latLng)
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_ORANGE)));;
        endLatADouble = latLng.latitude;
        endLngADouble = latLng.longitude;
        Log.d("30janV1","Destination Lat ==> "+endLatADouble);
        Log.d("30janV1","Destination Lng ==> "+endLngADouble);
        destination = new LatLng(endLatADouble,endLngADouble);

    } // createMakerDestination

    private void createMarkerUser() {
        origin = new LatLng(startLatADouble, startLngADouble);
        googleMap.addMarker(new MarkerOptions().position(origin));
    }
    @Override
    public void onClick(View v) {
        int id = v.getId();

        if (id==R.id.button_exit) {
            finish();
        }
        if (id==R.id.button_search) {
           //process search
        }


    } //onClick

    @Override
    public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
        return false;
    }
}  //class SearchRouteList
