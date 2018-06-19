package com.farazannajmi.majesticlife.FaaliatPackage;

import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.NumberPicker;

import com.farazannajmi.majesticlife.DataHolder;
import com.farazannajmi.majesticlife.DataStructures.Faaliat;
import com.farazannajmi.majesticlife.R;
import com.jjoe64.graphview.DefaultLabelFormatter;
import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.LegendRenderer;
import com.jjoe64.graphview.helper.DateAsXAxisLabelFormatter;
import com.jjoe64.graphview.helper.StaticLabelsFormatter;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

import java.text.NumberFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by Narges on 6/19/2018.
 *
 * http://www.android-graphview.org/
 */

public class GraphDialogFragment extends DialogFragment
{
    private int faaliatIndex;
    private static Faaliat ThisFaaliat;
    private GraphView graphView;

    public static GraphDialogFragment newInstance(int faaliatIndex)
    {
        GraphDialogFragment f = new GraphDialogFragment();

        // Supply num input as an argument.
        Bundle args = new Bundle();
        args.putInt("faaliatIndex", faaliatIndex);
        f.setArguments(args);

        return f;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState)
    {
        faaliatIndex = getArguments().getInt("faaliatIndex");
        ThisFaaliat = DataHolder.Faaliats.get(faaliatIndex);

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        LayoutInflater inflater = getActivity().getLayoutInflater();

        View view = inflater.inflate(R.layout.dialog_graph, null);

        //-----------------------
        graphView = view.findViewById(R.id.graph);

        graphView.setTitle("How many hours you spend \"" + ThisFaaliat.getFaaliat_Name() + "\"");
        graphView.setTitleTextSize(20);

        graphView.getLegendRenderer().setVisible(true);
        graphView.getLegendRenderer().setAlign(LegendRenderer.LegendAlign.TOP);
        graphView.getLegendRenderer().setMargin(20);

        LineGraphSeries<DataPoint> series_thisWeek = new LineGraphSeries<DataPoint>(new DataPoint[] {
                new DataPoint(0, 1),
                new DataPoint(1, 5),
                new DataPoint(2, 3),
                new DataPoint(3, 2),
                new DataPoint(4, 6),
                new DataPoint(5, 3),
                new DataPoint(6, 6)
        });

        series_thisWeek.setTitle("This Week");
        series_thisWeek.setColor(ContextCompat.getColor(FaaliatsActivity.context, ThisFaaliat.getColor_ResIndex()));

        LineGraphSeries<DataPoint> series_lastWeek = new LineGraphSeries<>(new DataPoint[] {
                new DataPoint(0, 3),
                new DataPoint(1, 2),
                new DataPoint(2, 3),
                new DataPoint(3, 5),
                new DataPoint(4, 6),
                new DataPoint(5, 1),
                new DataPoint(6, 4)
        });

        series_lastWeek.setTitle("Last Week");
        series_lastWeek.setColor(ContextCompat.getColor(FaaliatsActivity.context, R.color.lightgray));

        //graphView.getGridLabelRenderer().setVerticalLabelsSecondScaleColor(Color.RED);
        graphView.getGridLabelRenderer().setHorizontalAxisTitle("Days of week");
        graphView.getGridLabelRenderer().setVerticalAxisTitle("Hour");

        // use static labels for horizontal and vertical labels
        StaticLabelsFormatter staticLabelsFormatter = new StaticLabelsFormatter(graphView);
        staticLabelsFormatter.setHorizontalLabels(new String[] {"Sa", "Su", "Mo", "Tu", "We", "Th", "Fr"});
        graphView.getGridLabelRenderer().setLabelFormatter(staticLabelsFormatter);

        graphView.addSeries(series_thisWeek);
        graphView.addSeries(series_lastWeek);
        //-----------------------

        // Add action buttons
        builder.setPositiveButton(R.string.ok, new DialogInterface.OnClickListener()
        {
            @Override
            public void onClick(DialogInterface dialog, int id)
            {
                //times_numberPicker = getDialog().findViewById(R.id.pluseFaaliatD__times_numberPicker);
                getDialog().dismiss();
            }
        });

        //builder.setTitle("How many hours were you doing this activity?");

        builder.setView(view);
        return builder.create();
    }
}
