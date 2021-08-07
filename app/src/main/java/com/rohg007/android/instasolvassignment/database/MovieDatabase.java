package com.rohg007.android.instasolvassignment.database;

import android.content.Context;

import com.rohg007.android.instasolvassignment.dao.MovieDao;
import com.rohg007.android.instasolvassignment.models.MovieEntity;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

@Database(entities = MovieEntity.class, version = 1, exportSchema = false)
public abstract class MovieDatabase extends RoomDatabase {

    private static MovieDatabase movieDatabase;

    public abstract MovieDao movieDao();

    public static synchronized MovieDatabase getInstance(Context context){
        if(movieDatabase==null)
            movieDatabase = Room.databaseBuilder(context.getApplicationContext(), MovieDatabase.class, "movie_db")
                    .fallbackToDestructiveMigration()
                    .addCallback(roomCallback)
                    .build();
        return movieDatabase;
    }

    private static RoomDatabase.Callback roomCallback = new RoomDatabase.Callback(){
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
        }
    };

}
