package com.example.win.trafficroute;

import android.app.Activity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


/**
 * Created by win on 14/2/2560.
 */

public class SearchRouteList extends Activity implements View.OnClickListener, TextView.OnEditorActionListener {
//   TabHost tabSearch,tabMap,tabHistList;
    EditText editTextStart ,editTextEnd;
    Button buttonSearch , buttonExit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.searchroutelist);

        editTextStart = (EditText) findViewById(R.id.editText_start);
        editTextEnd = (EditText) findViewById(R.id.editText_end);

        editTextStart.setOnEditorActionListener(this);
        editTextEnd.setOnEditorActionListener(this);

        buttonSearch = (Button) findViewById(R.id.button_search);
        buttonExit = (Button) findViewById(R.id.button_exit);
        buttonSearch.setOnClickListener(this);
        buttonExit.setOnClickListener(this);


    }

    @Override
    public void onClick(View v) {
        int id = v.getId();

        if (id==R.id.button_exit) {
            finish();
        }
        if (id==R.id.button_search) {
           //process search
        }


    } //onClick

    @Override
    public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
        return false;
    }
}
