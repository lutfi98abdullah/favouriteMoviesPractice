package vttp.paf.practice.moviesDB.controllers;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import vttp.paf.practice.moviesDB.model.Actors;
import vttp.paf.practice.moviesDB.model.Movies;
import vttp.paf.practice.moviesDB.service.MovieService;

@Controller
@RequestMapping
public class MoviesController {
   
    @Autowired
    public MovieService movieSvc;

    @GetMapping
    public ModelAndView getMoviesTable() {
        ModelAndView mvc = new ModelAndView();

        List<Movies> moviesList = movieSvc.getAllMovies();
        List<Actors> actorsList = movieSvc.getAllActors();

        mvc.addObject("messageMovie", "");
        mvc.addObject("messageActor", "");
        mvc.addObject("moviesList", moviesList);
        mvc.addObject("actorslist", actorsList);
        mvc.setStatus(HttpStatus.OK);
        mvc.setViewName("index");

        return mvc;
    }

 
}
