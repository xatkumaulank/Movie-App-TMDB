package com.rohg007.android.instasolvassignment.network;

import android.util.Log;

import com.rohg007.android.instasolvassignment.models.ReviewResult;
import com.rohg007.android.instasolvassignment.models.VideosResult;

import java.util.Objects;

import androidx.lifecycle.MutableLiveData;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.rohg007.android.instasolvassignment.utils.Keys.apiKey;
import static com.rohg007.android.instasolvassignment.utils.Keys.language;

/*
    Repository class for managing api calls and data exchange between Room
 */
public class VideosRepository {

    private final VideosAPI videosAPI;
    private final MutableLiveData<VideosResult> videoMutableLiveData;
    private final MutableLiveData<Boolean> progressMutableLiveData;
    private final MutableLiveData<Boolean> failureMutableLiveData;

    public VideosRepository(long movieID){
        videosAPI = RetrofitService.createService(VideosAPI.class);
        videoMutableLiveData = new MutableLiveData<>();
        progressMutableLiveData = new MutableLiveData<>(false);
        failureMutableLiveData = new MutableLiveData<>(false);

        //called once to survive orientation changes
        getVideos(movieID);
    }

    public void getVideos(long movieID){
        progressMutableLiveData.setValue(true);
        failureMutableLiveData.setValue(false);

        videosAPI.getVideoResults(movieID, apiKey, language).enqueue(new Callback<VideosResult>() {
            @Override
            public void onResponse(Call<VideosResult> call, Response<VideosResult> response) {
                if(response.isSuccessful()){
                    videoMutableLiveData.setValue(response.body());
                }
                progressMutableLiveData.setValue(false);
                failureMutableLiveData.setValue(false);
            }

            @Override
            public void onFailure(Call<VideosResult> call, Throwable t) {
                progressMutableLiveData.setValue(false);
                failureMutableLiveData.setValue(true);
                Log.e("Video Failure: ", Objects.requireNonNull(t.getLocalizedMessage()));
            }
        });
    }

    public MutableLiveData<VideosResult> getVideoMutableLiveData() {
        return videoMutableLiveData;
    }

    public MutableLiveData<Boolean> getProgressMutableLiveData() {
        return progressMutableLiveData;
    }

    public MutableLiveData<Boolean> getFailureMutableLiveData() {
        return failureMutableLiveData;
    }
}
