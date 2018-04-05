package com.segeyburlaka.test.jellyworkz.mydaggermvprxtest.ui.base;


import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;


public class SimpleFragment extends DialogFragment {

    private Dialog mDialog;

    public SimpleFragment() {
        super();
        mDialog = null;
    }

    public SimpleFragment setDialog(Dialog dialog) {
        mDialog = dialog;
        return this;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        return mDialog;
    }
}
