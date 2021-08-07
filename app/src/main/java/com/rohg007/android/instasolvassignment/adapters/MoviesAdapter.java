package com.rohg007.android.instasolvassignment.adapters;

import android.app.LauncherActivity;
import android.content.Context;
import android.net.Uri;
import android.provider.ContactsContract;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.rohg007.android.instasolvassignment.R;
import com.rohg007.android.instasolvassignment.models.Movie;
import com.rohg007.android.instasolvassignment.models.MovieEntity;
import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import static com.rohg007.android.instasolvassignment.utils.Keys.imageBaseURL;

public class MoviesAdapter extends RecyclerView.Adapter<MoviesAdapter.MoviesViewHolder> {

    public interface OnItemClickListener {
        void onItemClick(MovieEntity movie, ImageView imageView);
    }


    private ArrayList<MovieEntity> movies;
    private final OnItemClickListener listener;

    public MoviesAdapter(ArrayList<MovieEntity> movies, OnItemClickListener listener) {
        this.listener = listener;
        this.movies = movies;
    }

    @NonNull
    @Override
    public MoviesAdapter.MoviesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.movie_list_item, parent, false);
        return new MoviesViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MoviesViewHolder holder, int position) {
        holder.bind(movies.get(position), listener);
    }

    @Override
    public int getItemCount() {
        return movies.size();
    }

    public class MoviesViewHolder extends RecyclerView.ViewHolder{

        ImageView itemImage;
        TextView itemTitle;

        public MoviesViewHolder(@NonNull View itemView) {
            super(itemView);

            itemImage = itemView.findViewById(R.id.item_image);
            itemTitle = itemView.findViewById(R.id.item_title);
        }

        public void bind(final MovieEntity movie, final OnItemClickListener listener){
            itemTitle.setText(movie.getMovieTitle());
            Picasso.get()
                    .load(imageBaseURL+movie.getPosterPath())
                    .into(itemImage);
            itemView.setOnClickListener(view -> listener.onItemClick(movie, itemImage));
        }
    }
}
