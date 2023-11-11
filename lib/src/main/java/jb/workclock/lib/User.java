package jb.workclock.lib;

import java.time.Instant;

public class User {

    public Integer userId;
    public String name;
    public String gmail;
    public Boolean is_admin;
    public Integer created_by;

    public User(Integer userId, String name, String gmail, Boolean is_admin, Integer created_by) {
        this.userId = userId;
        this.name = name;
        this.gmail = gmail;
        this.is_admin = is_admin;
        this.created_by = created_by;
    }
}
