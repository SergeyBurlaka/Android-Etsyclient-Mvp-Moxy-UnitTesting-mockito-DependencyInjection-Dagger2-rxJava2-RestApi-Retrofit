package com.segeyburlaka.test.jellyworkz.mydaggermvprxtest.ui.base;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;

import com.segeyburlaka.test.jellyworkz.mydaggermvprxtest.R;


public class ProgressDialog extends DialogFragment {

    public static ProgressDialog newInstance() {
        Bundle args = new Bundle();
        ProgressDialog fragment = new ProgressDialog();
        fragment.setCancelable(false);
        fragment.setArguments(args);
        return fragment;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.dialog_progress, null);
        AlertDialog dialog = new AlertDialog.Builder(getActivity())
                .setView(view)
                .create();
        Window window = dialog.getWindow();
        if (window != null) {
            window.setBackgroundDrawableResource(android.R.color.transparent);
        }
        return dialog;
    }
}
