package com.znetdevelopment.movies.services;

import com.znetdevelopment.movies.jdbc.JdbcService;
import com.znetdevelopment.movies.model.Actor;
import com.znetdevelopment.movies.model.Category;
import com.znetdevelopment.movies.model.Director;
import com.znetdevelopment.movies.model.Movie;
import io.reactivex.Single;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Singleton
public class MovieService {

    @Inject private JdbcService jdbcService;

    /**
     * Get the list of all movies.
     *
     * @return  The list of all movies
     */
    public Single<List<Movie>> getMovies() {
        return jdbcService.findAll(
                "select * from Movie m " +
                        "inner join Category c on c.id = m.categoryId " +
                        "inner join Director d on d.id = m.directorId"
        ).transform(this::buildMovie);
    }

    /**
     * Get the list of all movies that the particular actor appeared in.
     *
     * @param actor  The particular actor
     *
     * @return  The list of movies
     */
    public Single<List<Movie>> getMoviesActedIn(Actor actor) {
        return jdbcService.findAll(
                "select * from ActorMovie am " +
                        "inner join Movie m on am.movieId = m.id " +
                        "inner join Category c on c.id = m.categoryId " +
                        "inner join Director d on d.id = m.directorId " +
                        "where am.actorId = ?",
                actor.getId()
        ).transform(this::buildMovie);
    }

    /**
     * Get the list of movies directed by the particular director.
     *
     * @param director  The particular director
     *
     * @return  The list of movies
     */
    public Single<List<Movie>> getMoviesDirected(Director director) {
        return jdbcService.findAll(
                "select * from Movie m " +
                        "inner join Director d on m.directorId = d.id " +
                        "inner join Category c on c.id = m.categoryId " +
                        "where m.directorId = ?",
                director.getId()
        ).transform(this::buildMovie);
    }

    protected Movie buildMovie(ResultSet results) throws SQLException {
        String id = results.getString("Movie.id");
        String name = results.getString("Movie.name");
        Integer duration = results.getInt("Movie.duration");
        Integer releaseYear = results.getInt("Movie.releaseYear");
        String description = results.getString("Movie.description");

        Movie movie = new Movie(id, name);
        movie.setDuration(duration);
        movie.setReleaseYear(releaseYear);
        movie.setDescription(description);

        movie.setCategory(buildCategory(results));
        movie.setDirector(buildDirector(results));

        return movie;
    }

    protected Category buildCategory(ResultSet results) throws SQLException {
        String id = results.getString("Category.id");
        String name = results.getString("Category.name");
        String description = results.getString("Category.description");

        Category category = new Category(id, name);
        category.setDescription(description);

        return category;
    }

    protected Director buildDirector(ResultSet results) throws SQLException {
        String id = results.getString("Director.id");
        String firstName = results.getString("Director.firstName");
        String lastName = results.getString("Director.lastName");

        Director director = new Director(id, firstName, lastName);
        return director;
    }
}
