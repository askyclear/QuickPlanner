package com.example.seon.like;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by Seon on 2015-08-07.
 */
public class QuickAdapter extends BaseAdapter {
    private ArrayList<QuickList> mQuickList;
    private LayoutInflater inflater;
    private ViewHolder viewHolder;
    private Context mContext;
    //
    public QuickAdapter(Context context, ArrayList<QuickList> arrays){
        this.mContext = context;
        this.inflater = LayoutInflater.from(context);
        this.mQuickList = arrays;
    }
    //Adapter list Conunt
    @Override
    public int getCount() {
        return mQuickList.size();
    }
    //Item position get
    @Override
    public QuickList getItem(int position) {

        return mQuickList.get(position);
    }
    //Item position ID get
    @Override
    public long getItemId(int position) {

        return position;
    }
    //Row set
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final int pos = position;
        final Context context = parent.getContext();
        //View v = convertView;
        if(convertView == null){
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.quick_lv,parent,false );
            /*TextView quick_title= (TextView)convertView.findViewById(R.id.quick_title);
            quick_title.setText(mQuickList.get(pos).getTitle());
            Button quick_time = (Button)convertView.findViewById(R.id.quick_time);
            Button quick_time_add = (Button)convertView.findViewById(R.id.quick_add_bt);
            Button quick_check = (CheckBox)convertView.findViewById(R.id.quick_check);*/
            viewHolder = new ViewHolder();
            viewHolder.quick_title = (TextView)convertView.findViewById(R.id.quick_title);

            viewHolder.quick_time = (Button)convertView.findViewById(R.id.quick_time);
            viewHolder.quick_time_add = (Button)convertView.findViewById(R.id.quick_time_add);
            viewHolder.quick_check = (CheckBox)convertView.findViewById(R.id.quick_check);
            convertView.setTag(viewHolder);
        }else {
            viewHolder = (ViewHolder)convertView.getTag();
        }
        viewHolder.quick_title.setText(getItem(pos).getTitle());

        viewHolder.quick_time.setTag(pos);
        viewHolder.quick_time.setText(getItem(pos).getTime());
        viewHolder.quick_time.setOnClickListener(buttonClickListener);

        viewHolder.quick_time_add.setTag(pos);
        viewHolder.quick_time_add.setOnClickListener(buttonClickListener);

        viewHolder.quick_check.setTag(pos);
        viewHolder.quick_check.setOnClickListener(buttonClickListener);

        return convertView;
    }

    public void setArrayList(ArrayList<QuickList> arrays){
        this.mQuickList = arrays;
    }

    public ArrayList<QuickList> getArrayList(){
        return mQuickList;
    }

    private View.OnClickListener buttonClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {


                case R.id.quick_time:
                    Toast.makeText(
                            mContext,
                            "time Tag = " + v.getTag(),
                            Toast.LENGTH_SHORT
                    ).show();
                    break;


                case R.id.quick_time_add:
                    Toast.makeText(
                            mContext,
                            "time add Tag = " + v.getTag(),
                            Toast.LENGTH_SHORT
                    ).show();
                    break;

                // CheckBox
                case R.id.quick_check:
                    Toast.makeText(
                            mContext,
                            "Check Tag = " + v.getTag(),
                            Toast.LENGTH_SHORT
                    ).show();
                    break;

                default:
                    break;
            }
        }
    };


    class ViewHolder{
        public TextView quick_title = null;
        public Button quick_time = null;
        public Button quick_time_add = null;
        public CheckBox quick_check = null;
    }

    @Override
    protected void finalize() throws Throwable {
        free();
        super.finalize();
    }

    private void free(){
        inflater = null;
        mQuickList = null;
        viewHolder = null;
        mContext = null;
    }
}
