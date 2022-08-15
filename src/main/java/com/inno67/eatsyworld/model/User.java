package com.inno67.eatsyworld.model;

import javax.persistence.*;
import java.util.List;


@Entity(name = "Users")
public class User extends Timestamped {
    @GeneratedValue(
            strategy = GenerationType.AUTO
    )
    @Id
    private Long id;
    @Column(
            nullable = false
    )
    private String username;
    @Column(
            nullable = false
    )
    private String password;

    @OneToMany(mappedBy = "user")
    private List<Post> post;

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Long getId() {
        return this.id;
    }

    public String getUsername() {
        return this.username;
    }

    public String getPassword() {
        return this.password;
    }

    public User() {
    }
}