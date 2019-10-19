package com.example.heat_index;

import android.content.Context;
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


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_ausgabe, container, false);
        tempTextView = view.findViewById(R.id.ausgabe_eingegebene_temp);
        humidityTextView = view.findViewById(R.id.ausgabe_eingegebene_humidity);
        ergebnisTextView = view.findViewById(R.id.ausgabe_ergebnis);

        return view;
    }

    public void sendToTextView(Weather weather){
        tempTextView.setText("" + weather.getTemp());
        humidityTextView.setText("" + weather.getHumidity());
        ergebnisTextView.setText("" + weather.getHeatIndex() +
                (weather.getIsFahrenheit() ? R.string.f : R.string.c));
    }

}
