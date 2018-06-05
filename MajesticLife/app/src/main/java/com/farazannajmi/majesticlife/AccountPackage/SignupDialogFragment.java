package com.farazannajmi.majesticlife.AccountPackage;

import android.app.Activity;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.widget.EditText;

import com.farazannajmi.majesticlife.R;

/**
 * Created by Narges on 5/23/2018.
 * refrence: https://developer.android.com/guide/topics/ui/dialogs
 */

public class SignupDialogFragment extends DialogFragment
{
    private EditText Username_editText;
    private EditText Email_editText;
    private EditText Password_editText;

    /* The activity that creates an instance of this dialog fragment must
     * implement this interface in order to receive event callbacks.
     * Each method passes the DialogFragment in case the host needs to query it. */
    public interface SignupDialogListener
    {
        public void onDialogPositiveClick(DialogFragment dialog, String username, String email, String password);
        //public void onDialogNegativeClick(DialogFragment dialog);
    }

    // Use this instance of the interface to deliver action events
    SignupDialogListener mListener;

    // Override the Fragment.onAttach() method to instantiate the NoticeDialogListener
    @Override
    public void onAttach(Activity activity)
    {
        super.onAttach(activity);

        // Verify that the host activity implements the callback interface
        try
        {
            // Instantiate the NoticeDialogListener so we can send events to the host
            mListener = (SignupDialogListener) activity;
        }
        catch (ClassCastException e) {
            // The activity doesn't implement the interface, throw exception
            throw new ClassCastException(activity.toString()
                    + " must implement SignupDialogListener");
        }
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState)
    {
        Username_editText = new EditText(getActivity());

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        // Get the layout inflater
        LayoutInflater inflater = getActivity().getLayoutInflater();

        // Inflate and set the layout for the dialog
        // Pass null as the parent view because its going in the dialog layout
        builder.setView(inflater.inflate(R.layout.dialog_signup, null));
        // Add action buttons
        builder.setPositiveButton(R.string.sign_up, new DialogInterface.OnClickListener()
                {
                    @Override
                    public void onClick(DialogInterface dialog, int id)
                    {
                        Username_editText = getDialog().findViewById(R.id.SignupD_username_editText);
                        Email_editText = getDialog().findViewById(R.id.SignupD_email_editText);
                        Password_editText = getDialog().findViewById(R.id.SignupD_password_editText);

                        // Send the positive button event back to the host activity
                        mListener.onDialogPositiveClick(SignupDialogFragment.this,
                                Username_editText.getText().toString(),
                                Email_editText.getText().toString(),
                                Password_editText.getText().toString());
                    }
                });
        builder.setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener()
                {
                    public void onClick(DialogInterface dialog, int id)
                    {
                        SignupDialogFragment.this.getDialog().cancel();

//                        // Send the negative button event back to the host activity
//                        mListener.onDialogNegativeClick(SignupDialogFragment.this);
                    }
                });
        builder.setTitle("Please enter your info for sign up.");

        // Create the AlertDialog object and return it
        return builder.create();
    }
}
