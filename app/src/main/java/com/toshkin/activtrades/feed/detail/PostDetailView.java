package com.toshkin.activtrades.feed.detail;

import com.toshkin.activtrades.app.mvp.ErrorViewIndication;
import com.toshkin.activtrades.app.mvp.LoadingViewIndication;
import com.toshkin.activtrades.network.pojos.Comment;

import java.util.ArrayList;

public interface PostDetailView extends LoadingViewIndication, ErrorViewIndication{
    void onCommentsLoaded(ArrayList<Comment> comments);

    void onCommentAdded(Comment comment);

    void onCommentDeleted(Comment comment);
}
