package com.example.heat_index;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.util.List;


public class VerlaufFragment extends Fragment {
    private WeatherDao dao;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        dao = WeatherRoomDatabase.getDatabase(getActivity()).weatherDao();
        return inflater.inflate(R.layout.fragment_verlauf, container, false);

    }

    @Override
    public void onResume() {
        super.onResume();
        new LadeWeatherTask().execute();
    }

    class LadeWeatherTask extends AsyncTask<Void, Void, List<Weather>>{

        @Override
        protected List<Weather> doInBackground(Void... voids) {
            return dao.getAll();
        }

        @Override
        protected void onPostExecute(List<Weather> weathers) {
            super.onPostExecute(weathers);
            for (Weather weather : weathers) {
                System.out.println(weather.getHeatIndex() + (weather.getIsFahrenheit() ?
                        getString(R.string.f) : getString(R.string.c)));
            }
        }


    }
}
