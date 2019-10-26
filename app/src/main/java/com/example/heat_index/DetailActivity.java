package com.example.heat_index;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detail_activty);
        setTitle("Detail");

        TextView dateDetail = findViewById(R.id.date_detail);
        Bundle extras = getIntent().getExtras();
        Date date = new Date(extras.getLong("date"));
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        dateDetail.setText(("" + sdf.format(date)));
    }
}
