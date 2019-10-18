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
    private Fragment eingabeFrag, ausgabeFrag, infoFrag, verlaufFrag;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        eingabeFrag = new EingabeFragment();
        ausgabeFrag = new AusgabeFragment();
        infoFrag = new InfoFragment();
        verlaufFrag = new VerlaufFragment();

        frameContainer = findViewById(R.id.frame_container);

        getSupportFragmentManager().beginTransaction().replace(R.id.frame_container,
                eingabeFrag).commit();
        BottomNavigationView botNav = findViewById(R.id.bottom_bar);
        botNav.setOnNavigationItemSelectedListener(navbarListener);

    }



    private BottomNavigationView.OnNavigationItemSelectedListener navbarListener =
            menuItem -> {
                Fragment selectedFrag = null;
                switch (menuItem.getItemId()) {
                    case R.id.eingabe_nav:
                        selectedFrag = eingabeFrag;
                        break;
                    case R.id.ausgabe_nav:
                        selectedFrag = ausgabeFrag;
                        break;
                    case R.id.verlauf_nav:
                        selectedFrag = verlaufFrag;
                        break;
                    case R.id.info_nav:
                        selectedFrag = infoFrag;
                        break;
                }

                getSupportFragmentManager().beginTransaction().replace(R.id.frame_container,
                        selectedFrag).commit();
                return true;
            };

}

