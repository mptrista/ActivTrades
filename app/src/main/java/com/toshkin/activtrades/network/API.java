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
    Call<List<Comment>> getComments(@Path("id") int id);

    @POST("/posts/{id}/comments")
    Call<List<Comment>> addComment(@Path("id") int id, @Body Comment comment);

    @DELETE("/posts/{id}/comments/{commend_id}")
    Call<List<Comment>> addComment(@Path("id") int id, @Path("commend_id") String commentID);
}
