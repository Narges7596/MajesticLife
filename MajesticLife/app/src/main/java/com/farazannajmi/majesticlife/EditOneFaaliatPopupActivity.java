package com.farazannajmi.majesticlife;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.NumberPicker;
import android.widget.Toast;

import java.util.jar.Attributes;

public class EditOneFaaliatPopupActivity extends AppCompatActivity
{
    public AppManager TheAppManager;

    private Faaliat thisFaaliat;

    public ImageView Avatar_img;
    public EditText Name_editText;
    public NumberPicker XP_numberPicker;
    public NumberPicker HP_numberPicker;
    public NumberPicker SP_numberPicker;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_one_faaliat_popup);

        TheAppManager = (AppManager) getApplicationContext();

        //getting this current faaliat for editing:
        thisFaaliat = TheAppManager.Faaliats.get(getIntent().getIntExtra("The_Faaliat", 0));

        Toast.makeText(getApplicationContext(), "clicked, name: " + thisFaaliat.Name, Toast.LENGTH_LONG).show();

        //regoin getting UI elements and setting right values:
        Avatar_img = findViewById(R.id.EditOneF_avatar_img);
        Name_editText = findViewById(R.id.EditOneF_name_editText);
        XP_numberPicker = findViewById(R.id.EditOneF_xp_numberPicker);
        HP_numberPicker = findViewById(R.id.EditOneF_hp_numberPicker);
        SP_numberPicker = findViewById(R.id.EditOneF_sp_numberPicker);

        //Avatar_img.setImageBitmap(thisFaaliat.Avatar);

        Name_editText.setText(thisFaaliat.Name);

        //region setting min and max values for numberpickers
        final int minNumberPickerValue = -10;
        final int maxNumberPickerValue = 10;

        XP_numberPicker.setMinValue(0);
        XP_numberPicker.setMaxValue(maxNumberPickerValue - minNumberPickerValue);
        XP_numberPicker.setValue(0);
        XP_numberPicker.setValue(thisFaaliat.XpCount - minNumberPickerValue);
        XP_numberPicker.setFormatter(new NumberPicker.Formatter()
        {
            @Override
            public String format(int index)
            {
                return Integer.toString(index + minNumberPickerValue);
            }
        });

        HP_numberPicker.setMinValue(0);
        HP_numberPicker.setMaxValue(maxNumberPickerValue - minNumberPickerValue);
        HP_numberPicker.setValue(0);
        HP_numberPicker.setValue(thisFaaliat.HpCount - minNumberPickerValue);
        HP_numberPicker.setFormatter(new NumberPicker.Formatter()
        {
            @Override
            public String format(int index)
            {
                return Integer.toString(index + minNumberPickerValue);
            }
        });

        SP_numberPicker.setMinValue(0);
        SP_numberPicker.setMaxValue(maxNumberPickerValue - minNumberPickerValue);
        SP_numberPicker.setValue(0);
        SP_numberPicker.setValue(thisFaaliat.SpCount - minNumberPickerValue);
        SP_numberPicker.setFormatter(new NumberPicker.Formatter()
        {
            @Override
            public String format(int index)
            {
                return Integer.toString(index + minNumberPickerValue);
            }
        });
        //endregion

        //endregion

        //int myNewValue = XP_numberPicker.getValue() + minNumberPickerValue;
    }
}
