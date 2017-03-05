package com.example.win.trafficroute;

import android.content.pm.PackageManager;
import android.graphics.Color;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentContainer;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
//import android.support.design.widget.Snackbar;

import com.example.akexorcist.googledirection.DirectionCallback;
import com.example.akexorcist.googledirection.GoogleDirection;
import com.example.akexorcist.googledirection.constant.TransportMode;
import com.example.akexorcist.googledirection.model.Direction;
import com.example.akexorcist.googledirection.model.googledirection.util.DirectionConverter;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;

//import android.support.annotation.Nullable;
//import com.example.win.trafficroute.db.DatabaseHelper;

/**
 * Created by win on 19/2/2560.
 */
public class Tab_map extends AppCompatActivity implements OnMapReadyCallback, DirectionCallback {
    private Criteria criteria;

    private GoogleMap googleMap;
    private String serverKey = "AIzaSyBR3pJfYwyNXs24RQRIg-3fb40BeYMfkKw";//"AIzaSyDkxXWseLD9nGDV81y6DgBA1PLbwp5tzwU";
    private LatLng camera ;//= new LatLng(37.782437, -122.4281893);
    private LatLng origin ;//= new LatLng(37.7849569, -122.4068855);
    private LatLng destination;// = new LatLng(37.7814432, -122.4460177);
    private LocationManager locationManager;
    private Double startLatADouble = 0.0, startLngADouble = 0.0;
    private Double endLatADouble , endLngADouble;
    private FragmentContainer mapFragmentContainer;
    private SupportMapFragment mapFragment;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.searchroutelist);
        setContentView(R.layout.tabmap);
        //my setup map in tab
        locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);
        criteria = new Criteria();
        criteria.setAccuracy(Criteria.ACCURACY_FINE);
        criteria.setAltitudeRequired(false);
        criteria.setBearingRequired(false);

        mapFragment = ((SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.fragment_map));
        mapFragment.getMapAsync(this);
        googleMap.setTrafficEnabled(true);
        Log.d("Check","Tab_map ==>onCreate  = 1");
    }

    protected void onResume() {
        super.onResume();
        Log.d("Check","Tab_map ==>onResume  = 2");
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

        Log.d("Check","Tab_map ==>afterResume  = 23");
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

          } //after Resume

    public Location myfindLocation(String strProvider) {
        Location location = null;


        if (locationManager.isProviderEnabled(strProvider)) {

            if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
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
        destination = new LatLng(endLatADouble,endLngADouble);
        requestDirection();


    } // createMakerDestination

    public void requestDirection() {

//        Snackbar.make(mapFragment, "Direction Requesting...", Snackbar.LENGTH_SHORT).show();
        GoogleDirection.withServerKey(serverKey)
                .from(origin)
                .to(destination)
                .transportMode(TransportMode.DRIVING) //select mode travel
                .alternativeRoute(true)
                .execute(this);

    }


    private void createMarkerUser() {
        origin = new LatLng(startLatADouble, startLngADouble);
        googleMap.addMarker(new MarkerOptions().position(origin));
        Log.d("Check","Tab_map ==>createMarkerUser  = 11");

    }

    @Override
    public void onDirectionSuccess(Direction direction, String rawBody) {
        //        Snackbar.make(btnRequestDirection, "Success with status : " + direction.getStatus(), Snackbar.LENGTH_SHORT).show();

        direction.getStatus();
        if (direction.isOK()) {
            googleMap.addMarker(new MarkerOptions().position(origin));
            googleMap.addMarker(new MarkerOptions().position(destination));

            ArrayList<LatLng> directionPositionList = direction.getRouteList().get(0).getLegList().get(0).getDirectionPoint();
            googleMap.addPolyline(DirectionConverter.createPolyline(this, directionPositionList, 5, Color.RED));

//            btnRequestDirection.setVisibility(View.GONE);
        }
    }

    @Override
    public void onDirectionFailure(Throwable t) {

    }

//    @Override
//    public void onDirectionSuccess(Direction direction, String rawBody) {
////        Snackbar.make(btnRequestDirection, "Success with status : " + direction.getStatus(), Snackbar.LENGTH_SHORT).show();
//
//        direction.getStatus();
//        if (direction.isOK()) {
//            googleMap.addMarker(new MarkerOptions().position(origin));
//            googleMap.addMarker(new MarkerOptions().position(destination));
//
//            ArrayList<LatLng> directionPositionList = direction.getRouteList().get(0).getLegList().get(0).getDirectionPoint();
//            googleMap.addPolyline(DirectionConverter.createPolyline(this, directionPositionList, 5, Color.RED));
//
////            btnRequestDirection.setVisibility(View.GONE);
//        }
//    }
//
//    @Override
//    public void onDirectionFailure(Throwable t) {
////        Snackbar.make(btnRequestDirection, t.getMessage(), Snackbar.LENGTH_SHORT).show();
//    }
}
