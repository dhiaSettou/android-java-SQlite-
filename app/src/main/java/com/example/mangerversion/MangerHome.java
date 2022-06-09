package com.example.mangerversion;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.InputType;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

public class    MangerHome extends AppCompatActivity implements AdapterView.OnItemSelectedListener{
    EditText date_in;
    Button edit_btn;
    ListView listView;
    Db_helper db =  new Db_helper(this);
    ArrayList<Trip> arrayList;
    MyAdapter myAdapter;
    FloatingActionButton add ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_manger_home);

        listView = findViewById(R.id.listView);
        arrayList = new ArrayList<>();
          loadData();
        myAdapter.notifyDataSetChanged();

        this.setTitle("Available trips");
        add=findViewById(R.id.add);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MangerHome.this,addTrip.class);
                startActivity(i);
            }
        });




        Spinner spinner = findViewById(R.id.spinner1);
        Spinner spinner2 = findViewById(R.id.spinner2);
        date_in=findViewById(R.id.date_input);

        date_in.setInputType(InputType.TYPE_NULL);
        date_in.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDateDialog(date_in);

            }
            private void showDateDialog(final EditText date_in) {
                final Calendar calendar=Calendar.getInstance();
                DatePickerDialog.OnDateSetListener dateSetListener=new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        calendar.set(Calendar.YEAR,year);
                        calendar.set(Calendar.MONTH,month);
                        calendar.set(Calendar.DAY_OF_MONTH,dayOfMonth);
                        SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yy-MM-dd");
                        date_in.setText(simpleDateFormat.format(calendar.getTime()));

                    }
                };

                    new DatePickerDialog(MangerHome.this,dateSetListener,calendar.get(Calendar.YEAR),calendar.get(Calendar.MONTH),calendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });

        ArrayAdapter<CharSequence> adapter2 = ArrayAdapter.createFromResource(this,
                R.array.company, android.R.layout.simple_spinner_item);
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter2);
        spinner.setOnItemSelectedListener(this);


        ArrayAdapter<CharSequence> adapter3 = ArrayAdapter.createFromResource(this,
                R.array.wilaya, android.R.layout.simple_spinner_item);
        adapter3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner2.setAdapter(adapter3);
        spinner2.setOnItemSelectedListener(this);


//        MyAdapter adapter = new MyAdapter(this, from, to, images,dateTime,company,price);


    }

    public void loadData() {
        arrayList = db.getData();
        myAdapter = new MyAdapter(this,arrayList);
        listView.setAdapter(myAdapter);
        myAdapter.notifyDataSetChanged();

    }



    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

    }
    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
 //    class MyAdapter extends ArrayAdapter<String> {
//
//        Context context;
//        String from[];
//        String to[];
//        int imgs[];
//        String dateTime[];
//        String company[];
//        String price[];
//
//
//
//        MyAdapter (Context c, String from[], String to[], int imgs[],String dateTime[],String company[],String price[]) {
//            super(c, R.layout.row2, R.id.textView1, from);
//            this.context = c;
//            this.from = from;
//            this.to = to;
//            this.imgs = imgs;
//            this.dateTime=dateTime;
//            this.company=company;
//            this.price=price;
//
//
//
//
//        }
//
//        @NonNull
//        @Override
//        public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
//            LayoutInflater layoutInflater = (LayoutInflater)getApplicationContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
//            View row2 = layoutInflater.inflate(R.layout.row2, parent, false);
//            ImageView images = row2.findViewById(R.id.image);
//            TextView myTitle = row2.findViewById(R.id.textView1);
//            TextView myDescription = row2.findViewById(R.id.textView2);
//            TextView myDateTime = row2.findViewById(R.id.textView3);
//            TextView mycompany = row2.findViewById(R.id.textView4);
//            TextView myprice = row2.findViewById(R.id.textView5);
//
//
//
//
//
//            // now set our resources on views
//            images.setImageResource(imgs[position]);
//            myTitle.setText(from[position]);
//            myDescription.setText(to[position]);
//            myDateTime.setText(dateTime[position]);
//            mycompany.setText(company[position]);
//            myprice.setText(price[position]);
//
//
//
//
//
//
//
//            return row2;
//        }
//    }


};