package com.cansalman.marvelbook.service;

import com.cansalman.marvelbook.model.DataModel;

import io.reactivex.Observable;
import retrofit2.http.GET;

public interface MarvelAPI {

    @GET("characters?ts=1&apikey=YOURKEY&limit=100&offset=100")
    Observable<DataModel> getData();
}
