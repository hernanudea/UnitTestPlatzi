package dev.velasquez.javatest.movies.data;

import dev.velasquez.javatest.movies.model.Movie;

import java.util.Collection;
import java.util.List;

public interface MovieRepository {

    Movie findById(long id);

    Collection<Movie> findAll();

    void saveOrUpdate(Movie movie);

    List<Movie> findByName(String name);

    List<Movie> findByDirectorName(String directorName);
}