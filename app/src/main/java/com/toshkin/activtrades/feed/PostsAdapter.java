package com.toshkin.activtrades.feed;

import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import com.toshkin.activtrades.R;
import com.toshkin.activtrades.app.adapter.BaseAdapter;
import com.toshkin.activtrades.network.pojos.Post;

class PostsAdapter extends BaseAdapter<Post, PostsAdapter.PostViewHolder> {

    private ItemActionListener actionListener;

    public void setActionListener(ItemActionListener actionListener) {
        this.actionListener = actionListener;
    }

    public interface ItemActionListener {
        void onDeletePostRequest(Post post);

        void onPostDetailRequest(Post post);
    }

    public void removePost(int id) {
        for (int i = 0; i < getItemCount(); i++) {
            if (getItem(i).getId() == id) {
                getItems().remove(i);
                notifyItemRemoved(i);
            }
        }
    }

    @Override
    public PostViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.item_post, parent, false);
        return new PostViewHolder(view);
    }

    @Override
    public void onBindViewHolder(PostViewHolder holder, int position) {
        Post currentItem = getItem(position);
        holder.onBind(currentItem, actionListener);
    }

    static class PostViewHolder extends RecyclerView.ViewHolder {

        private TextView titleTextView;
        private TextView bodyTextView;
        private ImageButton imageButton;

        PostViewHolder(View itemView) {
            super(itemView);
            titleTextView = (TextView) itemView.findViewById(R.id.titleTextView);
            bodyTextView = (TextView) itemView.findViewById(R.id.bodyTextView);
            imageButton = (ImageButton) itemView.findViewById(R.id.delete_button);
        }

        void onBind(Post post, ItemActionListener listener) {
            String title = post.getTitle();
            if (!TextUtils.isEmpty(title)) {
                String processedTitle = title.trim().replaceAll("\\s+", " ");
                titleTextView.setText(processedTitle);
            }
            String body = post.getBody();
            if (!TextUtils.isEmpty(body)) {
                String processedBody = body.trim().replaceAll("\\s+", " ");
                bodyTextView.setText(processedBody);
            }
            imageButton.setOnClickListener(v -> {
                if (listener != null) {
                    listener.onDeletePostRequest(post);
                }
            });
            itemView.setOnClickListener(v -> {
                if (listener != null) {
                    listener.onPostDetailRequest(post);
                }
            });
        }

    }
}
