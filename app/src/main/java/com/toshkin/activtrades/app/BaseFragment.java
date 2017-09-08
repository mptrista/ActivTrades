package com.toshkin.activtrades.app;

import android.app.Activity;
import android.support.v4.app.Fragment;

public abstract class BaseFragment extends Fragment {

    protected ActivTradesApplication getApp() {
        Activity act = getActivity();
        if (act != null) {
            return (ActivTradesApplication) act.getApplication();
        } else {
            throw new IllegalStateException("You must call this method in or after onActivityAttached()");
        }
    }

    public void hideSoftKeyboard() {
        Object obj = getHost();
        if (obj instanceof BaseActivity) {
            ((BaseActivity) obj).hideSoftKeyboard();
        }
    }

    public void showSoftKeyboard() {
        Object obj = getHost();
        if (obj instanceof BaseActivity) {
            ((BaseActivity) obj).showSoftKeyboard();
        }
    }

}
