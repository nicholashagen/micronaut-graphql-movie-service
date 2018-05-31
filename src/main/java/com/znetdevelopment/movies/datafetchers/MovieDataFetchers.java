package com.znetdevelopment.movies.datafetchers;

import com.znetdevelopment.movies.graphql.DataFetchers;
import com.znetdevelopment.movies.graphql.DataFetcher;
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
 * Data Fetcher responsible for fetching data related to a particular movie.
 */
@Singleton
public class MovieDataFetchers implements DataFetchers {

    @Inject private ActorService actorService;

    /**
     * Get the list of actors appearing in the given movie.
     *
     * @param movie  The particular movie
     *
     * @return  The list of actors
     */
    @DataFetcher(property = "actors")
    public Single<List<Actor>> getMovieActors(Movie movie) {
        return actorService.getActors(movie);
    }
}
