package com.farazannajmi.majesticlife;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

/**
 * Created by Narges on 6/5/2018.
 */

public class SQLiteDatabaseHandler extends SQLiteOpenHelper
{
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "MajesticLife_DB";

    private static final String TABLE_NAME = "Faaliats";
    private static final String KEY_ID = "id";
    private static final String KEY_NAME = "name";
    private static final String KEY_AVATAR = "avatar";
    private static final String KEY_FCOLOR = "fcolor";
    private static final String KEY_XPCOUNT = "xpcount";
    private static final String KEY_HPCOUNT = "hpcount";
    private static final String KEY_SPCOUNT = "spcount";
    private static final String KEY_SKILLTIMES = "skilltimes";
    private static final String[] COLUMNS = { KEY_ID, KEY_NAME, KEY_AVATAR, KEY_FCOLOR,
            KEY_XPCOUNT, KEY_HPCOUNT, KEY_SPCOUNT, KEY_SKILLTIMES};


    public SQLiteDatabaseHandler(Context context)
    {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db)
    {
        String CREATION_TABLE = "CREATE TABLE " + TABLE_NAME + " ( " +
                KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                KEY_NAME  + " TEXT, " +
                KEY_AVATAR + " INTEGER, " +
                KEY_FCOLOR + " INTEGER, " +
                KEY_XPCOUNT + " INTEGER, " +
                KEY_HPCOUNT + " INTEGER, " +
                KEY_SPCOUNT + " INTEGER )";

        db.execSQL(CREATION_TABLE);
    }



    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
    {
        // you can implement here migration process
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        this.onCreate(db);
    }


//    public void deleteOne(Player player) {
//        // Get reference to writable DB
//        SQLiteDatabase db = this.getWritableDatabase();
//        db.delete(TABLE_NAME, "id = ?", new String[] { String.valueOf(player.getId()) });
//        db.close();
//    }
//
//    public Player getPlayer(int id) {
//        SQLiteDatabase db = this.getReadableDatabase();
//        Cursor cursor = db.query(TABLE_NAME, // a. table
//                COLUMNS, // b. column names
//                " id = ?", // c. selections
//                new String[] { String.valueOf(id) }, // d. selections args
//                null, // e. group by
//                null, // f. having
//                null, // g. order by
//                null); // h. limit
//
//        if (cursor != null)
//            cursor.moveToFirst();
//
//        Player player = new Player();
//        player.setId(Integer.parseInt(cursor.getString(0)));
//        player.setName(cursor.getString(1));
//        player.setPosition(cursor.getString(2));
//        player.setHeight(Integer.parseInt(cursor.getString(3)));
//
//        return player;
//    }
//
//    public List<Player> allPlayers() {
//
//        List<Player> players = new LinkedList<Player>();
//        String query = "SELECT  * FROM " + TABLE_NAME;
//        SQLiteDatabase db = this.getWritableDatabase();
//        Cursor cursor = db.rawQuery(query, null);
//        Player player = null;
//
//        if (cursor.moveToFirst()) {
//            do {
//                player = new Player();
//                player.setId(Integer.parseInt(cursor.getString(0)));
//                player.setName(cursor.getString(1));
//                player.setPosition(cursor.getString(2));
//                player.setHeight(Integer.parseInt(cursor.getString(3)));
//                players.add(player);
//            } while (cursor.moveToNext());
//        }
//
//        return players;
//    }
//
//    public void addPlayer(Player player) {
//        SQLiteDatabase db = this.getWritableDatabase();
//        ContentValues values = new ContentValues();
//        values.put(KEY_NAME, player.getName());
//        values.put(KEY_POSITION, player.getPosition());
//        values.put(KEY_HEIGHT, player.getHeight());
//        // insert
//        db.insert(TABLE_NAME,null, values);
//        db.close();
//    }
//
//    public int updatePlayer(Player player) {
//        SQLiteDatabase db = this.getWritableDatabase();
//        ContentValues values = new ContentValues();
//        values.put(KEY_NAME, player.getName());
//        values.put(KEY_POSITION, player.getPosition());
//        values.put(KEY_HEIGHT, player.getHeight());
//
//        int i = db.update(TABLE_NAME, // table
//                values, // column/value
//                "id = ?", // selections
//                new String[] { String.valueOf(player.getId()) });
//
//        db.close();
//
//        return i;
//    }
}