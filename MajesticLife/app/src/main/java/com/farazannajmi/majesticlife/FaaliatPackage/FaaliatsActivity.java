package com.farazannajmi.majesticlife.FaaliatPackage;

import android.app.Activity;
import android.arch.lifecycle.LifecycleOwner;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.content.Intent;
import android.provider.ContactsContract;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.farazannajmi.majesticlife.AccountPackage.SignupDialogFragment;
import com.farazannajmi.majesticlife.DataHolder;
import com.farazannajmi.majesticlife.DataStructures.Faaliat;
import com.farazannajmi.majesticlife.DataStructures.FaaliatRepetitions;
import com.farazannajmi.majesticlife.DataStructures.FaaliatRepetitionsViewModel;
import com.farazannajmi.majesticlife.DataStructures.FaaliatSkill;
import com.farazannajmi.majesticlife.DataStructures.FaaliatSkillViewModel;
import com.farazannajmi.majesticlife.DataStructures.FaaliatViewModel;
import com.farazannajmi.majesticlife.DataStructures.Skill;
import com.farazannajmi.majesticlife.DataStructures.SkillViewModel;
import com.farazannajmi.majesticlife.R;

import java.security.acl.Owner;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class FaaliatsActivity extends AppCompatActivity
{
    public static Context context;
    public static AppCompatActivity appCompatActivity;
    public static ArrayAdapter<Faaliat> faaliats_listView_adapter;
    private ListView faaliats_listView;
    public static boolean faaliatRepAdded = false;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_faaliats);
        context = this;
        appCompatActivity = this;

        //getting UI elements
        faaliats_listView = (ListView) findViewById(R.id.FaaliatsActivity_ListView);

        //create our new array adapter
        faaliats_listView_adapter = new FaaliatsListItemArrayAdapter(this, DataHolder.Faaliats, this);
        //bind the list view with the custom adapter
        faaliats_listView.setAdapter(faaliats_listView_adapter);
    }

    @Override
    protected void onResume()
    {
        super.onResume();
        faaliats_listView_adapter.notifyDataSetChanged();
    }

    public void UiElementsOnClick(View view)
    {
        if(view.getId() == R.id.FaaliatsActivity_addFaaliat_btn)
        {
            Faaliat newFaaliat = new Faaliat(DataHolder.Faaliats.size(), "new activity", DataHolder.FaaliatAvatars.get(0),
                    R.color.faaliatsColor1, 0, 0, 0, DataHolder.ThisUser.getUser_ID());
            DataHolder.Faaliats.add(newFaaliat);

            //adding skill in database:
            FaaliatViewModel faaliatViewModel = ViewModelProviders.of(this).get(FaaliatViewModel.class);
            faaliatViewModel.insert(newFaaliat);

            //opening new activity for editing this new faaliat:
            Intent intent = new Intent(FaaliatsActivity.this, EditFaaliatActivity.class);
            intent.putExtra("The_Faaliat", DataHolder.Faaliats.size() - 1);
            startActivity(intent);
        }
    }

    public static void ShowGainedPopup(final int faaliatIndex, final int times)
    {
        Log.d("WorkFlow", "Clicked on sign up btn in account management activity.");

        //region adding times to FaaliatRepetitions in database:

        //getting date and day of week
        Calendar calendar = Calendar.getInstance();
        final int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);
        final String date = calendar.get(Calendar.YEAR) + "/" + (calendar.get(Calendar.MONTH) + 1) + "/" + calendar.get(Calendar.DAY_OF_MONTH);

        //getting previews repetition counts (if exists)
        FaaliatRepetitionsViewModel faaliatRepetitionsViewModel = ViewModelProviders.of(appCompatActivity).get(FaaliatRepetitionsViewModel.class);
        faaliatRepetitionsViewModel.getFaaliatRepsForFaaliatDate(faaliatIndex, date).observe((LifecycleOwner) context, new Observer<List<FaaliatRepetitions>>() {
            @Override
            public void onChanged(@Nullable List<FaaliatRepetitions> faaliatRepetitions)
            {
                if(!faaliatRepAdded)
                {
                    if(faaliatRepetitions.size() == 1)
                    {
                        faaliatRepetitions.get(0).setRepetitionCount(faaliatRepetitions.get(0).getRepetitionCount() + times);
                        FaaliatRepetitionsViewModel faaliatRepetitionsViewModel = ViewModelProviders.of(appCompatActivity).get(FaaliatRepetitionsViewModel.class);
                        faaliatRepetitionsViewModel.update(faaliatRepetitions.get(0));
                        faaliatRepAdded = true;
                    }
                    else
                    {
                        FaaliatRepetitions newFaaliatRepetitions = new FaaliatRepetitions(faaliatIndex, dayOfWeek, date, times);

                        FaaliatRepetitionsViewModel faaliatRepetitionsViewModel = ViewModelProviders.of(appCompatActivity).get(FaaliatRepetitionsViewModel.class);
                        faaliatRepetitionsViewModel.insert(newFaaliatRepetitions);
                        faaliatRepAdded = true;
                    }
                }
            }
        });
        //endregion

        //showing dialogue popup to show user how much he has improved
        GainedFromFaaliatDialogFragment gainedFromFaaliatDialog = GainedFromFaaliatDialogFragment.newInstance(faaliatIndex, times);
        gainedFromFaaliatDialog.show(((Activity) context).getFragmentManager(), "GainedFromFaaliatDialogFragment");
    }

    public static void DeleteFaaliat(int faaliatIndex)
    {
        //delete from database:
        FaaliatViewModel faaliatViewModel = ViewModelProviders.of((AppCompatActivity)context).get(FaaliatViewModel.class);
        faaliatViewModel.delete(DataHolder.Faaliats.get(faaliatIndex));

        DataHolder.Faaliats.remove(faaliatIndex);
        faaliats_listView_adapter.notifyDataSetChanged();
    }
}
