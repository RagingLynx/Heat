package com.example.heat_index;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

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

    public interface EingabeFragmentListener {
        void onEingabeSent(String temp, String humidity, boolean isFahrenheit);
    }


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_eingabe, container, false);
        temp_text = view.findViewById(R.id.temperatur_eingabe);
        humidity_text = view.findViewById(R.id.humidity_eingabe);
        degreeSwitch = view.findViewById(R.id.degree_switch);

        degreeSwitch.setOnClickListener(e -> {
            degreeSwitch.setText(isFahrenheit ? R.string.c : R.string.f);
            isFahrenheit = !isFahrenheit;
        });

        berechnen = view.findViewById(R.id.berechnen);
        berechnen.setOnClickListener(click -> {
            String temperatur = temp_text.getText().toString();
            String feuchtigkeit = humidity_text.getText().toString();

            if(temperatur.equals("") || feuchtigkeit.equals("")) {
                System.out.println("feld leer");
                return;
            }

            listener.onEingabeSent(temperatur, feuchtigkeit, isFahrenheit);
            System.out.println("temp: " + temperatur + "\n humid: " + feuchtigkeit );

        });

        return view;
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

}
