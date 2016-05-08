package com.taguxdesign.maotong.putian;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by MaoTong on 2016/5/6.
 * QQ:974291433
 */
public class City {
    private String cityName;
    private List<Hospital> mHospitals = new ArrayList<>();

    public City(String cityName, List<Hospital> hospitals) {
        this.cityName = cityName;
        mHospitals = hospitals;
    }

    public City() {

    }

    public List<Hospital> getHospitals() {
        return mHospitals;
    }

    public void setHospitals(List<Hospital> hospitals) {
        mHospitals = hospitals;
    }

    public City(String cityName) {
        this.cityName = cityName;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;

    }

    @Override
    public String toString() {
        return "City{" +
                "cityName='" + cityName + '\'' +
                ", mHospitals=" + mHospitals +
                '}';
    }
}
