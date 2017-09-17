package com.toshkin.activtrades.feed;

import com.toshkin.activtrades.app.mvp.ErrorViewIndication;
import com.toshkin.activtrades.app.mvp.LoadingViewIndication;
import com.toshkin.activtrades.network.pojos.Post;

import java.util.List;

public interface PostsView extends LoadingViewIndication, ErrorViewIndication{
    void onNewPosts(List<Post> posts);
    void onPostDeleted(int postId);
}
