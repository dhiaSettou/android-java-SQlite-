package com.example.mangerversion;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

public class UserAdapter  extends BaseAdapter {
    Context context;
    ArrayList<Trip> arrayList;
    public UserAdapter(Context context, ArrayList<Trip> arrayList) {
        this.context = context;
        this.arrayList = arrayList;
    }
    @Override
    public int getCount() {
        return this.arrayList.size();
    }

    @Override
    public Object getItem(int i) {
        return arrayList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }
    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        Db_helper db =  new Db_helper(context);
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        view = inflater.inflate(R.layout.row, null);

        TextView t1_agency = (TextView) view.findViewById(R.id.agency1);
        TextView t2_destination = (TextView) view.findViewById(R.id.destination1);
        TextView t3_Date = (TextView) view.findViewById(R.id.date1);
        TextView t4_Time = (TextView) view.findViewById(R.id.time1);
        TextView t5_available = (TextView) view.findViewById(R.id.available1);
        TextView t6_price = (TextView) view.findViewById(R.id.price1);
        Trip trip = arrayList.get(i);

        t1_agency.setText(trip.getAgency());
        t2_destination.setText("Station :" + trip.getDestination());
        t3_Date.setText("Date: " + trip.getDate());
        t4_Time.setText("At : " + trip.getTime());
        t5_available.setText("available places: " + String.valueOf(trip.getAvailable()));
        t6_price.setText("Price: " + String.valueOf(trip.getPrice())+"DA");



        return view;
    }
}
