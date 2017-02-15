package com.example.win.trafficroute;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

/**
 * Created by win on 14/2/2560.
 */

public class MainStartSearchRoute extends Activity implements View.OnClickListener {
    private Button buttonConnect, buttonClose;
    private EditText editTextUser,editTextPassword;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.mainstartsearchroute);

        editTextUser = (EditText) findViewById(R.id.editText_user_name);
        editTextPassword = (EditText) findViewById(R.id.editText_user_password);

        buttonConnect = (Button) findViewById(R.id.button_user_connect);
        buttonClose = (Button) findViewById(R.id.button_connect_close);

        buttonConnect.setOnClickListener(this);
        Log.d("Check","0-Check Intent Before ==>");
        buttonClose.setOnClickListener(this);

    }//onCreate

    @Override
    public void onClick(View v) {
        int id = v.getId();
        Log.d("Check","1-Check Intent Before ==>"+id);
        if (id==R.id.button_user_connect){
//             onProcessConnect();
            // check database ผ่าน OPNEN หน้า search
//            Log.d("Check","2-Check Intent Before");
//            Intent i = new Intent(getApplicationContext(), SearchRouteList.class);
//            startActivity(i);
//            Log.d("Check","3-Check Intent after request intent "+i);
            openActivity(SearchRouteList.class);

    } if (id==R.id.button_connect_close){
            finish();
        }
    }

    private void onProcessConnect() {

    } //onProcessConnect

    public void openActivity(Class<?> cs) {
        startActivity(new Intent(this, cs));
    }
} //class MainStartSearchRoute
