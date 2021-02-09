package dev.velasquez.javatest.movies.data;

import static org.junit.Assert.*;

import dev.velasquez.javatest.movies.model.Genre;
import dev.velasquez.javatest.movies.model.Movie;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.jdbc.datasource.init.ScriptUtils;

import javax.sql.DataSource;

import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import static org.hamcrest.CoreMatchers.*;

public class MovieRepositoryIntegrationTest {

    private MovieRepositoryJdbc movieRepository;
    private DriverManagerDataSource dataSource;

    @Before
    public void setUp() throws Exception {
        dataSource =
                new DriverManagerDataSource("jdbc:h2:mem:test;MODE=MYSQL", "sa", "sa");

        ScriptUtils.executeSqlScript(dataSource.getConnection(), new ClassPathResource("sql-scripts/test-data.sql"));

        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);

        movieRepository = new MovieRepositoryJdbc(jdbcTemplate);
    }

    @Test
    public void load_all_movies() throws SQLException {

        Collection<Movie> movies = movieRepository.findAll();

        assertThat(movies, is(Arrays.asList(
                new Movie(1, "Dark Knight", 152, Genre.ACTION, "Director2"),
                new Movie(2, "Memento", 113, Genre.THRILLER, "Director3"),
                new Movie(3, "Matrix", 136, Genre.ACTION, "Director1")
        )));
    }

    @Test
    public void load_movie_by_id() {

        Movie movie = movieRepository.findById(2);

        assertThat(movie, is(new Movie(2, "Memento", 113, Genre.THRILLER, "Director2")));
    }

    @Test
    public void insert_a_movie() {

        Movie movie = new Movie("Super 8", 112, Genre.THRILLER, "Director1");

        movieRepository.saveOrUpdate(movie);

        Movie movieFromDb = movieRepository.findById(4);

        assertThat(movieFromDb, is(new Movie(4, "Super 8", 112, Genre.THRILLER, "Director1")));
    }

    //RETO 3
    //Buscar peliculas por nombre

    @Test
    public void find_movies_by_name() {

        List<Movie> movies = movieRepository.findByName("Dar");

        List<Movie> expectedMovies = new ArrayList<>();
        expectedMovies.add(new Movie(1, "Dark Knight", 152, Genre.ACTION, "Salas"));
        expectedMovies.add(new Movie(3, "Dartolomé", 117, Genre.THRILLER, "Salas"));

        assertThat(movies, is(expectedMovies));
    }

    @Test
    public void find_movies_by_director_name() {
        List<Movie> movies = movieRepository.findByDirectorName("Luis");

        List<Movie> expectedMovies = new ArrayList<Movie>();
        expectedMovies.add(new Movie(2, "Memento", 113, Genre.THRILLER, "Luis"));
        expectedMovies.add(new Movie(4, "Matrix", 136, Genre.ACTION, "Luis"));

        assertThat(movies, is(expectedMovies));
    }


    @After
    public void tearDown() throws Exception {

        // Remove H2 files -- https://stackoverflow.com/a/51809831/1121497
        final Statement s = dataSource.getConnection().createStatement();
        s.execute("drop all objects delete files"); // "shutdown" is also enough for mem db
    }
}