package com.example.heat_index;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity
        implements EingabeFragment.EingabeFragmentListener{

    private Fragment eingabeFrag, ausgabeFrag, infoFrag, verlaufFrag;
    private double temp;
    private int humidity;
    private boolean isFahrenheit;
    BottomNavigationView botNav;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Einzelne Fragmente auf die zugegriffen werden soll
        eingabeFrag = new EingabeFragment();
        ausgabeFrag = new AusgabeFragment();
        infoFrag = new InfoFragment();
        verlaufFrag = new VerlaufFragment();

        getSupportFragmentManager().beginTransaction()
                .replace(R.id.frame_container, eingabeFrag)
                .commit();
        setTitle(R.string.heat_index_berechnen);
        botNav = findViewById(R.id.bottom_bar);
        botNav.setOnNavigationItemSelectedListener(navbarListener);

    }



    //sorgt dafür dass das richtige Fragment angesprochen wird
    //setzt den Titel der App dementsprechend
    private BottomNavigationView.OnNavigationItemSelectedListener navbarListener =
            menuItem -> {
                Fragment selectedFrag;
                switch (menuItem.getItemId()) {
                    case R.id.eingabe_nav:
                        selectedFrag = eingabeFrag;
                        setTitle(R.string.heat_index_berechnen);
                        break;
                    case R.id.ausgabe_nav:
                        selectedFrag = ausgabeFrag;
                        setTitle(R.string.ergebnis_title);
                        break;
                    case R.id.verlauf_nav:
                        selectedFrag = verlaufFrag;
                        setTitle(R.string.verlauf_title);
                        break;
                    case R.id.info_nav:
                        selectedFrag = infoFrag;
                        setTitle(R.string.info_title);
                        break;
                    default: selectedFrag = eingabeFrag;
                }

                getSupportFragmentManager().beginTransaction().replace(R.id.frame_container,
                        selectedFrag).commit();
                return true;
            };


    @Override
    public void onEingabeSent(String temp, String humidity, boolean isFahrenheit) {
        this.temp = Double.parseDouble(temp);
        this.humidity = Integer.parseInt(humidity);
        ((AusgabeFragment)ausgabeFrag).sendToTextView(new Weather(this.temp, this.humidity, isFahrenheit));
        getSupportFragmentManager().beginTransaction().replace(R.id.frame_container,
                ausgabeFrag).commit();
        botNav.setSelectedItemId(R.id.ausgabe_nav);

    }
}

