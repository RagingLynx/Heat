package com.example.heat_index;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

//Noch die Main, sollte aber ausgetauscht werden sobald alle anderen laufen
public class MainActivity extends AppCompatActivity {
    private Button switchDegree;
    private boolean isFahrenheit = false;
    private EditText temp_text;
    private EditText humidity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first);

        temp_text = findViewById(R.id.temp_text);
        humidity = findViewById(R.id.humidity_text);
        Button calculateButton = findViewById(R.id.calculate);

        switchDegree = findViewById(R.id.degree_switch);
        switchDegree.setOnClickListener(e -> {
            if(!isFahrenheit) {
                switchDegree.setText(getString(R.string.f));
            }
            else{
                switchDegree.setText(getString(R.string.c));
            }
            isFahrenheit = !isFahrenheit;
        });



        calculateButton.setOnClickListener(e -> {
            Intent intent = new Intent(this, EingabeActivity.class);
            startActivity(intent);
            Weather w = new Weather(Double.parseDouble(temp_text.getText().toString()), Double.parseDouble(humidity.getText().toString()), isFahrenheit);
            System.out.println(Double.parseDouble(temp_text.getText().toString()));
            System.out.println(Double.parseDouble(humidity.getText().toString()));
            System.out.println(isFahrenheit);
            System.out.println(w.getHeatIndex());

        });

    }

}
