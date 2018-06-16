package com.farazannajmi.majesticlife.FaaliatPackage;

import android.animation.ObjectAnimator;
import android.app.Dialog;
import android.app.DialogFragment;
import android.arch.lifecycle.LifecycleOwner;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.DecelerateInterpolator;
import android.widget.NumberPicker;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.farazannajmi.majesticlife.DataHolder;
import com.farazannajmi.majesticlife.DataStructures.Faaliat;
import com.farazannajmi.majesticlife.DataStructures.FaaliatSkill;
import com.farazannajmi.majesticlife.DataStructures.FaaliatSkillViewModel;
import com.farazannajmi.majesticlife.DataStructures.Skill;
import com.farazannajmi.majesticlife.DataStructures.SkillViewModel;
import com.farazannajmi.majesticlife.DataStructures.UserViewModel;
import com.farazannajmi.majesticlife.LoadingActivity;
import com.farazannajmi.majesticlife.MainMenuActivity;
import com.farazannajmi.majesticlife.R;

import java.util.ArrayList;
import java.util.List;

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
        XpLevel_progBar.setMax(100);
        HpLevel_progBar.setMax(100);
        SpLevel_progBar.setMax(100);

        //setting UI elements values:
        XpLevel_txt.setText(Integer.toString(DataHolder.ThisUser.getXpLevel()));
        HpLevel_txt.setText(Integer.toString(DataHolder.ThisUser.getHpLevel()));
        SpLevel_txt.setText(Integer.toString(DataHolder.ThisUser.getSpLevel()));
        XpLevel_progBar.setProgress(DataHolder.ThisUser.getXP());
        HpLevel_progBar.setProgress(DataHolder.ThisUser.getHP());
        SpLevel_progBar.setProgress(DataHolder.ThisUser.getSP());


        //getting FaaliatSkills and setting it in UI:
        FaaliatSkillViewModel faaliatSkillViewModel = ViewModelProviders.of(FaaliatsActivity.appCompatActivity).get(FaaliatSkillViewModel.class);
        faaliatSkillViewModel.getSkillsForFaaliat(ThisFaaliat).observe((LifecycleOwner) FaaliatsActivity.context, new Observer<List<FaaliatSkill>>() {
            @Override
            public void onChanged(@Nullable List<FaaliatSkill> skills) {
                ArrayList<FaaliatSkill> faaliatSkills = (ArrayList) skills;

                for (int i = 0; i < faaliatSkills.size(); i++)
                {
                    Skill skill = DataHolder.Skills.get(faaliatSkills.get(i).getSkill_ID());
                    int a = faaliatSkills.get(i).getRepetitionCount();
                    skill.setProgress(skill.getProgress() + (faaliatSkills.get(i).getRepetitionCount() * repeatTime));

                    SkillViewModel skillViewModel = ViewModelProviders.of(FaaliatsActivity.appCompatActivity).get(SkillViewModel.class);
                    skillViewModel.update(skill);
                }
            }
        });


        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run()
            {
                //region ---------- adding xp,hp,sp ----------
                //region----XP----
                int preXp = DataHolder.ThisUser.getXP();
                int xp = DataHolder.ThisUser.getXP();
                xp += repeatTime * ThisFaaliat.getXpCount();
                if(xp >= 100)
                {
                    DataHolder.ThisUser.setXpLevel(DataHolder.ThisUser.getXpLevel() + 1);
                    XpLevel_txt.setText(Integer.toString(DataHolder.ThisUser.getXpLevel()));
                    xp = xp - 100;
                    XpLevel_txt.setTextColor(ContextCompat.getColor(FaaliatsActivity.context, R.color.colorAccent));
                }
                else if((xp < 0) && (DataHolder.ThisUser.getXpLevel() == 1))
                {
                    xp = 0;
                }
                else if(xp < 0)
                {
                    DataHolder.ThisUser.setXpLevel(DataHolder.ThisUser.getXpLevel() - 1);
                    XpLevel_txt.setText(Integer.toString(DataHolder.ThisUser.getXpLevel()));
                }
                DataHolder.ThisUser.setXP(xp);
                XpLevel_progBar.setProgress(xp);
                ObjectAnimator animationXP = ObjectAnimator.ofInt(XpLevel_progBar, "progress", preXp, xp);
                animationXP.setDuration(1000); // in milliseconds
                animationXP.setInterpolator(new DecelerateInterpolator());
                animationXP.start();
                //endregion

                //region----HP----
                int preHp = DataHolder.ThisUser.getHP();
                int hp = DataHolder.ThisUser.getHP();
                hp += repeatTime * ThisFaaliat.getHpCount();
                if(hp >= 100)
                {
                    DataHolder.ThisUser.setHpLevel(DataHolder.ThisUser.getHpLevel() + 1);
                    HpLevel_txt.setText(Integer.toString(DataHolder.ThisUser.getHpLevel()));
                    hp = hp - 100;
                    HpLevel_txt.setTextColor(ContextCompat.getColor(FaaliatsActivity.context, R.color.colorAccent));
                }
                else if((hp < 0) && (DataHolder.ThisUser.getHpLevel() == 1))
                {
                    hp = 0;
                }
                else if(hp < 0)
                {
                    DataHolder.ThisUser.setHpLevel(DataHolder.ThisUser.getHpLevel() - 1);
                    HpLevel_txt.setText(Integer.toString(DataHolder.ThisUser.getHpLevel()));
                }
                DataHolder.ThisUser.setHP(hp);
                HpLevel_progBar.setProgress(hp);
                ObjectAnimator animationHp = ObjectAnimator.ofInt(HpLevel_progBar, "progress", preHp, hp);
                animationHp.setDuration(1000); // in milliseconds
                animationHp.setInterpolator(new DecelerateInterpolator());
                animationHp.start();
                //endregion

                //region----SP----
                int preSp = DataHolder.ThisUser.getSP();
                int sp = DataHolder.ThisUser.getSP();
                sp += repeatTime * ThisFaaliat.getSpCount();
                if(sp >= 100)
                {
                    DataHolder.ThisUser.setSpLevel(DataHolder.ThisUser.getSpLevel() + 1);
                    SpLevel_txt.setText(Integer.toString(DataHolder.ThisUser.getSpLevel()));
                    sp = sp - 100;
                    SpLevel_txt.setTextColor(ContextCompat.getColor(FaaliatsActivity.context, R.color.colorAccent));
                }
                else if((sp < 0) && (DataHolder.ThisUser.getSpLevel() == 1))
                {
                    sp = 0;
                }
                else if(sp < 0)
                {
                    DataHolder.ThisUser.setSpLevel(DataHolder.ThisUser.getSpLevel() - 1);
                    SpLevel_txt.setText(Integer.toString(DataHolder.ThisUser.getSpLevel()));
                }
                DataHolder.ThisUser.setSP(sp);
                SpLevel_progBar.setProgress(sp);
                ObjectAnimator animationSp = ObjectAnimator.ofInt(SpLevel_progBar, "progress", preSp, sp);
                animationSp.setDuration(1000); // in milliseconds
                animationSp.setInterpolator(new DecelerateInterpolator());
                animationSp.start();
                //endregion
                //endregion ------------------------------

                //todo showing skill's progress!!

            }
        }, 500);

        // Add action buttons
        builder.setPositiveButton(R.string.ok, new DialogInterface.OnClickListener()
        {
            @Override
            public void onClick(DialogInterface dialog, int id)
            {
                getDialog().dismiss();
            }
        });

        builder.setView(view);
        // Create the AlertDialog object and return it
        return builder.create();
    }

    @Override
    public void onDismiss(DialogInterface dialog)
    {
        super.onDismiss(dialog);

        //saving in database:
        UserViewModel userViewModel = ViewModelProviders.of(FaaliatsActivity.appCompatActivity).get(UserViewModel.class);
        userViewModel.update(DataHolder.ThisUser);
    }
}
