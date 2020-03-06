package com.example.metropoliaweatherapp;

import java.util.ArrayList;

public class Preference {
    public String name;
    public int minTemp;
    public int maxTemp;
    public String location;
    public String weatherType;

    public Preference(String name, int minTemp, int maxTemp, String location, String weatherType) {
        ArrayList<Preference> preferences = new ArrayList<>();

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
