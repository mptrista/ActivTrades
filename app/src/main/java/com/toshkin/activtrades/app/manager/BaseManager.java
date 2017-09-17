package com.toshkin.activtrades.app.manager;

import com.toshkin.activtrades.network.API;

public abstract class BaseManager {

    public static final String ERROR_CONNECTING_TO_THE_SERVER = "Error connecting to the server";
    private API api;

    public BaseManager(API api) {
        this.api = api;
    }

    protected API getApi() {
        return api;
    }
}
