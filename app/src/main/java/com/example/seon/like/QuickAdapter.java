package com.example.seon.like;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Seon on 2015-08-07.
 */
public class QuickAdapter extends BaseAdapter {
    private ArrayList<String> m_list;
    public QuickAdapter(){

        m_list = new ArrayList<String>();
    }

    @Override
    public int getCount() {
        return m_list.size();
    }

    @Override
    public Object getItem(int position) {

        return m_list.get(position);
    }

    @Override
    public long getItemId(int position) {

        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final int pos = position;
        final Context context = parent.getContext();
        if(convertView==null){
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.quick_lv,parent,false);

            TextView title = (TextView)convertView.findViewById(R.id.quick_title);
            title.setText(m_list.get(position));
        }
        return convertView;
    }
    public void add(String _msg) {
        m_list.add(_msg);
    }
    public void remove(int _position) {
        m_list.remove(_position);
    }
    public void setItem(int _position, String str){
        m_list.set(_position, str);
    }


}
