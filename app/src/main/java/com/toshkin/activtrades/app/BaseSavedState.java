package com.toshkin.activtrades.app;

import android.os.Parcel;
import android.os.Parcelable;

public abstract class BaseSavedState implements Parcelable {
    public static final BaseSavedState EMPTY_STATE = new BaseSavedState() {};

    private final Parcelable mSuperState;

    /**
     * Constructor used to make the EMPTY_STATE singleton
     */
    private BaseSavedState() {
        mSuperState = null;
    }

    /**
     * Constructor called by derived classes when creating their SavedState objects
     *
     * @param superState The state of the superclass of this view
     */
    protected BaseSavedState(Parcelable superState) {
        if (superState == null) {
            throw new IllegalArgumentException("superState must not be null");
        }
        mSuperState = superState != EMPTY_STATE ? superState : null;
    }

    /**
     * Constructor used when reading from a parcel. Reads the state of the superclass.
     *
     * @param source
     */
    protected BaseSavedState(Parcel source) {
        // FIXME need class loader
        Parcelable superState = source.readParcelable(null);

        mSuperState = superState != null ? superState : EMPTY_STATE;
    }

    final public Parcelable getSuperState() {
        return mSuperState;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeParcelable(mSuperState, flags);
    }

}