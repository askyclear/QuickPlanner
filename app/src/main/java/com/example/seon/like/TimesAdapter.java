package com.example.seon.like;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by Seon on 2015-08-18.
 */
public class TimesAdapter extends BaseAdapter{
    private ArrayList<TimeList> mArrayList;
    private LayoutInflater inflater;
    private ViewHolder viewHolder;
    private Context mContext;
    //
    public TimesAdapter(Context context, ArrayList<TimeList> arrays){
        this.mContext = context;
        this.inflater = LayoutInflater.from(context);
        this.mArrayList = arrays;
    }
    //Adapter list Conunt
    @Override
    public int getCount() {
        return mArrayList.size();
    }
    //Item position get
    @Override
    public TimeList getItem(int position) {
        return mArrayList.get(position);
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
            convertView = inflater.inflate(R.layout.times_lv,parent,false );
            /*TextView quick_title= (TextView)convertView.findViewById(R.id.quick_title);
            quick_title.setText(mQuickList.get(pos).getTitle());
            Button quick_time = (Button)convertView.findViewById(R.id.quick_time);
            Button quick_time_add = (Button)convertView.findViewById(R.id.quick_add_bt);
            Button quick_check = (CheckBox)convertView.findViewById(R.id.quick_check);*/
            viewHolder = new ViewHolder();
            viewHolder.times_title = (TextView)convertView.findViewById(R.id.times_title);
            viewHolder.times_time = (TextView)convertView.findViewById(R.id.times_time);
            convertView.setTag(viewHolder);
        }else {
            viewHolder = (ViewHolder)convertView.getTag();
        }
        viewHolder.times_title.setText(getItem(pos).getTitle());
        viewHolder.times_time.setText(getItem(pos).getId()+"");
        return convertView;
    }

    public void setArrayList(ArrayList<TimeList> arrays){
        this.mArrayList = arrays;
    }

    public ArrayList<TimeList> getArrayList(){
        return mArrayList;
    }

    private View.OnClickListener buttonClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            /*switch (v.getId()) {
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
            }*/
        }
    };
    class ViewHolder{
        public TextView times_title = null;
        public TextView times_time = null;
    }

    @Override
    protected void finalize() throws Throwable {
        free();
        super.finalize();
    }

    private void free(){
        inflater = null;
        mArrayList = null;
        viewHolder = null;
        mContext = null;
    }
}