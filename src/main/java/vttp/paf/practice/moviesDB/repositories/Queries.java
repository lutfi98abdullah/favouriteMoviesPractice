package vttp.paf.practice.moviesDB.repositories;

public interface Queries {
    public static final String SQL_SELECT_MOVIES = "select * from favourite_movies where is_deleted = ?";
    public static final String SQL_SELECT_ACTORS = "select * from actors where is_deleted = ?";
}
