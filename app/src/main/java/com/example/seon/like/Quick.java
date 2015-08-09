package com.example.seon.like;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.FragmentManager;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

@SuppressLint("ValidFragment")
public class Quick extends Fragment {
    Context mContext;
    private QuickAdapter mAdapter;
    public Quick(Context context) {
        mContext = context;
    }

    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.quick, null);
        ListView quick_lv = (ListView)view.findViewById(R.id.quick_lv);
        Button quick_add_bt = (Button)view.findViewById(R.id.quick_add_bt);
        mAdapter = new QuickAdapter();
        quick_lv.setAdapter(mAdapter);
        mAdapter.add("Title");
        mAdapter.add("Title1");
        mAdapter.add("Title2");
        mAdapter.add("Title3");
        quick_add_bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fm = getSupportFragmentManager();
                DialogQuick  dialogFragment = new DialogQuick();
                dialogFragment.show(fm, "fragment_dialog_test");
            }
        });
        return view;
    }

}