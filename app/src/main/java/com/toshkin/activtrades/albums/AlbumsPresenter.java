package com.toshkin.activtrades.albums;

import com.toshkin.activtrades.app.mvp.BasePresenter;
import com.toshkin.activtrades.network.Callback;
import com.toshkin.activtrades.network.pojos.Album;

import java.util.ArrayList;

class AlbumsPresenter extends BasePresenter<AlbumsView> {

    private AlbumsManager albumsManager;

    AlbumsPresenter(AlbumsManager albumsManager) {
        this.albumsManager = albumsManager;
    }

    void getAlbums() {
        if (isOperational()) {
            getView().showLoadingIndicator();
        }
        albumsManager.getAlbums(new Callback<ArrayList<Album>, String>() {
            @Override
            public void onSuccess(ArrayList<Album> albums) {
                if (isOperational()) {
                    getView().hideLoadingIndicator();
                    getView().onNewAlbums(albums);
                }
            }

            @Override
            public void onError(String error) {
                if (isOperational()) {
                    getView().hideLoadingIndicator();
                    getView().onError(error);
                }
            }
        });
    }
}

