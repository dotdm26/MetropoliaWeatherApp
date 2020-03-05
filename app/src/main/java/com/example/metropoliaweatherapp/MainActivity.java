package com.example.metropoliaweatherapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.os.Bundle;

import java.io.IOException;

/*import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
*/
public class MainActivity extends AppCompatActivity {
    /**
     * Big message displaying "nice day" or "not a nice day".
     * Followed by a few details about the weather.
     * A spinner to select a specific activity (that comes with the preferred weather, ...)
     * Location button to locate the user and get the weather data?
     * Calendar button to review past dates' information
     */


    private Button calendarButton;
    private Button userButton;
    //    private Elements source;
    private TextView parse;
    private View.OnClickListener clickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            startActivity(new Intent(getApplicationContext(), view.getId() == R.id.calendarButton ? calendarActivity.class : PreferencesActivity.class));
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SharedPreferences sharedPreferences = getSharedPreferences(QuestionnaireActivity.SH_PR, MODE_PRIVATE);
        if(sharedPreferences.getBoolean(QuestionnaireActivity.FIRST_VISIT, true)) {
            // set the first visit to false in shared preferences
            Intent intent = new Intent(MainActivity.this, QuestionnaireActivity.class );
            startActivity(intent);

        } else {


            userButton = findViewById(R.id.userButton);
            userButton.setOnClickListener(clickListener);
            parse = findViewById(R.id.weatherData);

            calendarButton = findViewById(R.id.calendarButton);
            calendarButton.setOnClickListener(clickListener);

            new doIt().execute();
        }
    }

    public class doIt extends AsyncTask<Void, Void, Void> {
        @Override
        protected Void doInBackground(Void... voids) {
            try {
//                Document doc = Jsoup.connect("https://www.bbc.com/weather/658225").get();
//                source = doc.getElementsByClass("wr-value--temperature--c");
            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
//            parse.setText(source.get(0).text());
        }
    }

}
