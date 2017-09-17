package com.toshkin.activtrades.network;

public interface Callback<R, E> {
    void onSuccess(R response);

    void onError(E error);
}
