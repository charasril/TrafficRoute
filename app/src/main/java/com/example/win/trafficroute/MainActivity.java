package com.example.win.trafficroute;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private Button buttonStart,buttonClose,buttonRegsitration;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        buttonStart = (Button) findViewById(R.id.button_start);
        buttonClose = (Button) findViewById(R.id.button_close);
        buttonRegsitration = (Button) findViewById(R.id.button_registration);

        buttonStart.setOnClickListener(this);
        buttonClose.setOnClickListener(this);
        buttonRegsitration.setOnClickListener(this);


    }

    @Override
    public void onClick(View v) {
        int id = v.getId();


        if (id == R.id.button_start) {
            Intent i = new Intent(getApplicationContext(), MainStartSearchRoute.class);
            startActivity(i);
        } else if (id == R.id.button_registration) {
            Intent i = new Intent(getApplicationContext(), UserRegistration.class);
            startActivity(i);
        } else if (id == R.id.button_close) {
            finish();
        }
    } //onClick


}
