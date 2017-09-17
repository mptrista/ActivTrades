package com.toshkin.activtrades.albums;

import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.toshkin.activtrades.R;
import com.toshkin.activtrades.app.adapter.BaseAdapter;
import com.toshkin.activtrades.network.pojos.Album;

class AlbumsAdapter extends BaseAdapter<Album, AlbumsAdapter.AlbumViewHolder> {

    private AlbumActionListner listner;

    interface AlbumActionListner {
        void onAlbumClick(Album album);
    }

    public void setListner(AlbumActionListner listner) {
        this.listner = listner;
    }

    @Override
    public AlbumViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.item_album, parent, false);
        return new AlbumsAdapter.AlbumViewHolder(view);
    }

    @Override
    public void onBindViewHolder(AlbumViewHolder holder, int position) {
        holder.onBind(getItem(position), listner);
    }

    static class AlbumViewHolder extends RecyclerView.ViewHolder {

        private TextView userIdTextView;
        private TextView titleTextView;

        AlbumViewHolder(View itemView) {
            super(itemView);
            userIdTextView = (TextView) itemView.findViewById(R.id.comment_user_view);
            titleTextView = (TextView) itemView.findViewById(R.id.comment_body_view);
        }

        void onBind(Album album, AlbumActionListner listener) {
            userIdTextView.setText(String.valueOf(album.getUserId()));
            userIdTextView.setText(R.string.label_anonymous);
            if (!TextUtils.isEmpty(album.getTitle())) {
                titleTextView.setText(album.getTitle());
            }
            itemView.setOnClickListener(v -> {
                if (listener != null) {
                    listener.onAlbumClick(album);
                }
            });
        }
    }
}
