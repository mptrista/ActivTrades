package com.toshkin.activtrades.feed;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.toshkin.activtrades.R;
import com.toshkin.activtrades.app.mvp.BasePresenterFragment;
import com.toshkin.activtrades.network.pojos.Post;

import java.util.List;

public class PostsFragment extends BasePresenterFragment<PostsPresenter> implements PostsView,
        PostsAdapter.ItemActionListener {
    public static final String TAG = PostsFragment.class.getSimpleName();

    private PostsAdapter postsAdapter;

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
        getPresenter().getPosts();
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
        RecyclerView profileRecyclerView = (RecyclerView) view.findViewById(R.id.profileRecyclerView);
        postsAdapter = new PostsAdapter();
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        linearLayoutManager.setSmoothScrollbarEnabled(true);
        profileRecyclerView.setAdapter(postsAdapter);
        profileRecyclerView.setLayoutManager(linearLayoutManager);
        return view;
    }

    @Override
    public void onError(String message) {

    }

    @Override
    public void showLoadingIndicator() {

    }

    @Override
    public void hideLoadingIndicator() {

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
    public void onDeletePostRequest(Post post) {
        getPresenter().deletePost(post);
    }


}
