package com.example.heat_index;

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
    public WeatherListAdapter(WeatherDao dao){
        this.dao = dao;
    }



    public void setWeathers(List<Weather> weathers){
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
        TextView heatText = holder.itemView.findViewById(R.id.heat_text);
        ImageButton delbutton = holder.itemView.findViewById(R.id.delete_button);
        heatText.setText("" + weathers.get(position).getHeatIndex());
        delbutton.setOnClickListener((view) -> {
            new DeleteWeatherTask(dao, this).execute(weathers.get(position));
        });
    }

    @Override
    public int getItemCount() {
        return weathers.size();
    }
}
