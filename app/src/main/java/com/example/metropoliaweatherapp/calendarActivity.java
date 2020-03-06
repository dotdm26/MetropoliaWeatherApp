package com.example.metropoliaweatherapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.telecom.TelecomManager;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.TextView;

public class calendarActivity extends AppCompatActivity {
    /**
     * A calendar to select the date. The spinner allows the user to select the specific activity to determine
     * whether the weather of that day was good for the activity or not.
     * <p>
     * Optional: Display past weather data
     */

    private CalendarView calendarView;
    private TextView pastPrefsView;

    Button returnButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar);

        pastPrefsView = findViewById(R.id.pastPrefsView);

        calendarView = (CalendarView) findViewById(R.id.calendarView);
        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView calendarView, int i, int i1, int i2) {
                String date = (i1+1) + "/" + i2 + "/" + i;
                pastPrefsView.setText(date);
            }
        });

        //Button to return to main activity
        returnButton = findViewById(R.id.listButton);
        returnButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                returnMain();
            }
        });
    }

    //
    public void returnMain() {
        Intent main = new Intent(this, MainActivity.class);
        startActivity(main);

    }
}
