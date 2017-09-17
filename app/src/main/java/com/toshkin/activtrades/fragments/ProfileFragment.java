package com.toshkin.activtrades.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.toshkin.activtrades.R;
import com.toshkin.activtrades.app.mvp.BasePresenterFragment;
import com.toshkin.activtrades.presenters.ProfilePresenter;
import com.toshkin.activtrades.views.ProfileView;

public class ProfileFragment extends BasePresenterFragment<ProfilePresenter> implements ProfileView {

    public static final String TAG = ProfileFragment.class.getSimpleName();

    public static ProfileFragment newInstance() {
        return new ProfileFragment();
    }

    @Override
    protected ProfilePresenter createPresenter() {
        return new ProfilePresenter();
    }

    @Override
    public String TAG() {
        return TAG;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_profile, container, false);
    }
}
