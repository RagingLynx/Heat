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
    private Weather weather;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_ausgabe, container, false);

        TextView tempTextView, humidityTextView, ergebnisTextView, heatView;

        tempTextView = view.findViewById(R.id.ausgabe_eingegebene_temp);
        humidityTextView = view.findViewById(R.id.ausgabe_eingegebene_humidity);
        ergebnisTextView = view.findViewById(R.id.ausgabe_ergebnis);
        heatView = view.findViewById(R.id.heatInfo);

        if(weather != null){
            tempTextView.setText((getString(R.string.außentemp) + " " + weather.getTemp()
                    + (weather.getIsFahrenheit() ?
                    getString(R.string.f) : getString((R.string.c)))));
            humidityTextView.setText((getString(R.string.relative_luftfeuchtigkeit)
                    + " " + weather.getHumidity() + getString(R.string.string_percent)));
            ergebnisTextView.setText((getString(R.string.indexBetraegt) + "\n"
                    + weather.getHeatIndex() + (weather.getIsFahrenheit() ?
                    getString(R.string.f) : getString((R.string.c)))));


            String w1 = getString(R.string.warnung_1);
            String w2 = getString(R.string.warnung_2);
            String w3 = getString(R.string.warnung_3);
            String w4 = getString(R.string.warnung_4);

            if(!weather.getIsFahrenheit()) {
                if (weather.getHeatIndex() > 54) heatView.setText(w4);
                else if (weather.getHeatIndex() > 40) heatView.setText(w3);
                else if (weather.getHeatIndex() > 32) heatView.setText(w2);
                else if (weather.getHeatIndex() > 27) heatView.setText(w1);
                else heatView.setText("");
            }
            else{
                if (weather.getHeatIndex() > 130) heatView.setText(w4);
                else if (weather.getHeatIndex() > 105) heatView.setText(w3);
                else if (weather.getHeatIndex() > 90) heatView.setText(w2);
                else if (weather.getHeatIndex() > 80) heatView.setText(w1);
                else heatView.setText("");
            }



        }
        return view;
    }

    void sendToTextView(Weather weather){
        this.weather = weather;
    }

}
