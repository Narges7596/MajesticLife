package com.farazannajmi.majesticlife;

import android.content.Context;
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
    public static ArrayAdapter<Faaliat> faaliats_listView_adapter;
    private ListView faaliats_listView;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_faaliats);

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
            //todo: adding a new faaliat
        }
    }
}
