package com.farazannajmi.majesticlife.SkillPackage;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.farazannajmi.majesticlife.AvatarSelectDialogFragment;
import com.farazannajmi.majesticlife.DataHolder;
import com.farazannajmi.majesticlife.DataStructures.Skill;
import com.farazannajmi.majesticlife.R;

public class EditSkillActivity extends AppCompatActivity
{
    public static Context context;
    public static Skill thisSkill;

    public static ImageView Avatar_img;
    public EditText Name_editText;
    //public Button color_btn;

    //private static int colorIndex;
    public static int AvatarResource;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_skill);
        context = getApplicationContext();

        //getting this current faaliat for editing:
        thisSkill = DataHolder.Skills.get(getIntent().getIntExtra("The_Skill", 0));

        //region ------------ getting UI elements and setting right values: ------------
        Avatar_img = (ImageView) findViewById(R.id.EditSkill_avatar_img);
        Name_editText = (EditText) findViewById(R.id.EditSkill_name_editText);
        //color_btn = (Button) findViewById(R.id.EditSkill_color_btn);

        Avatar_img.setImageResource(thisSkill.getAvatar_ResIndex());
        AvatarResource = thisSkill.getAvatar_ResIndex();

        Name_editText.setText(thisSkill.getSkill_Name());

        //setting color btn true color:
        //color_btn.setBackgroundColor(ContextCompat.getColor(context, thisFaaliat.fColor));
        //colorIndex = 1;
        //endregion ------------------------
    }

    public void UiElementsOnClick(View view)
    {
        switch (view.getId())
        {
            case R.id.EditSkill_avatar_img:
            {
                showAvatarsAlertDialog();
                break;
            }
//            case R.id.EditSkill_color_btn:
//            {
//                //changing color
//                colorIndex++;
//                if(colorIndex == 6)
//                {
//                    colorIndex = 1;
//                }
//                switch (colorIndex)
//                {
//                    case 1:
//                    {
//                        color_btn.setBackgroundColor(ContextCompat.getColor(this, R.color.faaliatsColor1));
//                        thisFaaliat.fColor = R.color.faaliatsColor1;
//                    }
//                    break;
//                    case 2:
//                    {
//                        color_btn.setBackgroundColor(ContextCompat.getColor(this, R.color.faaliatsColor2));
//                        thisFaaliat.fColor = R.color.faaliatsColor2;
//                        break;
//                    }
//                    case 3:
//                    {
//                        color_btn.setBackgroundColor(ContextCompat.getColor(this, R.color.faaliatsColor3));
//                        thisFaaliat.fColor = R.color.faaliatsColor3;
//                        break;
//                    }
//                    case 4:
//                    {
//                        color_btn.setBackgroundColor(ContextCompat.getColor(this, R.color.faaliatsColor4));
//                        thisFaaliat.fColor = R.color.faaliatsColor4;
//                        break;
//                    }
//                    case 5:
//                    {
//                        color_btn.setBackgroundColor(ContextCompat.getColor(this, R.color.faaliatsColor5));
//                        thisFaaliat.fColor = R.color.faaliatsColor5;
//                        break;
//                    }
//                    default:
//                    {
//                        color_btn.setBackgroundColor(ContextCompat.getColor(this, R.color.faaliatsColor1));
//                        thisFaaliat.fColor = R.color.faaliatsColor1;
//                        break;
//                    }
//                }
//                FaaliatsActivity.faaliats_listView_adapter.notifyDataSetChanged();
//                break;
//            }
            case R.id.EditSkill_save_btn:
            {
                //saving name:
                    thisSkill.setSkill_Name(Name_editText.getText().toString());
                    thisSkill.setAvatar_ResIndex(AvatarResource);
                    //returning
                    finish();
                    SkillsActivity.skill_gridview_adapter.notifyDataSetChanged();

                break;
            }
            default:
                break;
        }
    }

    private void showAvatarsAlertDialog()
    {
        //showing dialogue popup to get the new avatar
        AvatarSelectDialogFragment avatarsDialogue = AvatarSelectDialogFragment.newInstance(DataHolder.SkillAvatars, true);
        avatarsDialogue.show(getFragmentManager(), "AvatarSelectDialogFragment");
    }

    public static void ChangeAvatar(int imageResource)
    {
        Avatar_img.setImageResource(imageResource);
        AvatarResource = imageResource;
    }
}
