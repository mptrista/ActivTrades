package com.toshkin.activtrades.app;

/**
 * <p>The abstract base presenter that needs to be inherited by fragment presenters.</p>
 * <p>The presenter should be instantiated by overriding {@link BasePresenterFragment#createPresenter()}.</p>
 *
 * @param <View> the type parameter
 * @author ltoshkin
 */
public abstract class BasePresenter<View> {

    private View view;
    private boolean isNewInstance;


    /**
     * Getter for the instance of the interface that handles the UI.
     *
     * @return the view
     */
    public View getView() {
        return view;
    }


    /**
     * Return true if the fragment that the presenter is attached to is in the resumed state.
     *
     * @return boolean
     */
    protected boolean isOperational() {
        return view != null;
    }

    /**
     * @return true if the presenter is requested from a new instance of a fragment/activity, false otherwise
     */
    public boolean isNewInstance() {
        return isNewInstance;
    }


    /**
     * Takes view. The method is called in the {@link BasePresenterFragment#onResume()}.
     *
     * @param view the view
     */
    protected void takeView(View view, boolean isNewInstance) {
        if (view == null) {
            throw new NullPointerException("View can't be null. Presenter view interface not implemented.");
        }
        if (this.view != null) {
            throw new IllegalStateException("View already added");
        }
        this.isNewInstance = isNewInstance;
        this.view = view;
    }


    /**
     * Called during the onStart() of the attached fragment.
     */
    protected void onViewAttached() {
    }


    /**
     * Called during the onStop() of the attached fragment.
     */
    protected void onViewDetached() {
    }


    /**
     * Called during the onDestroy() of the attached fragment. Here you can release resource.
     */
    protected void onViewDestroyed() {

    }


    /**
     * Drops view. The method is called in the {@link BasePresenterFragment#onPause()}.
     */
    protected void dropView() {
        this.view = null;
    }

}