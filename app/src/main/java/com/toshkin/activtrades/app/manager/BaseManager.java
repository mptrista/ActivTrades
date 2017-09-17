package com.toshkin.activtrades.app.manager;

import com.toshkin.activtrades.network.API;

public abstract class BaseManager {
    private API api;

    public BaseManager(API api) {
        this.api = api;
    }

    protected API getApi() {
        return api;
    }
}
