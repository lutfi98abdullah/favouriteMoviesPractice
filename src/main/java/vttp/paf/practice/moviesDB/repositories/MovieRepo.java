package vttp.paf.practice.moviesDB.repositories;

import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import vttp.paf.practice.moviesDB.model.Actors;
import vttp.paf.practice.moviesDB.model.Movies;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;



@Repository
public class MovieRepo {
    
    
    @Autowired
    private JdbcTemplate template;

    public List<Movies> getAllMovies(){
        
        List<Movies> moviesList = new LinkedList<>();
        final SqlRowSet rs = template.queryForRowSet(Queries.SQL_SELECT_MOVIES, "false");
        
        while (rs.next()) {
            Movies movie = new Movies();
            movie.setMovieId(rs.getInt("movie_id"));
            movie.setMovieName(rs.getString("movie_name"));
            movie.setPersonalRating(rs.getInt("personal_rating"));
            movie.setReleaseDate(rs.getDate("release_date"));
            movie.setSynopsis(rs.getString("synopsis"));
            movie.setIsDeleted(rs.getBoolean("is_deleted"));
            moviesList.add(movie);
        }

        return moviesList;

    }


    public List<Actors> getAllActors(){

        List<Actors> actorsList = new LinkedList<>();
        final SqlRowSet rs = template.queryForRowSet(Queries.SQL_SELECT_ACTORS, "false");

        while (rs.next()) {
            Actors actors = new Actors();
            actors.setActorId(rs.getInt("actor_id"));
            actors.setActorName(rs.getString("actor_name"));
            actors.setIsDeleted(rs.getBoolean("is_deleted"));
        }
        
        return actorsList;
        
    }
    
    
}
