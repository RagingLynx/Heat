package com.example.heat_index;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.material.bottomnavigation.BottomNavigationView;

//Noch die Main, sollte aber ausgetauscht werden sobald alle anderen laufen
public class MainActivity extends AppCompatActivity {
    private Button switchDegree;
    private boolean isFahrenheit = false;
    private EditText temp_text;
    private EditText humidity;
    private FrameLayout frameContainer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportFragmentManager().beginTransaction().replace(R.id.frame_container,
                new EingabeFragment()).commit();

        frameContainer = findViewById(R.id.frame_container);
        /*temp_text = findViewById(R.id.temperatur_eingabe);
        switchDegree = findViewById(R.id.degree_switch);

        switchDegree.setOnClickListener(e -> {
            String tempEingabe = temp_text.getText().toString();
        }); */

        BottomNavigationView botNav = findViewById(R.id.bottom_bar);
        botNav.setOnNavigationItemSelectedListener(navbarListener);

    }



    private BottomNavigationView.OnNavigationItemSelectedListener navbarListener =
            menuItem -> {
                Fragment selectedFrag = null;
                switch (menuItem.getItemId()) {
                    case R.id.eingabe_nav:
                        selectedFrag = new EingabeFragment();
                        break;
                    case R.id.ausgabe_nav:
                        selectedFrag = new AusgabeFragment();
                        break;
                    case R.id.verlauf_nav:
                        selectedFrag = new VerlaufFragment();
                        break;
                    case R.id.info_nav:
                        selectedFrag = new InfoFragment();
                        break;
                }

                getSupportFragmentManager().beginTransaction().replace(R.id.frame_container,
                        selectedFrag).commit();
                return true;
            };

}

