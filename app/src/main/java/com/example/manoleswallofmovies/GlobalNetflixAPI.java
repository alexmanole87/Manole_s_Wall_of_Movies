package com.example.manoleswallofmovies;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;

public interface GlobalNetflixAPI {
    @Headers({
            "X-RapidAPI-Key: your_api_key_here",
            "X-RapidAPI-Host: unogs-unogs-v1.p.rapidapi.com"
    })
    @GET("search/titles?order_by=date&type=movie")
    Call<NewReleasesResponse> searchTitles();
}
