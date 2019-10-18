package com.example.heat_index;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class EingabeFragment extends Fragment {
    @Nullable
    private Button degreeSwitch;
    private EditText temp_text;
    private EditText humidity_text;
    private boolean fahrenheit = false;
    private Button berechnen;


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_eingabe, container, false);
        temp_text = view.findViewById(R.id.temperatur_eingabe);
        humidity_text = view.findViewById(R.id.humidity_eingabe);
        degreeSwitch = view.findViewById(R.id.degree_switch);

        degreeSwitch.setOnClickListener(e -> {
            degreeSwitch.setText(fahrenheit ? R.string.c : R.string.f);
            fahrenheit = !fahrenheit;
        });

        berechnen = view.findViewById(R.id.berechnen);
        berechnen.setOnClickListener(click -> {
            String temperatur = temp_text.getText().toString();
            String feuchtigkeit = humidity_text.getText().toString();

            if(temperatur.equals("") || feuchtigkeit.equals("")) {
                System.out.println("feld leer");
                return;
            }

            double temp = Double.parseDouble(temperatur);
            Integer humidity = Integer.parseInt(feuchtigkeit);
            System.out.println("temp: " + temp + "\n humid: " + humidity );
        });

        return view;
    }
}
