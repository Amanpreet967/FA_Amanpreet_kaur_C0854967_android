package com.example.mapapplicationdemo.SqlLite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.mapapplicationdemo.AddressModel;

import java.util.ArrayList;

public class DBHandler extends SQLiteOpenHelper {

    // creating a constant variables for our database.
    // below variable is for our database name.
    private static final String DB_NAME = "userlocations";

    // below int is our database version
    private static final int DB_VERSION = 1;

    // below variable is for our table name.
    private static final String TABLE_NAME = "locations";

    // below variable is for our id column.
    private static final String ID_COL = "id";

    // below variable is for our course name column
    private static final String SAVE_ADDRESS = "name";

    // below variable id for our course duration column.
    private static final String LATITUDE = "latitude";

    // below variable for our course description column.
    private static final String LONGITUDE = "longitude";


    // creating a constructor for our database handler.
    public DBHandler(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    // below method is for creating a database by running a sqlite query
    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "CREATE TABLE " + TABLE_NAME + " ("
                + ID_COL + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + SAVE_ADDRESS + " TEXT,"
                + LATITUDE + " TEXT,"
                + LONGITUDE + " TEXT)";
        db.execSQL(query);
    }


    public void addNewLocation(String address, String latitude, String longitude) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(SAVE_ADDRESS, address);
        values.put(LATITUDE, latitude);
        values.put(LONGITUDE, longitude);
        db.insert(TABLE_NAME, null, values);
        db.close();
    }

    public void updateAddress(AddressModel address) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(SAVE_ADDRESS, address.getAddress());
        values.put(LATITUDE, address.getLatitude());
        values.put(LONGITUDE, address.getLongitude());
        db.update(TABLE_NAME, values, "id=?", new String[]{String.valueOf(address.getId())});
        db.close();
    }


    public ArrayList<AddressModel> getFavouritesLocation() {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor saveLocation = db.rawQuery("SELECT * FROM " + TABLE_NAME, null);
        ArrayList<AddressModel> locationArray = new ArrayList<>();
        if (saveLocation.moveToFirst()) {
            do {
                locationArray.add(new AddressModel(saveLocation.getString(1),
                        Double.parseDouble(saveLocation.getString(2)),
                        Double.parseDouble(saveLocation.getString(3)),
                        Integer.parseInt(saveLocation.getString(0))));
            } while (saveLocation.moveToNext());
        }
        saveLocation.close();
        return locationArray;
    }

    public void deleteCourse(String address) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_NAME, "name=?", new String[]{address});
        db.close();
    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // this method is called to check if the table exists already.
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }
}
