package com.toshkin.activtrades.app;

import android.app.Application;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.toshkin.activtrades.albums.AlbumsManager;
import com.toshkin.activtrades.feed.PostsManager;
import com.toshkin.activtrades.app.manager.ToDosManager;
import com.toshkin.activtrades.app.manager.UserManager;
import com.toshkin.activtrades.network.API;
import com.toshkin.activtrades.network.CollectionDeserializer;

import java.util.Collection;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ActivTradesApplication extends Application {

    public static final String HTTPS_BASE_URL = "https://jsonplaceholder.typicode.com";

    private API api;
    private Gson gson;
    private AlbumsManager albumsManager;
    private PostsManager postsManager;
    private ToDosManager toDosManager;
    private UserManager userManager;

    @Override
    public void onCreate() {
        super.onCreate();
        initRetrofit();
    }

    public AlbumsManager getAlbumsManager() {
        if (albumsManager == null) {
            albumsManager = new AlbumsManager(api, gson);
        }
        return albumsManager;
    }

    public PostsManager getPostsManager() {
        if (postsManager == null) {
            postsManager = new PostsManager(api, gson);
        }
        return postsManager;
    }

    public ToDosManager getToDosManager() {
        if (toDosManager == null) {
            toDosManager = new ToDosManager(api);
        }
        return toDosManager;
    }

    public UserManager getUserManager() {
        if (userManager == null) {
            userManager = new UserManager(api);
        }
        return userManager;
    }

    private void initRetrofit() {
        OkHttpClient client = new OkHttpClient.Builder()
                .retryOnConnectionFailure(true)
                .connectTimeout(15, TimeUnit.SECONDS)
                .readTimeout(15, TimeUnit.SECONDS)
                .writeTimeout(30, TimeUnit.SECONDS)
                .followRedirects(true)
                .followSslRedirects(true)
                .build();

        gson = new GsonBuilder()
                .registerTypeAdapter(Collection.class, new CollectionDeserializer())
                .create();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(HTTPS_BASE_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        api = retrofit.create(API.class);
    }

}