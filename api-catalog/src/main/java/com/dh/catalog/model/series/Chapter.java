package com.dh.catalog.model.series;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.UUID;

@Document(collection = "Chapters")
public class Chapter {

    @Id
    private String id = UUID.randomUUID().toString();
    private String name;
    private Integer number;
    private String urlStream;

    public Chapter(String name, Integer number, String urlStream) {
        this.name = name;
        this.number = number;
        this.urlStream = urlStream;
    }

    public Chapter() {
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Integer getNumber() {
        return number;
    }

    public String getUrlStream() {
        return urlStream;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public void setUrlStream(String urlStream) {
        this.urlStream = urlStream;
    }
}
