package com.example.mapapplicationdemo;

import java.io.Serializable;

public class AddressModel implements Serializable {
    private String address;
    private double latitude;
    private double longitude;
    private int id;

    public AddressModel(String address, double latitude, double longitude, int id) {
        this.address = address;
        this.latitude = latitude;
        this.longitude = longitude;
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }
}
