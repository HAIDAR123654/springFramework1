package com.codejava;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "user_table")
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private String email;
    private String city;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "eid")
    private BookEventEntity bookEventEntity;

    private LocalDate date;

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public UserEntity(int id, String name, String email, String city, BookEventEntity bookEventEntity) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.city = city;
        this.bookEventEntity = bookEventEntity;
    }

    public UserEntity(){};

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public BookEventEntity getBookEventEntity() {
        return bookEventEntity;
    }

    public void setBookEventEntity(BookEventEntity bookEventEntity) {
        this.bookEventEntity = bookEventEntity;
    }

    @Override
    public String toString() {
        return "UserEntity{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", city='" + city + '\'' +
                ", bookEventEntity=" + bookEventEntity +
                '}';
    }
}
