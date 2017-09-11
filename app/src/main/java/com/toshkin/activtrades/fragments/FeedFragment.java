package com.toshkin.activtrades.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.toshkin.activtrades.R;
import com.toshkin.activtrades.app.mvp.BasePresenterFragment;
import com.toshkin.activtrades.presenters.FeedPresenter;
import com.toshkin.activtrades.views.FeedView;

public class FeedFragment extends BasePresenterFragment<FeedPresenter> implements FeedView {
    public static final String TAG = FeedFragment.class.getSimpleName();

    public static FeedFragment newInstance() {
        return new FeedFragment();
    }

    @Override
    protected FeedPresenter createPresenter() {
        return null;
    }

    @Override
    public String TAG() {
        return TAG;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_feed, container, false);
    }
}
