package com.example.mapapplicationdemo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.example.mapapplicationdemo.SqlLite.DBHandler;

import java.util.ArrayList;

public class ViewLocationActivity extends AppCompatActivity {
    private ArrayList<AddressModel> myList =new ArrayList<>();
    private LocationViewAdapter adapter;
    private LinearLayoutManager mLinearLayoutManager;
    private DBHandler database;
    private RecyclerView recyclerView;
    private Context mContext;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favourite_places);
        recyclerView = findViewById(R.id.list);
        mContext = this;
        database = new DBHandler(mContext);
        getLocations();

    }

    @Override
    protected void onResume() {
        super.onResume();
        getLocations();
    }

    public void getLocations() {
        myList.clear();
        myList = database.getFavouritesLocation();
        adapter = new LocationViewAdapter(this, myList, new OnItemClickListener() {
            @Override
            public void deleteAddress(AddressModel addressModel) {
                myList.remove(addressModel);
                database.deleteCourse(addressModel.getAddress());
                adapter.notifyDataSetChanged();
                Toast.makeText(mContext, "Deleted", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void editAddress(AddressModel addressModel) {
                startActivity(new Intent(ViewLocationActivity.this, UpdateLocationActivity.class).putExtra(Constants.LOCATION_NAME, addressModel));
            }

        });
        mLinearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(mLinearLayoutManager);
    }
}