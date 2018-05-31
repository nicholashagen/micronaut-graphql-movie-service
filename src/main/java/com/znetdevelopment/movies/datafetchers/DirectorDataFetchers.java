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
 * Data Fetcher responsible for fetching data related to a particular director.
 */
@Singleton
public class DirectorDataFetchers implements DataFetchers {

    @Inject private MovieService movieService;

    /**
     * Get the list of movies that the director directed.
     *
     * @param director  The particular director
     *
     * @return  The list of movies
     */
    @DataFetcher(property = "directedMovies")
    public Single<List<Movie>> getMoviesDirected(Director director) {
        return movieService.getMoviesDirected(director);
    }
}
