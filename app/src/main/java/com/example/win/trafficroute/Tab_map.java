package com.example.win.trafficroute;

import android.location.Criteria;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
//import com.example.win.trafficroute.db.DatabaseHelper;

/**
 * Created by win on 19/2/2560.
 */
public class Tab_map extends AppCompatActivity implements OnMapReadyCallback {
    private Criteria criteria;

    private GoogleMap googleMap;
    private String serverKey = "AIzaSyDkxXWseLD9nGDV81y6DgBA1PLbwp5tzwU";
    private LatLng camera ;//= new LatLng(37.782437, -122.4281893);
    private LatLng origin ;//= new LatLng(37.7849569, -122.4068855);
    private LatLng destination;// = new LatLng(37.7814432, -122.4460177);
    private LocationManager locationManager;
    private Double startLatADouble = 0.0, startLngADouble = 0.0;
    private Double endLatADouble , endLngADouble

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.searchroutelist);

        //my setup map in tab
        locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);
        criteria = new Criteria();
        criteria.setAccuracy(Criteria.ACCURACY_FINE);
        criteria.setAltitudeRequired(false);
        criteria.setBearingRequired(false);

        ((SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.fragment_map)).getMapAsync(this);

    }

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
        Log.d("19janV1","Destination Lat ==> "+endLatADouble);
        Log.d("19janV1","Destination Lng ==> "+endLngADouble);
        destination = new LatLng(endLatADouble,endLngADouble);

    } // createMakerDestination

    private void createMarkerUser() {
        origin = new LatLng(startLatADouble, startLngADouble);
        googleMap.addMarker(new MarkerOptions().position(origin));
    }
}
