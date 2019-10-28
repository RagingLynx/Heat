package com.example.heat_index;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class DetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detail_activty);
        setTitle("Detail");

        TextView dateDetail = findViewById(R.id.date_detail),
                tempDetail = findViewById(R.id.temp_detailpage_text),
                humidityDetail = findViewById(R.id.humidity_detailpage_text),
                heatIndexDetail = findViewById(R.id.heatindex_detailpage_text),
                warnung = findViewById(R.id.warnhinweis_detailseite);

        Bundle extras = getIntent().getExtras();

        boolean isFahrenheit = extras.getBoolean("isFahrenheit");

        Date date = new Date(extras.getLong("date"));
        SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy \n hh:mm");
        dateDetail.setText(("" + sdf.format(date)));

        tempDetail.setText((extras.getDouble("temp") + (isFahrenheit ? getString(R.string.f):
                getString(R.string.c))));

        humidityDetail.setText(( extras.getInt("humidity")
                + getString(R.string.string_percent)));

        double heatIndex = extras.getDouble("heatIndex");
        heatIndexDetail.setText((heatIndex + (isFahrenheit ? getString(R.string.f) :
                getString(R.string.c))));


        String warnHinweis = "";
        if(!isFahrenheit) {
            if (heatIndex > 54) warnHinweis = getString(R.string.warnung_4);
            else if (heatIndex > 40) warnHinweis = getString(R.string.warnung_3);
            else if (heatIndex > 32) warnHinweis = getString(R.string.warnung_2);
            else if (heatIndex >= 27) warnHinweis = getString(R.string.warnung_1);
            else warnHinweis = "";
        }
        else{
            if (heatIndex > 130) warnHinweis = getString(R.string.warnung_4);
            else if (heatIndex > 105) warnHinweis = getString(R.string.warnung_3);
            else if (heatIndex > 90) warnHinweis = getString(R.string.warnung_2);
            else if (heatIndex > 80) warnHinweis = getString(R.string.warnung_1);
        }
        warnung.setText(warnHinweis);

    }
}
