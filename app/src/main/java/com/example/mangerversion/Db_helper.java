package com.example.mangerversion;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class Db_helper  extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "station.db";
    public static final int DATABASE_VERSION = 7;

    public Db_helper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
//        db.execSQL("create  table user ( id INTEGER PRIMARY KEY AUTOINCREMENT , Username Text, Email Text, password Text, Confirm_password Text)");
        String SQL_CREATE_ENTRIES = "CREATE TABLE " + AppContract.UserEntry.TABLE_NAME + " (" +
                AppContract.UserEntry._ID + " INTEGER PRIMARY KEY," +
                AppContract.UserEntry.COLUMN_USERNAME + " TEXT," +
                AppContract.UserEntry.COLUMN_PASSWORD + " TEXT)";


        String SQL_CREATE_ENTRIES2 = "CREATE TABLE " + AppContract.TripEntry.TABLE_NAME + " (" +
                AppContract.TripEntry._ID + " INTEGER PRIMARY KEY," +
                AppContract.TripEntry.COLUMN_TRIP_AGENCY + " TEXT," +
                AppContract.TripEntry.COLUMN_TRIP_DESTINATION + " TEXT," +
                AppContract.TripEntry.COLUMN_TRIP_DATE + " TEXT," +
                AppContract.TripEntry.COLUMN_TRIP_TIME + " TEXT," +
                AppContract.TripEntry.COLUMN_TRIP_PASSENGERS + " INTEGER," +
                AppContract.TripEntry.COLUMN_TRIP_PRICE + " INTEGER," +
                AppContract.TripEntry.COLUMN_TRIP_IMAGE + " TEXT)"
                ;
        db.execSQL(SQL_CREATE_ENTRIES);
        db.execSQL(SQL_CREATE_ENTRIES2);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + AppContract.UserEntry.TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + AppContract.TripEntry.TABLE_NAME);
        onCreate(db);
    }

    public boolean addUser(String userName ,String password) {

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put ( AppContract.UserEntry.COLUMN_USERNAME, userName);
        contentValues.put (  AppContract.UserEntry.COLUMN_PASSWORD, password);

        long result = db.insert (AppContract.UserEntry.TABLE_NAME, null, contentValues);

        if (result == -1)
            return false;
        else
            return true;
}
    public Boolean checkUsername(String username) {
        SQLiteDatabase db = this.getWritableDatabase();
        String SQL_CHECK_USERNAME = "Select * FROM " + AppContract.UserEntry.TABLE_NAME +" WHERE " +AppContract.UserEntry.COLUMN_USERNAME + " = ? " ;
        Cursor cursor = db.rawQuery(SQL_CHECK_USERNAME, new String[]{username});
        if (cursor.getCount() > 0)
            return true;
        else
            return false;
    }
    public Boolean checkAccount(String username, String password){
        SQLiteDatabase MyDB = this.getWritableDatabase();
        String SQL_CHECK_ACCOUNT = "Select * FROM " + AppContract.UserEntry.TABLE_NAME +" WHERE " +AppContract.UserEntry.COLUMN_USERNAME + " = ? and " +AppContract.UserEntry.COLUMN_PASSWORD+ " = ?";

        Cursor cursor = MyDB.rawQuery(SQL_CHECK_ACCOUNT, new String[] {username,password});
        System.out.println(cursor.getCount());
        if(cursor.getCount()>0)
            return true;
        else
            return false;
    }
    public boolean addTrip(String agency ,String destination,String date,String time,Integer passengers,Integer price) {

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put (AppContract.TripEntry.COLUMN_TRIP_AGENCY, agency);
        contentValues.put (AppContract.TripEntry.COLUMN_TRIP_DESTINATION, destination);
        contentValues.put (AppContract.TripEntry.COLUMN_TRIP_DATE, date);
        contentValues.put (AppContract.TripEntry.COLUMN_TRIP_TIME, time);
        contentValues.put (AppContract.TripEntry.COLUMN_TRIP_PASSENGERS, passengers);
        contentValues.put (AppContract.TripEntry.COLUMN_TRIP_PRICE, price);
        long result = db.insert (AppContract.TripEntry.TABLE_NAME, null, contentValues);
        if (result == -1)
            return false;
        else
            return true;
    }

    public ArrayList<Trip> getData(){
        ArrayList<Trip> arrayList = new ArrayList<> ();
        SQLiteDatabase db = this.getReadableDatabase ();

        Cursor cursor= db.rawQuery("SELECT * FROM trip ", null);
        while (cursor.moveToNext ())
        {
            Integer id= cursor.getInt (0);
              String agency = cursor.getString (1) ;
              String destination = cursor.getString (2) ;
              String date = cursor.getString (3) ;
              String time = cursor.getString (4) ;
            Integer available = cursor.getInt (5) ;
            Integer price = cursor.getInt (6) ;
//              String image = cursor.getString (7) ;



            Trip trip = new Trip (id, agency, destination, date, time, available,price);

        arrayList.add (trip);
        }
        return arrayList;





}
          public  int delete(String id){
              SQLiteDatabase db = this.getWritableDatabase();
               return db.delete(AppContract.TripEntry.TABLE_NAME,"_id=?", new  String[]{id});

          }

}
