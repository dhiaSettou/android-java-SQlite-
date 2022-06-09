package com.example.mangerversion;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
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
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

public class HomePage extends AppCompatActivity implements AdapterView.OnItemSelectedListener{
    EditText date_in;

    ListView listView;
    Db_helper db =  new Db_helper(this);
    ArrayList<Trip> arrayList;
    UserAdapter myAdapter;
    //    int images[] = {R.drawable.ccc, R.drawable.ggg, R.drawable.ttt, R.drawable.iii, R.drawable.ggg};
Button button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        this.setTitle("Available trips");

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listView = findViewById(R.id.listView);
        arrayList = new ArrayList<>();
        loadData();

//        button =findViewById(R.id.reserve);
//        button.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                openDialog();
//            }
//        });
//
//






        Spinner spinner = findViewById(R.id.spinner1);
        Spinner spinner2 = findViewById(R.id.spinner2);
        date_in=findViewById(R.id.date_input);
        date_in.setInputType(InputType.TYPE_NULL);
        date_in.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDateDialog(date_in);

            }
            private void showDateDialog(final EditText date_in)  {
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

                new DatePickerDialog(HomePage.this,dateSetListener,calendar.get(Calendar.YEAR),calendar.get(Calendar.MONTH),calendar.get(Calendar.DAY_OF_MONTH)).show();
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

        listView = findViewById(R.id.listView);




    }
    public void loadData() {
        arrayList = db.getData();
        myAdapter = new UserAdapter(this,arrayList);
        listView.setAdapter(myAdapter);

    }
    public void showDialog(View v){
        AlertDialog.Builder alert = new AlertDialog.Builder (  this);
        alert.setTitle ("booking  successed");
        alert.setMessage ("your place is 12");

        alert.setPositiveButton( "Ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Toast.makeText(  HomePage. this,"Thank you", Toast.LENGTH_SHORT).show();
            }
            });

        alert.create().show();


    }
    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

    }
    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }

}