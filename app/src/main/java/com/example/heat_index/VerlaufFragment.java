package com.example.heat_index;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;


public class VerlaufFragment extends Fragment {
    private WeatherDao dao;
    RecyclerView recyclerView;
    WeatherListAdapter wAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        dao = WeatherRoomDatabase.getDatabase(getActivity()).weatherDao();
        View view = inflater.inflate(R.layout.fragment_verlauf, container, false);


        wAdapter = new WeatherListAdapter(dao);
        recyclerView = view.findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(wAdapter);

        return view;
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
            wAdapter.setWeathers(weathers);
        }


    }
}
