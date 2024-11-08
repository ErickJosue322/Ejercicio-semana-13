package com.example.animeovalist.models;

import java.util.List;

public class EpisodesResponse {
    private List<com.example.animeovalist.models.EpisodeModel> episodes;

    public List<com.example.animeovalist.models.EpisodeModel> getEpisodes() {
        return episodes;
    }

    public void setEpisodes(List<com.example.animeovalist.models.EpisodeModel> episodes) {
        this.episodes = episodes;
    }
}
