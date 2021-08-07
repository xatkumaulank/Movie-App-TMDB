package com.rohg007.android.instasolvassignment.viewmodels;

import android.app.Application;

import com.rohg007.android.instasolvassignment.models.VideosResult;
import com.rohg007.android.instasolvassignment.network.VideosRepository;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

public class VideosViewModel extends AndroidViewModel {

    private final VideosRepository videosRepository;

    public VideosViewModel(@NonNull Application application, long movieID) {
        super(application);
        videosRepository = new VideosRepository(movieID);
    }

    public void getVideos(long movieID){
        videosRepository.getVideos(movieID);
    }

    public MutableLiveData<VideosResult> getVideosLiveData(){
        return videosRepository.getVideoMutableLiveData();
    }

    public MutableLiveData<Boolean> progressMutableLiveData(){
        return videosRepository.getProgressMutableLiveData();
    }

    public MutableLiveData<Boolean> failureMutableLiveData(){
        return videosRepository.getFailureMutableLiveData();
    }
}
