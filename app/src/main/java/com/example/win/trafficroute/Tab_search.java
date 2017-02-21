package com.example.win.trafficroute;

import android.app.Activity;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.LatLng;

/**
 * Created by win on 19/2/2560.
 */
public class Tab_search extends Activity implements View.OnClickListener {
    private Button btnSearch ,btnSetMap,btnCloseSearch;
    private GoogleMap googleMap;
    private String serverKey = "AIzaSyBR3pJfYwyNXs24RQRIg-3fb40BeYMfkKw";
    private LatLng origin ;
    private LatLng destination;
    private LocationManager locationManager;
    private Double startLatADouble = 0.0, startLngADouble = 0.0;
    private Double endLatADouble , endLngADouble;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.searchroutelist);
        setContentView(R.layout.tabsearchmap);
        btnSearch = (Button) findViewById(R.id.button_search_way) ;
        btnSetMap = (Button) findViewById(R.id.button_set_way) ;
        btnCloseSearch = (Button) findViewById(R.id.button_search_close) ;

        btnSearch.setOnClickListener(this);
        btnSetMap.setOnClickListener(this);
        btnCloseSearch.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        if (id == R.id.button_search_way) {
            requestSearchWay();
        }  if (id == R.id.button_set_way) {
            setWayOnMap();
        }
        if (id == R.id.button_search_close) {

        }

    } //onClick

    private void setWayOnMap() {


    } //setWayOnMap

    private void requestSearchWay() {


    } //requestSearchWay

}
