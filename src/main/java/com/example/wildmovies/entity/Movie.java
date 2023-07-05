package com.example.wildmovies.entity;

import jakarta.persistence.*;

// préférer LocalDate et LocalDateTime à java.util.Date ou java.sql.Date
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
public class Movie {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private String title;

    private String poster;

    private LocalDateTime releaseDate;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    public Movie() {
    }

    public Movie(UUID id, String title, String poster, LocalDateTime releaseDate) {
        this.id = id;
        this.title = title;
        this.poster = poster;
        this.releaseDate = releaseDate;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPoster() {
        return poster;
    }

    public void setPoster(String poster) {
        this.poster = poster;
    }

    public LocalDateTime getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(LocalDateTime releaseDate) {
        this.releaseDate = releaseDate;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }
}
