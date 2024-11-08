package com.example.ejerciciosemana13.activities;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ejerciciosemana13.R;
import com.example.ejerciciosemana13.adapters.AnimeAdapter;
import com.example.ejerciciosemana13.api.ApiService;
import com.example.ejerciciosemana13.api.RetrofitClient;
import com.example.ejerciciosemana13.models.AnimeModel;
import com.example.ejerciciosemana13.models.ApiResponse;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private AnimeAdapter adapter;
    private List<AnimeModel> animeList = new ArrayList<>();
    private ApiService apiService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Inicializar RecyclerView
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Inicializar Adapter
        adapter = new AnimeAdapter(this, animeList, malId -> {
            // Manejar el clic en un item
//            Intent intent = new Intent(MainActivity.this, EpisodesActivity.class);
//            intent.putExtra("malId", malId);
//            startActivity(intent);
        });

        recyclerView.setAdapter(adapter);

        // Inicializar Retrofit
        apiService = RetrofitClient.getClient().create(ApiService.class);

        // Cargar datos
        loadAnimeData();
    }

    private void loadAnimeData() {
        Call<ApiResponse> call = apiService.getTopAnime("ova");
        call.enqueue(new Callback<ApiResponse>() {
            @Override
            public void onResponse(Call<ApiResponse> call, Response<ApiResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    animeList.clear();
                    animeList.addAll(response.body().getData());
                    adapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onFailure(Call<ApiResponse> call, Throwable t) {
                Toast.makeText(MainActivity.this,
                        "Error al cargar los datos: " + t.getMessage(),
                        Toast.LENGTH_SHORT).show();
            }
        });
    }
}