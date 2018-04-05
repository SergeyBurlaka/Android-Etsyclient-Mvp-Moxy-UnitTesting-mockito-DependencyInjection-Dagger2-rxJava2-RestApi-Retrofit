package com.segeyburlaka.test.jellyworkz.mydaggermvprxtest.models.interfaces;


import android.os.Parcelable;

public interface Model<T> extends Parcelable {
    T getId();

    void setId(T id);
}
