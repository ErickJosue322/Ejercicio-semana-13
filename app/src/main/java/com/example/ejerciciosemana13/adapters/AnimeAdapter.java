package com.example.ejerciciosemana13.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.ejerciciosemana13.R;
import com.example.ejerciciosemana13.activities.EpisodesActivity;
import com.example.ejerciciosemana13.models.AnimeModel;

import java.util.List;

public class AnimeAdapter extends RecyclerView.Adapter<AnimeAdapter.AnimeViewHolder> {
    private List<AnimeModel> animeList;
    private Context context;
    private OnItemClickListener listener;

    public interface OnItemClickListener {
        void onItemClick(int malId);
    }

    public AnimeAdapter(Context context, List<AnimeModel> animeList, OnItemClickListener listener) {
        this.context = context;
        this.animeList = animeList;
        this.listener = listener;
    }

    @NonNull
    @Override
    public AnimeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_anime, parent, false);
        return new AnimeViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AnimeViewHolder holder, int position) {
        AnimeModel anime = animeList.get(position);

        holder.titleView.setText(anime.getTitle());
      //  holder.airedView.setText("Aired: " + anime.getAiredString());
        holder.episodesView.setText("Episodes: " + anime.getEpisodes());

        Glide.with(context)
                .load(anime.getImages().getJpg().getImageUrl())
                .into(holder.imageView);

        holder.itemView.setOnClickListener(v -> listener.onItemClick(anime.getMalId()));

        holder.itemView.setOnClickListener(v -> {
            Intent intent = new Intent(context, EpisodesActivity.class);
            intent.putExtra("malId", anime.getMalId());
            intent.putExtra("animeTitle", anime.getTitle());
            context.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return animeList.size();
    }

    static class AnimeViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView titleView;
        TextView airedView;
        TextView episodesView;

        public AnimeViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.animeImage);
            titleView = itemView.findViewById(R.id.animeTitle);
            airedView = itemView.findViewById(R.id.animeAired);
            episodesView = itemView.findViewById(R.id.animeEpisodes);
        }
    }
}