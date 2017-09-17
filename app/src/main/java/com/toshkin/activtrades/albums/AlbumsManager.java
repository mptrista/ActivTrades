package com.toshkin.activtrades.albums;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.reflect.TypeToken;
import com.toshkin.activtrades.app.manager.BaseManager;
import com.toshkin.activtrades.network.API;
import com.toshkin.activtrades.network.Callback;
import com.toshkin.activtrades.network.pojos.Album;
import com.toshkin.activtrades.network.pojos.Photo;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Response;

public class AlbumsManager extends BaseManager {

    private Gson gson;

    public AlbumsManager(API api, Gson gson) {
        super(api);
        this.gson = gson;
    }

    void getAlbums(Callback<ArrayList<Album>, String> callback) {
        getApi().getAlbums().enqueue(new retrofit2.Callback<JsonElement>() {
            @Override
            public void onResponse(Call<JsonElement> call, Response<JsonElement> response) {
                if (response.isSuccessful()) {
                    ArrayList<Album> albums = gson.fromJson(response.body(), new TypeToken<ArrayList<Album>>() {
                    }.getType());
                    callback.onSuccess(albums);
                } else {
                    callback.onError(ERROR_CONNECTING_TO_THE_SERVER);
                }
            }

            @Override
            public void onFailure(Call<JsonElement> call, Throwable t) {

            }
        });
    }

    public void getPhotos(int albuID, Callback<ArrayList<Photo>, String> callback) {
        getApi().getPhotos(albuID).enqueue(new retrofit2.Callback<JsonElement>() {
            @Override
            public void onResponse(Call<JsonElement> call, Response<JsonElement> response) {
                if (response.isSuccessful()) {
                    ArrayList<Photo> albums = gson.fromJson(response.body(), new TypeToken<ArrayList<Photo>>() {
                    }.getType());
                    callback.onSuccess(albums);
                } else {
                    callback.onError(ERROR_CONNECTING_TO_THE_SERVER);
                }
            }

            @Override
            public void onFailure(Call<JsonElement> call, Throwable t) {

            }
        });
    }

}
