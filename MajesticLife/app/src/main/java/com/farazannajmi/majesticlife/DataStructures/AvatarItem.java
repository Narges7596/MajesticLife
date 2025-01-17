package com.farazannajmi.majesticlife.DataStructures;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

import javax.annotation.Nonnull;

/**
 * Created by Narges on 6/25/2018.
 */

@Entity(tableName = "AvatarItem_table")
public class AvatarItem
{
    @Nonnull
    @PrimaryKey
    @ColumnInfo(name = "ResourceIndex")
    private int ResourceIndex;

    @ColumnInfo(name = "IsBought")
    private boolean IsBought;

    @ColumnInfo(name = "ItemType")
    private int ItemType;
    /*
    0 => background
    1 => skin
    2 => cloth
    3 => eyes
    4 => mouth
    5 => crown
     */

    @ColumnInfo(name = "Price")
    private int Price;

    @Nonnull
    public int getResourceIndex() {return ResourceIndex;}
    public boolean getIsBought() {return IsBought;}
    public int getItemType() {return ItemType;}
    public int getPrice() {return  Price;}

    public void setResourceIndex(@Nonnull int ResourceIndex) {this.ResourceIndex = ResourceIndex;}
    public void setIsBought(boolean IsBought) {this.IsBought = IsBought;}
    public void setItemType(int ItemType) {this.ItemType = ItemType;}
    public void setPrice(int Price) {this.Price = Price;}

    public AvatarItem(@Nonnull int ResourceIndex, boolean IsBought, int ItemType, int Price)
    {
        this.ResourceIndex = ResourceIndex;
        this.IsBought = IsBought;
        this.ItemType = ItemType;
        this.Price = Price;
    }
}
