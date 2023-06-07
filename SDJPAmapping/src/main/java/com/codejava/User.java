package com.codejava;

import jakarta.validation.Valid;
import jakarta.validation.constraints.*;

import java.time.LocalDate;

public class User {
    private int id;
    @NotNull(message = "name can't be null")
    @NotEmpty(message = "name can't be empty")
    @Pattern(regexp = "^[A-Z][a-z]+( [A-Z][a-z]+)?$", message = "Name is not in proper format")
    private String name;
    @NotNull(message = "email can't be null")
    @NotEmpty(message = "email can't be empty")
    @Email(message = "Wrong email id")
    private String email;
    @NotNull(message = "city can't be null")
    @NotEmpty(message = "city can't be empty")
    private String city;

    @NotNull(message = "date can't be null")
    private LocalDate date;

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public boolean isDateTomorrow() {
        LocalDate tomorrow = LocalDate.now().plusDays(1);
        return date.equals(tomorrow);
    }

    @Valid
    private BookEvent bookEvent;
    public User(){};
    public User(int id, String name, String email, String city, BookEvent bookEvent) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.city = city;
        this.bookEvent = bookEvent;
    }

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

    public BookEvent getBookEvent() {
        return bookEvent;
    }

    public void setBookEvent(BookEvent bookEvent) {
        this.bookEvent = bookEvent;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", city='" + city + '\'' +
                ", bookEvent=" + bookEvent +
                '}';
    }
}
