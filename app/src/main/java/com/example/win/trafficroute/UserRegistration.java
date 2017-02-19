package com.example.win.trafficroute;

import android.app.Activity;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
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

        userEditText.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
//                String input;
                if(actionId == EditorInfo.IME_ACTION_DONE)
                {
//                    input= v.getText().toString();
//                    this.calculate(input);
                    ib_edit = true;
                    return true; // consume.
                }
                return false; // pass on to other listeners.
            }
                  });

        userEditText.setOnFocusChangeListener(new View.OnFocusChangeListener()
        {
            @Override
            public void onFocusChange(View v, boolean hasFocus)
            {
                String input;
                EditText editText;

                if(!hasFocus)
                {
                    editText= (EditText) v;
                    input= editText.getText().toString();
                    ib_edit = true;
                }
            }
        });

        passwordEditText.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
//                String input;
                if(actionId == EditorInfo.IME_ACTION_DONE)
                {
//                    input= v.getText().toString();
//                    this.calculate(input);
                    ib_edit = true;
                    return true; // consume.
                }
                return false; // pass on to other listeners.
            }
        });

        passwordEditText.setOnFocusChangeListener(new View.OnFocusChangeListener()
        {
            @Override
            public void onFocusChange(View v, boolean hasFocus)
            {
                String input;
                EditText editText;

                if(!hasFocus)
                {
                    editText= (EditText) v;
                    input= editText.getText().toString();
                    ib_edit = true;
                }
            }
        });

        fnameEditText.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
//                String input;
                if(actionId == EditorInfo.IME_ACTION_DONE)
                {
//                    input= v.getText().toString();
//                    this.calculate(input);
                    ib_edit = true;
                    return true; // consume.
                }
                return false; // pass on to other listeners.
            }
        });

        fnameEditText.setOnFocusChangeListener(new View.OnFocusChangeListener()
        {
            @Override
            public void onFocusChange(View v, boolean hasFocus)
            {
                String input;
                EditText editText;

                if(!hasFocus)
                {
                    editText= (EditText) v;
                    input= editText.getText().toString();
                    ib_edit = true;
                }
            }
        });

        lnameEditText.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
//                String input;
                if(actionId == EditorInfo.IME_ACTION_DONE)
                {
//                    input= v.getText().toString();
//                    this.calculate(input);
                    ib_edit = true;
                    return true; // consume.
                }
                return false; // pass on to other listeners.
            }
        });

        lnameEditText.setOnFocusChangeListener(new View.OnFocusChangeListener()
        {
            @Override
            public void onFocusChange(View v, boolean hasFocus)
            {
                String input;
                EditText editText;

                if(!hasFocus)
                {
                    editText= (EditText) v;
                    input= editText.getText().toString();
                    ib_edit = true;
                }
            }
        });

        confirmEditText.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
//                String input;
                if(actionId == EditorInfo.IME_ACTION_DONE)
                {
//                    input= v.getText().toString();
//                    this.calculate(input);
                    ib_edit = true;
                    return true; // consume.
                }
                return false; // pass on to other listeners.
            }
        });

        confirmEditText.setOnFocusChangeListener(new View.OnFocusChangeListener()
        {
            @Override
            public void onFocusChange(View v, boolean hasFocus)
            {
                String input;
                EditText editText;

                if(!hasFocus)
                {
                    editText= (EditText) v;
                    input= editText.getText().toString();
                    ib_edit = true;
                }
            }
        });

       emailEditText.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
//                String input;
                if(actionId == EditorInfo.IME_ACTION_DONE)
                {
//                    input= v.getText().toString();
//                    this.calculate(input);
                    ib_edit = true;
                    return true; // consume.
                }
                return false; // pass on to other listeners.
            }
        });

        emailEditText.setOnFocusChangeListener(new View.OnFocusChangeListener()
        {
            @Override
            public void onFocusChange(View v, boolean hasFocus)
            {
                String input;
                EditText editText;

                if(!hasFocus)
                {
                    editText= (EditText) v;
                    input= editText.getText().toString();
                    ib_edit = true;
                }
            }
        });



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
                Log.d("Check","UserRegistration OnClick ==>button_connect_save");
                onSaveProcess();

                break;
            case R.id.button_clear :
                Log.d("Check","UserRegistration OnClick ==>button_clear");
                onClearScreenProcess();
                break;
            case R.id.button_close :
                onExit();
                break;

        }

    }

    private void onExit() {
//        int dialog;
//        if (ib_edit==true){
//            //dialog = onAlertDialog(this, "Exit", "Do You want to Save before Exit?","Yes","No");
//
//
//            if (dialog==1){
//                onSaveProcess();
//            }
//        }

        finish();
    } //onExit


    private void onClearScreenProcess() {
        int dialog;
        Log.d("Check","1-UserRegistration onClearScreenProcess ==>onClearScreenProcess");

        if ( userEditText.length() != 0 || fnameEditText.length() != 0 ||
                lnameEditText.length() != 0 ||  confirmEditText.length() != 0 ||
                emailEditText.length() != 0) {
            Log.d("Check","2-UserRegistration onClearScreenProcess ==>onClearScreenProcess");
            dialog = 1;//onAlertDialog(this, "Cancel", "Do You want to Cancel?","Yes","No");
            if (dialog == 2) {
                userEditText.setText(null);
                passwordEditText.setText(null);
                fnameEditText.setText(null);
                lnameEditText.setText(null);
                confirmEditText.setText(null);
                emailEditText.setText(null);
                ib_edit = false;
            }
        } else {
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
//        int dialog, chkSave;
        Log.d("Check","1-in-UserRegistration onSaveProcess ==>onSaveProcess");
//        dialog =1;// onAlertDialog(this,"Save","Do You want to save user?","Yse","No");
        Log.d("Check","2-Pass Dialog-UserRegistration onSaveProcess ==>onSaveProcess");
        msgReturn=1;
//        AlertDialog.Builder dialog = new AlertDialog.Builder(this);
//        dialog.setTitle("Save");
//        dialog.setCancelable(true);
//        dialog.setMessage("Do you want to save New User Rregistration?");
//        dialog.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
//            @Override
//            public void onClick(DialogInterface dialog, int which) {
//                msgReturn = 1;
//            }
//        }) ;
//
//
//        dialog.setNegativeButton("No", new DialogInterface.OnClickListener() {
//            public void onClick(DialogInterface dialog, int which) {
//                msgReturn = 2;
//            }
//        });
//        dialog.show();

        if (msgReturn==1){
            // Connect Database save User
            if (((userEditText.getText() == null) || (userEditText.length()) ==0) ||
               (passwordEditText.getText() == null || passwordEditText.length()==0) ||
               (fnameEditText.getText() == null || fnameEditText.length()==0) ||
               (lnameEditText.getText() == null || lnameEditText.length()==0 ) ||
               (confirmEditText.getText() == null || confirmEditText.length()==0) ||
               (emailEditText.getText() == null || emailEditText.length()==0)){
                /*=============================================== */
//                chkSave = 1;//onAlertDialog(this,"Save","Your Information Not Compleate,Click Yes : when you confirm /Cancel : when you cance","Yes","Cancel");
//                if (chkSave==2){
//                    finish();
//                }
//                dialog = new AlertDialog.Builder(this);
//                dialog.setTitle("Save");
//                dialog.setCancelable(true);
//                dialog.setMessage("Your Information Not Compleate,Pls Check Again.!!!");
//                dialog.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialog, int which) {
//
//                    }
//                }) ;
//                dialog.show();



                /*======================================================*/
            } // check ค่าว่าง

            if (passwordEditText.getText()!=confirmEditText.getText()) {
////                chkSave = 1;//onAlertDialog(this,"Save","Password and Confirm password does not equal : YES ==> fill data agan / NO ==> Cancel out this screen","Yes","Cancel");
////                if (chkSave==2){
////                    finish();
////                }
//
//                dialog = new AlertDialog.Builder(this);
//                dialog.setTitle("Save");
//                dialog.setCancelable(true);
//                dialog.setMessage("Check Password : Your Password and Confirm not match,Pls Check Again.!!!");
//                dialog.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialog, int which) {
//
//                    }
//                }) ;
//                dialog.show();

            } // check password not equal

            // process connect save
            mHelper = new DatabaseHelper(this);
            mDatabase = mHelper.getWritableDatabase();
//            Date dateActive = (Date.valueOf(Date));
            Cursor mCursor = mDatabase.rawQuery("SELECT * FROM " + mHelper.TABLE_USER_NAME
                    + " WHERE " + mHelper.COL_USER_NAME+ "='" + userEditText.getText() + "'", null);
//                    + " AND " + mHelper.COL_USER_FNAME + "='" + fnameEditText.getText() + "'"
//                    + " AND " + mHelper.COL_USER_LNAME + "='" + lnameEditText.getText() + "'"
//                    + " AND " + mHelper.COL_USER_ACTIEVE_DATE + "='"+"Feb 01,2017" + "'"
//                    + " AND " + mHelper.COL_USER_EMAIL + "='" + emailEditText.getText() + "'"
//                    + " AND " + mHelper.COL_USER_PASSWORD + "='" +  passwordEditText.getText() + "'", null);

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

                Toast.makeText(getApplicationContext(), "เพิ่มข้อมูลผู้ใช้เรียบร้อยแล้ว"
                        , Toast.LENGTH_SHORT).show();


            }//mCursor.getCount() = 0 เพิ่ม
            else {
                Toast.makeText(getApplicationContext(), "คุณมีข้อมูลผู้ใช้คนนี้อยู่แล้ว "+ userEditText.getText()
                        , Toast.LENGTH_SHORT).show();
            }
             }
            //
           // onAlertDialog(this,"Save","Save Completa","Yes","No");
            mHelper.close();
            mDatabase.close();
            ib_edit =false;
            onClearScreenProcess();
    } //onSaveProcess

    @Override
    public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
        ib_edit = true;
        return true;
    }

//    public Integer onAlertDialog(Activity msgActivity,String msgTitle, String msgQuestion,String msgPositiveBtn,String msgNegativeBtn){
//        Log.d("Check","UserRegistration onAlertDialog");
//        AlertDialog.Builder dialog = new AlertDialog.Builder(msgActivity);
//        dialog.setTitle(msgTitle);
//        dialog.setCancelable(true);
//        dialog.setMessage(msgQuestion);
//        dialog.setPositiveButton(msgPositiveBtn, new DialogInterface.OnClickListener() {
//            @Override
//            public void onClick(DialogInterface dialog, int which) {
//                msgReturn = 1;
//            }
//        }) ;
//
//
//        dialog.setNegativeButton(msgNegativeBtn, new DialogInterface.OnClickListener() {
//            public void onClick(DialogInterface dialog, int which) {
//                msgReturn = 2;
//            }
//        });
//        dialog.show();
//        Log.d("Check","UserRegistration onAlertDialog-finsh");
//        return msgReturn;
//
//    }
} //class UserRegistration

