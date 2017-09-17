package com.toshkin.activtrades.albums.detail;

import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.LinearSnapHelper;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SnapHelper;
import android.view.View;
import android.widget.ProgressBar;

import com.toshkin.activtrades.R;
import com.toshkin.activtrades.app.mvp.BasePresenterActivity;
import com.toshkin.activtrades.network.pojos.Album;
import com.toshkin.activtrades.network.pojos.Photo;

import java.util.ArrayList;

public class AlbumPhotosActivity extends BasePresenterActivity<AlbumPhotosPresenter> implements AlbumPhotosView {
    public static final String EXTRA_ALBUM = "AlbumPhotosActivity.EXTRA_ALBUM";

    private Album album;
    private ProgressBar progressBar;
    private PhotosAdapter photosAdapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photo_view);
        album = getIntent().getParcelableExtra(EXTRA_ALBUM);
        progressBar = (ProgressBar) findViewById(R.id.loading_indicator);

        RecyclerView photosRecyclerView = (RecyclerView) findViewById(R.id.scroll_gallery_view);
        photosAdapter = new PhotosAdapter();
        photosRecyclerView.setAdapter(photosAdapter);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        photosRecyclerView.setLayoutManager(linearLayoutManager);

        SnapHelper snapHelper = new LinearSnapHelper();
        snapHelper.attachToRecyclerView(photosRecyclerView);
    }

    @Override
    protected AlbumPhotosPresenter createPresenter() {
        return new AlbumPhotosPresenter(getApp().getAlbumsManager());
    }

    @Override
    public void onStart() {
        super.onStart();
        getPresenter().getPhotos(album.getId());
    }

    @Override
    public void onError(String message) {
        Snackbar.make(progressBar, message, Snackbar.LENGTH_SHORT)
                .setActionTextColor(Color.RED)
                .show();
    }

    @Override
    public void showLoadingIndicator() {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideLoadingIndicator() {
        progressBar.setVisibility(View.GONE);
    }

    @Override
    public void onPhotosLoaded(ArrayList<Photo> photos) {
        photosAdapter.addItems(photos);
    }
}
