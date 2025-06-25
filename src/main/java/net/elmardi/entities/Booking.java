package net.elmardi.entities;

import java.time.LocalDate;

public class Booking {
    private User user;
    private Room room;
    private LocalDate checkIn;
    private LocalDate checkOut;
    private int totalPrice;

    public Booking(User user, Room room, LocalDate checkIn, LocalDate checkOut, int totalPrice) {
        this.user = user;
        this.room = room;
        this.checkIn = checkIn;
        this.checkOut = checkOut;
        this.totalPrice = totalPrice;
    }

    public User getUser() {
        return user;
    }

    public Room getRoom() {
        return room;
    }

    public LocalDate getCheckIn() {
        return checkIn;
    }

    public LocalDate getCheckOut() {
        return checkOut;
    }

    public int getTotalPrice() {
        return totalPrice;
    }
}
