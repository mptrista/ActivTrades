package com.toshkin.activtrades;

import android.os.Bundle;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;

public class BottomNavigationActivity extends AppCompatActivity {


    private BottomNavigationView.OnNavigationItemSelectedListener navigationListener = item -> {
        switch (item.getItemId()) {
            case R.id.navigation_feed:
                return true;
            case R.id.navigation_albums:
                return true;
            case R.id.navigation_todos:
                return true;
            case R.id.navigation_profile:
                return true;
        }
        return false;
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(navigationListener);
    }

}
