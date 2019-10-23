package com.example.heat_index;

import android.app.AlertDialog;
import android.content.res.Resources;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.Collections;
import java.util.List;

public class WeatherListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private List<Weather> weathers = Collections.emptyList();
    private final WeatherDao dao;
    WeatherListAdapter(WeatherDao dao){
        this.dao = dao;
    }



    void setWeathers(List<Weather> weathers){
        this.weathers = weathers;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.weather_list_item,
                parent, false);
        return new WeatherViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        //hier einfach alle Views aufführen und setzen die zur Darstellung gebraucht werden
        TextView aussentempText = holder.itemView.findViewById(R.id.temperatur_text);
        TextView luftfeuchtText = holder.itemView.findViewById(R.id.luftfeucht_text);
        TextView heatText = holder.itemView.findViewById(R.id.heat_text);
        Resources res = holder.itemView.getContext().getResources();


        ImageButton delbutton = holder.itemView.findViewById(R.id.delete_button);
        Weather aktuellesWeather = weathers.get(position);

        aussentempText.setText((aktuellesWeather.getTemp() +
                (aktuellesWeather.getIsFahrenheit() ?
                        res.getString(R.string.f) : res.getString(R.string.c))));
        luftfeuchtText.setText((aktuellesWeather.getHumidity()
                + res.getString(R.string.string_percent)));
        heatText.setText((aktuellesWeather.getHeatIndex() +
                (aktuellesWeather.getIsFahrenheit() ?
                        res.getString(R.string.f) : res.getString(R.string.c))));




        delbutton.setOnClickListener((view) -> {
            String jaString = holder.itemView.getResources().getString(R.string.ja_auswahl);
            new AlertDialog.Builder(holder.itemView.getContext())
                    .setTitle(R.string.löschen_title)
                    .setMessage(R.string.eintrag_löschen_frage)
                    .setPositiveButton(jaString, (a, b) ->
                            new DeleteWeatherTask(dao, this).execute(weathers.get(position)))
                    .setNegativeButton(R.string.abbruch_auswahl, null)
                    .show();
        });
    }

    @Override
    public int getItemCount() {
        return weathers.size();
    }
}
