package com.farazannajmi.majesticlife.FaaliatPackage;

import android.app.Dialog;
import android.app.DialogFragment;
import android.arch.lifecycle.LifecycleOwner;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.NumberPicker;

import com.farazannajmi.majesticlife.DataHolder;
import com.farazannajmi.majesticlife.DataStructures.Faaliat;
import com.farazannajmi.majesticlife.DataStructures.FaaliatRepetitions;
import com.farazannajmi.majesticlife.DataStructures.FaaliatRepetitionsViewModel;
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
import java.util.List;

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

        final View view = inflater.inflate(R.layout.dialog_graph, null);

        //getting date and day of week
        final Calendar calendar = Calendar.getInstance();
        final int todayDayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);
        final int todayDate = calendar.get(Calendar.DAY_OF_MONTH);
        final String date = calendar.get(Calendar.YEAR) + "/" + calendar.get(Calendar.MONTH) + "/" + calendar.get(Calendar.DAY_OF_MONTH);

        //-----------------------
        FaaliatRepetitionsViewModel faaliatRepetitionsViewModel = ViewModelProviders.of(FaaliatsActivity.appCompatActivity).get(FaaliatRepetitionsViewModel.class);
        faaliatRepetitionsViewModel.getAllFaaliatRepsForFaaliat(faaliatIndex).observe((LifecycleOwner) FaaliatsActivity.context, new Observer<List<FaaliatRepetitions>>() {
                    @Override
                    public void onChanged(@Nullable List<FaaliatRepetitions> faaliatRepetitions)
                    {
                        int[] thisWeek = new int[7];
                        int[] lastWeek = new int[7];

                        for(int i = 0; i < faaliatRepetitions.size(); i++)
                        {
                            FaaliatRepetitions fr = faaliatRepetitions.get(i);
                            if(fr.getDayOfWeek() <= todayDayOfWeek)
                            {
                                String[] splits = fr.getFR_Date().split("/");
                                int n = todayDate - Integer.parseInt(splits[2]);
                                if(n >= 7)
                                {
                                    lastWeek[fr.getDayOfWeek() - 1] = fr.getRepetitionCount();
                                }
                                else if (n < 7 && n >= 0)
                                {
                                    thisWeek[fr.getDayOfWeek() - 1] = fr.getRepetitionCount();
                                }
                            }
                            else //if(fr.getDayOfWeek() > todayDayOfWeek)
                            {
                                lastWeek[fr.getDayOfWeek() - 1] = fr.getRepetitionCount();
                            }
                        }

                        graphView = view.findViewById(R.id.graph);

                        graphView.setTitle("How many hours you spend \"" + ThisFaaliat.getFaaliat_Name() + "\"");
                        graphView.setTitleTextSize(20);

                        graphView.getLegendRenderer().setVisible(true);
                        graphView.getLegendRenderer().setAlign(LegendRenderer.LegendAlign.TOP);
                        graphView.getLegendRenderer().setMargin(20);

                        LineGraphSeries<DataPoint> series_thisWeek = new LineGraphSeries<DataPoint>(new DataPoint[] {
                                new DataPoint(1, thisWeek[0]),
                                new DataPoint(2, thisWeek[1]),
                                new DataPoint(3, thisWeek[2]),
                                new DataPoint(4, thisWeek[3]),
                                new DataPoint(5, thisWeek[4]),
                                new DataPoint(6, thisWeek[5]),
                                new DataPoint(7, thisWeek[6])
                        });

                        series_thisWeek.setTitle("This Week");
                        series_thisWeek.setColor(ContextCompat.getColor(FaaliatsActivity.context, ThisFaaliat.getColor_ResIndex()));

                        LineGraphSeries<DataPoint> series_lastWeek = new LineGraphSeries<>(new DataPoint[] {
                                new DataPoint(1, lastWeek[0]),
                                new DataPoint(2, lastWeek[1]),
                                new DataPoint(3, lastWeek[2]),
                                new DataPoint(4, lastWeek[3]),
                                new DataPoint(5, lastWeek[4]),
                                new DataPoint(6, lastWeek[5]),
                                new DataPoint(7, lastWeek[6])
                        });

                        series_lastWeek.setTitle("Last Week");
                        series_lastWeek.setColor(ContextCompat.getColor(FaaliatsActivity.context, R.color.lightgray));

                        //graphView.getGridLabelRenderer().setVerticalLabelsSecondScaleColor(Color.RED);
                        graphView.getGridLabelRenderer().setHorizontalAxisTitle("Days of week");
                        graphView.getGridLabelRenderer().setVerticalAxisTitle("Hour");

                        // use static labels for horizontal and vertical labels
                        StaticLabelsFormatter staticLabelsFormatter = new StaticLabelsFormatter(graphView);
                        staticLabelsFormatter.setHorizontalLabels(new String[] {"Su", "Mo", "Tu", "We", "Th", "Fr", "Sa"});
                        graphView.getGridLabelRenderer().setLabelFormatter(staticLabelsFormatter);

                        graphView.addSeries(series_thisWeek);
                        graphView.addSeries(series_lastWeek);
                    }
        });
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
