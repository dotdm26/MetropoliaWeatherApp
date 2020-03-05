package com.example.metropoliaweatherapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class QuestionnaireActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    public EditText editText4;
    public EditText editText;
    public Button saveButton;
    public EditText editText3;
    public Spinner spinner3;


    public static final String SH_PR = "shPr";
    public static final String TEXT = "text";
    public static final String FIRST_VISIT = "first";

    public String text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_questionnaire);

        editText4 =  findViewById(R.id.editText4);
        editText =  findViewById(R.id.editText);
        saveButton = findViewById(R.id.saveButton);
        editText3 = findViewById(R.id.editText3);
        spinner3 = findViewById(R.id.spinner3);


        Spinner spinner1 = findViewById(R.id.spinner3);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.weather_types, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner1.setAdapter(adapter);
        spinner1.setOnItemSelectedListener(this);

        saveButton = findViewById(R.id.saveButton);
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                savePrefs();
            }
        });
    }

    public void savePrefs(){
        SharedPreferences sharedPreferences = getSharedPreferences(SH_PR, MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();


        editor.putString(TEXT, text);
        editor.apply();
    }

    public void loadPrefs(){
        SharedPreferences sharedPreferences = getSharedPreferences(SH_PR, MODE_PRIVATE);

    }


    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        text = adapterView.getItemAtPosition(i).toString();
        Toast.makeText(adapterView.getContext(), text, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}
