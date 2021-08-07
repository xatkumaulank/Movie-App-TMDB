package com.rohg007.android.instasolvassignment.viewmodels;

import android.app.Application;

import com.rohg007.android.instasolvassignment.models.ReviewResult;
import com.rohg007.android.instasolvassignment.network.ReviewsRepository;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

public class ReviewViewModel extends AndroidViewModel {

    private final ReviewsRepository reviewsRepository;

    public ReviewViewModel(@NonNull Application application, long movieID) {
        super(application);
        reviewsRepository = new ReviewsRepository(movieID);
    }

    public void getReviews(long movieID){
        reviewsRepository.getReviews(movieID);
    }

    public MutableLiveData<ReviewResult> getReviewResultLiveData(){
        return reviewsRepository.getReviewMutableLiveData();
    }

    public MutableLiveData<Boolean> progressMutableLiveData(){
        return reviewsRepository.getProgressMutableLiveData();
    }

    public MutableLiveData<Boolean> failureMutableLiveData(){
        return reviewsRepository.getFailureMutableLiveData();
    }
}
