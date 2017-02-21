package com.example.win.trafficroute;

import android.app.Activity;
import android.app.LocalActivityManager;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TabHost;

/**
 * Created by win on 21/2/2560.
 */

public class tabpage_activity extends Activity{
    LocalActivityManager mLocalActivityManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tabpagelayout);

        mLocalActivityManager = new LocalActivityManager(this, false);
        mLocalActivityManager.dispatchCreate(savedInstanceState);
        Log.d("Check","tabpage_activity ==>Intent-1 ");
        TabHost tabHost = (TabHost) findViewById(R.id.tabhost);
        tabHost.setup(mLocalActivityManager);

        TabHost.TabSpec tabSearch = tabHost.newTabSpec("tab1")
                .setIndicator("Search")
                .setContent(new Intent(this, tab1.class));
        TabHost.TabSpec tabMap = tabHost.newTabSpec("tab2")
                .setIndicator("Map")
//                .setContent(new Intent(this, tab2.class));
                .setContent(new Intent(this, Tab_map.class));
        TabHost.TabSpec tabHist = tabHost.newTabSpec("tab3")
                .setIndicator("History")
                .setContent(new Intent(this, tab3.class));
        Log.d("Check","tabpage_activity ==>Intent-3 ");
        tabHost.addTab(tabSearch);
        Log.d("Check","tabpage_activity ==>Intent-4 ");
        tabHost.addTab(tabMap);
        Log.d("Check","tabpage_activity ==>Intent-5 ");
        tabHost.addTab(tabHist);
        Log.d("Check","tabpage_activity ==>Intent-6 ");
    }


    @Override
    protected void onPause() {
        super.onPause();
        Log.d("Check","tabpage_activity onPause==>Intent-7 ");
        mLocalActivityManager.dispatchPause(!isFinishing());

    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d("Check","tabpage_activity onResume==>Intent-7 ");
        mLocalActivityManager.dispatchResume();
    }

}
