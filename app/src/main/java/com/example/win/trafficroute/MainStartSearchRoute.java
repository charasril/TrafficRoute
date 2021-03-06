package com.example.win.trafficroute;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.Editable;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

import com.example.win.trafficroute.db.DatabaseHelper;

public class MainStartSearchRoute extends Activity implements View.OnClickListener {
    private Button buttonConnect, buttonClose, buttonSave;
    private EditText editTextUser,editTextPassword;
    private EditText editTextNewUser,editTextNewPassword,editTextNewConfrmPassword,editTextEmail;
    private CheckBox checkBox_forgotUser,checkBox_forgotPassword;
    private DatabaseHelper mHelper;
    private SQLiteDatabase mDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mainstartsearchroute);
        // set Connect Database
        DatabaseHelper mHelper = new DatabaseHelper(this);

        //Set Control
        editTextUser = (EditText) findViewById(R.id.editText_user_name);
        editTextPassword = (EditText) findViewById(R.id.editText_user_password);

        editTextNewUser = (EditText) findViewById(R.id.editText_User);
        editTextNewPassword = (EditText) findViewById(R.id.editText_new_password);


        editTextNewConfrmPassword = (EditText) findViewById(R.id.editText_confrmPassword);
        editTextEmail = (EditText) findViewById(R.id.editText_email);


        buttonConnect = (Button) findViewById(R.id.button_user_connect);
        buttonClose = (Button) findViewById(R.id.button_connect_close);

        buttonConnect.setOnClickListener(this);
        buttonClose.setOnClickListener(this);

        checkBox_forgotUser = (CheckBox) findViewById(R.id.checkBox_forgotUser);
        checkBox_forgotUser.setOnClickListener(this);

        checkBox_forgotPassword = (CheckBox) findViewById(R.id.checkBox_forgotPassword);
        checkBox_forgotPassword.setOnClickListener(this);

        buttonSave = (Button) findViewById(R.id.button_connect_save);
        buttonSave.setOnClickListener(this);


    } //onCreate


    @Override
    public void onClick(View v) {
        int id = v.getId();
        Log.d("Check","1-Check Intent Before ==>"+id);
        switch (id){
            case R.id.checkBox_forgotUser:
                editTextNewUser.setEnabled(false);
                editTextNewUser.setText(editTextUser.getText());

                if (checkBox_forgotUser.isChecked()){

                }
                editTextNewPassword.setText(null);
                editTextNewConfrmPassword.setText(null);
                editTextEmail.setText(null);
                break;

            case R.id.checkBox_forgotPassword :
                editTextNewUser.setEnabled(false);
                editTextNewUser.setText(editTextUser.getText());

                editTextNewPassword.setText(null);
                editTextNewConfrmPassword.setText(null);
                editTextEmail.setText(null);
                break;
            case R.id.button_connect_close :
                finish();
                break;
            case R.id.button_user_connect :
                onProcessConnect();
                break;
            case R.id.button_connect_save:
                onProcessSave();
                break;
             }

    } //onClick

    private void onProcessSave() {
     //Case Chage New User and Password

        mDatabase = mHelper.getWritableDatabase();
        Editable userName = editTextNewUser.getText();
        Cursor mCursor = mDatabase.rawQuery("SELECT * FROM TABLE_USER_INFORMATION WHERE  USER_NAME = "
                +userName ,null);
        if ((mCursor.getCount())==0) {

            AlertDialog.Builder dialog = new AlertDialog.Builder(this);
            dialog.setTitle("Not Found!!");
            dialog.setCancelable(true);
            dialog.setMessage("User Name Not Found. Please check your User Name Again");
            dialog.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                }
            }) ;
            dialog.show();
        }

        String SqlUpdate = "(UPDATE TABLE TABLE_USER_NAME SET COL_USER_NAME = "+editTextNewUser.getText()
                +" , COL_USER_PASSWORD = "+editTextNewPassword.getText()
                +" WHERE COL_USER_EMAIL = "+editTextEmail.getText();

        mDatabase.execSQL(SqlUpdate);
        //onAlertDialog(this,"Change","Do you want to connect and open search window","OK","No",2);
        AlertDialog.Builder dialog = new AlertDialog.Builder(this);
        dialog.setTitle("Success");
        dialog.setCancelable(false);
        dialog.setMessage("Do you want to connect and open search window");
        dialog.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                openActivity(SearchRouteList.class);
            }
        }) ;

        dialog.setNegativeButton("No", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {

                checkBox_forgotUser.setChecked(false);
                checkBox_forgotPassword.setChecked(false);

                editTextNewUser.setEnabled(false);
                editTextNewConfrmPassword.setEnabled(false);
                editTextEmail.setEnabled(false);
                editTextNewPassword.setEnabled(false);

                editTextNewUser.setText(null);
                editTextNewPassword.setText(null);
                editTextNewConfrmPassword.setText(null);
                editTextEmail.setText(null);

                editTextUser.setText(null);
                editTextPassword.setText(null);
                editTextUser.setFocusable(true);

            }
        });
        dialog.show();

    } //onProcessSave

    private void onProcessConnect() {

        Editable userName = editTextUser.getText();
        if (checkBox_forgotUser.isChecked()) {
            userName = editTextNewUser.getText();
        }

        mDatabase = mHelper.getReadableDatabase();
        Cursor mCursor;
        mCursor = mDatabase.rawQuery("SELECT * FROM TABLE_USER_INFORMATION WHERE  USER_NAME = '"
                                      +userName +"'", null);

        if ((mCursor.getCount())==0) {
            onAlertDialog(this,"Not Found","User Name Not Found. Please check your User Name Again","OK","No",1);
//            AlertDialog.Builder dialog = new AlertDialog.Builder(this);
//            dialog.setTitle("Not Found!!");
//            dialog.setCancelable(true);
//            dialog.setMessage("User Name Not Found. Please check your User Name Again");
//            dialog.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
//                @Override
//                public void onClick(DialogInterface dialog, int which) {
//
//                }
//            });
//            dialog.show();
        }

        openActivity(SearchRouteList.class);

    } //onProcessConnect


    public void openActivity(Class<?> cs) {
        startActivity(new Intent(this, cs));
    }


    public void onAlertDialog(Activity msgActivity,String msgTitle, String msgQuestion,
                              String msgPositiveBtn,String msgNegativeBtn,Integer msgBtn){

        AlertDialog.Builder dialog = new AlertDialog.Builder(msgActivity);
        dialog.setTitle(msgTitle);
        dialog.setCancelable(true);
        dialog.setMessage(msgQuestion);
        dialog.setPositiveButton(msgPositiveBtn, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        }) ;

        if (msgBtn==2) {
            dialog.setNegativeButton(msgNegativeBtn, new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {

                }
            });
        }
        dialog.show();



    }

} //MainStartSearchRoute
