package com.example.wildmovies.entity;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;

import java.util.UUID;

@Entity
@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "id")
public class MovieComment {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Lob
    private String content;

    private Float rating;

    // relié à un film
    // celui qui contient la clef étrangère
    @ManyToOne
    @JoinColumn(name = "movie_id")
    private Movie movieCommented;

    // relié à un utilisateur
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User author;

    public MovieComment() {
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Float getRating() {
        return rating;
    }

    public void setRating(Float rating) {
        this.rating = rating;
    }

    public Movie getMovieCommented() {
        return movieCommented;
    }

    public void setMovieCommented(Movie movieCommented) {
        this.movieCommented = movieCommented;
    }

    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }
}
