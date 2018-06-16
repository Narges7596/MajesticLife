package com.farazannajmi.majesticlife.FaaliatPackage;

import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.NumberPicker;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.farazannajmi.majesticlife.DataHolder;
import com.farazannajmi.majesticlife.DataStructures.Faaliat;
import com.farazannajmi.majesticlife.LoadingActivity;
import com.farazannajmi.majesticlife.MainMenuActivity;
import com.farazannajmi.majesticlife.R;

/**
 * Created by Narges on 6/16/2018.
 */

public class GainedFromFaaliatDialogFragment extends DialogFragment
{
    private static Faaliat ThisFaaliat;

    private TextView XpLevel_txt;
    private TextView HpLevel_txt;
    private TextView SpLevel_txt;
    private ProgressBar XpLevel_progBar;
    private ProgressBar HpLevel_progBar;
    private ProgressBar SpLevel_progBar;

    public static GainedFromFaaliatDialogFragment newInstance(int faaliatIndex, int repeatTime)
    {
        GainedFromFaaliatDialogFragment f = new GainedFromFaaliatDialogFragment();

        // Supply num input as an argument.
        Bundle args = new Bundle();
        args.putInt("faaliatIndex", faaliatIndex);
        args.putInt("repeatTime", repeatTime);
        f.setArguments(args);

        return f;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState)
    {
        final int faaliatIndex = getArguments().getInt("faaliatIndex");
        final int repeatTime = getArguments().getInt("repeatTime");
        ThisFaaliat = DataHolder.Faaliats.get(faaliatIndex);

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        LayoutInflater inflater = getActivity().getLayoutInflater();

        View view = inflater.inflate(R.layout.dialog_gainedfromfaaliat, null);

        //getting UI elements:
        XpLevel_txt = view.findViewById(R.id.gainedD_XpLevel_txt);
        HpLevel_txt = view.findViewById(R.id.gainedD_HpLevel_txt);
        SpLevel_txt = view.findViewById(R.id.gainedD_SpLevel_txt);
        XpLevel_progBar = view.findViewById(R.id.gainedD_Xp_progBar);
        HpLevel_progBar = view.findViewById(R.id.gainedD_Hp_progBar);
        SpLevel_progBar = view.findViewById(R.id.gainedD_Sp_progBar);

//        //setting UI elements values:
//        XpLevel_txt.setText(Integer.toString(DataHolder.ThisUser.getXpLevel()));
//        HpLevel_txt.setText(Integer.toString(DataHolder.ThisUser.getHpLevel()));
//        SpLevel_txt.setText(Integer.toString(DataHolder.ThisUser.getSpLevel()));
//        XpLevel_progBar.setProgress(DataHolder.ThisUser.getXP());
//        HpLevel_progBar.setProgress(DataHolder.ThisUser.getHP());
//        SpLevel_progBar.setProgress(DataHolder.ThisUser.getSP());

//        final Handler handler = new Handler();
//        handler.postDelayed(new Runnable() {
//            @Override
//            public void run()
//            {
//                //region ---------- adding xp,hp,sp ----------
//                //----XP----
//                int xp = repeatTime * DataHolder.ThisUser.getXP();
//                xp += ThisFaaliat.getXpCount();
//                if(xp >= 100)
//                {
//                    DataHolder.ThisUser.setXpLevel(DataHolder.ThisUser.getXpLevel() + 1);
//                    XpLevel_txt.setText(Integer.toString(DataHolder.ThisUser.getXpLevel()));
//                    xp = xp - 100;
//                }
//                else if(xp < 0)
//                {
//                    DataHolder.ThisUser.setXpLevel(DataHolder.ThisUser.getXpLevel() - 1);
//                    XpLevel_txt.setText(Integer.toString(DataHolder.ThisUser.getXpLevel()));
//                }
//                DataHolder.ThisUser.setXP(xp);
//                XpLevel_progBar.setProgress(xp);
//
//                //----HP----
//                int hp = repeatTime * DataHolder.ThisUser.getHP();
//                hp += ThisFaaliat.getHpCount();
//                if(hp >= 100)
//                {
//                    DataHolder.ThisUser.setHpLevel(DataHolder.ThisUser.getHpLevel() + 1);
//                    HpLevel_txt.setText(Integer.toString(DataHolder.ThisUser.getHpLevel()));
//                    hp = hp - 100;
//                }
//                else if(hp < 0)
//                {
//                    DataHolder.ThisUser.setHpLevel(DataHolder.ThisUser.getHpLevel() - 1);
//                    HpLevel_txt.setText(Integer.toString(DataHolder.ThisUser.getHpLevel()));
//                }
//                DataHolder.ThisUser.setHP(hp);
//                HpLevel_progBar.setProgress(hp);
//
//                //----SP----
//                int sp = repeatTime * DataHolder.ThisUser.getSP();
//                sp += ThisFaaliat.getSpCount();
//                if(sp >= 100)
//                {
//                    DataHolder.ThisUser.setSpLevel(DataHolder.ThisUser.getSpLevel() + 1);
//                    SpLevel_txt.setText(Integer.toString(DataHolder.ThisUser.getSpLevel()));
//                    sp = sp - 100;
//                }
//                else if(sp < 0)
//                {
//                    DataHolder.ThisUser.setSpLevel(DataHolder.ThisUser.getSpLevel() - 1);
//                    SpLevel_txt.setText(Integer.toString(DataHolder.ThisUser.getSpLevel()));
//                }
//                DataHolder.ThisUser.setSP(sp);
//                SpLevel_progBar.setProgress(sp);
//                //endregion ------------------------------
//            }
//        }, 500);

        // Add action buttons
        builder.setPositiveButton(R.string.ok, new DialogInterface.OnClickListener()
        {
            @Override
            public void onClick(DialogInterface dialog, int id)
            {
                //todo save in database
                getDialog().dismiss();
            }
        });

        builder.setView(view);
        // Create the AlertDialog object and return it
        return builder.create();
    }
}
