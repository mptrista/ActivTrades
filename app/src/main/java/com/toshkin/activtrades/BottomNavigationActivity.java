package com.toshkin.activtrades;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

import com.toshkin.activtrades.app.mvp.BasePresenterFragment;

public class BottomNavigationActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener {

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.navigation_feed:
                openFragment(NavigationHelper.Screen.FEED);
                return true;
            case R.id.navigation_albums:
                openFragment(NavigationHelper.Screen.ALBUMS);
                return true;
            case R.id.navigation_todos:
                openFragment(NavigationHelper.Screen.TODOS);
                return true;
            case R.id.navigation_profile:
                openFragment(NavigationHelper.Screen.PROFILE);
                return true;
        }
        return false;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(this);
    }

    private void openFragment(NavigationHelper.Screen screen) {
        BasePresenterFragment fragment = NavigationHelper.getInstance().getFragment(screen);
        getSupportFragmentManager().beginTransaction().replace(R.id.content, fragment, fragment.TAG()).commit();
    }


}
