package com.rohg007.android.instasolvassignment.viewmodels;

import android.app.Application;

import com.rohg007.android.instasolvassignment.models.MovieEntity;
import com.rohg007.android.instasolvassignment.network.PopularMoviesRepository;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

public class PopularMoviesViewModel extends AndroidViewModel {

    private final PopularMoviesRepository popularMoviesRepository;

    public PopularMoviesViewModel(@NonNull Application application) {
        super(application);
        popularMoviesRepository = PopularMoviesRepository.getInstance(application);
    }

    public void getPopularMovies(){
        popularMoviesRepository.getPopularMovies();
    }


    public MutableLiveData<Boolean> getProgressLiveData(){
        return popularMoviesRepository.getProgressMutableLiveData();
    }

    public MutableLiveData<Boolean> getFailureLiveData(){
        return popularMoviesRepository.getResponseFailureLiveData();
    }

    public LiveData<List<MovieEntity>> getMovieEntityLiveData(){
        return popularMoviesRepository.getMovieEntityLiveData();
    }
}
