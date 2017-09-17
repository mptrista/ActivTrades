package com.toshkin.activtrades.network;

import com.google.gson.JsonElement;
import com.toshkin.activtrades.network.pojos.Comment;
import com.toshkin.activtrades.network.pojos.CreatedResponse;
import com.toshkin.activtrades.network.pojos.Post;

import org.json.JSONArray;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface API {

    //POSTS

    @POST("/posts")
    Call<Post> addPost(@Body Post post);

    @DELETE("/posts/{id}")
    Call<Void> deletePost(@Path("id") int id);

    @GET("/posts/{id}")
    Call<Post> getPost(@Path("id") int id);

    @GET("/posts")
    Call<JsonElement> getAllPost();

    // COMMENTS

    @GET("/posts/{id}/comments")
    Call<JsonElement> getComments(@Path("id") int id);

    @POST("/posts/{id}/comments")
    Call<Comment> addComment(@Path("id") int id, @Body Comment comment);

    @DELETE("/posts/{id}/comments/{comment_id}")
    Call<Void> deleteComment(@Path("id") int id, @Path("comment_id") int commentID);
}
