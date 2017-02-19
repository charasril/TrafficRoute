package com.example.win.trafficroute;

import android.app.Activity;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.ListView;

import com.example.win.trafficroute.db.DatabaseHelper;

/**
 * Created by win on 19/2/2560.
 */
public class Tab_HistList extends Activity{
    private DatabaseHelper mHelper;
    private SQLiteDatabase mDatabase;
    private ListView listViewHistList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.searchroutelist);

        //tab hist list activty
        listViewHistList = (ListView) findViewById(R.id.listView_histlist);
        mHelper = new DatabaseHelper(this);
        mDatabase = mHelper.getWritableDatabase();
    }
}
