package com.example.win.trafficroute;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.win.trafficroute.db.DatabaseHelper;

/**
 * Created by win on 14/2/2560.
 */

public class UserRegistration extends Activity implements View.OnClickListener, TextView.OnEditorActionListener {

    private Button buttonSave, buttonClose, buttonClear;
    private EditText userEditText, passwordEditText, fnameEditText, lnameEditText, confirmEditText, emailEditText;
    private boolean ib_edit = false;
    private Integer msgReturn;

    private DatabaseHelper mHelper;
    private SQLiteDatabase mDatabase;


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

        userEditText.setOnEditorActionListener(this);
        passwordEditText.setOnEditorActionListener(this);
        fnameEditText.setOnEditorActionListener(this);
        lnameEditText.setOnEditorActionListener(this);
        confirmEditText.setOnEditorActionListener(this);
        emailEditText.setOnEditorActionListener(this);

        buttonSave = (Button) findViewById(R.id.button_connect_save);
        buttonSave.setOnClickListener(this);

        buttonClear = (Button) findViewById(R.id.button_clear);
        buttonClear.setOnClickListener(this);

        buttonClose = (Button) findViewById(R.id.button_close);
        buttonClose.setOnClickListener(this);

        onClearScreenProcess();


    } //onCreate

    @Override
    public void onClick(View v) {
        int id = v.getId();

        switch (id){
            case R.id.button_connect_save:
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
        int dialog;
        if (ib_edit==true){
            dialog = onAlertDialog(this, "Exit", "Do You want to Save before Exit?","Yes","No");
            if (dialog==1){
                onSaveProcess();
            }
        }
        finish();
    } //onExit


    private void onClearScreenProcess() {
        int dialog;

        if (ib_edit==true) {
            dialog = onAlertDialog(this, "Cancel", "Do You want to Cancel?","Yes","No");
            if (dialog == 2) {
                userEditText.setText(null);
                passwordEditText.setText(null);
                fnameEditText.setText(null);
                lnameEditText.setText(null);
                confirmEditText.setText(null);
                emailEditText.setText(null);
                ib_edit = false;
            }
        }if (ib_edit==false) {
            userEditText.setText(null);
            passwordEditText.setText(null);
            fnameEditText.setText(null);
            lnameEditText.setText(null);
            confirmEditText.setText(null);
            emailEditText.setText(null);
            ib_edit = false;

        }
    } //onClearScreenProcess


    private void onSaveProcess() {
        int dialog, chkSave;
        dialog = onAlertDialog(this,"Save","Do You want to save user?","Yse","No");
        if (dialog==1){
            // Connect Database save User
            if (((userEditText.getText() == null) || (userEditText.length()) ==0) ||
               (passwordEditText.getText() == null || passwordEditText.length()==0) ||
               (fnameEditText.getText() == null || fnameEditText.length()==0) ||
               (lnameEditText.getText() == null || lnameEditText.length()==0 ) ||
               (confirmEditText.getText() == null || confirmEditText.length()==0) ||
               (emailEditText.getText() == null || emailEditText.length()==0)){
                chkSave = onAlertDialog(this,"Save","Your Information Not Compleate,Click Yes : when you confirm /Cancel : when you cance","Yes","Cancel");
                if (chkSave==2){
                    finish();
                }
            } // check ค่าว่าง

            if (passwordEditText.getText()!=confirmEditText.getText()) {
                chkSave = onAlertDialog(this,"Save","Password and Confirm password does not equal : YES ==> fill data agan / NO ==> Cancel out this screen","Yes","Cancel");
                if (chkSave==2){
                    finish();
                }

            } // check password not equal

            // process connect save
            mHelper = new DatabaseHelper(this);
            mDatabase = mHelper.getWritableDatabase();
//            Date dateActive = (Date.valueOf(Date));
            Cursor mCursor = mDatabase.rawQuery("SELECT * FROM " + mHelper.TABLE_USER_NAME
                    + " WHERE " + mHelper.COL_USER_NAME+ "='" + userEditText.getText() + "'"
                    + " AND " + mHelper.COL_USER_FNAME + "='" + fnameEditText.getText() + "'"
                    + " AND " + mHelper.COL_USER_LNAME + "='" + lnameEditText.getText() + "'"
                    + " AND " + mHelper.COL_USER_ACTIEVE_DATE + "='"+"Feb 01,2017" + "'"
                    + " AND " + mHelper.COL_USER_EMAIL + "='" + emailEditText.getText() + "'"
                    + " AND " + mHelper.COL_USER_PASSWORD + "='" +  passwordEditText.getText() + "'", null);

            if(mCursor.getCount() == 0) {
                mDatabase.execSQL("INSERT INTO " + mHelper.TABLE_USER_NAME
                        + " (" + mHelper.COL_USER_NAME + ", "
                        + mHelper.COL_USER_FNAME+ ", "
                        + mHelper.COL_USER_LNAME + ", "
                        + mHelper.COL_USER_ACTIEVE_DATE+ ", "
                        + mHelper.COL_USER_EMAIL + ", "
                        + mHelper.COL_USER_PASSWORD+") VALUES ('"
                        + userEditText.getText()+ "','"+ fnameEditText.getText()+"','"
                        + lnameEditText.getText()+"','" +""+ "','"+emailEditText.getText()
                        + "','" +passwordEditText.getText()+"');");

//                editName.setText("");
//                editLastName.setText("");
//                editSchool.setText("");

                Toast.makeText(getApplicationContext(), "เพิ่มข้อมูลนักเรียนเรียบร้อยแล้ว"
                        , Toast.LENGTH_SHORT).show();
            }//mCursor.getCount() = 0 เพิ่ม
            else {
                Toast.makeText(getApplicationContext(), "คุณมีข้อมูลนักเรียนคนนี้อยู่แล้ว"
                        , Toast.LENGTH_SHORT).show();
            }
             }
            //
           // onAlertDialog(this,"Save","Save Completa","Yes","No");
            ib_edit =false;
            onClearScreenProcess();
    } //onSaveProcess

    @Override
    public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
        ib_edit = true;
        return true;
    }

    public Integer onAlertDialog(Activity msgActivity,String msgTitle, String msgQuestion,String msgPositiveBtn,String msgNegativeBtn){

        AlertDialog.Builder dialog = new AlertDialog.Builder(msgActivity);
        dialog.setTitle(msgTitle);
        dialog.setCancelable(true);
        dialog.setMessage(msgQuestion);
        dialog.setPositiveButton(msgPositiveBtn, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                msgReturn = 1;
            }
        }) ;


        dialog.setNegativeButton(msgNegativeBtn, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                msgReturn = 2;
            }
        });
        dialog.show();
        return msgReturn;

    }
} //class UserRegistration

