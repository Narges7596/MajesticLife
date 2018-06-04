package com.farazannajmi.majesticlife;

import android.content.Context;
import android.provider.ContactsContract;
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
    public static Context context;
    public static Faaliat thisFaaliat;
    public static ArrayAdapter<Skill_Time> Skills_ListView_adapter;

    public static ImageView Avatar_img;
    public EditText Name_editText;
    public NumberPicker XP_numberPicker;
    public NumberPicker HP_numberPicker;
    public NumberPicker SP_numberPicker;
    public static ListView Skills_ListView;

    final int minNumberPickerValue = -10;
    final int maxNumberPickerValue = 10;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        context = getApplicationContext();

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

        Avatar_img.setImageResource(thisFaaliat.Avatar);

        Name_editText.setText(thisFaaliat.Name);

        //region ------------ setting min and max values for numberpickers ------------
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
//        XP_numberPicker.setOnValueChangedListener(new NumberPicker.OnValueChangeListener()
//        {
//            @Override
//            public void onValueChange(NumberPicker picker, int oldVal, int newVal)
//            {
//                thisFaaliat.XpCount = XP_numberPicker.getValue() + minNumberPickerValue;
//            }
//        });

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
        //endregion ------------------------

        //create our new array adapter
        Skills_ListView_adapter = new EditFaaliatSkillsListItemArrayAdapter(context, thisFaaliat.SkillTimes);

        //bind the list view with the custom adapter
        Skills_ListView.setAdapter(Skills_ListView_adapter);
        //endregion ------------------------
    }

//    public static void SettingFaaliatSkills()
//    {
//        //create our new array adapter
//        Skills_ListView_adapter = new EditFaaliatSkillsListItemArrayAdapter(context, thisFaaliat.SkillTimes);
//
//        //bind the list view with the custom adapter
//        Skills_ListView.setAdapter(Skills_ListView_adapter);
//    }

    public void UiElementsOnClick(View view)
    {
        switch (view.getId())
        {
            case R.id.EditOneF_avatar_img:
            {
                showAvatarsAlertDialog();
                break;
            }
            case R.id.EditOneF_addskill_btn:
            {
                Skill_Time st = null;
                //finding a new skill:
                for(int i = 0; i < DataHolder.Skills.size(); i++)
                {
                    boolean found = false;
                    for(int j = 0; j < thisFaaliat.SkillTimes.size(); j++)
                    {
                        if(i == thisFaaliat.SkillTimes.get(j).index)
                        {
                            found = true;
                        }
                    }

                    if(!found)
                    {
                        Skill newSkill = DataHolder.Skills.get(i);
                        st = new Skill_Time(newSkill, 1, i);
                        break;
                    }
                }

                if(st == null)
                {
                    Toast.makeText(getApplicationContext(), "No more skills!", Toast.LENGTH_LONG).show();
                }
                else
                {
                    thisFaaliat.SkillTimes.add(st);
                    Skills_ListView_adapter.notifyDataSetChanged();
                }
                break;
            }
            case R.id.EditOneF_save_btn:
            {
                //saving name:
                thisFaaliat.Name =  Name_editText.getText().toString();

                thisFaaliat.XpCount = XP_numberPicker.getValue() + minNumberPickerValue;
                thisFaaliat.HpCount = HP_numberPicker.getValue() + minNumberPickerValue;
                thisFaaliat.SpCount = SP_numberPicker.getValue() + minNumberPickerValue;

                //returning
                finish();
                FaaliatsActivity.faaliats_listView_adapter.notifyDataSetChanged();
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
    }

    public static void ChangeAvatar(int imageResource)
    {
        Avatar_img.setImageResource(imageResource);
        thisFaaliat.Avatar = imageResource;
    }
}
