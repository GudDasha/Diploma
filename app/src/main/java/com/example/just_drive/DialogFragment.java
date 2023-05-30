package com.example.just_drive;

import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.NonNull;

public class DialogFragment extends androidx.fragment.app.DialogFragment {
    @NonNull
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        String help = getArguments().getString("help");
        AlertDialog.Builder builder=new AlertDialog.Builder(getActivity());
        return builder
                .setTitle("Подсказка")
                .setMessage(help)
                .setPositiveButton("Продолжить", null)
                .create();
    }
}
