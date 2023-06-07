package com.codejava;

import jakarta.persistence.*;

@Entity
@Table(name = "book_entity")
public class BookEventEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int eid;
    private String eventName;

    private int seatLeft;

    public BookEventEntity(){};
    public BookEventEntity(int eid, String eventName, int seatLeft) {
        this.eid = eid;
        this.eventName = eventName;
        this.seatLeft = seatLeft;
    }

    public int getEid() {
        return eid;
    }

    public void setEid(int eid) {
        this.eid = eid;
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
        return "BookEventEntity{" +
                "eid=" + eid +
                ", eventName='" + eventName + '\'' +
                ", seatLeft=" + seatLeft +
                '}';
    }
}
