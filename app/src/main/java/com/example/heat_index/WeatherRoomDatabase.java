package com.example.heat_index;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {Weather.class}, version = 2, exportSchema = false)
public abstract class WeatherRoomDatabase extends RoomDatabase {
    public abstract WeatherDao weatherDao();

    private static WeatherRoomDatabase INSTANCE;

    static WeatherRoomDatabase getDatabase(Context context){
        if(INSTANCE == null){
            INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                    WeatherRoomDatabase.class, "Weather_Database")
                    .fallbackToDestructiveMigration().build();
        }
        return INSTANCE;
    }

}
