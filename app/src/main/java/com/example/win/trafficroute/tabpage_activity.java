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
        TabHost tabHost = (TabHost) findViewById(R.id.tabhost);
        tabHost.setup(mLocalActivityManager);

        TabHost.TabSpec tabSearch = tabHost.newTabSpec("tab1")
                .setIndicator("Search")
                .setContent(new Intent(this, Tab_search.class));
        TabHost.TabSpec tabMap = tabHost.newTabSpec("tab2")
                .setIndicator("Map")
//                .setContent(new Intent(this, tab2.class));
                .setContent(new Intent(this, Tab_map.class));
        TabHost.TabSpec tabHist = tabHost.newTabSpec("tab3")
                .setIndicator("History")
                .setContent(new Intent(this, tab3.class));

        tabHost.addTab(tabSearch);
        tabHost.addTab(tabMap);
        tabHost.addTab(tabHist);
    }


    @Override
    protected void onPause() {
        super.onPause();
        mLocalActivityManager.dispatchPause(!isFinishing());
        Log.d("Check","tabpage_activity-onPause 1");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d("Check","tabpage_activity-onResume 2");
        mLocalActivityManager.dispatchResume();
        Log.d("Check","tabpage_activity-onResume 3");
    }

}
