package com.example.ivandimitrov.restorantmenu;

/**
 * Created by Ivan Dimitrov on 2/14/2017.
 */

public class RestaurantNode {
    private String mName;
    private double mLat;
    private double mLng;
    private int    mStars;

    public RestaurantNode(String name, double lat, double lng, int stars) {
        mName = name;
        mLat = lat;
        mLng = lng;
        mStars = stars;
    }

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        mName = name;
    }

    public double getLat() {
        return mLat;
    }

    public void setLat(double lat) {
        mLat = lat;
    }

    public double getLng() {
        return mLng;
    }

    public void setLng(double lng) {
        mLng = lng;
    }

    public int getStars() {
        return mStars;
    }

    public void setStars(int stars) {
        mStars = stars;
    }
}
