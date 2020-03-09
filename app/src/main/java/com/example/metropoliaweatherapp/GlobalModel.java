package com.example.metropoliaweatherapp;

import java.util.ArrayList;
import java.util.List;

/**
 * GlobalModel singleton for calling the preferences.
 */

public class GlobalModel {

    private static final GlobalModel ourInstance = new GlobalModel();
    public static List<Preference> preferences;

    private GlobalModel() {
        preferences = new ArrayList<>();
    }

    static GlobalModel getInstance() {
        return ourInstance;
    }

    public List<Preference> getPreferences() {
        return preferences;
    }

    public void prefAdd(Preference pref) {
        preferences.add(pref);
    }
}
