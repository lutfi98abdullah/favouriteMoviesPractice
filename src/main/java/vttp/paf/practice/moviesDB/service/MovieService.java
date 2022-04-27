package vttp.paf.practice.moviesDB.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import vttp.paf.practice.moviesDB.model.Actors;
import vttp.paf.practice.moviesDB.model.Movies;
import vttp.paf.practice.moviesDB.repositories.MovieRepo;

@Service
public class MovieService {
    
    @Autowired
    public MovieRepo moviesRepo;

    public List<Movies> getAllMovies(){
        return moviesRepo.getAllMovies();
    }

    public List<Actors> getAllActors(){
        return moviesRepo.getAllActors();
    }
}
