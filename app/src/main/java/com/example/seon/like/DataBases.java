package com.example.seon.like;

import android.provider.BaseColumns;

/**
 * Created by Seon on 2015-08-11.
 */
public final  class DataBases {
    public static final class CreateDB implements BaseColumns{
        public static final String TITLE = "title";
        public static final String _TABLENAME = "quick";
        public static final String _Id = "_id";
        public static final String _Time = "time";
        public static final String _CREATE =
                "create table "+_TABLENAME + "("
                                +_Id + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                                +TITLE+" TEXT NOT NULL, "
                                +_Time+" INTEGER);";
    }
}
