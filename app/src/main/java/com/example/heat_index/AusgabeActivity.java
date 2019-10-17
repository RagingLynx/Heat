package com.example.heat_index;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class AusgabeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ausgabe);

        BottomNavigationView navigation = findViewById(R.id.bottom_bar);
        navigation.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()) {
                    case R.id.eingabe_nav:
                        break;
                    case R.id.ausgabe_nav:
                        Intent intent2 = new Intent(AusgabeActivity.this, AusgabeActivity.class);
                        startActivity(intent2);
                        break;
                    case R.id.verlauf_nav:
                        Intent intent3 = new Intent(AusgabeActivity.this, DatenAnsichtActivity.class);
                        startActivity(intent3);
                        break;
                    case R.id.info_nav:
                        Intent intent4 = new Intent(AusgabeActivity.this, InfoActivity.class);
                        startActivity(intent4);
                        break;
                }
                return false;
            }
        });
    }
}
