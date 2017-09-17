package com.toshkin.activtrades.feed;

import android.support.annotation.NonNull;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.reflect.TypeToken;
import com.toshkin.activtrades.app.manager.BaseManager;
import com.toshkin.activtrades.network.API;
import com.toshkin.activtrades.network.Callback;
import com.toshkin.activtrades.network.pojos.Comment;
import com.toshkin.activtrades.network.pojos.Post;

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

    public void getComments(int postId, Callback<ArrayList<Comment>, String> callback) {
        getApi().getComments(postId).enqueue(new retrofit2.Callback<JsonElement>() {
            @Override
            public void onResponse(Call<JsonElement> call, Response<JsonElement> response) {
                ArrayList<Comment> comments = gson.fromJson(response.body(), new TypeToken<ArrayList<Comment>>() {
                }.getType());
                if (response.isSuccessful()) {
                    callback.onSuccess(comments);
                } else {
                    callback.onError(ERROR_CONNECTING_TO_THE_SERVER);
                }
            }

            @Override
            public void onFailure(Call<JsonElement> call, Throwable t) {
                callback.onError(ERROR_CONNECTING_TO_THE_SERVER);
            }
        });
    }

    public void deleteComment(int postID, int commentID, @NonNull Callback<Integer, String> callback) {
        getApi().deleteComment(postID, commentID).enqueue(new retrofit2.Callback<Void>() {
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

    public void addComment(int postID, @NonNull Comment comment, Callback<Comment, String> callback) {
        getApi().addComment(postID, comment).enqueue(new retrofit2.Callback<Comment>() {
            @Override
            public void onResponse(Call<Comment> call, Response<Comment> response) {
                if (response.isSuccessful()) {
                    // Setting random ids because the server is returning same id
                    // and not recognizing it when trying to delete it anyway
                    response.body().setId(UUID.randomUUID().hashCode());
                    callback.onSuccess(response.body());
                } else {
                    callback.onError(response.errorBody().toString());
                }
            }

            @Override
            public void onFailure(Call<Comment> call, Throwable t) {
                callback.onError(t.getLocalizedMessage());
            }
        });
    }
}
