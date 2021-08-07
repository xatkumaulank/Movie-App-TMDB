package com.rohg007.android.instasolvassignment.network;

import com.rohg007.android.instasolvassignment.models.PopularMoviesResult;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface PopularMoviesAPI {

    @GET("movie/popular")
    Call<PopularMoviesResult> getPopularMovies(
            @Query("api_key") String apiKey,
            @Query("language") String language,
            @Query("page") int page
    );
}
