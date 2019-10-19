package com.example.heat_index;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;


public class AusgabeFragment extends Fragment {
    private TextView tempTextView, humidityTextView, ergebnisTextView;
    private Weather weather;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_ausgabe, container, false);
        tempTextView = view.findViewById(R.id.ausgabe_eingegebene_temp);
        humidityTextView = view.findViewById(R.id.ausgabe_eingegebene_humidity);
        ergebnisTextView = view.findViewById(R.id.ausgabe_ergebnis);

        if(weather != null){
            tempTextView.setText("" + weather.getTemp() + (weather.getIsFahrenheit() ?
                    getString(R.string.f) : getString((R.string.c))));
            humidityTextView.setText("" + weather.getHumidity());
            ergebnisTextView.setText("" + weather.getHeatIndex() + (weather.getIsFahrenheit() ?
                    getString(R.string.f) : getString((R.string.c))));
        }
        return view;
    }

    public void sendToTextView(Weather weather){
        this.weather = weather;
    }

}
