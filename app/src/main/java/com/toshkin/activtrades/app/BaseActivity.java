
package com.toshkin.activtrades.app;

import android.support.v7.app.AppCompatActivity;
import android.view.inputmethod.InputMethodManager;

public abstract class BaseActivity extends AppCompatActivity {


    public void hideSoftKeyboard() {
        if (getCurrentFocus() != null) {
            InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
            inputMethodManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
        }
    }

    public void showSoftKeyboard() {
        if (getCurrentFocus() != null) {
            InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
            inputMethodManager.showSoftInput(getCurrentFocus(), 0);
        }
    }

    protected ActivTradesApplication getApp() {
        return (ActivTradesApplication) getApplication();
    }

}