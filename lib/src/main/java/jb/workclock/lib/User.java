package jb.workclock.lib;

import java.time.Instant;

public class User {

    public Integer userId;
    public String name;
    public String gmail;
    public Boolean isAdmin;
    public Integer createdBy;

    public User(Integer userId, String name, String gmail, Boolean isAdmin, Integer createdBy) {
        this.userId = userId;
        this.name = name;
        this.gmail = gmail;
        this.isAdmin = isAdmin;
        this.createdBy = createdBy;
    }
}
