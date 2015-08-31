package com.example.seon.like;

import android.annotation.SuppressLint;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

@SuppressLint("ValidFragment")
public class Times extends Fragment {
    Context mContext;
    private TimesAdapter mTimesAdapter;
    long now;
    TimeDBHelper mTimeDBHelper;
    ArrayList<TimeList> mArrayList;
    TimeList timelist;
    public Times(Context context) {
        mContext = context;
    }
    int[] deleted;
    String[] description;
    long[] dtstart;
    long[] dtend;
    String[] title;
    int today_counter;
    private String[] today_titles;
    private int[] today_deleteds;
    private String[] today_descriptions;
    private long[] today_dtstarts;
    private long[] today_dtends;
    Cursor cursor;
    String[] projectionEvents = new String[]{
            "deleted",//event deleted?
            "description",//protect
            "dtstart",//event start time
            "dtend",//event end time
            "duration",//duration
            "eventTimezone",
            "lastDate",
            "title"
    };//call list

    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.times, null);

        ListView times_lv = (ListView)view.findViewById(R.id.times_lv);
        TextView date_today = (TextView)view.findViewById(R.id.date_today);
        mArrayList = new ArrayList<TimeList>();
        mTimeDBHelper = new TimeDBHelper(mContext);
        mTimeDBHelper.open();
        //current time call
        now = System.currentTimeMillis();
        //calender events call
        Uri events		= Uri.parse("content://com.android.calendar/events");
        int counter = 0;
        //uri call events;
        cursor = mContext.getContentResolver().query(events, projectionEvents, null, null,null);
        if(cursor.moveToFirst()){
            deleted = new int[cursor.getCount()];
            description = new String[cursor.getCount()];
            dtstart = new long[cursor.getCount()];
            dtend = new long[cursor.getCount()];
            title = new String[cursor.getCount()];
            do{
                deleted[counter] = cursor.getInt(0);
                description[counter] = cursor.getString(1);
                dtstart[counter] = cursor.getLong(2);
                dtend[counter] = cursor.getLong(3);
                title[counter] = cursor.getString(7);
                counter++;
            }while(cursor.moveToNext());
        }
        today_deleteds = new int[100];
        today_dtstarts = new long[100];
        today_dtends = new long[100];
        today_titles = new String[100];
        today_descriptions = new String[100];
        long kor_gtm = 9*1000*60*60;
        today_counter = 0;
        for(int i = 0; i <counter; i++){
            if((now/1000/60/60)/24 == (dtstart[i]/1000/60/60+9)/24){
                today_deleteds[today_counter] = deleted[i];
                today_descriptions[today_counter] = description[i];
                today_dtstarts[today_counter] = dtstart[i]+kor_gtm;
                today_dtends[today_counter] = dtend[i]+kor_gtm;
                today_titles[today_counter] = title[i];
                today_counter++;
            }
        }
        cursorToArray();
        //db All calrum call
        //Adapter ArrayList set
        mTimesAdapter = new TimesAdapter(mContext,mArrayList);
        //ListView Adapter set
        times_lv.setAdapter(mTimesAdapter);



        return view;
    }
    private void cursorToArray() {

        cursor = null;
        cursor = mTimeDBHelper.getAllColumn();

        while (cursor.moveToNext()) {
            int _id = cursor.getInt(cursor.getColumnIndex("_id"));
            String _title = cursor.getString(cursor.getColumnIndex("title"));
            int k = 0;
            for(int j = 0;j<today_counter;j++) {
                if ((today_dtstarts[j] / 1000 / 60 / 60) % 24 == _id) {
                    timelist = new TimeList(_id,
                            _title+today_titles, "", "");
                    k++;
                }
            }
            if(k==0){
                timelist = new TimeList(_id,
                        _title, "", "");
            }
            mArrayList.add(timelist);
        }
        cursor.close();
    }


}