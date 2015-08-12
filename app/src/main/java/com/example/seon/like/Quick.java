package com.example.seon.like;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.sql.SQLException;
import java.util.ArrayList;

@SuppressLint("ValidFragment")
public class Quick extends Fragment {
    Context mContext;
    private QuickAdapter mAdapter;
    public Quick(Context context) {
        mContext = context;
    }
    ArrayList<QuickList> mQuickList = null;
    private Cursor cursor;
    private SQLiteDatabase db;
    private QuickDBHelper mQuickDBHelper;
    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.quick, null);
        ListView quick_lv = (ListView)view.findViewById(R.id.quick_lv);
        Button quick_add_bt = (Button)view.findViewById(R.id.quick_add_bt);
        mQuickList = new ArrayList<QuickList>();
        for(int i = 0; i<10; i++){
            mQuickList.add(new QuickList("title","",i+""));
            Log.i("list : ",mQuickList.toString());
        }
        mAdapter = new QuickAdapter(mContext,mQuickList);
        quick_lv.setAdapter(mAdapter);

        quick_add_bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LayoutInflater li = LayoutInflater.from(mContext);
                final View promptView = li.inflate(R.layout.customalertdialog, null);

                AlertDialog.Builder ab = new AlertDialog.Builder(mContext);
                ab.setView(promptView);
                //final EditText text = (EditText)promptView.findViewById(R.id.editTextDialogUserInput);

                ab
                    .setCancelable(true)
                        .setTitle("Quick add")
                        .setMessage("Please input title")
                    .setPositiveButton("Add", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            EditText et = (EditText) promptView.findViewById(R.id.editTextDialogUserInput);
                            //mQuickDBHelper.insertTitle(et.getText().toString().trim());//db add
                            Toast.makeText(mContext, et.getText().toString().trim(), Toast.LENGTH_SHORT).show();
                            /*mAdapter.setArrayList(mQuickList);
		                	mAdapter.notifyDataSetChanged();
			                Cursor.close();*/

                        }
                    })
                    .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            Toast.makeText(mContext, "Input canceled", Toast.LENGTH_SHORT).show();
                            dialog.cancel();
                        }
                    });

                //AlertDialog dialog = ab.create();
                //dialog.show();
                ab.show();
            }
        });
        return view;
    }
}