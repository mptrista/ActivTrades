package com.toshkin.activtrades.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.toshkin.activtrades.R;
import com.toshkin.activtrades.app.mvp.BasePresenter;
import com.toshkin.activtrades.app.mvp.BasePresenterFragment;
import com.toshkin.activtrades.views.AlbumsView;

public class AlbumsFragment extends BasePresenterFragment implements AlbumsView {
    public static final String TAG = AlbumsFragment.class.getSimpleName();

    public static AlbumsFragment newInstance() {
        return new AlbumsFragment();
    }

    @Override
    protected BasePresenter createPresenter() {
        return null;
    }

    @Override
    public String TAG() {
        return TAG;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_albums, container, false);
    }
}
