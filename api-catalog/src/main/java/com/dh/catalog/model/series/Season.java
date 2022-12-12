package com.dh.catalog.model.series;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Document(collection = "Seasons")
public class Season {

    @Id
    private String id = UUID.randomUUID().toString();
    private Integer seasonNumber;
    private List<Chapter> chapters = new ArrayList<>();

    public Season(Integer seasonNumber, List<Chapter> chapters) {
        this.seasonNumber = seasonNumber;
        this.chapters = chapters;
    }

    public Season() {
    }

    public String getId() {
        return id;
    }

    public Integer getSeasonNumber() {
        return seasonNumber;
    }

    public List<Chapter> getChapters() {
        return chapters;
    }

    public void setSeasonNumber(Integer seasonNumber) {
        this.seasonNumber = seasonNumber;
    }

    public void setChapters(List<Chapter> chapters) {
        this.chapters = chapters;
    }
}
