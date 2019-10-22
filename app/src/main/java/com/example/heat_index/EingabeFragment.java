package com.example.heat_index;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class EingabeFragment extends Fragment {
    @Nullable
    private Button degreeSwitch;
    private EditText temp_text;
    private EditText humidity_text;
    private boolean isFahrenheit = false;
    private Button berechnen;
    private EingabeFragmentListener listener;
    private WeatherDao dao;
    private Weather weather;

    public interface EingabeFragmentListener {
        void onEingabeSent(Weather w);
    }


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_eingabe, container, false);

        dao = WeatherRoomDatabase.getDatabase(getActivity()).weatherDao();

        temp_text = view.findViewById(R.id.temperatur_eingabe);
        humidity_text = view.findViewById(R.id.humidity_eingabe);
        degreeSwitch = view.findViewById(R.id.degree_switch);

        degreeSwitch.setText(isFahrenheit ? R.string.f : R.string.c);
        degreeSwitch.setOnClickListener(e -> {
            degreeSwitch.setText(isFahrenheit ? R.string.c : R.string.f);
            isFahrenheit = !isFahrenheit;
        });

        berechnen = view.findViewById(R.id.berechnen);
        berechnen.setOnClickListener(click -> {
            String temperatur = temp_text.getText().toString();
            String feuchtigkeit = humidity_text.getText().toString();


            if(temperatur.equals("") || feuchtigkeit.equals("")) {
                Toast.makeText(getContext(),
                        "Bitte alle Felder ausfüllen",
                        Toast.LENGTH_LONG)
                        .show();
            }

            else {
                double temper = Double.parseDouble(temperatur);
                int humid = Integer.parseInt(feuchtigkeit);

                if ((humid < 0 || humid > 100) && ((!isFahrenheit && (temper < 27 || temper > 43)))
                        || isFahrenheit && (temper < 80 || temper > 110)) {
                    Toast.makeText(getContext(),
                            "Temperatur muss zwischen 27 und 43°C bzw 80 und 110°F liegen, und Luftfeuchtigkeit zwischen 0 und 100%!",
                            Toast.LENGTH_LONG)
                            .show();

                } else if (humid < 0 || humid > 100) {
                    Toast.makeText(getContext(),
                            "Luftfeuchtigkeit muss zwischen 0 und 100% liegen", Toast.LENGTH_LONG)
                            .show();
                } else if (!isFahrenheit && (temper < 27 || temper > 43)) {
                    Toast.makeText(getContext(),
                            "Der Heat Index kann nur für Temperaturen zwischen 27 und 43°C berechnet werden",
                            Toast.LENGTH_LONG)
                            .show();
                } else {
                    weather = new Weather(temper, humid, isFahrenheit);
                    listener.onEingabeSent(weather);

                    saveWeatherOnClick();
                }
            }
        });

        return view;
    }

    private void saveWeatherOnClick(){
        new SpeichernTask().execute(weather);
    }

    @Override
    public void onAttach(@NonNull Context context) {
        if(context instanceof EingabeFragmentListener){
            listener = (EingabeFragmentListener)context;
        }
        else{
            throw new RuntimeException(context.toString() +
                    " must implement EingabeFragmentListener");
        }
        super.onAttach(context);
    }

    @Override
    public void onDetach() {
        super.onDetach();
        listener = null;

    }

    public class SpeichernTask extends AsyncTask<Weather, Void, Void>{

        @Override
        protected Void doInBackground(Weather... weathers){
            dao.insert(weathers[0]);
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
        }

    }


}
