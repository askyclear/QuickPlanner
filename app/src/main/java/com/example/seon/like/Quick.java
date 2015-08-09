package com.example.seon.like;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

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
        final View view = inflater.inflate(R.layout.quick, null);
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
                LayoutInflater li = LayoutInflater.from(mContext);
                View promptView = li.inflate(R.layout.customalertdialog, null);

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
                            //mAdapter.getItemId(0) = text.getText().toString();
                            Toast.makeText(mContext, "onClickListen", Toast.LENGTH_SHORT);
                        }
                    })
                    .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
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