package com.example.win.trafficroute;

import android.app.Activity;
import android.os.Bundle;
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
        buttonClose.setOnClickListener(this);

    }//onCreate

    @Override
    public void onClick(View v) {
        int id = v.getId();
        if (id==R.id.button_user_connect){
             onProcessConnect();

    } if (id==R.id.button_connect_close){
            finish();
        }
    }

    private void onProcessConnect() {
        
    } //onProcessConnect


} //class MainStartSearchRoute
