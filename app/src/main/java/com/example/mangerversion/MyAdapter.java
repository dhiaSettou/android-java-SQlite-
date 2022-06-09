package com.example.mangerversion;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

public class MyAdapter extends BaseAdapter {

    Context context;
    ArrayList<Trip> arrayList;

    public MyAdapter(Context context, ArrayList<Trip> arrayList) {
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
        view = inflater.inflate(R.layout.manger_row, null);

        TextView t1_agency = (TextView) view.findViewById(R.id.agency);
        TextView t2_destination = (TextView) view.findViewById(R.id.destination);
        TextView t3_Date = (TextView) view.findViewById(R.id.date);
        TextView t4_Time = (TextView) view.findViewById(R.id.time);
        TextView t5_available = (TextView) view.findViewById(R.id.available);
        TextView t6_price = (TextView) view.findViewById(R.id.Price);
        Button delete_btn = (Button) view.findViewById(R.id.delete);
        Trip trip = arrayList.get(i);

        t1_agency.setText(trip.getAgency());
        t2_destination.setText("Station :" + trip.getDestination());
        t3_Date.setText("Date: " + trip.getDate());
        t4_Time.setText("At : " + trip.getTime());
        t5_available.setText("available places: " + String.valueOf(trip.getAvailable()));
        t6_price.setText("Price: " + String.valueOf(trip.getPrice())+"DA");
        delete_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            db.delete(String.valueOf(trip.getId()))
            ;
            }
        });


        return view;
    }
}
