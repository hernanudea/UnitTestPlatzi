package dev.velasquez.javatest.movies.data;

import dev.velasquez.javatest.movies.model.Genre;
import dev.velasquez.javatest.movies.model.Movie;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import java.sql.Types;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

public class MovieRepositoryJdbc implements MovieRepository {

    private JdbcTemplate jdbcTemplate;

    public MovieRepositoryJdbc(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Movie findById(long id) {

        Object[] args = {id};

        return jdbcTemplate.queryForObject("select * from movies where id = ?", args, movieMapper);
    }

    @Override
    public Collection<Movie> findAll() {
        return jdbcTemplate.query("select * from movies", movieMapper);
    }

    @Override
    public void saveOrUpdate(Movie movie) {

        jdbcTemplate.update("insert into movies (name, minutes, genre) values (?, ?, ?)",
                movie.getName(), movie.getMinutes(), movie.getGenre().toString());
    }

    @Override
    public List<Movie> findByName(String name) {
        String sql = "Select * from movies where name like ?";

        List<Map<String, Object>> matchedMovies = jdbcTemplate.queryForList(sql, new Object[]{
                "%" + name + "%"
        }, new int[]{
                Types.VARCHAR
        });

        List<Movie> moviesList = new ArrayList<>();
        matchedMovies.forEach(rowMap -> {
            int ID = (int) rowMap.get("id");
            String nombre = (String) rowMap.get("name");
            int minute = (int) rowMap.get("minutes");
            Genre gen = Genre.valueOf(rowMap.get("genre").toString());
            String director = (String) rowMap.get("director");

            moviesList.add(new Movie(ID, nombre, minute, gen, director));
        });

        return moviesList;
    }

    @Override
    public List<Movie> findByDirectorName(String directorName) {
        String sql = "Select * from movies where director like ?";

        List<Map<String, Object>> matchedMovies = jdbcTemplate.queryForList(sql, new Object[]{
            "%" + directorName + "%"}, new int[]{
            Types.VARCHAR
        });

        List<Movie> moviesList = new ArrayList<>();
        matchedMovies.forEach(rowMap -> {
            int ID = (int) rowMap.get("id");
            String nombre = (String) rowMap.get("name");
            int minute = (int) rowMap.get("minutes");
            Genre gen = Genre.valueOf(rowMap.get("genre").toString());
            String director = (String) rowMap.get("director");

            moviesList.add(new Movie(ID, nombre, minute, gen, director));
        });

        return moviesList;
    }

    private static RowMapper<Movie> movieMapper = (rs, rowNum) ->
            new Movie(
                    rs.getInt("id"),
                    rs.getString("name"),
                    rs.getInt("minutes"),
                    Genre.valueOf(rs.getString("genre")),
                    "Director1");
}