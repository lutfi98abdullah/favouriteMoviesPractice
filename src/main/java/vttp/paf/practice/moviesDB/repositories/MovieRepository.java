package vttp.paf.practice.moviesDB.repositories;

import java.math.BigInteger;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import vttp.paf.practice.moviesDB.model.Actors;
import vttp.paf.practice.moviesDB.model.Movies;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.jdbc.support.rowset.SqlRowSet;



@Repository
public class MovieRepository {

    @Autowired
    private JdbcTemplate template;

    public List<Movies> getAllMovies() {

        List<Movies> moviesList = new LinkedList<>();
        final SqlRowSet rs = template.queryForRowSet(Queries.SQL_SELECT_ACTIVE_MOVIES, false);

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

    public List<Actors> getAllActors() {

        List<Actors> actorsList = new LinkedList<>();
        final SqlRowSet rs = template.queryForRowSet(Queries.SQL_SELECT_ACTIVE_ACTORS, false);

        while (rs.next()) {
            Actors actor = new Actors();
            actor.setActorId(rs.getInt("actor_id"));
            actor.setActorName(rs.getString("actor_name"));
            actor.setIsDeleted(rs.getBoolean("is_deleted"));
            actorsList.add(actor);
        }

        return actorsList;
    }

    public Optional<List<Actors>> getActorsByMovie(int movieId) {

        List<Actors> movieCast = new LinkedList<>();

        final SqlRowSet rs = template.queryForRowSet(Queries.SQL_SELECT_ACTIVE_ACTORS_BY_MOVIE, movieId, false);

        while (rs.next()) {
            Actors actor = new Actors();
            actor.setActorId(rs.getInt("actor_id"));
            actor.setActorName(rs.getString("actor_name"));
            movieCast.add(actor);
        }

        return Optional.of(movieCast);

    }

    public Optional<Movies> getMovieById(int movieId) {

        Movies movie = new Movies();

        final SqlRowSet rs = template.queryForRowSet(Queries.SQL_SELECT_ACTIVE_MOVIE_BY_ID, movieId, false);

        while (rs.next()) {
            movie.setMovieId(rs.getInt("movie_id"));
            movie.setMovieName(rs.getString("movie_name"));
            movie.setPersonalRating(rs.getInt("personal_rating"));
            movie.setReleaseDate(rs.getDate("release_date"));
            movie.setSynopsis(rs.getString("synopsis"));
        }

        return Optional.of(movie);

    }

    public Optional<Integer> actorAlreadyExists(int actorId, int movieId) {
        final SqlRowSet rs = template.queryForRowSet(Queries.SQL_CHECK_IF_MOVIECAST_EXISTS, actorId, movieId);
        int count = 0;
        if (!rs.next())
            return Optional.empty();

        count++;
        return Optional.of(count);

    }

    public boolean addActorToMovie(int actorId, int movieId) {
        int count = template.update(Queries.SQL_INSERT_MOVIECAST, actorId, movieId);

        return 1 == count;
    }

    public Optional<Integer> actorAlreadyCreated(String actorName, Boolean is_deleted) {
        final SqlRowSet rs = template.queryForRowSet(Queries.SQL_CHECK_IF_ACTOR_EXISTS, actorName, is_deleted);
        int count = 0;
        if (!rs.next())
            return Optional.empty();

        count++;
        return Optional.of(count);

    }

    public boolean createActor(String actorName, Boolean is_deleted) {
        int count = template.update(Queries.SQL_INSERT_ACTOR, actorName, is_deleted);

        return 1 == count;
    }

    public Optional<Integer> movieAlreadyCreated(String movieName, Boolean is_deleted) {
        final SqlRowSet rs = template.queryForRowSet(Queries.SQL_CHECK_IF_MOVIE_EXISTS, movieName, is_deleted);
        int count = 0;
        if (!rs.next())
            return Optional.empty();

        count++;
        return Optional.of(count);

    }

    public boolean createMovie(String movieName, Integer rating, java.util.Date releaseDate, String synopsis, Boolean isDeleted) {
        int count = template.update(Queries.SQL_INSERT_MOVIE, movieName, rating, releaseDate, synopsis, isDeleted);

        return 1 == count;
    }

    public Integer createMovieWithTransactional(String movieName,Integer rating,java.util.Date releaseDate,String synopsis,Boolean isDeleted){
        
        KeyHolder keyHolder= new GeneratedKeyHolder();
        java.sql.Date sqlDate
            = new java.sql.Date(releaseDate.getTime());
        template.update(conn -> {
            PreparedStatement ps = conn.prepareStatement(Queries.SQL_INSERT_MOVIE,Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, movieName);
            ps.setInt(2, rating);
            ps.setDate(3, sqlDate);
            ps.setString(4, synopsis);
            ps.setBoolean(5, isDeleted);
            return ps;
        },keyHolder);
        
        BigInteger generatedMovieId = (BigInteger) keyHolder.getKey();
        return generatedMovieId.intValue();
    }

    public boolean deleteMovieById(Integer movieId) {
        int count = template.update(Queries.SQL_DELETE_MOVIE_BY_ID, movieId);
        return 1 == count;
    }

    public boolean deleteActorById(Integer actorId) {
        int count = template.update(Queries.SQL_DELETE_ACTOR_BY_ID, actorId);
        return 1 == count;
    }

    public boolean deleteMovieCast(Integer movieId, Integer actorId) {
        int count = template.update(Queries.SQL_DELETE_MOVIE_CAST,movieId, actorId);
        return 1 == count;
    }

}
