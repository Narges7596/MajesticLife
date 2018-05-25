package com.farazannajmi.majesticlife;

import android.content.Context;
import android.content.Intent;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class EditFaaliatsActivity extends AppCompatActivity
{
    public AppManager TheAppManager;

    private ListView editFaaliats_listView;

    private static Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_faaliats);

        TheAppManager = (AppManager) getApplicationContext();

        context = getApplicationContext();

        //getting UI elements
        editFaaliats_listView = (ListView) findViewById(R.id.EditFaaliatsActivity_ListView);

        //create our new array adapter
        ArrayAdapter<Faaliat> adapter = new EditFaaliatsListItemArrayAdapter(this, TheAppManager.Faaliats);

        //bind the list view with the custom adapter
        editFaaliats_listView.setAdapter(adapter);
    }

    public void UiElementsOnClick(View view)
    {
        if(view.getId() == R.id.EditFaaliatsActivity_NewFaaliat_btn)
        {

        }
    }
}
