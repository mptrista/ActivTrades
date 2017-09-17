package com.toshkin.activtrades.feed.detail;

import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.toshkin.activtrades.R;
import com.toshkin.activtrades.app.mvp.BasePresenterActivity;
import com.toshkin.activtrades.app.mvp.ErrorViewIndication;
import com.toshkin.activtrades.app.mvp.LoadingViewIndication;
import com.toshkin.activtrades.network.pojos.Comment;
import com.toshkin.activtrades.network.pojos.Post;

import java.util.ArrayList;

public class PostDetailActivity extends BasePresenterActivity<PostDetailPresenter> implements
        LoadingViewIndication, ErrorViewIndication, PostDetailView, CommentsAdapter.CommentActionListener {
    public static final String EXTRA_POST = "PostDetailActivity.EXTRA_POST";

    private Post post;

    private ProgressBar progressBar;

    private CommentsAdapter commentsAdapter;
    private RecyclerView commentRecyclerView;

    private EditText commentEditText;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_post);
        post = getIntent().getParcelableExtra(EXTRA_POST);
        progressBar = (ProgressBar) findViewById(R.id.loading_indicator);
        commentEditText = (EditText) findViewById(R.id.comment_edit_text);
        commentEditText.setOnEditorActionListener((v, actionId, event) -> {
            if (actionId == EditorInfo.IME_ACTION_SEND) {
                if (validateInput()) {
                    getPresenter().addComment(post.getId(), commentEditText.getText().toString());
                } else {
                    Snackbar.make(commentEditText, "Comment should be filled",
                            Snackbar.LENGTH_SHORT)
                            .setActionTextColor(Color.RED)
                            .show();
                }
                return true;
            } else {
                return false;
            }
        });
        commentsAdapter = new CommentsAdapter();
        configurePostViews();
        configureRecyclerView();
    }

    private boolean validateInput() {
        String comment = commentEditText.getText().toString().trim();
        return !TextUtils.isEmpty(comment);
    }

    @Override
    public void onStart() {
        super.onStart();
        commentsAdapter.setActionListener(this);
        if (commentsAdapter.isEmpty()) {
            getPresenter().getComments(post.getId());
        }
    }

    @Override
    public void onStop() {
        super.onStop();
        commentsAdapter.setActionListener(null);
    }

    private void configurePostViews() {
        TextView titleTextView = (TextView) findViewById(R.id.title_text_view);
        TextView bodyTextView = (TextView) findViewById(R.id.body_text_view);

        titleTextView.setText(post.getTitle());
        bodyTextView.setText(post.getBody());
    }

    private void configureRecyclerView() {
        commentRecyclerView = (RecyclerView) findViewById(R.id.comments_recycler_view);
        commentRecyclerView.setAdapter(commentsAdapter);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setSmoothScrollbarEnabled(true);
        commentRecyclerView.setLayoutManager(linearLayoutManager);
    }

    @Override
    protected PostDetailPresenter createPresenter() {
        return new PostDetailPresenter(getApp().getPostsManager());
    }

    @Override
    public void onError(String message) {
        Snackbar.make(progressBar, message, Snackbar.LENGTH_SHORT)
                .setActionTextColor(Color.RED)
                .show();
    }

    @Override
    public void showLoadingIndicator() {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideLoadingIndicator() {
        progressBar.setVisibility(View.INVISIBLE);
    }

    @Override
    public void onCommentsLoaded(ArrayList<Comment> comments) {
        commentsAdapter.addItems(comments);
    }

    @Override
    public void onCommentAdded(Comment comment) {
        commentsAdapter.addItemFirst(comment);
        commentRecyclerView.smoothScrollToPosition(0);
        commentEditText.setText("");
    }

    @Override
    public void onCommentDeleted(Comment comment) {
        commentsAdapter.removeComment(comment.getId());
    }

    @Override
    public void onCommentDelete(Comment comment) {
        getPresenter().deleteComment(post.getId(), comment);
    }
}
