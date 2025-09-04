package com.example.watchlist.services;

import com.example.watchlist.models.Movie;
import com.example.watchlist.repositories.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MovieService
{
    @Autowired
    MovieRepository movieRepository;

    @Autowired
    RatingService ratingService;

    // create
    public void create(Movie movie)
    {
        String rating = ratingService.getMovieRating(movie.getTitle());
        if(rating!=null)
        {
            movie.setRating(Float.parseFloat(rating));
        }
        movieRepository.save(movie);
    }

    //readAll
    public List<Movie> getAllMovies()
    {
        return movieRepository.findAll();
    }

    public Movie getMovieById(Integer id)
    {
        return movieRepository.findById(id).get();
    }

    public void update(Movie movie, Integer id)
    {
        Movie movieToBeUpdated = getMovieById(id);
        movieToBeUpdated.setTitle(movie.getTitle());
        movieToBeUpdated.setRating(movie.getRating());
        movieToBeUpdated.setPriority(movie.getPriority());
        movieToBeUpdated.setComment(movie.getComment());
        create(movieToBeUpdated);
    }
}
