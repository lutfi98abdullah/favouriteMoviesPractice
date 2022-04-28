package vttp.paf.practice.moviesDB.service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import vttp.paf.practice.moviesDB.Exception.ActorException;
import vttp.paf.practice.moviesDB.Exception.MovieException;
import vttp.paf.practice.moviesDB.model.Actors;
import vttp.paf.practice.moviesDB.model.Movies;
import vttp.paf.practice.moviesDB.repositories.MovieRepository;

@Service
public class MovieService {

    @Autowired
    private MovieRepository moviesRepo;

    public List<Movies> getAllMovies() {

        return moviesRepo.getAllMovies();

    }

    public List<Actors> getAllActors() {

        return moviesRepo.getAllActors();

    }

    public Optional<List<Actors>> getActorsByMovie(int movieId) {

        Optional<List<Actors>> opt = moviesRepo.getActorsByMovie(movieId);
        if (opt.isEmpty()) {
            return Optional.empty();
        }

        List<Actors> actorList = opt.get();

        return Optional.of(actorList);

    }

    public Optional<Movies> getMovieById(int movieId) {

        Optional<Movies> opt = moviesRepo.getMovieById(movieId);

        if (opt.isEmpty()) {
            return Optional.empty();
        }

        Movies movie = opt.get();

        return Optional.of(movie);

    }
    @Transactional(rollbackFor = MovieException.class)
    public void addActorToMovie(int actorId, int movieId) throws MovieException {

        Optional<Integer> opt = moviesRepo.actorAlreadyExists(actorId, movieId);

        if (opt.isPresent())
            throw new MovieException("%s is already your cast".formatted(actorId));

        if (!moviesRepo.addActorToMovie(actorId, movieId))
            throw new MovieException("Cannot add %s as movieCast. Please check with admin".formatted(actorId));

    }

    public void createActor(String actorName,Boolean isDeleted) throws ActorException{

        Optional<Integer> opt = moviesRepo.actorAlreadyCreated(actorName, isDeleted);

        if (opt.isPresent())
            throw new ActorException("%s is already available".formatted(actorName));

        if (!moviesRepo.createActor(actorName,isDeleted))
            throw new ActorException("Cannot add %s as movieCast. Please check with admin".formatted(actorName));

    }

    public void createMovie(String movieName,Integer rating,Date releaseDate,String synopsis,Boolean isDeleted) throws MovieException{

        Optional<Integer> opt = moviesRepo.movieAlreadyCreated(movieName, isDeleted);

        if (opt.isPresent())
            throw new MovieException("%s is already available".formatted(movieName));
        
        if(!moviesRepo.createMovie(movieName, rating, releaseDate, synopsis, isDeleted))
            throw new MovieException("Cannot add %s. Please check with admin".formatted(movieName));


    }
    @Transactional(rollbackFor = MovieException.class)
    public void createMovieWithActor(String movieName,Integer rating,Date releaseDate,String synopsis,Boolean isDeleted, Integer actorId) throws MovieException{
        
        Optional<Integer> opt = moviesRepo.movieAlreadyCreated(movieName, isDeleted);


        if (opt.isPresent())
            throw new MovieException("%s is already available".formatted(movieName));
        
        final Integer movieId = moviesRepo.createMovieWithTransactional(movieName, rating, releaseDate, synopsis, isDeleted);

        addActorToMovie(actorId, movieId);

    }

    
    
}