package com.example.watchlist.controllers;

import com.example.watchlist.models.Movie;
import com.example.watchlist.services.MovieService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class MovieController
{
    @Autowired
    MovieService movieService;

//localhost:8080/watchlistItemForm
    @GetMapping("/watchlistItemForm")
    public ModelAndView getWatchlistForm(@RequestParam(required = false) Integer id)
    {
        String viewName = "watchlistItemForm";
        Map<String,Object> model = new HashMap<>();
        if(id==null)
        {
            Movie movie = new Movie();
//        movie.setTitle("Dummy Movie Name");
//        movie.setRating(0);
//        movie.setPriority("Low");
//        movie.setComment("No Comment");
            model.put("watchlistItem",movie);
        }
        else
        {
            Movie movie = movieService.getMovieById(id);
            model.put("watchlistItem",movie);
        }

        return new ModelAndView(viewName,model);
    }

    @PostMapping("/watchlistItemForm")
    public ModelAndView submitWatchlistForm(@Valid @ModelAttribute("watchlistItem") Movie movie, BindingResult bindingResult)
    {
        if(bindingResult.hasErrors())
        {
            // if errors are there, re-display the form
            return new ModelAndView("watchlistItemForm");
        }
        Integer id= movie.getId();
        if(id==null)
            movieService.create(movie);
        else
            movieService.update(movie,id);
        RedirectView redirectView = new RedirectView();
        redirectView.setUrl("/watchlist");
        return new ModelAndView(redirectView);
    }

    @GetMapping("/watchlist")
    public ModelAndView getWatchlist()
    {
        String viewName = "watchlist";
        Map<String,Object> model = new HashMap<>();
        List<Movie> movieList = movieService.getAllMovies();
        model.put("watchlistRows",movieList);
        model.put("noOfMovies",movieList.size());
        return new ModelAndView(viewName,model);
    }


}
