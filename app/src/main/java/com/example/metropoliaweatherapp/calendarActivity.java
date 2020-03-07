package com.example.metropoliaweatherapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class calendarActivity extends AppCompatActivity {
    /**
     * A calendar to select the date. The spinner allows the user to select the specific activity to determine
     * whether the weather of that day was good for the activity or not.
     * <p>
     * Optional: Display past weather data
     */


    Button returnButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar);

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
