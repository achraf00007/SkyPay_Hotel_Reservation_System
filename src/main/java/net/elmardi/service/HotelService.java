package net.elmardi.service;

import net.elmardi.entities.Booking;
import net.elmardi.entities.Room;
import net.elmardi.entities.User;
import net.elmardi.enums.RoomType;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class HotelService {
    private List<Room> rooms = new ArrayList<>();
    private List<User> users = new ArrayList<>();
    private List<Booking> bookings = new ArrayList<>();

    private Room findRoomByNumber(int roomNumber) {
        return rooms.stream().filter(
                r -> r.getRoomNumber() == roomNumber
        ).findFirst().orElse(null);
    }

    public void setRoom(int roomNumber, RoomType type, int pricePerNight) {
        Room existingRoom = findRoomByNumber(roomNumber);
        if (existingRoom != null) {
            existingRoom.setType(type);
            existingRoom.setPricePerNight(pricePerNight);
        } else {
            rooms.add(new Room(roomNumber, type, pricePerNight));
        }
    }


    public void bookRoom(int userId, int roomNumber, LocalDate checkIn, LocalDate checkOut, int pricePerNight) {
        User user = findUserById(userId);
        Room room = findRoomByNumber(roomNumber);

        if (user == null || room == null) {
            throw new IllegalArgumentException("User or Room Not Found");
        }

        if (checkIn.isAfter(checkOut)) {
            throw new IllegalArgumentException("Invalid dates.");
        }

        Booking booking = new Booking(user, room, checkIn, checkOut, pricePerNight);
        if (user.getBalance() >= pricePerNight) {
            bookings.add(booking);
            user.setBalance(user.getBalance() - pricePerNight);
        }else {
            throw new IllegalArgumentException("Insufficient balance.");
        }
    }

    public void printAll() {
        System.out.println("\n * Rooms \n");
        rooms.forEach(room -> System.out.println(
                "N°" + room.getRoomNumber() + " - " + room.getType() + " - " + room.getPricePerNight() + "MAD/Night"
        ));

        System.out.println("\n__________________________________________________________________________");
        System.out.println("\n * Reservations \n");
        bookings.forEach(booking -> System.out.println(
                "User #" + booking.getUser().getUserId() +
                        ", Room #" + booking.getRoom().getRoomNumber() +
                        ", From " + booking.getCheckIn() + " -> " + booking.getCheckOut() +
                        ", Price: " + booking.getTotalPrice() + "MAD"
        ));
    }

    private User findUserById(int userId) {
        return users.stream().filter(u -> u.getUserId() == userId).findFirst().orElse(null);
    }

    public void setUser(int userId, int balance) {
        User existingUser = findUserById(userId);
        if (existingUser != null) {
            existingUser.setBalance(balance);
        }else {
            users.add(new User(userId, balance));
        }
    }

    public void printAllUsers() {
        System.out.println("\n__________________________________________________________________________");
        System.out.println("\n -> Users \n");
        users.forEach(user -> System.out.println(
                "ID: " + user.getUserId() + ", Balance: " + user.getBalance() + " MAD"
        ));
        System.out.println("\n__________________________________________________________________________\n\n");
    }
}
