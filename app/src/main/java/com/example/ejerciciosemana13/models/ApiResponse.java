package com.example.ejerciciosemana13.models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

// ApiResponse.java
public class ApiResponse {
    @SerializedName("data")
    private List<AnimeModel> data;

    public List<AnimeModel> getData() {
        return data;
    }
}