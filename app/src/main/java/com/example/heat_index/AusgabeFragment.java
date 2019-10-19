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
    private TextView tempTextView, humidityTextView, ergebnisTextView, heatView;
    private Weather weather;

    private String w1 = "Vorsicht – Bei längeren Zeiträumen und körperlicher Aktivität kann es zu Erschöpfungserscheinungen kommen.",
                   w2 = "Erhöhte Vorsicht – Es besteht die Möglichkeit von Hitzeschäden wie Sonnenstich, Hitzekrampf und Hitzekollaps.",
                    w3= "Gefahr – Sonnenstich, Hitzekrampf und Hitzekollaps sind wahrscheinlich; Hitzschlag ist möglich.",
                   w4= "Erhöhte Gefahr – Hitzschlag und Sonnenstich sind wahrscheinlich.";

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_ausgabe, container, false);
        tempTextView = view.findViewById(R.id.ausgabe_eingegebene_temp);
        humidityTextView = view.findViewById(R.id.ausgabe_eingegebene_humidity);
        ergebnisTextView = view.findViewById(R.id.ausgabe_ergebnis);
        heatView = view.findViewById(R.id.heatInfo);

        if(weather != null){
            tempTextView.setText("Außentemperatur: " + weather.getTemp() + (weather.getIsFahrenheit() ?
                    getString(R.string.f) : getString((R.string.c))));
            humidityTextView.setText("relative Luftfeuchtigkeit: " + weather.getHumidity() + " %");
            ergebnisTextView.setText("Der HeatIndex beträgt \n" + weather.getHeatIndex() + (weather.getIsFahrenheit() ?
                    getString(R.string.f) : getString((R.string.c))));
            if(weather.getHeatIndex() >54) heatView.setText(w4);
            else if(weather.getHeatIndex() > 40) heatView.setText(w3);
            else if(weather.getHeatIndex() > 32) heatView.setText(w2);
            else if(weather.getHeatIndex() > 27) heatView.setText(w1);
            else heatView.setText("");



        }
        return view;
    }

    public void sendToTextView(Weather weather){
        this.weather = weather;
    }

}
