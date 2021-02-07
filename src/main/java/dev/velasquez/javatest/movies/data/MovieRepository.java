package dev.velasquez.javatest.movies.data;

import dev.velasquez.javatest.movies.model.Movie;

import java.util.Collection;

public interface MovieRepository {

    Movie findById(long id);

    Collection<Movie> findAll();

    void saveOrUpdate(Movie movie);
}