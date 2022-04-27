package vttp.paf.practice.moviesDB.model;

public class Actors {
    private Integer actorId;
    private String actorName;
    private Boolean isDeleted;
    
    public Integer getActorId() {
        return actorId;
    }
    public void setActorId(Integer actorId) {
        this.actorId = actorId;
    }
    public String getActorName() {
        return actorName;
    }
    public void setActorName(String actorName) {
        this.actorName = actorName;
    }
    public Boolean getIsDeleted() {
        return isDeleted;
    }
    public void setIsDeleted(Boolean isDeleted) {
        this.isDeleted = isDeleted;
    }
  
}
