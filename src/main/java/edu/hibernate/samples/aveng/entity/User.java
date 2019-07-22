package edu.hibernate.samples.aveng.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import java.util.Date;

@Entity
public class User {
    private String nickname;
    private String email;
    private String password;
    private boolean active;

    @Column(name = "last_logging_date")
    private Date lastLoggingDate;

    public User(String nickname, String email, String password) {
        // boolean def value - false :3
        this.nickname = nickname;
        this.email = email;
        this.password = password;
        // Represents the time at which it was created.
        this.lastLoggingDate = new Date();
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }


}
