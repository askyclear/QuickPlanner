package com.example.seon.like;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteDatabase.CursorFactory;

/**
 * Created by Seon on 2015-08-17.
 */
public class TimeDBHelper {
    private static final String DATABASE_NAME = "timesDB.db";
    private static final int DATABASE_VERSION = 1;
    private static final String _TABLE_NAME = "times";
    private static final String _Id = "_id";
    public static SQLiteDatabase mDB;
    private DatabaseHelper mDBHelper;
    private Context mCtx;
    public class DatabaseHelper extends SQLiteOpenHelper {
        public DatabaseHelper(Context context, String name,
                              CursorFactory factory, int version){
            super(context, name, factory, version);

        }
        @Override
        public void onCreate(SQLiteDatabase db) {
            db.execSQL("create table "+_TABLE_NAME + "("
                    +_Id + " INTEGER NOT NULL, " +
                    "title TEXT NOT NULL, " +
                    "detail TEXT NOT NULL);");
            for(int i = 0; i<24; i++) {
                db.execSQL("INSERT INTO " + _TABLE_NAME + " (_id, title, detail)" + "VALUES (" + i + ",'','')");
            }
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            db.execSQL("DROP TABLE IF EXISTS "+_TABLE_NAME);
            onCreate(db);
        }
    }
    public TimeDBHelper(Context context){
        this.mCtx = context;
    }
    public TimeDBHelper open() throws SQLException {
        mDBHelper = new DatabaseHelper(mCtx, DATABASE_NAME, null, DATABASE_VERSION);
        mDB = mDBHelper.getWritableDatabase();
        return this;
    }

    public void close(){
        mDB.close();
    }
    //insert
    public long insertTitle(String title){
        //insert ex)insertColumn(String title, String contact)
        ContentValues values = new ContentValues();// all
        values.put("title", title);//setting
        return mDB.insert(_TABLE_NAME, null, values);//db insert
    }
    //delete
    public boolean deleteTitle(long rowID){
        return mDB.delete(_TABLE_NAME,
                _Id + "=" +rowID,null) > 0;
    }
    //update
    public boolean updateTitle(long rowID, String title){
        ContentValues values = new ContentValues();
        values.put(DataBases.CreateDB.TITLE, title);
        return mDB.update(_TABLE_NAME,
                values, _Id + "=" + rowID, null) > 0;
    }
    //call All DataBase;
    public Cursor getAllColumn(){
        return mDB.query(_TABLE_NAME, null, null, null, null, null, null);
    }
    //All delete
    public void allDelete(){
        mDB.delete(_TABLE_NAME,null,null);
    }
}
