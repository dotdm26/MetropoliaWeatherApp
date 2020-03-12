package com.example.metropoliaweatherapp;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * For calling a preference's detailed information (name, weather type, ...)
 */

public class Preference {
    private String name;
    private String location;
    private String weatherType;
    private int minTemp;
    private int maxTemp;
    private List<Preference> preferences;

    public Preference(String name, int minTemp, int maxTemp, String location, String weatherType) {
        this.name = name;
        this.minTemp = minTemp;
        this.maxTemp = maxTemp;
        this.location = location;
        this.weatherType = weatherType;
    }

    public String getName() {
        return name;
    }

    public int getMinTemp() {
        return minTemp;
    }

    public int getMaxTemp() {
        return maxTemp;
    }

    public String getLocation() {
        return location;
    }

    public String getWeatherType() {
        return weatherType;
    }

    @Override
    public String toString() {
        return this.name;
    }
}
