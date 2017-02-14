package com.example.win.trafficroute;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

/**
 * Created by win on 14/2/2560.
 */

public class UserRegistration extends Activity implements View.OnClickListener {

    private Button buttonSave, buttonClose, buttonClear;
    private EditText userEditText, passwordEditText, fnameEditText, lnameEditText, confirmEditText, emailEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.userrregistration);

        userEditText = (EditText) findViewById(R.id.editText_user_name);
        passwordEditText = (EditText) findViewById(R.id.editText_user_password);
        fnameEditText = (EditText) findViewById(R.id.editText_fname);
        lnameEditText = (EditText) findViewById(R.id.editText_Lnaem);
        confirmEditText = (EditText) findViewById(R.id.editText_confrmPassword);
        emailEditText = (EditText) findViewById(R.id.editText_email);

        buttonSave = (Button) findViewById(R.id.button_save);
        buttonSave.setOnClickListener(this);

        buttonClear = (Button) findViewById(R.id.button_clear);
        buttonClear.setOnClickListener(this);

        buttonClose = (Button) findViewById(R.id.button_close);
        buttonClose.setOnClickListener(this);

    } //onCreate

    @Override
    public void onClick(View v) {
        int id = v.getId();

        switch (id){
            case R.id.button_save :
                onSaveProcess();
                break;
            case R.id.button_clear :
                onClearScreenProcess();
                break;
            case R.id.button_close :
                onExit();
                break;

        }

    }

    private void onExit() {
        finish();
    } //onExit


    private void onClearScreenProcess() {
    } //onClearScreenProcess


    private void onSaveProcess() {

    } //onSaveProcess

} //class UserRegistration

