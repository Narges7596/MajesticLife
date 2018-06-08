package com.farazannajmi.majesticlife;

import android.app.Activity;
import android.database.sqlite.SQLiteDatabase;

import com.backtory.java.internal.BacktoryUser;
import com.farazannajmi.majesticlife.DataStructures.Faaliat;
import com.farazannajmi.majesticlife.DataStructures.Skill;
import com.farazannajmi.majesticlife.DataStructures.User;

import java.util.ArrayList;

/**
 * Created by Narges on 5/30/2018.
 */

public class DataHolder
{
    public static com.farazannajmi.majesticlife.DataStructures.User User;
    public static BacktoryUser CurrentBacktoryUser;
    public static ArrayList<Faaliat> Faaliats;
    public static ArrayList<Skill> Skills;

    public static Faaliat[][] WeeklyPlan;

    public static ArrayList<Integer> FaaliatAvatars;
    public static ArrayList<Integer> SkillAvatars;

    private static SQLiteDatabase database;

    public static void InitialData()
    {
        //todo: remove tests and initial all user xp & ... with 0
        Faaliats = new ArrayList<Faaliat>();
        Skills = new ArrayList<Skill>();
        User = new User(0, "UserName", "hello@mail.com",
                R.drawable.ic_king, 20, 3, 2, 1, 70, 10);

        WeeklyPlan = new Faaliat[17][7];
        for(int dayCounter = 0; dayCounter < 7; dayCounter++)
        {
            for(int hourCounter = 0; hourCounter < 17; hourCounter++)
            {
                WeeklyPlan[hourCounter][dayCounter] =
                        new Faaliat(-1,"empty", 0,0,
                                0,0,0,DataHolder.User.getUser_ID());
            }
        }

        FaaliatAvatars = new ArrayList<Integer>();
        FaaliatAvatars.add(R.drawable.ic_majestic_activities);
        FaaliatAvatars.add(R.drawable.ic_circle);
        FaaliatAvatars.add(R.drawable.ic_coin);
        FaaliatAvatars.add(R.drawable.ic_queen);
        FaaliatAvatars.add(R.drawable.ic_king);
        FaaliatAvatars.add(R.drawable.ic_quests);
        for (int i = 1; i < 10; i++)
        {
            FaaliatAvatars.add(R.drawable.ic_majestic_activities);
        }

        SkillAvatars = new ArrayList<Integer>();
        SkillAvatars.add(R.drawable.ic_skills);
        SkillAvatars.add(R.drawable.ic_circle);
        SkillAvatars.add(R.drawable.ic_coin);
        SkillAvatars.add(R.drawable.ic_queen);
        SkillAvatars.add(R.drawable.ic_king);
        SkillAvatars.add(R.drawable.ic_quests);
        for (int i = 1; i < 10; i++)
        {
            FaaliatAvatars.add(R.drawable.ic_skills);
        }

        //region ----------------------------test------------------------------
        Skill s1 = new Skill("Knowledge", SkillAvatars.get(0), 5, 50, DataHolder.User.getUser_ID());
        DataHolder.Skills.add(s1);
        Skill s2 = new Skill("HandCrafting", SkillAvatars.get(1), 10, 25, DataHolder.User.getUser_ID());
        DataHolder.Skills.add(s2);
        Skill s3 = new Skill("Elm", SkillAvatars.get(2), 5, 50, DataHolder.User.getUser_ID());
        DataHolder.Skills.add(s3);
        Skill s4 = new Skill("Olom", SkillAvatars.get(3), 2, 25, DataHolder.User.getUser_ID());
        DataHolder.Skills.add(s4);


        Faaliat f0 = new Faaliat(0, "Embroidery", FaaliatAvatars.get(0),
                R.color.faaliatsColor1, -10, 5, 2, DataHolder.User.getUser_ID());
//        ArrayList<FaaliatSkill> st = new ArrayList<FaaliatSkill>();
//        st.add(new FaaliatSkill(DataHolder.Skills.get(0), 10, 0));
//        st.add(new FaaliatSkill(DataHolder.Skills.get(1), 20, 1));
//        f0.SkillTimes = st;
        DataHolder.Faaliats.add(f0);

        Faaliat f1 = new Faaliat(1, "Alaki", FaaliatAvatars.get(0),
                R.color.faaliatsColor3, -1, 2, 3,  DataHolder.User.getUser_ID());
//        ArrayList<FaaliatSkill> st2 = new ArrayList<FaaliatSkill>();
//        st2.add(new FaaliatSkill(DataHolder.Skills.get(2), 2, 2));
//        st2.add(new FaaliatSkill(DataHolder.Skills.get(3), 3, 3));
//        f1.SkillTimes = st2;
        DataHolder.Faaliats.add(f1);
        //endregion
    }

    public static void LoadData()
    {
        //todo
        InitialData();
    }

    /* --------------using database--------------
    UserDao userDao = RepoDatabase
        .getInstance(context)
        .getUserDao();

userDao.insert(new User(1,
        "Jake Wharton",
        "https://avatars0.githubusercontent.com/u/66577"));

     */


    public static void SaveData (Activity activity)
    {
        //todo

        //------------a variable:
        //public static final int NEW_WORD_ACTIVITY_REQUEST_CODE = 1;
        //private PlanCellViewModel mPlanCellViewModel;
        //------------in onCreat:
        //mPlanCellViewModel = ViewModelProviders.of(this).get(PlanCellViewModel.class);
        //
        //mPlanCellViewModel.getAllWords().observe(this, new Observer<List<PlanCell>>() {
        //    @Override
        //    public void onChanged(@Nullable final List<PlanCell> words) {
        //        // Update the cached copy of the words in the adapter.
        //        adapter.setWords(words);
        //    }
        //});

        //-------------in onActivityResult:
//        super.onActivityResult(requestCode, resultCode, data);
//
//        if (requestCode == NEW_WORD_ACTIVITY_REQUEST_CODE && resultCode == RESULT_OK) {
//            Word word = new Word(data.getStringExtra(NewWordActivity.EXTRA_REPLY));
//            mWordViewModel.insert(word);
//        } else {
//            Toast.makeText(
//                    getApplicationContext(),
//                    R.string.empty_not_saved,
//                    Toast.LENGTH_LONG).show();
//        }
        //-----------in OnClick:
//        Intent intent = new Intent(MainActivity.this, NewWordActivity.class);
//        startActivityForResult(intent, NEW_WORD_ACTIVITY_REQUEST_CODE);

    }

//    private static boolean InitDataBase ()
//    {
//        try
//        {
//            database = SQLiteDatabase.openOrCreateDatabase("MajesticLife_DB", null);
//        }
//        catch (Exception e)
//        {
//            return false;
//        }
//
//        return true;
//    }
}
