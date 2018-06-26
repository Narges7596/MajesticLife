package com.farazannajmi.majesticlife.DataStructures;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.PrimaryKey;

import javax.annotation.Nonnull;

import static android.arch.persistence.room.ForeignKey.CASCADE;

/**
 * Created by Narges on 6/25/2018.
 */

@Entity(tableName = "Avatar_table",
        foreignKeys = {
                @ForeignKey(entity = User.class,
                parentColumns = "User_ID",
                childColumns = "User_ID",
                onDelete = CASCADE)
//                ,@ForeignKey(entity = AvatarItem.class,
//                        parentColumns = "ResourceIndex",
//                        childColumns = "Background_ResIndex",
//                        onDelete = CASCADE),
//                @ForeignKey(entity = AvatarItem.class,
//                        parentColumns = "ResourceIndex",
//                        childColumns = "Skin_ResIndex",
//                        onDelete = CASCADE),
//                @ForeignKey(entity = AvatarItem.class,
//                        parentColumns = "ResourceIndex",
//                        childColumns = "Cloth_ResIndex",
//                        onDelete = CASCADE),
//                @ForeignKey(entity = AvatarItem.class,
//                        parentColumns = "ResourceIndex",
//                        childColumns = "Eyes_ResIndex",
//                        onDelete = CASCADE),
//                @ForeignKey(entity = AvatarItem.class,
//                        parentColumns = "ResourceIndex",
//                        childColumns = "Mouth_ResIndex",
//                        onDelete = CASCADE),
//                @ForeignKey(entity = AvatarItem.class,
//                        parentColumns = "ResourceIndex",
//                        childColumns = "Crown_ResIndex",
//                        onDelete = CASCADE)}
        })
public class Avatar
{
    @Nonnull
    @PrimaryKey
    @ColumnInfo(name = "Avatar_ID")
    private int Avatar_ID;

    @ColumnInfo(name = "User_ID")
    private int User_ID;

    @ColumnInfo(name = "IsKing")
    private boolean IsKing;


    @ColumnInfo(name = "Background_ResIndex")
    private int Background_ResIndex;

    @ColumnInfo(name = "Skin_ResIndex")
    private int Skin_ResIndex;

    @ColumnInfo(name = "Cloth_ResIndex")
    private int Cloth_ResIndex;

    @ColumnInfo(name = "Eyes_ResIndex")
    private int Eyes_ResIndex;

    @ColumnInfo(name = "Mouth_ResIndex")
    private int Mouth_ResIndex;

    @ColumnInfo(name = "Crown_ResIndex")
    private int Crown_ResIndex;

    @Nonnull
    public int getAvatar_ID() {return Avatar_ID;}
    public int getUser_ID() {return User_ID;}
    public boolean getIsKing() {return IsKing;}
    public int getBackground_ResIndex() {return Background_ResIndex;}
    public int getSkin_ResIndex() {return Skin_ResIndex;}
    public int getCloth_ResIndex() {return Cloth_ResIndex;}
    public int getEyes_ResIndex() {return Eyes_ResIndex;}
    public int getMouth_ResIndex() {return Mouth_ResIndex;}
    public int getCrown_ResIndex() {return Crown_ResIndex;}

    public void setAvatar_ID(@Nonnull int Avatar_ID) {this.Avatar_ID = Avatar_ID;}
    public void setUser_ID(int User_ID) {this.User_ID = User_ID;}
    public void setIsKing(boolean IsKing) {this.IsKing = IsKing;}
    public void setBackground_ResIndex(int Background_ResIndex) {this.Background_ResIndex = Background_ResIndex;}
    public void setSkin_ResIndex(int Skin_ResIndex) {this.Skin_ResIndex = Skin_ResIndex;}
    public void setCloth_ResIndex(int Cloth_ResIndex) {this.Cloth_ResIndex = Cloth_ResIndex;}
    public void setEyes_ResIndex(int Eyes_ResIndex) {this.Eyes_ResIndex = Eyes_ResIndex;}
    public void setMouth_ResIndex(int Mouth_ResIndex) {this.Mouth_ResIndex = Mouth_ResIndex;}
    public void setCrown_ResIndex(int Crown_ResIndex) {this.Crown_ResIndex = Crown_ResIndex;}

    public Avatar(@Nonnull int Avatar_ID, int User_ID, boolean IsKing, int Background_ResIndex,
                  int Skin_ResIndex, int Cloth_ResIndex, int Eyes_ResIndex, int Mouth_ResIndex, int Crown_ResIndex)
    {
        this.Avatar_ID = Avatar_ID;
        this.User_ID = User_ID;
        this.IsKing = IsKing;
        this.Background_ResIndex = Background_ResIndex;
        this.Skin_ResIndex = Skin_ResIndex;
        this.Cloth_ResIndex = Cloth_ResIndex;
        this.Eyes_ResIndex = Eyes_ResIndex;
        this.Mouth_ResIndex = Mouth_ResIndex;
        this.Crown_ResIndex = Crown_ResIndex;
    }
}
