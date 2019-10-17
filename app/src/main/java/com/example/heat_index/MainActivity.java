package com.example.heat_index;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.material.bottomnavigation.BottomNavigationView;

//Noch die Main, sollte aber ausgetauscht werden sobald alle anderen laufen
public class MainActivity extends AppCompatActivity {
    private Button switchDegree;
    private boolean isFahrenheit = false;
    private Button calculate;
    private EditText temp_text;
    private EditText humidity;
    private Button switchbutton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first);


        temp_text = findViewById(R.id.temp_text);
        humidity = findViewById(R.id.humidity_text);
        switchbutton = findViewById(R.id.calculate);

        switchDegree = (Button) findViewById(R.id.degree_switch);
        switchDegree.setOnClickListener(e -> {
            if(!isFahrenheit) {
                switchDegree.setText("°F");
            }
            else{
                switchDegree.setText("°C");
            }
            isFahrenheit = !isFahrenheit;
        });

        calculate = findViewById(R.id.calculate);
        calculate.setOnClickListener(e -> {
            Weather w = new Weather(Double.parseDouble(temp_text.getText().toString()), Double.parseDouble(humidity.getText().toString()), isFahrenheit);
            System.out.println(Double.parseDouble(temp_text.getText().toString()));
            System.out.println(Double.parseDouble(humidity.getText().toString()));
            System.out.println(isFahrenheit);
            System.out.println(w.getHeatIndex());
        });

        switchbutton.setOnClickListener(e -> {
            Intent intent = new Intent(this, EingabeActivity.class);
            startActivity(intent);

        });

    }

}
