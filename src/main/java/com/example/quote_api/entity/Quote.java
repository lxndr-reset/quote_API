package com.example.quote_api.entity;

import jakarta.persistence.*;

import java.sql.Timestamp;
import java.util.Objects;

@Entity
@Table(name = "\"quote\"")
public class Quote {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "username")
    private String name;

    @Column(name = "rate")
    private short rate = 0;

    @Column(name = "edition_date")
    private Timestamp editionDate;

    @ManyToOne(
            cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH},
            fetch = FetchType.EAGER
    )
    @JoinColumn(name = "user_email", referencedColumnName = "email")
    private User user;

    public Quote(Long id, String name, User user) {
        this.id = id;
        updateEditionDate();
        this.name = name;
        this.user = user;
    }

    public Quote(Long id, String name) {
        this.id = id;
        updateEditionDate();
        this.name = name;
    }
    public Quote(String name) {
        updateEditionDate();
        this.name = name;
    }

    public Quote() {
        updateEditionDate();
    }

    public Timestamp getEditionDate() {
        return editionDate;
    }

    private void updateEditionDate() {
        this.editionDate = new Timestamp(System.currentTimeMillis());
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        updateEditionDate();
        this.user = user;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Quote quote)) return false;
        return getRate() == quote.getRate()
                && Objects.equals(getId(), quote.getId())
                && Objects.equals(getName(), quote.getName())
                && Objects.equals(getEditionDate(), quote.getEditionDate())
                && Objects.equals(getUser(), quote.getUser());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getName(), getRate(), getEditionDate(), getUser());
    }

    public short upvote() {
        updateEditionDate();
        return ++this.rate;
    }

    public short downvote() {
        updateEditionDate();
        return --this.rate;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        updateEditionDate();
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        updateEditionDate();
        this.name = name;
    }

    public short getRate() {
        return rate;
    }

    public void setRate(short rate) {
        updateEditionDate();
        this.rate = rate;
    }

    @Override
    public String toString() {
        return "Quote{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", rate=" + rate +
                ", editionDate=" + editionDate +
                ", user=" + user +
                '}';
    }
}
