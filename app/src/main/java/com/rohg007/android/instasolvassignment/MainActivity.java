package com.rohg007.android.instasolvassignment;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityOptionsCompat;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.facebook.shimmer.ShimmerFrameLayout;
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton;
import com.rohg007.android.instasolvassignment.adapters.MoviesAdapter;
import com.rohg007.android.instasolvassignment.models.MovieEntity;
import com.rohg007.android.instasolvassignment.viewmodels.PopularMoviesViewModel;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ArrayList<MovieEntity> movieEntities;

    private RecyclerView moviesRv;
    private ShimmerFrameLayout shimmerFrameLayout;
    private ExtendedFloatingActionButton failureFab;
    private TextView emptyView;

    private PopularMoviesViewModel popularMoviesViewModel;

    private MoviesAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initViewModels();
        bindViews();

        movieEntities = new ArrayList<>();
        setupRecyclerView();

        handleAPIFailure();
        handleProgress();
        handleDataChange();

        failureFab.setOnClickListener(view -> popularMoviesViewModel.getPopularMovies());
    }

    private void bindViews(){
        moviesRv = findViewById(R.id.movies_rv);
        shimmerFrameLayout = findViewById(R.id.shimmer_view_container);
        failureFab = findViewById(R.id.retry_fab);
        emptyView = findViewById(R.id.empty_view);
    }

    private void initViewModels(){
        popularMoviesViewModel = new ViewModelProvider(this).get(PopularMoviesViewModel.class);
    }

    private void setupRecyclerView(){
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 2);
        moviesRv.setLayoutManager(gridLayoutManager);

        adapter = new MoviesAdapter(movieEntities, this::goToDetails);

        moviesRv.setAdapter(adapter);
        manageFabOnScroll();
    }

    private void goToDetails(MovieEntity movie, ImageView imageView){
        Intent intent = new Intent(this, MovieDetailActivity.class);
        intent.putExtra("MOVIE", movie);
        ActivityOptionsCompat options = ActivityOptionsCompat.makeSceneTransitionAnimation(this, imageView, "poster");
        startActivity(intent, options.toBundle());
    }

    private void handleAPIFailure(){
        popularMoviesViewModel.getFailureLiveData().observe(this, failure->{
            if(failure){
                failureFab.setVisibility(View.VISIBLE);
            } else {
                failureFab.setVisibility(View.GONE);
            }
        });
    }

    private void handleProgress(){
        popularMoviesViewModel.getProgressLiveData().observe(this, progress -> {
            if(progress){
                shimmerFrameLayout.startShimmerAnimation();
                shimmerFrameLayout.setVisibility(View.VISIBLE);
                moviesRv.setVisibility(View.GONE);
            } else {
                shimmerFrameLayout.stopShimmerAnimation();
                shimmerFrameLayout.setVisibility(View.GONE);
                moviesRv.setVisibility(View.VISIBLE);
            }
        });
    }

    private void handleDataChange(){
        popularMoviesViewModel.getMovieEntityLiveData().observe(this, movieEntities -> {
            if(movieEntities!=null){
                this.movieEntities.clear();
                if(!movieEntities.isEmpty()) {
                    this.movieEntities.addAll(movieEntities);
                    emptyView.setVisibility(View.GONE);
                } else {
                    emptyView.setVisibility(View.VISIBLE);
                }
                adapter.notifyDataSetChanged();
            } else {
                emptyView.setVisibility(View.VISIBLE);
            }
        });
    }

    private void manageFabOnScroll(){
        moviesRv.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                if(dy!=0 && failureFab.isExtended())
                    failureFab.shrink();
                super.onScrolled(recyclerView, dx, dy);

            }

            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                if(newState == RecyclerView.SCROLL_STATE_IDLE && !failureFab.isExtended() && moviesRv.computeVerticalScrollOffset()==0)
                    failureFab.extend();
                super.onScrollStateChanged(recyclerView, newState);
            }
        });
    }
}
