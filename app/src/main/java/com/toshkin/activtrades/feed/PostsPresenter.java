package com.toshkin.activtrades.feed;

import com.toshkin.activtrades.app.mvp.BasePresenter;
import com.toshkin.activtrades.network.Callback;
import com.toshkin.activtrades.network.pojos.Post;

import java.util.ArrayList;

class PostsPresenter extends BasePresenter<PostsView> {

    private PostsManager manager;

    PostsPresenter(PostsManager manager) {
        this.manager = manager;
    }

    void getPosts() {
        if (isOperational()) {
            getView().showLoadingIndicator();
        }
        manager.getPosts(new Callback<ArrayList<Post>, String>() {
            @Override
            public void onSuccess(ArrayList<Post> response) {
                if (isOperational()) {
                    getView().hideLoadingIndicator();
                    getView().onNewPosts(response);
                }
            }

            @Override
            public void onError(String error) {
                if (isOperational()) {
                    getView().hideLoadingIndicator();
                    getView().onError(error);
                }
            }
        });
    }

    void deletePost(Post post) {
        if (isOperational()) {
            getView().showLoadingIndicator();
        }
        manager.deletePost(post.getId(), new Callback<Integer, String>() {
            @Override
            public void onSuccess(Integer postId) {
                if (isOperational()) {
                    getView().hideLoadingIndicator();
                    getView().onPostDeleted(postId);
                }
            }

            @Override
            public void onError(String error) {
                if (isOperational()) {
                    getView().hideLoadingIndicator();
                    getView().onError(error);
                }
            }
        });
    }


    public void addPost(String title, String body) {
        if (isOperational()) {
            getView().showLoadingIndicator();
        }
        Post post = new Post(title, body);
        manager.addPost(post, new Callback<Post, String>() {
            @Override
            public void onSuccess(Post post) {
                if (isOperational()) {
                    getView().hideLoadingIndicator();
                    getView().onPostAdded(post);
                }
            }

            @Override
            public void onError(String error) {
                if (isOperational()) {
                    getView().hideLoadingIndicator();
                    getView().onError(error);
                }
            }
        });
    }
}
