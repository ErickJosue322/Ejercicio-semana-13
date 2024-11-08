package com.example.ejerciciosemana13.models;

import com.google.gson.annotations.SerializedName;

public class AnimeModel {
    @SerializedName("mal_id")
    private int malId;

    @SerializedName("title")
    private String title;

    @SerializedName("images")
    private Images images;

    @SerializedName("aired")
    private Aired aired;

    @SerializedName("episodes")
    private int episodes;

    public Images getImages() {
        return images;
    }

    public void setImages(Images images) {
        this.images = images;
    }

    public int getMalId() {
        return malId;
    }

    public void setMalId(int malId) {
        this.malId = malId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Aired getAired() {
        return aired;
    }

    public void setAired(Aired aired) {
        this.aired = aired;
    }

    public int getEpisodes() {
        return episodes;
    }

    public void setEpisodes(int episodes) {
        this.episodes = episodes;
    }

    // Clases anidadas para manejar el JSON
    public static class Images {
        @SerializedName("jpg")
        private Jpg jpg;

        public static class Jpg {
            @SerializedName("image_url")
            private String imageUrl;

            public String getImageUrl() {
                return imageUrl;
            }
        }

        public Jpg getJpg() {
            return jpg;
        }
    }

    public static class Aired {
        @SerializedName("string")
        private String string;

        public String getString() {
            return string;
        }
    }
}
