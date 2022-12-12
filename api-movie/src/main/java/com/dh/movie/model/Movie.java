package com.dh.movie.model;

import javax.persistence.*;

@Entity
@Table(name = "movies")
public class Movie {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String genre;
    private String urlStream;

    public Movie(String name, String genre, String urlStream) {
        this.name = name;
        this.genre = genre;
        this.urlStream = urlStream;
    }

    public Movie() {
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getGenre() {
        return genre;
    }

    public String getUrlStream() {
        return urlStream;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public void setUrlStream(String urlStream) {
        this.urlStream = urlStream;
    }
}
