package com.farazannajmi.majesticlife.DataStructures;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Embedded;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

import javax.annotation.Nonnull;

import static android.arch.persistence.room.ForeignKey.CASCADE;

/**
 * Created by Narges on 6/20/2018.
 */

@Entity(tableName = "FaaliatRepetitions_table",
        primaryKeys = {"Faaliat_ID", "FR_Date"},
        foreignKeys = @ForeignKey(entity = Faaliat.class,
                parentColumns = "Faaliat_ID",
                childColumns = "Faaliat_ID",
                onDelete = CASCADE))
public class FaaliatRepetitions
{
    @Nonnull
    @ColumnInfo(name = "Faaliat_ID")
    private int Faaliat_ID;

    @NonNull
    @ColumnInfo(name = "FR_Date")
    private String FR_Date;

    @ColumnInfo(name = "DayOfWeek")
    private int DayOfWeek;

    @ColumnInfo(name = "RepetitionCount")
    private int RepetitionCount;


    @Nonnull
    public int getFaaliat_ID() {return Faaliat_ID;}

    @Nonnull
    public String getFR_Date() {return FR_Date;}
    public int getDayOfWeek() {return DayOfWeek;}
    public int getRepetitionCount() {return RepetitionCount;}

    public void setFaaliat_ID(@Nonnull int Faaliat_ID) {this.Faaliat_ID = Faaliat_ID;}
    public void setFR_Date(@Nonnull String FR_Date) {this.FR_Date = FR_Date;}
    public void setDayOfWeek(int DayOfWeek) {this.DayOfWeek = DayOfWeek;}
    public void setRepetitionCount(int RepetitionCount) {this.RepetitionCount = RepetitionCount;}

    public FaaliatRepetitions(@Nonnull int Faaliat_ID, int DayOfWeek, @Nonnull String FR_Date, int RepetitionCount)
    {
        this.Faaliat_ID = Faaliat_ID;
        this.DayOfWeek = DayOfWeek;
        this.FR_Date = FR_Date;
        this.RepetitionCount  = RepetitionCount ;
    }
}
