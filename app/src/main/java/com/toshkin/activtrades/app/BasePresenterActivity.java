package com.toshkin.activtrades.app;

import android.os.Bundle;
import android.support.annotation.CallSuper;
import android.support.v7.app.AppCompatDelegate;

public abstract class BasePresenterActivity<PresenterType extends BasePresenter> extends BaseActivity {

    private PresenterType presenter;
    private boolean isNewActivity;

    static {
        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true);
    }

    /**
     * <p>This method should be overridden in order to instantiate a {@link BasePresenter}.</p>
     * <p>This is also where you can get reference in the Fragment to the Presenter.</p>
     *
     * @return the presenter type
     */
    protected abstract PresenterType createPresenter();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        isNewActivity = (savedInstanceState == null);
        presenter = createPresenter();
    }

    /**
     * Getter for the instance of the {@link BasePresenter}
     *
     * @return {@link BasePresenter}
     */
    public PresenterType getPresenter() {
        return presenter;
    }

    @Override
    @CallSuper
    public void onStart() {
        //noinspection unchecked
        presenter.takeView(this, isNewActivity);
        presenter.onViewAttached();
        super.onStart();
    }

    @Override
    @CallSuper
    public void onStop() {
        presenter.dropView();
        presenter.onViewDetached();
        super.onStop();
    }

    @Override
    public void onDestroy() {
        presenter.onViewDestroyed();
        super.onDestroy();
    }
}