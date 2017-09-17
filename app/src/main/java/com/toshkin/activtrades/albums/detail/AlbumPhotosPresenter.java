package com.toshkin.activtrades.albums.detail;

import com.toshkin.activtrades.albums.AlbumsManager;
import com.toshkin.activtrades.app.mvp.BasePresenter;
import com.toshkin.activtrades.network.Callback;
import com.toshkin.activtrades.network.pojos.Photo;

import java.util.ArrayList;

class AlbumPhotosPresenter extends BasePresenter<AlbumPhotosView> {
    private AlbumsManager albumsManager;

    AlbumPhotosPresenter(AlbumsManager albumsManager) {
        this.albumsManager = albumsManager;
    }

    public void getPhotos(int albumID) {
        if (isOperational()) {
            getView().showLoadingIndicator();
        }
        albumsManager.getPhotos(albumID, new Callback<ArrayList<Photo>, String>() {
            @Override
            public void onSuccess(ArrayList<Photo> photos) {
                if (isOperational()) {
                    getView().hideLoadingIndicator();
                    getView().onPhotosLoaded(photos);
                }
            }

            @Override
            public void onError(String error) {
                getView().onError(error);
            }
        });
    }
}
