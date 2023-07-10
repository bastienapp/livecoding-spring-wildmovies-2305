package com.example.wildmovies.controller;

import com.example.wildmovies.entity.Movie;
import com.example.wildmovies.entity.MovieComment;
import com.example.wildmovies.entity.User;
import com.example.wildmovies.repository.MovieCommentRepository;
import com.example.wildmovies.repository.MovieRepository;
import com.example.wildmovies.repository.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Set;
import java.util.UUID;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserRepository userRepository;
    private final MovieRepository movieRepository;
    private final MovieCommentRepository movieCommentRepository;

    public UserController(
            UserRepository userRepositoryInjected,
            MovieRepository movieRepositoryInjected,
            MovieCommentRepository movieCommentRepositoryInjected
    ) {
        this.userRepository = userRepositoryInjected;
        this.movieRepository = movieRepositoryInjected;
        this.movieCommentRepository = movieCommentRepositoryInjected;
    }

    @GetMapping("")
    public List<User> getAll() {
        return this.userRepository.findAll();
    }

    @PostMapping("")
    public User create(@RequestBody User newUser) {
        return this.userRepository.save(newUser);
    }

    // ajouter un film en favoris : GET <POST> (car on créé un truc dans la base) PUT DELETE

    @PostMapping("/{userId}/movies/{movieId}/favourites")
    public User addMovieToFavourites(
            @PathVariable UUID userId,
            @PathVariable UUID movieId) {
        User user = this.userRepository
                .findById(userId)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "User not found with id " + userId));
        Movie movie = this.movieRepository
                .findById(movieId)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Movie not found with id " + movieId));
        // ajouter le film à la liste des favoris de l'utilisateur
        Set<Movie> favouriteList = user.getFavouriteMovies();
        favouriteList.add(movie);

        // enregistrer la modification
        return this.userRepository.save(user);
    }

    @PostMapping("/{userId}/movies/{movieId}/comments")
    @ResponseStatus(value = HttpStatus.CREATED)
    public MovieComment addCommentToMovie(
            @PathVariable UUID userId,
            @PathVariable UUID movieId,
            @RequestBody MovieComment comment
    ) {
        // que faire pour poster un nouveau commentaire en base de données ?

        // récupérer utilisateur
        User author = this.userRepository
                .findById(userId)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "User not found with id " + userId));

        // récupérer le film
        Movie movie = this.movieRepository
                .findById(movieId)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Movie not found with id " + movieId));

        // attacher l'utilisateur et le film au commentaire
        comment.setAuthor(author);
        comment.setMovieCommented(movie);

        // on enregistre
        return this.movieCommentRepository.save(comment);
    }
}
