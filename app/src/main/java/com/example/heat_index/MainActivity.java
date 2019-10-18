package com.example.heat_index;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.google.android.material.bottomnavigation.BottomNavigationView;

//Noch die Main, sollte aber ausgetauscht werden sobald alle anderen laufen
public class MainActivity extends AppCompatActivity {

    private Fragment eingabeFrag, ausgabeFrag, infoFrag, verlaufFrag;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        eingabeFrag = new EingabeFragment();
        ausgabeFrag = new AusgabeFragment();
        infoFrag = new InfoFragment();
        verlaufFrag = new VerlaufFragment();

        getSupportFragmentManager().beginTransaction().replace(R.id.frame_container,
                eingabeFrag).commit();
        setTitle(R.string.heat_index_berechnen);
        BottomNavigationView botNav = findViewById(R.id.bottom_bar);
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

}

