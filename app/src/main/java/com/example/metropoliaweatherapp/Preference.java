package com.example.metropoliaweatherapp;

/**
 * For calling a preference's detailed information (name, weather type, ...)
 */

public class Preference {
    public String name;
    public int minTemp;
    public int maxTemp;
    public String location;
    public String weatherType;

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
