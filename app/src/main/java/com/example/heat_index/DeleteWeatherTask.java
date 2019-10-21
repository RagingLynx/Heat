package com.example.heat_index;

import android.os.AsyncTask;
import android.provider.UserDictionary;

import java.util.List;

public class DeleteWeatherTask extends AsyncTask<Weather, Void, List<Weather>> {
    private final WeatherDao dao;
    private final WeatherListAdapter adapter;

    public DeleteWeatherTask(WeatherDao dao, WeatherListAdapter adapter){
        this.dao = dao;
        this.adapter = adapter;
    }

    @Override
    protected List<Weather> doInBackground(Weather... weathers){
        dao.delete(weathers[0]);
        return dao.getAll();
    }

    @Override
    protected void onPostExecute(List<Weather> weathers){
        super.onPostExecute(weathers);
        adapter.setWeathers(weathers);
    }
}
