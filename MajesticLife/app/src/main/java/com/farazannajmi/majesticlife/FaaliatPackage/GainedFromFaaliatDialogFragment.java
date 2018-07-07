package com.farazannajmi.majesticlife.FaaliatPackage;

import android.animation.ObjectAnimator;
import android.app.Dialog;
import android.app.DialogFragment;
import android.arch.lifecycle.LifecycleOwner;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
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
import android.util.TypedValue;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.DecelerateInterpolator;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.NumberPicker;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
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
    private static ArrayList<Skill> thisFaaliatSkills = new ArrayList<>();
    private static ArrayList<Integer> preSkillProgresses = new ArrayList<>();
    private static boolean gotAllFaaliatSkills;
    private static ArrayList<ProgressBar> skillsProgressBars = new ArrayList<>();
    private static ArrayList<Integer> preSkillsLevels = new ArrayList<>();
    private static ArrayList<TextView> skillsLevelTv = new ArrayList<>();

    private TextView Coins_txt;
    private TextView XpLevel_txt;
    private TextView HpLevel_txt;
    private TextView SpLevel_txt;
    private ProgressBar XpLevel_progBar;
    private ProgressBar HpLevel_progBar;
    private ProgressBar SpLevel_progBar;
    private LinearLayout parentLayout;

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
        gotAllFaaliatSkills = false;

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        LayoutInflater inflater = getActivity().getLayoutInflater();

        final View view = inflater.inflate(R.layout.dialog_gainedfromfaaliat, null);

        //getting UI elements:
        Coins_txt = view.findViewById(R.id.gainedD_coins_txt);
        XpLevel_txt = view.findViewById(R.id.gainedD_XpLevel_txt);
        HpLevel_txt = view.findViewById(R.id.gainedD_HpLevel_txt);
        SpLevel_txt = view.findViewById(R.id.gainedD_SpLevel_txt);
        XpLevel_progBar = view.findViewById(R.id.gainedD_Xp_progBar);
        HpLevel_progBar = view.findViewById(R.id.gainedD_Hp_progBar);
        SpLevel_progBar = view.findViewById(R.id.gainedD_Sp_progBar);
        XpLevel_progBar.setMax(100);
        HpLevel_progBar.setMax(100);
        SpLevel_progBar.setMax(100);
        parentLayout = view.findViewById(R.id.gainedD_parent);

        //setting UI elements values:
        Coins_txt.setText("+0");
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
                if(!gotAllFaaliatSkills)
                {
                    ArrayList<FaaliatSkill> faaliatSkills = (ArrayList) skills;

                    for (int i = 0; i < faaliatSkills.size(); i++)
                    {
                        Skill skill = DataHolder.Skills.get(faaliatSkills.get(i).getSkill_ID());
                        preSkillProgresses.add(skill.getProgress());
                        preSkillsLevels.add(skill.getLevel());

                        int newProgress = skill.getProgress() + (faaliatSkills.get(i).getRepetitionCount() * repeatTime);
                        if(newProgress >= 100) //skill level upped
                        {
                            skill.setLevel(skill.getLevel() + 1);
                            newProgress = newProgress - 100;

                            //adding coin reward:
                            DataHolder.ThisUser.setCoins(DataHolder.ThisUser.getCoins() + DataHolder.LevelUpReward);
                            //saving user
                            UserViewModel userViewModel = ViewModelProviders.of(FaaliatsActivity.appCompatActivity).get(UserViewModel.class);
                            userViewModel.update(DataHolder.ThisUser);
                        }
                        else if((newProgress < 0) && (skill.getLevel() == 1))
                        {
                            newProgress = 0;
                        }
                        skill.setProgress(newProgress);

                        thisFaaliatSkills.add(skill);

                        SkillViewModel skillViewModel = ViewModelProviders.of(FaaliatsActivity.appCompatActivity).get(SkillViewModel.class);
                        skillViewModel.update(skill);


                        //region ---------- adding skills data to UI ----------
                        int fifty = getActivity().getResources().getDimensionPixelSize(R.dimen.fivezero);
                        int oneFifty = getActivity().getResources().getDimensionPixelSize(R.dimen.onefivezero);

                        LinearLayout row = new LinearLayout(view.getContext());
                        row.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
                        row.setOrientation(LinearLayout.VERTICAL);
                        row.setGravity(Gravity.RIGHT);
                        row.setGravity(Gravity.CENTER_HORIZONTAL);
                        row.setGravity(Gravity.CENTER_VERTICAL);

                        TextView skillName_tv = new TextView(view.getContext());
                        LinearLayout.LayoutParams tv_params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                        tv_params.setMargins(2, 0, 10, 0);
                        skillName_tv.setLayoutParams(tv_params);
                        skillName_tv.setText(skill.getSkill_Name());
                        //skillName_tv.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
                        skillName_tv.setTextSize(TypedValue.COMPLEX_UNIT_SP, 15);
                        row.addView(skillName_tv);

                        LinearLayout innerHorizontalLayout = new LinearLayout(view.getContext());
                        innerHorizontalLayout.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
                        innerHorizontalLayout.setOrientation(LinearLayout.HORIZONTAL);
                        innerHorizontalLayout.setGravity(Gravity.RIGHT);
                        //innerHorizontalLayout.setGravity(Gravity.CENTER_HORIZONTAL);
                        innerHorizontalLayout.setGravity(Gravity.CENTER_VERTICAL);

                        RelativeLayout relativeLayout = new RelativeLayout(view.getContext());
                        relativeLayout.setLayoutParams(new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT));

                            ImageView imageView = new ImageView(view.getContext());
                            RelativeLayout.LayoutParams image_params = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
                            image_params.addRule(RelativeLayout.CENTER_IN_PARENT, RelativeLayout.TRUE);
                            imageView.setLayoutParams(image_params);
                            imageView.setImageResource(R.drawable.ic_circle);
                            relativeLayout.addView(imageView);

                            TextView skillLevel_tv = new TextView(view.getContext());
                            RelativeLayout.LayoutParams level_params = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
                            level_params.addRule(RelativeLayout.CENTER_IN_PARENT, RelativeLayout.TRUE);
                            skillLevel_tv.setLayoutParams(level_params);
                            skillLevel_tv.setText(Integer.toString(skill.getLevel()));
                            skillLevel_tv.setTextColor(Color.BLACK);
                            skillLevel_tv.setTextSize(TypedValue.COMPLEX_UNIT_SP, 15);
                            skillsLevelTv.add(skillLevel_tv);
                            relativeLayout.addView(skillLevel_tv);

                        innerHorizontalLayout.addView(relativeLayout);

                        ProgressBar progressBar = new ProgressBar(view.getContext(), null, android.R.attr.progressBarStyleHorizontal);
                        LinearLayout.LayoutParams prog_params = new LinearLayout.LayoutParams(oneFifty, LinearLayout.LayoutParams.WRAP_CONTENT);
                        prog_params.setMargins(10,0,0,0);
                        progressBar.setLayoutParams(prog_params);
                        progressBar.setMax(100);
                        progressBar.setProgress(preSkillProgresses.get(i));
                        skillsProgressBars.add(progressBar);
                        innerHorizontalLayout.addView(progressBar);

                        row.addView(innerHorizontalLayout);
                        parentLayout.addView(row);
                        //endregion ------------------------------
                    }
                    gotAllFaaliatSkills = true;
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

                //adding skill progress:
                for(int skillCounter = 0; skillCounter < thisFaaliatSkills.size(); skillCounter++)
                {
                    Skill s = thisFaaliatSkills.get(skillCounter);
                    if(s.getLevel() > preSkillsLevels.get(skillCounter)) //if skill level upped
                    {
                        skillsLevelTv.get(skillCounter).setText(Integer.toString(s.getLevel()));
                        skillsLevelTv.get(skillCounter).setTextColor(ContextCompat.getColor(FaaliatsActivity.context, R.color.colorAccent));

                        Coins_txt.setText("+" + DataHolder.LevelUpReward);
                    }
                    skillsProgressBars.get(skillCounter).setProgress(s.getProgress());
                    ObjectAnimator animationskill = ObjectAnimator.ofInt(skillsProgressBars.get(skillCounter), "progress", preSkillProgresses.get(skillCounter), s.getProgress());
                    animationskill.setDuration(1000); // in milliseconds
                    animationskill.setInterpolator(new DecelerateInterpolator());
                    animationskill.start();
                }
            }
        }, 1000);

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
