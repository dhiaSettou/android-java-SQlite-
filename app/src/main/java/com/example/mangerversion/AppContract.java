package com.example.mangerversion;

import android.provider.BaseColumns;

public class AppContract {
    private AppContract(){}

    public static class UserEntry implements BaseColumns {
        public static final String TABLE_NAME = "user";
        public static final String COLUMN_USERNAME = "userName";
        public static final String COLUMN_PASSWORD = "password";

    }
    public static class TripEntry implements BaseColumns {
        public static final String TABLE_NAME = "trip";
        public static final String COLUMN_TRIP_AGENCY = "agency";
        public static final String COLUMN_TRIP_DESTINATION = "destination";
        public static final String COLUMN_TRIP_DATE = "date";
        public static final String COLUMN_TRIP_TIME = "time";
        public static final String COLUMN_TRIP_PASSENGERS = "passengers";
        public static final String COLUMN_TRIP_PRICE = "price";
        public static final String COLUMN_TRIP_IMAGE = "image";





    }

}
