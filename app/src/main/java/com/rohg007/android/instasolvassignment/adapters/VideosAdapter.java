package com.rohg007.android.instasolvassignment.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.rohg007.android.instasolvassignment.R;
import com.rohg007.android.instasolvassignment.models.Video;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import static com.rohg007.android.instasolvassignment.utils.Keys.imageBaseURL;
import static com.rohg007.android.instasolvassignment.utils.Keys.ytImageBaseURL;

public class VideosAdapter extends RecyclerView.Adapter<VideosAdapter.VideoViewHolder> {

    public interface OnItemClickListener {
        void onItemClick(String videoID);
    }

    private ArrayList<Video> videos;
    private final VideosAdapter.OnItemClickListener listener;

    public VideosAdapter(ArrayList<Video> videos, VideosAdapter.OnItemClickListener listener) {
        this.videos = videos;
        this.listener = listener;
    }

    @NonNull
    @Override
    public VideosAdapter.VideoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.video_list_item, parent, false);
        return new VideoViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull VideosAdapter.VideoViewHolder holder, int position) {
        holder.bind(videos.get(position), listener);
    }

    @Override
    public int getItemCount() {
        return videos.size();
    }

    public class VideoViewHolder extends RecyclerView.ViewHolder{
        private ImageView videoImage;

        public VideoViewHolder(@NonNull View itemView) {
            super(itemView);
            videoImage = itemView.findViewById(R.id.video_list_image);
        }

        public void bind(final Video video, final VideosAdapter.OnItemClickListener listener){
            Picasso.get()
                    .load(ytImageBaseURL+"/"+video.getKey()+"/0.jpg")
                    .into(videoImage);
            itemView.setOnClickListener(view -> listener.onItemClick(video.getKey()));
        }
    }
}
