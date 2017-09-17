package com.toshkin.activtrades.feed;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.ProgressBar;

import com.toshkin.activtrades.R;
import com.toshkin.activtrades.app.mvp.BasePresenterFragment;
import com.toshkin.activtrades.feed.detail.PostDetailActivity;
import com.toshkin.activtrades.network.pojos.Post;

import java.util.List;

public class PostsFragment extends BasePresenterFragment<PostsPresenter> implements PostsView,
        PostsAdapter.ItemActionListener {
    public static final String TAG = PostsFragment.class.getSimpleName();

    private PostsAdapter postsAdapter;
    private RecyclerView postsRecyclerView;
    private ProgressBar progressBar;
    private EditText titleView;
    private EditText bodyView;

    public static PostsFragment newInstance() {
        return new PostsFragment();
    }

    @Override
    protected PostsPresenter createPresenter() {
        return new PostsPresenter(getApp().getPostsManager());
    }

    @Override
    public String TAG() {
        return TAG;
    }

    @Override
    public void onStart() {
        super.onStart();
        if (postsAdapter.isEmpty()) {
            getPresenter().getPosts();
        }
        postsAdapter.setActionListener(this);
    }

    @Override
    public void onStop() {
        super.onStop();
        postsAdapter.setActionListener(null);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_posts, container, false);
        postsRecyclerView = (RecyclerView) view.findViewById(R.id.profileRecyclerView);
        postsAdapter = new PostsAdapter();
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        linearLayoutManager.setSmoothScrollbarEnabled(true);
        postsRecyclerView.setAdapter(postsAdapter);
        postsRecyclerView.setLayoutManager(linearLayoutManager);

        progressBar = (ProgressBar) view.findViewById(R.id.loading_indicator);
        titleView = (EditText) view.findViewById(R.id.post_title_text_view);
        bodyView = (EditText) view.findViewById(R.id.post_body_text_view);
        bodyView.setOnEditorActionListener((v, actionId, event) -> {
            if (actionId == EditorInfo.IME_ACTION_DONE) {
                if (validateInput()) {
                    setEnableViews(false);
                    getPresenter().addPost(titleView.getText().toString(), bodyView.getText().toString());
                } else {
                    Snackbar.make(titleView, "Title and Body should be filled",
                            Snackbar.LENGTH_SHORT)
                            .setActionTextColor(Color.RED)
                            .show();
                }
                return true;
            } else {
                return false;
            }
        });
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        postsRecyclerView = null;
        progressBar = null;
        titleView = null;
        bodyView = null;
    }

    private boolean validateInput() {
        String title = titleView.getText().toString().trim();
        if (TextUtils.isEmpty(title)) {
            return false;
        }
        String body = bodyView.getText().toString().trim();
        return !TextUtils.isEmpty(body);
    }

    @Override
    public void onError(String message) {
        setEnableViews(true);
        Snackbar.make(titleView, message, Snackbar.LENGTH_SHORT)
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
    public void onNewPosts(List<Post> posts) {
        postsAdapter.addItems(posts);
    }

    @Override
    public void onPostDeleted(int postId) {
        postsAdapter.removePost(postId);
    }

    @Override
    public void onPostAdded(Post post) {
        postsAdapter.addItemFirst(post);
        setEnableViews(true);
        titleView.setText("");
        bodyView.setText("");
        postsRecyclerView.smoothScrollToPosition(0);
    }

    private void setEnableViews(boolean enabled) {
        titleView.setEnabled(enabled);
        bodyView.setEnabled(enabled);
    }

    @Override
    public void onDeletePostRequest(Post post) {
        getPresenter().deletePost(post);
    }

    @Override
    public void onPostDetailRequest(Post post) {
        Intent intent = new Intent(getContext(), PostDetailActivity.class);
        intent.putExtra(PostDetailActivity.EXTRA_POST, post);
        startActivity(intent);
    }

}
