package com.codejava;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public class BookEvent {

    int id;
    @NotNull(message = "event can't be null")
    @NotEmpty(message = "event can't be empty")
    String eventName;
    int seatLeft;

    public BookEvent(int id, String eventName, int seatLeft) {
        this.id = id;
        this.eventName = eventName;
        this.seatLeft = seatLeft;
    }

    public BookEvent(){};

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEventName() {
        return eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public int getSeatLeft() {
        return seatLeft;
    }

    public void setSeatLeft(int seatLeft) {
        this.seatLeft = seatLeft;
    }

    @Override
    public String toString() {
        return "BookEvent{" +
                "id=" + id +
                ", eventName='" + eventName + '\'' +
                ", seatLeft=" + seatLeft +
                '}';
    }
}
