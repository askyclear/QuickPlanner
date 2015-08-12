package com.example.seon.like;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteDatabase.CursorFactory;

import java.sql.SQLException;

/**
 * Created by Seon on 2015-08-09.
 */
public class QuickDBHelper{
    private static final String DATABASE_NAME = "quick.db";
    private static final int DATABASE_VERSION = 1;
    public static SQLiteDatabase mDB;
    private DatabaseHelper mDBHelper;
    private Context mCtx;

    public class DatabaseHelper extends SQLiteOpenHelper{
        public DatabaseHelper(Context context, String name,
                              CursorFactory factory, int version){
            super(context,name,factory,version);

        }
        @Override
        public void onCreate(SQLiteDatabase db) {
            db.execSQL(DataBases.CreateDB._CREATE);
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            db.execSQL("DROP TABLE IF EXISTS "+DataBases.CreateDB._TABLENAME);
            onCreate(db);
        }
    }

    public QuickDBHelper(Context context){
        this.mCtx = context;
    }

    public QuickDBHelper open() throws SQLException{
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
        values.put(DataBases.CreateDB.TITLE, title);//setting
        return mDB.insert(DataBases.CreateDB._TABLENAME, null, values);//db insert
    }
    //delete
    public boolean deleteTitle(long rowID){
        return mDB.delete(DataBases.CreateDB._TABLENAME,
                            DataBases.CreateDB._ID + "=" +rowID,null) > 0;
    }
    //update
    public boolean updateTitle(long rowID, String title){
        ContentValues values = new ContentValues();
        values.put(DataBases.CreateDB.TITLE, title);
        return mDB.update(DataBases.CreateDB._TABLENAME,
                values,  DataBases.CreateDB._ID+ "=" +rowID,null) > 0;
    }


}