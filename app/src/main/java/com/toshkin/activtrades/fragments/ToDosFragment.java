package com.toshkin.activtrades.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.toshkin.activtrades.R;
import com.toshkin.activtrades.app.mvp.BasePresenterFragment;
import com.toshkin.activtrades.presenters.ToDosPresenter;
import com.toshkin.activtrades.views.ToDosView;

public class ToDosFragment extends BasePresenterFragment<ToDosPresenter> implements ToDosView {
    public static final String TAG = ToDosFragment.class.getSimpleName();

    public static ToDosFragment newInstance() {
        return new ToDosFragment();
    }

    @Override
    protected ToDosPresenter createPresenter() {
        return null;
    }

    @Override
    public String TAG() {
        return TAG;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_todos, container, false);
    }
}
