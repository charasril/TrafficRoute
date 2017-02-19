package com.example.win.trafficroute;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

/**
 * Created by win on 19/2/2560.
 */

public class SearchRouteActivity extends AppCompatActivity implements View.OnClickListener {

      Button button_searchlayout_search, button_serachlayout_close;
      EditText editText_search_from , editText_search_destination ;
//    private DatabaseHelper mHelper;
//    private SQLiteDatabase mDatabase;
//    private GoogleMap googleMap;
//    private String serverKey = "AIzaSyDkxXWseLD9nGDV81y6DgBA1PLbwp5tzwU";
//    private LatLng camera ;//= new LatLng(37.782437, -122.4281893);
//    private LatLng origin ;//= new LatLng(37.7849569, -122.4068855);
//    private LatLng destination = new LatLng(37.7814432, -122.4460177);
//    private LocationManager locationManager;
//    private Double startLatADouble = 0.0, startLngADouble = 0.0;
//    private Double endLatADouble , endLngADouble;
//    private DatabaseHelper myManage;
//    private Criteria criteria;
//
//    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.search_layout);
//
        button_searchlayout_search = (Button) findViewById(R.id.button_search1);
        button_serachlayout_close = (Button) findViewById(R.id.button_close);
        button_searchlayout_search.setOnClickListener(this);
        button_serachlayout_close.setOnClickListener(this);

        editText_search_from = (EditText) findViewById(R.id.editText_search_from);
        editText_search_destination = (EditText) findViewById(R.id.editText_search_destination);


//        DatabaseHelper mHelper = new DatabaseHelper(this);
//        SQLiteDatabase mDatabase = mHelper.getWritableDatabase();
//
//        //my setup
//        locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);
//        criteria = new Criteria();
//        criteria.setAccuracy(Criteria.ACCURACY_FINE);
//        criteria.setAltitudeRequired(false);
//        criteria.setBearingRequired(false);
//
////        SupportMapFragment mapSearch = new SupportMapFragment(). findViewById(R.id.fragment_map);
////        myManage = new DatabaseHelper(SearchRouteList.this);
//
////        ((SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.fragment_map)).getMapAsync(this);
//
//    }
//
////    private FragmentManager getSupportFragmentManager() {
////        return null;
    }
//
//
//    protected void onResume() {
//        super.onResume();
//
//        afterResume();
//    }
//
//    private void afterResume() {
//
//        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
//            // TODO: Consider calling
//            //    ActivityCompat#requestPermissions
//            // here to request the missing permissions, and then overriding
//            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
//            //                                          int[] grantResults)
//            // to handle the case where the user grants the permission. See the documentation
//            // for ActivityCompat#requestPermissions for more details.
//            return;
//        }
//        locationManager.removeUpdates(locationListener);
//        //หาพิกัด
//        Location networkLocation = myfindLocation(LocationManager.NETWORK_PROVIDER);
//        if (networkLocation != null) {
//            startLatADouble = networkLocation.getLatitude();
//            startLngADouble = networkLocation.getLongitude();
//        }
//
//        Location gpsLocation = myfindLocation(LocationManager.GPS_PROVIDER);
//        if (gpsLocation != null) {
//            startLatADouble = gpsLocation.getLatitude();
//            startLngADouble = gpsLocation.getLongitude();
//
//        }
//        Log.d("15FebV1", "Lat ==> " + startLatADouble);
//        Log.d("15FebV1", "Lat ==> " + startLngADouble);
//
//
//    } //after Resume
//
//
//    public Location myfindLocation(String strProvider) {
//        Location location = null;
//
//        if (locationManager.isProviderEnabled(strProvider)) {
//
//            if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
//                // TODO: Consider calling
//                //    ActivityCompat#requestPermissions
//                // here to request the missing permissions, and then overriding
//                //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
//                //                                          int[] grantResults)
//                // to handle the case where the user grants the permission. See the documentation
//                // for ActivityCompat#requestPermissions for more details.
//                return null;
//            } //
//            locationManager.requestLocationUpdates(strProvider, 1000, 10, locationListener);
//            location = locationManager.getLastKnownLocation(strProvider);
//
//        }
//        return location;
//    }
//    //get Location
//    public LocationListener locationListener = new LocationListener() {
//        @Override
//        public void onLocationChanged(Location location) {
//            startLatADouble = location.getLatitude();
//            startLngADouble = location.getLongitude();
//
//        }
//
//        @Override
//        public void onStatusChanged(String provider, int status, Bundle extras) {
//
//        }
//
//        @Override
//        public void onProviderEnabled(String provider) {
//
//        }
//
//        @Override
//        public void onProviderDisabled(String provider) {
//
//        }
//    };
//
//
//    @Override
//    //ทำการดแลเกี่ยวกัที่
//    public void onMapReady(final GoogleMap googleMap) {
//        this.googleMap = googleMap;
//
//        //set up center map
//        camera = new LatLng(startLatADouble, startLngADouble);
//
//        googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(camera, 15));
//
//        //การสร้าง marker ของ user ==> สร้างจุดเริมตินเป็น marker
//        createMarkerUser();
//
//        //get event click map
//        googleMap.setOnMapClickListener(new GoogleMap.OnMapClickListener() {
//            @Override
//            public void onMapClick(LatLng latLng) {
//
//                googleMap.clear();
//
//                createMarkerUser(); //refresh marker
//
//                createMakerDestination(latLng);
//
//            } //onMapClick
//        });
//
//    }
//
//    private void createMakerDestination(LatLng latLng) {
//
//        googleMap.addMarker(new MarkerOptions()
//                .position(latLng)
//                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_ORANGE)));;
//        endLatADouble = latLng.latitude;
//        endLngADouble = latLng.longitude;
//        Log.d("30janV1","Destination Lat ==> "+endLatADouble);
//        Log.d("30janV1","Destination Lng ==> "+endLngADouble);
//        destination = new LatLng(endLatADouble,endLngADouble);
//
//    } // createMakerDestination
//
//    private void createMarkerUser() {
//        origin = new LatLng(startLatADouble, startLngADouble);
//        googleMap.addMarker(new MarkerOptions().position(origin));
//    }
//    @Override
    public void onClick(View v) {
        int id = v.getId();

//        if (id==R.id.button_exit) {
//            finish();
//        }
//        if (id==R.id.button_search) {
//            //process search
        }
//
//
//    } //onClick
//
//    @Override
//    public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
//        return false;
//    }
}
