package com.toshkin.activtrades.albums.detail;

import com.toshkin.activtrades.app.mvp.ErrorViewIndication;
import com.toshkin.activtrades.app.mvp.LoadingViewIndication;
import com.toshkin.activtrades.network.pojos.Photo;

import java.util.ArrayList;

interface AlbumPhotosView extends LoadingViewIndication, ErrorViewIndication {
    void onPhotosLoaded(ArrayList<Photo> photos);
}
