package com.example.ejerciciosemana13.api;

import com.example.ejerciciosemana13.models.ApiResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

// ApiService.java
public interface ApiService {
    @GET("top/anime")
    Call<ApiResponse> getTopAnime(@Query("type") String type);
    @GET("anime/{id}/episodes")
    Call<com.example.animeovalist.models.EpisodesResponse> getAnimeEpisodes(@Path("id") int animeId);
}
