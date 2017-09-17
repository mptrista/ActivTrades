package com.toshkin.activtrades.albums;

import com.toshkin.activtrades.app.mvp.ErrorViewIndication;
import com.toshkin.activtrades.app.mvp.LoadingViewIndication;
import com.toshkin.activtrades.network.pojos.Album;

import java.util.ArrayList;

interface AlbumsView extends LoadingViewIndication, ErrorViewIndication {
    void onNewAlbums(ArrayList<Album> albums);
}
