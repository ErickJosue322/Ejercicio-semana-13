package com.example.ejerciciosemana13.activities;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.widget.Toolbar;




import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.ejerciciosemana13.models.EpisodeModel;
import com.example.ejerciciosemana13.models.EpisodesResponse;


import com.example.ejerciciosemana13.R;
import com.example.ejerciciosemana13.adapters.EpisodesAdapter;
import com.example.ejerciciosemana13.api.ApiService;
import com.example.ejerciciosemana13.api.RetrofitClient;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EpisodesActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private EpisodesAdapter adapter;
    private List<com.example.animeovalist.models.EpisodeModel> episodeList = new ArrayList<>();
    private ApiService apiService;
    private ProgressBar progressBar;
    private TextView errorView;
    private int animeId;
    private String animeTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_episodes);

        // Obtener datos del intent
        animeId = getIntent().getIntExtra("malId", -1);
        animeTitle = getIntent().getStringExtra("animeTitle");

        if (animeId == -1) {
            Toast.makeText(this, "Error: Anime ID no encontrado", Toast.LENGTH_SHORT).show();
            finish();
            return;
        }

        // Configurar Toolbar
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle(animeTitle);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        // Inicializar vistas
        initViews();

        // Configurar RecyclerView
        setupRecyclerView();

        // Inicializar Retrofit
        apiService = RetrofitClient.getClient().create(ApiService.class);

        // Cargar episodios
        loadEpisodes();
    }

    private void initViews() {
        recyclerView = findViewById(R.id.episodesRecyclerView);
        progressBar = findViewById(R.id.progressBar);
        errorView = findViewById(R.id.errorView);
    }

    private void setupRecyclerView() {
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new EpisodesAdapter(this, episodeList);
        recyclerView.setAdapter(adapter);

        // Agregar decoración para espaciado entre items
        recyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
    }

    private void loadEpisodes() {
        showLoading();

        apiService.getAnimeEpisodes(animeId).enqueue(new Callback<com.example.animeovalist.models.EpisodesResponse>() {
            @Override
            public void onResponse(Call<com.example.animeovalist.models.EpisodesResponse> call, Response<com.example.animeovalist.models.EpisodesResponse> response) {
                hideLoading();

                if (response.isSuccessful() && response.body() != null) {
                    List<com.example.animeovalist.models.EpisodeModel> episodes = response.body().getEpisodes();
                    if (!episodes.isEmpty()) {
                        episodeList.clear();
                        episodeList.addAll(episodes);
                        adapter.notifyDataSetChanged();
                    } else {
                        showError("No hay episodios disponibles");
                    }
                } else {
                    showError("Error al cargar los episodios");
                }
            }

            @Override
            public void onFailure(Call<com.example.animeovalist.models.EpisodesResponse> call, Throwable t) {
                hideLoading();
                showError("Error de conexión: " + t.getMessage());
            }
        });
    }

    private void showLoading() {
        progressBar.setVisibility(View.VISIBLE);
        errorView.setVisibility(View.GONE);
        recyclerView.setVisibility(View.GONE);
    }

    private void hideLoading() {
        progressBar.setVisibility(View.GONE);
        recyclerView.setVisibility(View.VISIBLE);
    }

    private void showError(String message) {
        errorView.setText(message);
        errorView.setVisibility(View.VISIBLE);
        recyclerView.setVisibility(View.GONE);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}