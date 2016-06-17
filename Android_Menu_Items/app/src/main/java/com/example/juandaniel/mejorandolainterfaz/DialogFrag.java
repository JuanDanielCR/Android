package com.example.juandaniel.mejorandolainterfaz;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.os.Bundle;

/**
 * Created by Juan Daniel on 01/05/2015.
 */

public class DialogFrag extends DialogFragment {
        public interface DialogListener{
            public void onDialogPositiveClick(DialogFragment dialog);
            public void onDialogNegativeClick(DialogFragment dialog);
        }
    DialogListener listener;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try{
           listener=(DialogListener)activity;
        }catch(ClassCastException e){ }
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder= new AlertDialog.Builder(getActivity());
        builder.setTitle("Titulo");
        return builder.create();
    }
}
