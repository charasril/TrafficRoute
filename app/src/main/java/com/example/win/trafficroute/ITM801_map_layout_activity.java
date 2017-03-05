package com.example.win.trafficroute;

import android.app.ProgressDialog;
import android.location.Address;
import android.location.Location;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.Polygon;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polygon;
import com.google.android.gms.maps.model.PolygonOptions;



import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by win on 05/3/2560.
 */

public class ITM801_map_layout_activity extends AppCompatActivity implements OnMapReadyCallback, View.OnClickListener {
    private MapFragment mapFragment;
    private GoogleMap googleMap;
    private String currentLocationStr;
    List<Address> addressList = null;
    static ProgressDialog progDialog = null;
    private TextView mLocationTextView;
    private EditText mAddressEditText;
    private ImageView mGeoCodingBtn;
    protected static final String kCURRENT_TRACKING_MODE = "kCURRENT_TRACKING_MODE";
    protected static final String kFIX_AT_CODEMOBILES_MODE = "kFIX_AT_CODEMOBILES_MODE";
    private GoogleMap mMapView;
    private static final long UPDATE_INTERVAL = 5000;
    private static final long FASTEST_INTERVAL = 1000;
    private List<LatLng> listOfLatLng = new ArrayList<>();
    private ImageView splashImageView;
    private LocationRequest mLocationRequest;
    public static int REQ_GEO_CODING_SEARCH = 1;
    private GoogleApiClient googleApiClient;
    private Polygon polygon;
    private EditText editTextMapRoot;
    private Button buttonRootSave;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.itm801_map_layout);

        editTextMapRoot = (EditText) findViewById(R.id.editText_map_root);
        buttonRootSave = (Button) findViewById(R.id.button_root_save);
        buttonRootSave.setOnClickListener(this);

        // Set Map bind Widget Map Fagment wth Google Map
        SupportMapFragment mapFragment = ((SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.fragment_map_root));
//        mapFragment.getMapAsync(this);

        mMapView = mapFragment.getMap();
        mMapView.setTrafficEnabled(true);
        mMapView.setMapType(GoogleMap.MAP_TYPE_HYBRID);


        mMapView.setInfoWindowAdapter(new GoogleMap.InfoWindowAdapter() {
            @Override
            public View getInfoWindow(Marker marker) {
                View v = getLayoutInflater().inflate(R.layout.marker_info_content, null);
                TextView tvTitle = (TextView) v.findViewById(R.id.tv_title);
                if (marker.getTitle() != null && !marker.getTitle().equals("")) {
                    tvTitle.setText(marker.getTitle());
                    tvTitle.setVisibility(View.VISIBLE);
                }else{
                    tvTitle.setVisibility(View.GONE);
                }
                LatLng latLng = marker.getPosition();
                TextView tvLat = (TextView) v.findViewById(R.id.tv_lat);
                TextView tvLng = (TextView) v.findViewById(R.id.tv_lng);
                DecimalFormat formatter = new DecimalFormat("#,###.000");

                tvLat.setText("Latitude: " + formatter.format(latLng.latitude) + "°");
                tvLng.setText("Longtitude: " + formatter.format(latLng.longitude) + "°");

                return v;
            }

            @Override
            public View getInfoContents(Marker marker) {
                return null;
            }
        });


        googleApiClient = new GoogleApiClient.Builder(this)
                .addApi(LocationServices.API)
                .addConnectionCallbacks(new GoogleApiClient.ConnectionCallbacks() {
                    @Override
                    public void onConnected(Bundle bundle) {
                        mLocationRequest = LocationRequest.create();
                        mLocationRequest
                                .setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
                        mLocationRequest.setInterval(UPDATE_INTERVAL);
                        mLocationRequest.setFastestInterval(FASTEST_INTERVAL);
                        LocationServices.FusedLocationApi.requestLocationUpdates(googleApiClient, mLocationRequest, new LocationListener() {
                            @Override
                            public void onLocationChanged(Location location) {

                                DecimalFormat formatter = new DecimalFormat("#,###.00");

                                final String lat = formatter.format(location.getLatitude());
                                final String lng = formatter.format(location.getLongitude());

                                currentLocationStr = String
                                        .format("Latitude: %s°, Longtitude: %s°",
                                                lat, lng);
                                mLocationTextView
                                        .setText(currentLocationStr);
                            }
                        });

                    }

                    @Override
                    public void onConnectionSuspended(int i) {
                        Toast.makeText(getApplicationContext(), "Connection is susppended!", Toast.LENGTH_LONG).show();

                    }
                })
                .addOnConnectionFailedListener(new GoogleApiClient.OnConnectionFailedListener() {
                    @Override
                    public void onConnectionFailed(ConnectionResult connectionResult) {
                        Toast.makeText(getApplicationContext(), "Connection is failed!", Toast.LENGTH_LONG).show();

                    }
                }).build();

        requestAdhocPermission();


    } // On create

    private void requestAdhocPermission() {
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {

    }

    @Override
    public void onClick(View v) {

    }
}
