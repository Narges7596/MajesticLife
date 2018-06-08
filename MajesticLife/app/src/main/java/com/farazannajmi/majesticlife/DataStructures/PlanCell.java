package com.farazannajmi.majesticlife.DataStructures;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.PrimaryKey;

import javax.annotation.Nonnull;

import static android.arch.persistence.room.ForeignKey.CASCADE;

/**
 * Created by Narges on 6/7/2018.
 */

@Entity(tableName = "PlanCell_table",
        foreignKeys = @ForeignKey(entity = Faaliat.class,
                parentColumns = "Faaliat_ID",
                childColumns = "Faaliat_ID",
                onDelete = CASCADE))
public class PlanCell
{
    @PrimaryKey
    @Nonnull
    @ColumnInfo(name = "PlanCell_ID")
    private int PlanCell_ID;

    @Nonnull
    @ColumnInfo(name = "Faaliat_ID")
    private int Faaliat_ID;

    @ColumnInfo(name = "WeekDay")
    private int WeekDay;
    @ColumnInfo(name = "DayHour")
    private int DayHour;

    @Nonnull
    public int getPlanCell_ID(){return this.PlanCell_ID;}
    public int getFaaliat_ID(){return this.Faaliat_ID;}
    public int getWeekDay(){return this.WeekDay;}
    public int getDayHour(){return this.DayHour;}

    public void setPlanCell_ID(int PlanCell_ID){this.PlanCell_ID = PlanCell_ID;}
    public void setFaaliat_ID(int Faaliat_ID){this.Faaliat_ID = Faaliat_ID;}
    public void setWeekDay(int WeekDay){this.WeekDay = WeekDay;}
    public void setDayHour(int DayHour){this.DayHour = DayHour;}

    public PlanCell (int PlanCell_ID, int Faaliat_ID, int WeekDay, int DayHour)
    {
        this.PlanCell_ID = PlanCell_ID;
        this.Faaliat_ID = Faaliat_ID;
        this.WeekDay = WeekDay;
        this.DayHour = DayHour;
    }
}
