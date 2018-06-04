package com.farazannajmi.majesticlife;

import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.NumberPicker;
import android.widget.Toast;

public class EditOneFaaliatPopupActivity extends AppCompatActivity
{
    public static Faaliat thisFaaliat;

    public static ImageView Avatar_img;
    public EditText Name_editText;
    public NumberPicker XP_numberPicker;
    public NumberPicker HP_numberPicker;
    public NumberPicker SP_numberPicker;
    public ListView Skills_ListView;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_one_faaliat_popup);

        //getting this current faaliat for editing:
        thisFaaliat = DataHolder.Faaliats.get(getIntent().getIntExtra("The_Faaliat", 0));

        Toast.makeText(getApplicationContext(), "clicked, name: " + thisFaaliat.Name, Toast.LENGTH_LONG).show();

        //region ------------ getting UI elements and setting right values: ------------
        Avatar_img = (ImageView) findViewById(R.id.EditOneF_avatar_img);
        Name_editText = (EditText) findViewById(R.id.EditOneF_name_editText);
        XP_numberPicker = (NumberPicker) findViewById(R.id.EditOneF_xp_numberPicker);
        HP_numberPicker = (NumberPicker) findViewById(R.id.EditOneF_hp_numberPicker);
        SP_numberPicker = (NumberPicker) findViewById(R.id.EditOneF_sp_numberPicker);
        Skills_ListView = (ListView) findViewById(R.id.EditOneF_skills_ListView);

        //Avatar_img.setImageBitmap(thisFaaliat.Avatar);

        Name_editText.setText(thisFaaliat.Name);

        //region ------------ setting min and max values for numberpickers ------------
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
        XP_numberPicker.setOnValueChangedListener(new NumberPicker.OnValueChangeListener()
        {
            @Override
            public void onValueChange(NumberPicker picker, int oldVal, int newVal)
            {
                thisFaaliat.XpCount = newVal;
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
        HP_numberPicker.setOnValueChangedListener(new NumberPicker.OnValueChangeListener()
        {
            @Override
            public void onValueChange(NumberPicker picker, int oldVal, int newVal)
            {
                thisFaaliat.HpCount = newVal;
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
        SP_numberPicker.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
                thisFaaliat.SpCount = newVal;
            }
        });
        //endregion ------------------------

        SettingFaaliatSkills();
        //endregion ------------------------

        //int myNewValue = XP_numberPicker.getValue() + minNumberPickerValue;
    }

    public void SettingFaaliatSkills()
    {
        //create our new array adapter
        ArrayAdapter<Skill_Time> adapter = new EditFaaliatSkillsListItemArrayAdapter(this, thisFaaliat.SkillTimes);

        //bind the list view with the custom adapter
        Skills_ListView.setAdapter(adapter);
    }

    public void UiElementsOnClick(View view)
    {
        switch (view.getId())
        {
            case R.id.EditOneF_avatar_img:
            {
                showAvatarsAlertDialog();
                break;
            }
            case R.id.EditOneF_save_btn:
            {
                //todo: saving all changes
                //saving name:
                thisFaaliat.Name =  Name_editText.getText().toString();

                // todo: saving avatar:
                //thisFaaliat.Avatar = ;
                break;
            }
            default:
                break;
        }
    }

    private void showAvatarsAlertDialog()
    {
        //showing dialogue popup to get the new avatar
        AvatarSelectDialogFragment avatarsDialogue = AvatarSelectDialogFragment.newInstance(DataHolder.FaaliatAvatars);
        avatarsDialogue.show(getFragmentManager(), "AvatarSelectDialogFragment");


//        // Prepare grid view
//        GridView gridView = new GridView(this);
//        gridView.setAdapter(new ImageAdapter(this, DataHolder.FaaliatAvatars));
//        gridView.setNumColumns(3);
//        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener()
//        {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id)
//            {
//                Avatar_img.setImageResource(DataHolder.FaaliatAvatars.get(position));
//            }
//        });
//
//        // Set grid view to alertDialog
//        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
//        builder.setView(gridView);
//        builder.setTitle("Select a new avatar");
//        builder.show();
    }

    public static void ChangeAvatar(int imageResource)
    {
        Avatar_img.setImageResource(imageResource);
    }
}
