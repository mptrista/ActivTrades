package com.toshkin.activtrades.feed.detail;

import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import com.toshkin.activtrades.R;
import com.toshkin.activtrades.app.adapter.BaseAdapter;
import com.toshkin.activtrades.network.pojos.Comment;

class CommentsAdapter extends BaseAdapter<Comment, CommentsAdapter.CommentViewHolder> {

    private CommentActionListener listner;

    interface CommentActionListener {
        void onCommentDelete(Comment comment);
    }

    void setActionListener(CommentActionListener listener) {
        this.listner = listener;
    }

    @Override
    public CommentViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.item_comment, parent, false);
        return new CommentViewHolder(view);
    }

    @Override
    public void onBindViewHolder(CommentViewHolder holder, int position) {
        holder.onBind(getItem(position), listner);
    }

    void removeComment(int id) {
        for (int i = 0; i < getItemCount(); i++) {
            if (getItem(i).getId() == id) {
                getItems().remove(i);
                notifyItemRemoved(i);
            }
        }
    }

    static class CommentViewHolder extends RecyclerView.ViewHolder {
        private TextView usernameTextView;
        private TextView commentTextView;
        private ImageButton deleteButton;

        CommentViewHolder(View itemView) {
            super(itemView);
            usernameTextView = (TextView) itemView.findViewById(R.id.comment_user_view);
            commentTextView = (TextView) itemView.findViewById(R.id.comment_body_view);
            deleteButton = (ImageButton) itemView.findViewById(R.id.delete_button);
        }

        void onBind(Comment comment, CommentActionListener listener) {
            if (!TextUtils.isEmpty(comment.getName())) {
                usernameTextView.setText(comment.getName());
            } else {
                usernameTextView.setText(R.string.label_anonymous);
            }
            if (!TextUtils.isEmpty(comment.getBody())) {
                commentTextView.setText(comment.getBody());
            }
            deleteButton.setOnClickListener(v -> {
                if (listener != null) {
                    listener.onCommentDelete(comment);
                }
            });

        }
    }
}
