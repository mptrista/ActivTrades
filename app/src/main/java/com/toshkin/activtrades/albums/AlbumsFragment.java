package com.toshkin.activtrades.albums;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.toshkin.activtrades.R;
import com.toshkin.activtrades.albums.detail.AlbumPhotosActivity;
import com.toshkin.activtrades.app.mvp.BasePresenterFragment;
import com.toshkin.activtrades.network.pojos.Album;

import java.util.ArrayList;

public class AlbumsFragment extends BasePresenterFragment<AlbumsPresenter> implements AlbumsView,
        AlbumsAdapter.AlbumActionListner {
    public static final String TAG = AlbumsFragment.class.getSimpleName();

    private AlbumsAdapter albumsAdapter;
    private ProgressBar progressBar;

    public static AlbumsFragment newInstance() {
        return new AlbumsFragment();
    }

    @Override
    protected AlbumsPresenter createPresenter() {
        return new AlbumsPresenter(getApp().getAlbumsManager());
    }

    @Override
    public String TAG() {
        return TAG;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_albums, container, false);
        RecyclerView albumsRecyclerView = (RecyclerView) view.findViewById(R.id.albums_recycler_view);
        albumsAdapter = new AlbumsAdapter();
        albumsRecyclerView.setAdapter(albumsAdapter);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        layoutManager.setSmoothScrollbarEnabled(true);
        albumsRecyclerView.setLayoutManager(layoutManager);
        progressBar = (ProgressBar) view.findViewById(R.id.loading_indicator);
        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        albumsAdapter.setListner(this);
        getPresenter().getAlbums();
    }

    @Override
    public void onStop() {
        super.onStop();
        albumsAdapter.setListner(null);
    }

    @Override
    public void onAlbumClick(Album album) {
        Intent intent = new Intent(getContext(), AlbumPhotosActivity.class);
        intent.putExtra(AlbumPhotosActivity.EXTRA_ALBUM, album);
        startActivity(intent);
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
    public void onNewAlbums(ArrayList<Album> albums) {
        albumsAdapter.addItems(albums);
    }
}
