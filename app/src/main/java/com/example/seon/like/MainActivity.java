package com.example.seon.like;

import android.app.Activity;
import android.content.Context;
import android.support.v7.app.ActionBarActivity;
import android.content.Intent;
import android.os.Vibrator;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.SearchView;
import android.widget.Toast;


public class MainActivity extends Activity implements OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Button &#xd638;&#xcd9c;
        Button chat = (Button)findViewById(R.id.chat);
        Button life_time = (Button)findViewById(R.id.life_time);
        Button setting = (Button)findViewById(R.id.setting);
        Button close = (Button)findViewById(R.id.close);
        /* 진동 울리기
        Vibrator vibrator = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
        vibrator.vibrate(3000);*/
        //click envent
        chat.setOnClickListener(this);
        life_time.setOnClickListener(this);
        setting.setOnClickListener(this);
        close.setOnClickListener(this);
        Toast.makeText(this, "MainActivity Created", Toast.LENGTH_SHORT);
    }
    @Override
    public void onClick(View v){
        if(v.getId() == R.id.chat){
            Intent intent = new Intent(this,chat.class);
            startActivity(intent);
        }
        if(v.getId()==R.id.life_time) {
            Intent intent = new Intent(this,life_time.class);
            startActivity(intent);
        }
        if(v.getId()==R.id.setting){
            Intent intent = new Intent(this,Setting.class);
            startActivity(intent);
        }
        if(v.getId()==R.id.close){
            Toast.makeText(this,"Finsh~ Good Day~",Toast.LENGTH_LONG).show();
            finish();
        }
        }
}
