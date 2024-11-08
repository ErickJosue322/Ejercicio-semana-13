package com.example.ejerciciosemana13.adapters;



import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ejerciciosemana13.R;
import com.example.animeovalist.models.EpisodeModel;

import java.util.List;

public class EpisodesAdapter extends RecyclerView.Adapter<EpisodesAdapter.EpisodeViewHolder> {
    private Context context;
    private List<EpisodeModel> episodes;

    public EpisodesAdapter(Context context, List<EpisodeModel> episodes) {
        this.context = context;
        this.episodes = episodes;
    }

    @NonNull
    @Override
    public EpisodeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_episode, parent, false);
        return new EpisodeViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull EpisodeViewHolder holder, int position) {
        EpisodeModel episode = episodes.get(position);

        holder.episodeNumber.setText("Episode " + episode.getEpisodeId());
        holder.episodeTitle.setText(episode.getTitle());
        holder.episodeAired.setText("Aired: " + episode.getAired());
        holder.episodeScore.setText("Score: " + episode.getScore());

        // Mostrar indicadores de Filler/Recap si existen
        if (episode.isFiller()) {
            holder.episodeFiller.setVisibility(View.VISIBLE);
        } else {
            holder.episodeFiller.setVisibility(View.GONE);
        }

        if (episode.isRecap()) {
            holder.episodeRecap.setVisibility(View.VISIBLE);
        } else {
            holder.episodeRecap.setVisibility(View.GONE);
        }
    }

    @Override
    public int getItemCount() {
        return episodes != null ? episodes.size() : 0;
    }

    public static class EpisodeViewHolder extends RecyclerView.ViewHolder {
        TextView episodeNumber, episodeTitle, episodeAired, episodeScore;
        TextView episodeFiller, episodeRecap;

        public EpisodeViewHolder(View itemView) {
            super(itemView);
            episodeNumber = itemView.findViewById(R.id.episodeNumber);
            episodeTitle = itemView.findViewById(R.id.episodeTitle);
            episodeAired = itemView.findViewById(R.id.episodeAired);
            episodeScore = itemView.findViewById(R.id.episodeScore);
            episodeFiller = itemView.findViewById(R.id.episodeFiller);
            episodeRecap = itemView.findViewById(R.id.episodeRecap);
        }
    }
}
