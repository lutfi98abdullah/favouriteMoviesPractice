package vttp.paf.practice.moviesDB.model;

import java.util.Date;

public class Movies {
    private Integer movieId;
    private String movieName;
    private Integer personalRating;
    private Date releaseDate;
    private String synopsis;
    private Boolean isDeleted;
    
    public Integer getMovieId() {
        return movieId;
    }
    public void setMovieId(Integer movieId) {
        this.movieId = movieId;
    }
    public String getMovieName() {
        return movieName;
    }
    public void setMovieName(String movieName) {
        this.movieName = movieName;
    }
    public Integer getPersonalRating() {
        return personalRating;
    }
    public void setPersonalRating(Integer personalRating) {
        this.personalRating = personalRating;
    }
    public Date getReleaseDate() {
        return releaseDate;
    }
    public void setReleaseDate(Date releaseDate) {
        this.releaseDate = releaseDate;
    }
    public String getSynopsis() {
        return synopsis;
    }
    public void setSynopsis(String synopsis) {
        this.synopsis = synopsis;
    }
    public Boolean getIsDeleted() {
        return isDeleted;
    }
    public void setIsDeleted(Boolean isDeleted) {
        this.isDeleted = isDeleted;
    }
  
}
