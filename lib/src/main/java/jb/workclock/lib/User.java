package jb.workclock.lib;

import java.time.Instant;

public class User {

    public Integer userId;
    public String name;
    public String gmail;

    public User(Integer userId, String name, String gmail) {
        this.userId = userId;
        this.name = name;
        this.gmail = gmail;
    }
}
