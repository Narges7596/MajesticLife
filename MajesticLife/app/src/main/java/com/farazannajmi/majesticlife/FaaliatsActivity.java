package com.farazannajmi.majesticlife;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class FaaliatsActivity extends AppCompatActivity
{
    public AppManager TheAppManager;

    private ListView faaliats_listView;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_faaliats);

        TheAppManager = (AppManager) getApplicationContext();

        //getting UI elements
        faaliats_listView = (ListView) findViewById(R.id.FaaliatsActivity_ListView);

        //region ----------------------------test------------------------------
        Skill s = new Skill("Elm", 5, 50);
        Skill s2 = new Skill("Olom", 2, 25);
        List<Skill_Time> st = new ArrayList<Skill_Time>();
        st.add(new Skill_Time(s, 10));
        st.add(new Skill_Time(s2, 20));
        TheAppManager.Faaliats.add(new Faaliat("Embroidery", -10, 5, 2, st));

        Skill s3 = new Skill("skill3", 5, 50);
        Skill s4 = new Skill("skill4", 5, 50);
        List<Skill_Time> st2 = new ArrayList<Skill_Time>();
        st2.add(new Skill_Time(s3, 2));
        st2.add(new Skill_Time(s4, 3));
        TheAppManager.Faaliats.add(new Faaliat("Alaki", -1, 2, 3, st2));
        //endregion

        //create our new array adapter
        ArrayAdapter<Faaliat> adapter = new FaaliatsListItemArrayAdapter(this, TheAppManager.Faaliats);

        //bind the list view with the custom adapter
        faaliats_listView.setAdapter(adapter);
    }

//    private void create()
//    {
//        faaliats_listView = (ListView) findViewById(R.id.FaaliatsActivity_ListView);
//
//        //region ----------------------------test------------------------------
//        Skill s = new Skill("Elm", 5, 50);
//        Skill s2 = new Skill("Olom", 2, 25);
//        List<Skill_Time> st = new ArrayList<Skill_Time>();
//        st.add(new Skill_Time(s, 10));
//        st.add(new Skill_Time(s2, 20));
//        TheAppManager.Faaliats.add(new Faaliat("Embroidery", -10, 10, 20, st));
//
//        Skill s3 = new Skill("skill3", 5, 50);
//        Skill s4 = new Skill("skill4", 5, 50);
//        List<Skill_Time> st2 = new ArrayList<Skill_Time>();
//        st2.add(new Skill_Time(s3, 2));
//        st2.add(new Skill_Time(s4, 3));
//        TheAppManager.Faaliats.add(new Faaliat("Alaki", -1, 2, 3, st2));
//        //endregion
//
//        //create our new array adapter
//        ArrayAdapter<Faaliat> adapter = new FaaliatsListItemArrayAdapter(this, TheAppManager.Faaliats);
//
//        //bind the list view with the custom adapter
//        faaliats_listView.setAdapter(adapter);
//    }
//
//    @Override
//    protected void onResume()
//    {
//        super.onResume();
//        create();
//    }

    public void UiElementsOnClick(View view)
    {
        if(view.getId() == R.id.FaaliatsActivity_edit_btn)
        {
            Intent EditIntent = new Intent(FaaliatsActivity.this, EditFaaliatsActivity.class);
            startActivity(EditIntent);
        }
    }
}
