package com.znetdevelopment.movies.datafetchers;

import com.znetdevelopment.movies.graphql.DataFetcher;
import com.znetdevelopment.movies.graphql.DataFetchers;
import com.znetdevelopment.movies.model.Actor;
import com.znetdevelopment.movies.model.Director;
import com.znetdevelopment.movies.model.Movie;
import com.znetdevelopment.movies.services.ActorService;
import com.znetdevelopment.movies.services.MovieService;
import io.reactivex.Single;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.List;

/**
 * Data Fetcher responsible for fetching the root queries.
 */
@Singleton
public class QueryDataFetchers implements DataFetchers {

    @Inject private MovieService movieService;
    @Inject private ActorService actorService;

    /**
     * Get the list of all movies.
     *
     * @return  The list of all movies
     */
    @DataFetcher
    public Single<List<Movie>> getMovies() {
        return movieService.getMovies();
    }

    /**
     * Get the list of all actors.
     *
     * @return  The list of all actors
     */
    @DataFetcher(property = "actors")
    public Single<List<Actor>> getActors() {
        return actorService.getActors();
    }
}
