package com.example.heat_index;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

@Dao
public interface WeatherDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public void insert(Weather weather);

    @Query("SELECT * FROM Weather")
    public List<Weather> getAll();

    @Delete
    public void delete(Weather weather);


}
