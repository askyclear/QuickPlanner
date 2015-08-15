package com.example.seon.like;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.BounceInterpolator;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.baoyz.swipemenulistview.SwipeMenu;
import com.baoyz.swipemenulistview.SwipeMenuCreator;
import com.baoyz.swipemenulistview.SwipeMenuItem;
import com.baoyz.swipemenulistview.SwipeMenuListView;

import java.sql.SQLException;
import java.util.ArrayList;

@SuppressLint("ValidFragment")
public class Quick extends Fragment{
    Context mContext;
    private QuickAdapter mAdapter;
    public Quick(Context context) {
        mContext = context;
    }
    ArrayList<QuickList> mQuickList = null;
    private Cursor cursor;
    private SQLiteDatabase db;
    private QuickDBHelper mQuickDBHelper;
    private QuickList quicklist;
    private SwipeMenuListView quick_lv;
    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.quick, null);
        quick_lv = (SwipeMenuListView)view.findViewById(R.id.quick_lv);
        Button quick_add_bt = (Button)view.findViewById(R.id.quick_add_bt);
        mQuickList = new ArrayList<QuickList>();

        mQuickDBHelper = new QuickDBHelper(mContext);
        try {
            mQuickDBHelper.open();
        } catch (SQLException e) {
            e.printStackTrace();
        }


        cursorToArray();
        //create MenuCreator
        SwipeMenuCreator creator = new SwipeMenuCreator() {
            @Override
            public void create(SwipeMenu menu) {
              /*  // create "open" item
                SwipeMenuItem openItem = new SwipeMenuItem(
                        mContext);
                // set item background
                openItem.setBackground(new ColorDrawable(Color.rgb(0xC9, 0xC9,
                        0xCE)));
                // set item width
                openItem.setWidth(dp2px(90));
                // set item title
                openItem.setTitle("Open");
                // set item title fontsize
                openItem.setTitleSize(18);
                // set item title font color
                openItem.setTitleColor(Color.WHITE);
                // add to menu
                menu.addMenuItem(openItem);*/

                // create "delete" item
                SwipeMenuItem deleteItem = new SwipeMenuItem(
                        mContext);
                // set item background
                deleteItem.setBackground(new ColorDrawable(Color.rgb(0xF9,
                        0x3F, 0x25)));
                // set item width
                deleteItem.setWidth(dp2px(90));
                // set a icon
                deleteItem.setIcon(R.drawable.ic_delete);
                // add to menu
                menu.addMenuItem(deleteItem);
            }
        };
        //set creator
        quick_lv.setMenuCreator(creator);
        mAdapter = new QuickAdapter(mContext,mQuickList);
        quick_lv.setAdapter(mAdapter);
        quick_lv.setOnMenuItemClickListener(new SwipeMenuListView.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(int position, SwipeMenu menu, int index) {
                switch (index) {
                    case 1:
                        // open
                        //open(item);
                        break;
                    case 0:
                        // delete
//					delete(item);
                        boolean result = mQuickDBHelper.deleteTitle(position);
                        //
                        if (result) {
                            mQuickList.remove(position);
                            mAdapter.setArrayList(mQuickList);
                            mAdapter.notifyDataSetChanged();
                            Toast.makeText(mContext, "delete complete", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(mContext, "delete failed", Toast.LENGTH_SHORT).show();
                        }
                        break;
                }
                return false;
            }
        });
        //Right
        //quick_lv.setSwipeDirection(SwipeMenuListView.DIRECTION_RIGHT);

        //left
        quick_lv.setSwipeDirection(SwipeMenuListView.DIRECTION_LEFT);
        //smooth
        //quick_lv.setCloseInterpolator(new BounceInterpolator());
        //title add
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
                        //if add push, arraylist add
                        public void onClick(DialogInterface dialog, int which) {
                            EditText et = (EditText) promptView.findViewById(R.id.editTextDialogUserInput);
                            mQuickDBHelper.insertTitle(et.getText().toString().trim());//db add
                            Toast.makeText(mContext, et.getText().toString().trim(), Toast.LENGTH_SHORT).show();
                            mQuickList.clear();
                            cursorToArray();
                            mAdapter.setArrayList(mQuickList);
		                	mAdapter.notifyDataSetChanged();
			                cursor.close();

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
        mQuickDBHelper.close();
        return view;
    }
    //db data input arraylist
    private void cursorToArray() {

        cursor = null;
        cursor = mQuickDBHelper.getAllColumn();

        while (cursor.moveToNext()) {
            quicklist = new QuickList(
                    cursor.getString(cursor.getColumnIndex("title")), "", "");
            mQuickList.add(quicklist);
        }
        cursor.close();
    }
    //delete
    /*private void deletePosition() {
        LayoutInflater li = LayoutInflater.from(mContext);
        final View promptView = li.inflate(R.layout.delete_messge, null);

        AlertDialog.Builder ab = new AlertDialog.Builder(mContext);
        ab.setView(promptView);
        ab
                .setCancelable(true)
                .setTitle("Quick add")
                .setMessage("Please input title")
                .setPositiveButton("Add", new DialogInterface.OnClickListener() {
                    @Override
                    //if add push, arraylist add
                    public void onClick(DialogInterface dialog, int which) {

                        //
                        QuickList mquicklist = mQuickList.get(selectedPos);
                        int id =  mquicklist.getId();
                        boolean result = mQuickDBHelper.deleteTitle(id);


                        //
                        if (result) {
                            mQuickList.remove(selectedPos);
                            mAdapter.setArrayList(mQuickList);
                            mAdapter.notifyDataSetChanged();
                            Toast.makeText(mContext, "delete complete", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(mContext, "delete failed", Toast.LENGTH_SHORT).show();
                        }

                    }
                })
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(mContext, "Cancel~~~~", Toast.LENGTH_SHORT).show();
                        dialog.cancel();
                    }
                });

        //AlertDialog dialog = ab.create();
        //dialog.show();
        ab.show();
    }*/
    private int dp2px(int dp) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp,
                getResources().getDisplayMetrics());
    }

}