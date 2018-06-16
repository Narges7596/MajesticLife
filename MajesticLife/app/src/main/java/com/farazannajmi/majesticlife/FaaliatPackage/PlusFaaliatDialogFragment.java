package com.farazannajmi.majesticlife.FaaliatPackage;

import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.NumberPicker;

import com.farazannajmi.majesticlife.R;

/**
 * Created by Narges on 6/4/2018.
 */

public class PlusFaaliatDialogFragment extends DialogFragment
{
    private int faaliatIndex;
    private NumberPicker times_numberPicker;

    public static PlusFaaliatDialogFragment newInstance(int faaliatIndex)
    {
        PlusFaaliatDialogFragment f = new PlusFaaliatDialogFragment();

        // Supply num input as an argument.
        Bundle args = new Bundle();
        args.putInt("faaliatIndex", faaliatIndex);
        f.setArguments(args);

        return f;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState)
    {
        faaliatIndex = getArguments().getInt("faaliatIndex");

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        LayoutInflater inflater = getActivity().getLayoutInflater();

        View view = inflater.inflate(R.layout.dialog_plusefaaliat, null);

        times_numberPicker = view.findViewById(R.id.pluseFaaliatD__times_numberPicker);
        times_numberPicker.setMaxValue(4);
        times_numberPicker.setMinValue(1);

        // Add action buttons
        builder.setPositiveButton(R.string.ok, new DialogInterface.OnClickListener()
        {
            @Override
            public void onClick(DialogInterface dialog, int id)
            {
                //times_numberPicker = getDialog().findViewById(R.id.pluseFaaliatD__times_numberPicker);
                getDialog().dismiss();
                FaaliatsActivity.ShowGainedPopup(faaliatIndex, times_numberPicker.getValue());
            }
        });
        builder.setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener()
        {
            public void onClick(DialogInterface dialog, int id)
            {
                getDialog().cancel();
            }
        });

        builder.setTitle("How many hours were you doing this activity?");

        builder.setView(view);
        // Create the AlertDialog object and return it
        return builder.create();
    }
}