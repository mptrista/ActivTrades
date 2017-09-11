package com.toshkin.activtrades.presenters;

import com.toshkin.activtrades.app.mvp.BasePresenter;
import com.toshkin.activtrades.views.AlbumsView;

public class AlbumsPresenter extends BasePresenter<AlbumsView> {
    public AlbumsPresenter(Retrofit retrofit) {
        super(retrofit);
    }
}
