package com.dh.catalog.model.series;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Document(collection = "Series")
public class Serie {

    @Id
    private String id = UUID.randomUUID().toString();
    private String name;
    private String genre;
    private List<Season> seasons = new ArrayList<>();

    public Serie(String name, String genre, List<Season> seasons) {
        this.name = name;
        this.genre = genre;
        this.seasons = seasons;
    }

    public Serie() {
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getGenre() {
        return genre;
    }

    public List<Season> getSeasons() {
        return seasons;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public void setSeasons(List<Season> seasons) {
        this.seasons = seasons;
    }

}



