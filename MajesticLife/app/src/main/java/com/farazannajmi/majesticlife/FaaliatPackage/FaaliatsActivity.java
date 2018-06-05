package com.farazannajmi.majesticlife.FaaliatPackage;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.farazannajmi.majesticlife.DataHolder;
import com.farazannajmi.majesticlife.DataStructures.Faaliat;
import com.farazannajmi.majesticlife.DataStructures.Skill_Time;
import com.farazannajmi.majesticlife.R;

import java.util.ArrayList;

public class FaaliatsActivity extends AppCompatActivity
{
    public static Context context;
    public static ArrayAdapter<Faaliat> faaliats_listView_adapter;
    private ListView faaliats_listView;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_faaliats);
        context = getApplicationContext();

        //getting UI elements
        faaliats_listView = (ListView) findViewById(R.id.FaaliatsActivity_ListView);

        //create our new array adapter
        faaliats_listView_adapter = new FaaliatsListItemArrayAdapter(this, DataHolder.Faaliats);

        //bind the list view with the custom adapter
        faaliats_listView.setAdapter(faaliats_listView_adapter);
    }

    public void UiElementsOnClick(View view)
    {
        if(view.getId() == R.id.FaaliatsActivity_addFaaliat_btn)
        {
            Faaliat newFaaliat = new Faaliat("new activity", DataHolder.FaaliatAvatars.get(0),R.color.faaliatsColor1,
                    0, 0, 0,
                    new ArrayList<Skill_Time>());
            DataHolder.Faaliats.add(newFaaliat);

            //opening new activity for editing this new faaliat:
            Intent intent = new Intent(FaaliatsActivity.this, EditOneFaaliatPopupActivity.class);
            intent.putExtra("The_Faaliat", DataHolder.Faaliats.size() - 1);
            startActivity(intent);

            faaliats_listView_adapter.notifyDataSetChanged();
        }
    }

    public static void ShowGainedPopup(int faaliatIndex, int times)
    {
        Log.d("WorkFlow", "Clicked on sign up btn in account management activity.");

        //todo
        //showing dialogue popup to show user how much he has improved
//        PluseFaaliatDialogFragment pluseFaaliatDialog = PluseFaaliatDialogFragment.newInstance(position);
//        pluseFaaliatDialog.show(((Activity) context).getFragmentManager(), "PluseFaaliatDialogFragment");
    }
}
