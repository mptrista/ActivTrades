package com.toshkin.activtrades.app;

import android.os.Bundle;
import android.support.annotation.CallSuper;
import android.support.v7.app.AppCompatDelegate;

/**
 * <p>The abstract base fragment that works with {@link BasePresenter}.</p>
 * <p>The fragment should be inherited in order to apply the MVP architecture.</p>
 *
 * @param <PresenterType> the presenter type
 */
public abstract class BasePresenterFragment<PresenterType extends BasePresenter> extends BaseFragment {

    private PresenterType presenter;
    private boolean isNewFragment;

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
        isNewFragment = (savedInstanceState == null);
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
        presenter.takeView(this, isNewFragment);
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

