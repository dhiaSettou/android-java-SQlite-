package com.example.mangerversion;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.InputType;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class addTrip extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    ImageView imageView;
    Button pickbtn;
    //time picker
    EditText time_in;
    EditText date_in;
    Button save;
    Spinner spinner;
    Spinner spinner2;
    EditText passengers , price;
    Db_helper db =  new Db_helper(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_trip);
        this.setTitle("Add new trip");
        passengers =findViewById(R.id.passengers_num);
        price =findViewById(R.id.price);
        spinner = findViewById(R.id.spinner5);
        spinner2 = findViewById(R.id.spinner6);
        save = findViewById(R.id.save);


        //time picker
        time_in = findViewById(R.id.time_input);
        time_in.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showTimeDialog(time_in);
            }

            private void showTimeDialog(final EditText time_in) {
                final Calendar calendar = Calendar.getInstance();

                TimePickerDialog.OnTimeSetListener timeSetListener = new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        calendar.set(Calendar.HOUR_OF_DAY, hourOfDay);
                        calendar.set(Calendar.MINUTE, minute);
                        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm");
                        time_in.setText(simpleDateFormat.format(calendar.getTime()));
                        System.out.println(time_in.getText().toString());

                    }
                };

                new TimePickerDialog(addTrip.this, timeSetListener, calendar.get(Calendar.HOUR_OF_DAY), calendar.get(Calendar.MINUTE), false).show();
            }

        });
        //image picker
        pickbtn = findViewById(R.id.pickbtn);
        imageView = findViewById(R.id.image);
        ActivityResultLauncher<Intent> resultLauncher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                new ActivityResultCallback<ActivityResult>() {
                    @Override
                    public void onActivityResult(ActivityResult result) {
                        Intent intent = result.getData();

                        if (intent != null) {

                            try {
                                Bitmap bitmap = MediaStore.Images.Media.getBitmap(
                                        getContentResolver(), intent.getData()
                                );
                                imageView.setImageBitmap(bitmap);
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                });
        pickbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent1 = new Intent(Intent.ACTION_PICK);
                intent1.setType("image/*");
                resultLauncher.launch(intent1);
            }
        });


        ArrayAdapter<CharSequence> adapter2 = ArrayAdapter.createFromResource(this,
                R.array.company, android.R.layout.simple_spinner_item);
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter2);
        String agencyy = spinner.getItemAtPosition(spinner.getSelectedItemPosition()).toString();


        ArrayAdapter<CharSequence> adapter3 = ArrayAdapter.createFromResource(this,
                R.array.wilaya, android.R.layout.simple_spinner_item);
        adapter3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner2.setAdapter(adapter3);
        spinner2.setOnItemSelectedListener(this);

        date_in = findViewById(R.id.date_input2);
        date_in.setInputType(InputType.TYPE_NULL);
        date_in.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDateDialog(date_in);

            }

            private void showDateDialog(final EditText date_in) {
                final Calendar calendar = Calendar.getInstance();
                DatePickerDialog.OnDateSetListener dateSetListener = new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        calendar.set(Calendar.YEAR, year);
                        calendar.set(Calendar.MONTH, month);
                        calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yy-MM-dd");
                        date_in.setText(simpleDateFormat.format(calendar.getTime()));


                    }
                };

                new DatePickerDialog(addTrip.this, dateSetListener, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });

    }
    public void btn_add2(View view) {
        String agencyName = spinner.getItemAtPosition(spinner.getSelectedItemPosition()).toString();
        String destinationTo = spinner2.getItemAtPosition(spinner2.getSelectedItemPosition()).toString();
        String dateOfTrip = date_in.getText().toString();
        String timeOfTrip = time_in.getText().toString();
        Integer passengerNumber = Integer.parseInt(passengers.getText().toString());
        Integer priceOfTrip = Integer.parseInt(price.getText().toString());
        System.out.println(agencyName+destinationTo+dateOfTrip+timeOfTrip+passengerNumber+priceOfTrip);

        if (agencyName.equals("") || destinationTo.equals("") || dateOfTrip.equals("")|| timeOfTrip.equals("")|| passengerNumber.equals("")|| priceOfTrip.equals(""))
            Toast.makeText(addTrip.this, "Please enter all the fields", Toast.LENGTH_SHORT).show();
        else {

        Boolean result = db.addTrip(agencyName,destinationTo,dateOfTrip,timeOfTrip,passengerNumber,priceOfTrip);
        if (result == true) {
            Toast.makeText(addTrip.this, "Tripe added successfully", Toast.LENGTH_SHORT).show();

            if (result == false) {
                Toast.makeText(addTrip.this, "Error", Toast.LENGTH_SHORT).show();

            }
        }
            date_in.setText("");
            time_in.setText("");
            passengers.setText("");
            price.setText("");
            spinner.setAdapter(null);
            spinner2.setAdapter(null);
            Intent intent = new Intent(getApplicationContext(),MangerHome.class);
            startActivity(intent);

    }


    }


    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }

}