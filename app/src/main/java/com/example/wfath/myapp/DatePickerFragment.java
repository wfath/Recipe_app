package com.example.wfath.myapp;

import android.app.Dialog;

import android.view.View;

import android.os.Bundle;

import androidx.fragment.app.DialogFragment;
import android.app.AlertDialog;

public class DatePickerFragment extends DialogFragment {
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState){
        View v = getActivity().getLayoutInflater().inflate(R.layout.dialog_date, null);

        return new AlertDialog.Builder(getActivity())
                .setView(v)
                .setTitle(R.string.date_picker_title)
                .setPositiveButton(android.R.string.ok, null)
                .create();
    }
}
