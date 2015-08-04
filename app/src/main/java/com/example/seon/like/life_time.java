package com.example.seon.like;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

/**
 * Created by Seon on 2015-07-27.
 */
public class life_time extends Activity implements OnClickListener{
    int[] deleted;
    String[] description;
    long[] dtstart;
    long[] dtend;
    String[] title;
    private String[] today_titles;
    private int[] today_deleteds;
    private String[] today_descriptions;
    private long[] today_dtstarts;
    private long[] today_dtends;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.life_time);

        long now = System.currentTimeMillis();//현재 시간 호출

        String[] projectionEvents = new String[] {
                "deleted",
                "description",
                "dtstart",
                "dtend",
                "duration",
                "eventTimezone",
                "lastDate",
                "title",
        };//캘린더에서 가져올 목록
        int counter = 0;
        //Uri calendars	= Uri.parse("content://com.android.calendar/calendars");//캘린더 가져오기
        Uri events		= Uri.parse("content://com.android.calendar/events");//일정 을 검색


        Cursor c = getContentResolver().query(events, projectionEvents, null, null,null);
        if(c.moveToFirst()){
            deleted = new int[c.getCount()];
            description = new String[c.getCount()];
            dtstart = new long[c.getCount()];
            dtend = new long[c.getCount()];
            title = new String[c.getCount()];
            do{
                deleted[counter] = c.getInt(0);
                description[counter] = c.getString(1);
                dtstart[counter] = c.getLong(2);
                dtend[counter] = c.getLong(3);
                title[counter] = c.getString(7);
                counter++;
            }while(c.moveToNext());
        }
        today_deleteds = new int[100];
        today_dtstarts = new long[100];
        today_dtends = new long[100];
        today_titles = new String[100];
        today_descriptions = new String[100];
        int today_counter=0;
        long kor_gtm = 9*1000*60*60;
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


        Button reTurn = (Button)findViewById(R.id.reTurn);
        Button chat_1 = (Button)findViewById(R.id.chat_1);

        reTurn.setOnClickListener(this);
        chat_1.setOnClickListener(this);
    }
    @Override
    public void onClick(View v){
            if(v.getId() == R.id.chat_1){
                Intent intent = new Intent(this,chat.class);
                startActivity(intent);
            }
            if(v.getId()==R.id.reTurn) {
                finish();
        }
    }


}
