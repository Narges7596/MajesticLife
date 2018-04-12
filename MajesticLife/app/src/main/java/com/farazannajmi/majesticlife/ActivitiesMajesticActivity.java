package com.farazannajmi.majesticlife;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class ActivitiesMajesticActivity extends AppCompatActivity
{
    private ArrayList<Activity_Majesty> activityItemsList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_activities_majestic);

        Skill s = new Skill();
        s.Name_sk = "Elm";
        Skill s2 = new Skill();
        s2.Name_sk = "Olom";
        List<Skill_Time> st = new ArrayList<Skill_Time>();
        st.add(new Skill_Time(s, 10));
        st.add(new Skill_Time(s2, 20));

        activityItemsList.add(new Activity_Majesty("Embroidery", -10, 10, 20, st));

        //create our new array adapter
        ArrayAdapter<Activity_Majesty> adapter = new ActivityListItemArrayAdapter(this, 0, activityItemsList);

        //Find list view and bind it with the custom adapter
        ListView listView = (ListView) findViewById(R.id.MajesticActivity_ListView);
        listView.setAdapter(adapter);
    }
}

//custom ArrayAdapter for activity list items
class ActivityListItemArrayAdapter extends ArrayAdapter<Activity_Majesty>
{
    private Context context;
    private List<Activity_Majesty> activity_listItems;

    //constructor, call on creation
    public ActivityListItemArrayAdapter(Context context, int resource, ArrayList<Activity_Majesty> objects)
    {
        super(context, resource, objects);

        this.context = context;
        this.activity_listItems = objects;
    }

    //called when rendering the list
    public View getView(int position, View convertView, ViewGroup parent)
    {
        //get the property we are displaying
        Activity_Majesty activity_majesty = activity_listItems.get(position);

        //get the inflater and inflate the XML layout for each item
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.listitem_majesticactivity, null);

        TextView name_txt = (TextView) view.findViewById(R.id.listItem_activity_name_txt);
        TextView hp_txt = (TextView) view.findViewById(R.id.listItem_activity_hp_txt);
        TextView sp_txt = (TextView) view.findViewById(R.id.listItem_activity_sp_txt);
        TextView xp_txt = (TextView) view.findViewById(R.id.listItem_activity_xp_txt);
        TextView skills_txt = (TextView) view.findViewById(R.id.listItem_activity_skills_txt);
        ImageView avatar_img = (ImageView) view.findViewById(R.id.listItem_activity_avatar_img);

        //set address and description
        name_txt.setText(activity_majesty.Name_ac);
        hp_txt.setText(activity_majesty.HpCount);
        sp_txt.setText(activity_majesty.SpCount);
        xp_txt.setText(activity_majesty.XpCount);

        String sk_txt = "";
        for (int i = 0; i < activity_majesty.SkillTimes.size(); i++)
        {
            sk_txt += "+" + activity_majesty.SkillTimes.get(i).RepeatingTime + " " + activity_majesty.SkillTimes.get(i).TheSill.Name_sk + "\n";
        }

        skills_txt.setText(sk_txt);


//        //get the image associated with this property
//        int imageID = context.getResources().getIdentifier(property.getImage(), "drawable", context.getPackageName());
//        image.setImageResource(imageID);

        return view;
    }
}
