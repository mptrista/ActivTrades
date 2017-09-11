package com.toshkin.activtrades;

import com.toshkin.activtrades.app.mvp.BasePresenterFragment;
import com.toshkin.activtrades.fragments.AlbumsFragment;
import com.toshkin.activtrades.fragments.FeedFragment;
import com.toshkin.activtrades.fragments.ProfileFragment;
import com.toshkin.activtrades.fragments.ToDosFragment;

import java.util.HashMap;

public class NavigationHelper {

    enum Screen {
        FEED,
        TODOS,
        ALBUMS,
        PROFILE
    }

    private static NavigationHelper instance;

    public static NavigationHelper getInstance() {
        if (instance == null) {
            instance = new NavigationHelper();
        }
        return instance;
    }

    private NavigationHelper() {

    }

    private HashMap<Screen, BasePresenterFragment> fragments = new HashMap<>();

    public BasePresenterFragment getFragment(Screen screen) {
        BasePresenterFragment fragment = fragments.get(screen);
        if (fragment == null) {
            switch (screen) {
                case FEED:
                    fragment = FeedFragment.newInstance();
                    fragments.put(Screen.FEED, fragment);
                    break;
                case TODOS:
                    fragment = ToDosFragment.newInstance();
                    fragments.put(Screen.TODOS, fragment);
                    break;
                case ALBUMS:
                    fragment = AlbumsFragment.newInstance();
                    fragments.put(Screen.ALBUMS, fragment);
                    break;
                case PROFILE:
                    fragment = ProfileFragment.newInstance();
                    fragments.put(Screen.PROFILE, fragment);
                    break;
            }
        }
        return fragment;
    }

}
