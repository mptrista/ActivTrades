package com.toshkin.activtrades.feed;

import android.support.annotation.NonNull;
import android.text.TextUtils;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.internal.Excluder;
import com.google.gson.reflect.TypeToken;
import com.toshkin.activtrades.app.manager.BaseManager;
import com.toshkin.activtrades.network.API;
import com.toshkin.activtrades.network.Callback;
import com.toshkin.activtrades.network.pojos.CreatedResponse;
import com.toshkin.activtrades.network.pojos.Post;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.UUID;

import retrofit2.Call;
import retrofit2.Response;

public class PostsManager extends BaseManager {

    public static final String ERROR_CONNECTING_TO_THE_SERVER = "Error connecting to the server";
    private Gson gson;

    public PostsManager(API api, Gson gson) {
        super(api);
        this.gson = gson;
    }

    public void getPosts(@NonNull Callback<ArrayList<Post>, String> callback) {
        getApi().getAllPost().enqueue(new retrofit2.Callback<JsonElement>() {
            @Override
            public void onResponse(Call<JsonElement> call, Response<JsonElement> response) {
                ArrayList<Post> posts = gson.fromJson(response.body(), new TypeToken<ArrayList<Post>>() {
                }.getType());
                if (response.isSuccessful()) {
                    callback.onSuccess(posts);
                } else {
                    callback.onError(ERROR_CONNECTING_TO_THE_SERVER);
                }
            }

            @Override
            public void onFailure(Call<JsonElement> call, Throwable t) {
                callback.onError(t.getLocalizedMessage());
            }
        });
    }

    public void getPost(int postID, @NonNull Callback<Post, String> callback) {
        getApi().getPost(postID).enqueue(new retrofit2.Callback<Post>() {
            @Override
            public void onResponse(Call<Post> call, Response<Post> response) {
            }

            @Override
            public void onFailure(Call<Post> call, Throwable t) {
                callback.onError(t.getLocalizedMessage());
            }
        });
    }


    public void deletePost(int postID, @NonNull Callback<Integer, String> callback) {
        getApi().deletePost(postID).enqueue(new retrofit2.Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {

                if (response.isSuccessful()) {
                    callback.onSuccess(postID);
                } else {
                    //Workaround the server not returning correct response
                    if (404 == response.code()) {
                        callback.onSuccess(postID);
                    } else {
                        callback.onError(ERROR_CONNECTING_TO_THE_SERVER);
                    }
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                callback.onError(t.getLocalizedMessage());
            }
        });
    }

    public void addPost(@NonNull Post post, Callback<Post, String> callback) {
        getApi().addPost(post).enqueue(new retrofit2.Callback<Post>() {
            @Override
            public void onResponse(Call<Post> call, Response<Post> response) {
                if (response.isSuccessful()) {
                    // Setting random ids because the server is returning only 101 for id
                    // and not recognizing it when trying to delete it anyway
                    response.body().setId(UUID.randomUUID().hashCode());
                    callback.onSuccess(response.body());
                } else {
                    callback.onError(response.errorBody().toString());
                }
            }

            @Override
            public void onFailure(Call<Post> call, Throwable t) {
                callback.onError(t.getLocalizedMessage());
            }
        });
    }

}
