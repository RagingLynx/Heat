package com.example.heatindex;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import com.example.heat_index.R;

public class MainActivity extends AppCompatActivity {
    private Button switchDegree;
    private boolean isFahrenheit = false;
    private Button calculate;
    private EditText temp_text;
    private EditText humidity;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first);

        temp_text = findViewById(R.id.temp_text);
        humidity = findViewById(R.id.humidity_text);

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

    }

}
