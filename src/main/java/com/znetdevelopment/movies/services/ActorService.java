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
public class ActorService {

    @Inject private JdbcService jdbcService;

    /**
     * Get the list of all actors.
     *
     * @return  The list of actors
     */
    public Single<List<Actor>> getActors() {
        return jdbcService.findAll(
                "select * from Actor"
        ).transform(this::buildActor);
    }

    /**
     * Get the list of actors appearing in the given movie.
     *
     * @param movie  The particular movie
     *
     * @return  The list of actors
     */
    public Single<List<Actor>> getActors(Movie movie) {
        return jdbcService.findAll(
                "select * from ActorMovie am " +
                        "inner join Actor a on am.actorId = a.id " +
                        "where am.movieId = ?",
                movie.getId()
        ).transform(this::buildActor);
    }

    protected Actor buildActor(ResultSet results) throws SQLException {
        String id = results.getString("Actor.id");
        String firstName = results.getString("Actor.firstName");
        String lastName = results.getString("Actor.lastName");

        Actor actor = new Actor(id, firstName, lastName);
        return actor;
    }
}
