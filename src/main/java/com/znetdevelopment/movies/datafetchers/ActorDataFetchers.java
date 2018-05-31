package com.znetdevelopment.movies.datafetchers;

import com.znetdevelopment.movies.graphql.DataFetcher;
import com.znetdevelopment.movies.graphql.DataFetchers;
import com.znetdevelopment.movies.model.Actor;
import com.znetdevelopment.movies.model.Director;
import com.znetdevelopment.movies.model.Movie;
import com.znetdevelopment.movies.services.MovieService;
import io.reactivex.Single;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.List;

/**
 * Data Fetcher responsible for fetching information related to an actor.
 */
@Singleton
public class ActorDataFetchers implements DataFetchers {

    @Inject private MovieService movieService;

    /**
     * Get the list of movies that an actor appeared in.
     *
     * @param actor  The particular actor
     *
     * @return  The list of movies
     */
    @DataFetcher(property = "actedInMovies")
    public Single<List<Movie>> getMoviesActedIn(Actor actor) {
        return movieService.getMoviesActedIn(actor);
    }
}
