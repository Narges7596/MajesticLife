package com.farazannajmi.majesticlife;

import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;

import com.farazannajmi.majesticlife.FaaliatPackage.EditFaaliatActivity;
import com.farazannajmi.majesticlife.SkillPackage.EditSkillActivity;

import java.util.ArrayList;

/**
 * Created by Narges on 6/4/2018.
 */

public class AvatarSelectDialogFragment extends DialogFragment
{
    private GridView gridView;
    private ArrayList<Integer> imageList;
    private boolean isSkill;

    public static AvatarSelectDialogFragment newInstance(ArrayList<Integer> imageList, boolean isSkill)
    {
        AvatarSelectDialogFragment f = new AvatarSelectDialogFragment();

        // Supply num input as an argument.
        Bundle args = new Bundle();
        args.putIntegerArrayList("imageList", imageList);
        args.putBoolean("isSkill", isSkill);
        f.setArguments(args);

        return f;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState)
    {
        this.imageList = getArguments().getIntegerArrayList("imageList");
        this.isSkill = getArguments().getBoolean("isSkill");

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        // Get the layout inflater
        LayoutInflater inflater = getActivity().getLayoutInflater();
        // Inflate and set the layout for the dialog
        // Pass null as the parent view because its going in the dialog layout
        //builder.setView(inflater.inflate(R.layout.dialog_avatarlist,null));

        View view = getActivity().getLayoutInflater().inflate(R.layout.dialog_avatarlist, null);

        // Add action buttons
        builder.setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener()
        {
            public void onClick(DialogInterface dialog, int id)
            {
                AvatarSelectDialogFragment.this.getDialog().cancel();
            }
        });


        builder.setTitle("Select a new avatar");

        gridView = (GridView) view.findViewById(R.id.AvatarlistD_images_gridView);
        gridView.setNumColumns(3);
        gridView.setAdapter(new ImageAdapter(getActivity(), imageList));
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id)
            {
                if(isSkill)
                    EditSkillActivity.ChangeAvatar(DataHolder.SkillAvatars.get(position));
                else
                    EditFaaliatActivity.ChangeAvatar(DataHolder.FaaliatAvatars.get(position));

                AvatarSelectDialogFragment.this.getDialog().dismiss();
            }
        });

        builder.setView(view);

        // Create the AlertDialog object and return it
        return builder.create();
    }
}
