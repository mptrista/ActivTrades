package com.toshkin.activtrades.app;

import android.os.Parcelable;
import android.support.annotation.NonNull;

public interface InstanceStateRestorable {
    @NonNull
    Parcelable onSaveInstanceState();

    void onRestoreInstanceState(@NonNull Parcelable state);
}
