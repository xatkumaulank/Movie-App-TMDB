package com.rohg007.android.instasolvassignment.dao;

import com.rohg007.android.instasolvassignment.models.MovieEntity;

import java.util.ArrayList;
import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

@Dao
public interface MovieDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAllMovies(ArrayList<MovieEntity> movies);

    @Query("DELETE FROM movie_table")
    void deleteAllMovies();

    @Query("SELECT * FROM movie_table")
    LiveData<List<MovieEntity>> getAllMovies();
}
