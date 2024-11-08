package com.example.animeovalist.models;

public class EpisodeModel {
    private int episodeId;
    private String title;
    private String aired;
    private double score;
    private boolean isFiller;
    private boolean isRecap;

    // Getters y Setters
    public int getEpisodeId() {
        return episodeId;
    }

    public void setEpisodeId(int episodeId) {
        this.episodeId = episodeId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAired() {
        return aired;
    }

    public void setAired(String aired) {
        this.aired = aired;
    }

    public double getScore() {
        return score;
    }

    public void setScore(double score) {
        this.score = score;
    }

    public boolean isFiller() {
        return isFiller;
    }

    public void setFiller(boolean filler) {
        isFiller = filler;
    }

    public boolean isRecap() {
        return isRecap;
    }

    public void setRecap(boolean recap) {
        isRecap = recap;
    }
}
