package com.toshkin.activtrades.feed.detail;

import com.toshkin.activtrades.app.mvp.BasePresenter;
import com.toshkin.activtrades.feed.PostsManager;
import com.toshkin.activtrades.network.Callback;
import com.toshkin.activtrades.network.pojos.Comment;

import java.util.ArrayList;

public class PostDetailPresenter extends BasePresenter<PostDetailView> {

    private PostsManager manager;

    public PostDetailPresenter(PostsManager postsManager) {
        this.manager = postsManager;
    }

    void getComments(int postID) {
        if (isOperational()) {
            getView().showLoadingIndicator();
        }
        manager.getComments(postID, new Callback<ArrayList<Comment>, String>() {
            @Override
            public void onSuccess(ArrayList<Comment> response) {
                if (isOperational()) {
                    getView().hideLoadingIndicator();
                    getView().onCommentsLoaded(response);
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

    void deleteComment(int postID, Comment comment) {
        if (isOperational()) {
            getView().showLoadingIndicator();
        }
        manager.deleteComment(postID, comment.getId(), new Callback<Integer, String>() {
            @Override
            public void onSuccess(Integer commentId) {
                if (isOperational()) {
                    getView().hideLoadingIndicator();
                    getView().onCommentDeleted(comment);
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

    void addComment(int postID, String body) {
        if (isOperational()) {
            getView().showLoadingIndicator();
        }
        Comment comment = new Comment(body);
        manager.addComment(postID, comment, new Callback<Comment, String>() {
            @Override
            public void onSuccess(Comment comment) {
                if (isOperational()) {
                    getView().hideLoadingIndicator();
                    getView().onCommentAdded(comment);
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
