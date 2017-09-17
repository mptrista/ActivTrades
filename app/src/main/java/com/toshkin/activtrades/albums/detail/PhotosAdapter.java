package com.toshkin.activtrades.albums.detail;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.chrisbanes.photoview.PhotoView;
import com.squareup.picasso.Picasso;
import com.toshkin.activtrades.R;
import com.toshkin.activtrades.app.adapter.BaseAdapter;
import com.toshkin.activtrades.network.pojos.Photo;

class PhotosAdapter extends BaseAdapter<Photo, PhotosAdapter.PhotoViewHolder> {

    @Override
    public PhotoViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.item_photo, parent, false);
        return new PhotosAdapter.PhotoViewHolder(view);
    }

    @Override
    public void onBindViewHolder(PhotoViewHolder holder, int position) {
        holder.onBind(getItem(position).getUrl());
    }

    static class PhotoViewHolder extends RecyclerView.ViewHolder {
        private PhotoView photoView;

        PhotoViewHolder(View itemView) {
            super(itemView);
            photoView = (PhotoView) itemView.findViewById(R.id.photo_view);
        }

        void onBind(String url) {
            Picasso.with(photoView.getContext()).cancelRequest(photoView);
            Picasso.with(photoView.getContext()).load(url).centerCrop().fit().into(photoView);
        }
    }
}
