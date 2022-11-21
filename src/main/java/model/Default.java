package model;
import java.sql.Timestamp;
public class Default {
    private Integer id;
    private Timestamp createdAt;
    private Timestamp updatedAt;
    public Default(Integer id, Timestamp createdAt, Timestamp updatedAt){
        this.id = id;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }
    public Integer getId() {
        return id;
    }
    public Timestamp getCreatedAt() {
        return createdAt;
    }
    public Timestamp getupdatedAt() {
        return updatedAt;
    }
}