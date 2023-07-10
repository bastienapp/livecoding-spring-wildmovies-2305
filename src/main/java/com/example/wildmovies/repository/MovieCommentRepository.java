package com.example.wildmovies.repository;

import com.example.wildmovies.entity.MovieComment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface MovieCommentRepository extends JpaRepository<MovieComment, UUID> {
}
