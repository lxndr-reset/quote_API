package com.example.quote_api.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.sql.Timestamp;
import java.util.Objects;

import static java.lang.System.*;

@Entity
@Table(name = "\"app_user\"")
public class User {

    @Id
    @Column(name = "email")
    private String email;

    @Column(name = "password")
    private String password;

    @Column(name = "name")
    private String name;

    @Column(name = "creation_date")
    private Timestamp creationTime;

    public User(String email, String password, String name) {
        this.email = email;
        this.password = password;
        this.name = name;
        this.creationTime = generateCurrentTimestamp();
    }

    private static Timestamp generateCurrentTimestamp() {
        return new Timestamp(currentTimeMillis());
    }

    public User(String email) {
        this.email = email;
        this.creationTime = generateCurrentTimestamp();
    }

    public User() {
        this.creationTime = generateCurrentTimestamp();
    }

    @Override
    public String toString() {
        return "User{" +
                "email='" + email + '\'' +
                ", password='" + "[PROTECTED]" + '\'' +
                ", name='" + name + '\'' +
                ", creationTime=" + creationTime +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User user)) return false;
        return Objects.equals(email, user.email)
                && Objects.equals(name, user.name)
                && Objects.equals(creationTime, user.creationTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(email, name, creationTime);
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Timestamp getCreationTime() {
        return creationTime;
    }

    public void setCreationTimeNow() {
        this.creationTime = generateCurrentTimestamp();
    }
}
