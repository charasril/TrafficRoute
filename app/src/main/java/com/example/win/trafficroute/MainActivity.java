package com.example.win.trafficroute;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private Button buttonStart,buttonClose,buttonRegsitration, buttonTab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        buttonStart = (Button) findViewById(R.id.button_start);
        buttonClose = (Button) findViewById(R.id.button_close);
        buttonRegsitration = (Button) findViewById(R.id.button_registration);
        buttonTab = (Button) findViewById(R.id.button_tab);

        buttonStart.setOnClickListener(this);
        buttonClose.setOnClickListener(this);
        buttonRegsitration.setOnClickListener(this);
        buttonTab.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        int id = v.getId();

        Toast.makeText(getApplicationContext(), "Hello Message on Main ==> Search RouteRoute"+id
                , Toast.LENGTH_SHORT).show();


        if (id == R.id.button_start) {
            Log.d("Check","MainActvity OnClick ==>button_start");
            Intent i = new Intent(getApplicationContext(), MainStartSearchRoute.class);
            startActivity(i);
        } else if (id == R.id.button_registration) {
            Log.d("Check","MainActvity OnClick ==>button_registration");
            Intent i = new Intent(getApplicationContext(), UserRegistration.class);
            startActivity(i);
        } else if (id == R.id.button_close) {
            finish();
        } else if (id == R.id.button_tab) {
            Log.d("Check","MainActvity OnClick ==>button_tab");

//            Toast.makeText(getApplicationContext(),"1-Main Actvity Route==> On Click " , Toast.LENGTH_SHORT).show();
//            Intent i = new Intent(getApplicationContext(), SearchRouteList.class);
            Intent i = new Intent(getApplicationContext(), tabpage_activity.class);
            Log.d("Check","MainActvity OnClick ==>Intent "+i);
            startActivity(i);
        }

    } //onClick


}
