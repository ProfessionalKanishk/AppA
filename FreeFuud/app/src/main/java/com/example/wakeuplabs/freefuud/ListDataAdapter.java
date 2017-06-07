package com.example.wakeuplabs.freefuud;

import android.content.Context;
import android.provider.ContactsContract;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Anjana on 6/7/2017.
 */

public class ListDataAdapter extends ArrayAdapter {

    List list = new ArrayList();
    public ListDataAdapter(@NonNull Context context, @LayoutRes int resource) {
        super(context, resource);
    }

    static class LayoutHandler{

        TextView EVENTS,FOOD,LOCATION,TIME;
    }

    @Override
    public void add(Object object){
        super.add(object);
        list.add(object);
    }

    @Override
    public int getCount(){
        return list.size();
    }

    @Override
    public Object getItem(int position){
        return list.get(position);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        View row = convertView;
        LayoutHandler layoutHandler;
        if(row == null){
            LayoutInflater layoutInflater = (LayoutInflater)this.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            row = layoutInflater.inflate(R.layout.raw_layout,parent,false);
            layoutHandler = new LayoutHandler();
            layoutHandler.EVENTS = (TextView)row.findViewById(R.id.text_user_event);
            layoutHandler.FOOD = (TextView)row.findViewById(R.id.text_user_food);
            layoutHandler.LOCATION = (TextView)row.findViewById(R.id.text_user_location);
            layoutHandler.TIME = (TextView)row.findViewById(R.id.text_user_time);
            row.setTag(layoutHandler);
        }
        else{
            layoutHandler = (LayoutHandler)row.getTag();


        }
        DataProvider dataProvider = (DataProvider)this.getItem(position);
        layoutHandler.EVENTS.setText(dataProvider.getEvents());
        layoutHandler.FOOD.setText(dataProvider.getFood());
        layoutHandler.LOCATION.setText(dataProvider.getLocation());
        layoutHandler.TIME.setText(dataProvider.getTime());
        return super.getView(position, convertView, parent);
    }


}
