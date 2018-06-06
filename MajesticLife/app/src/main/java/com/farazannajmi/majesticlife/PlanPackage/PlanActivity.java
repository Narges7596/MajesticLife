package com.farazannajmi.majesticlife.PlanPackage;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TableRow;

import com.farazannajmi.majesticlife.R;

public class PlanActivity extends AppCompatActivity
{
    public TableLayout table;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_plan);

        table = (TableLayout) findViewById(R.id.plan_table);

        for(int rowCounter = 0; rowCounter < 7; rowCounter++)
        {
            TableRow row = new TableRow(this);
            row.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT));
            for (int itemCounter = 0; itemCounter < 16; itemCounter++)
            {
                Button btn = new Button(this);
                btn.setText(Integer.toString(itemCounter));
                row.addView(btn);
            }
            table.addView(row);
        }
    }
}
