package net.elmardi;

import net.elmardi.enums.RoomType;
import net.elmardi.service.HotelService;

import java.time.LocalDate;

public class Main {
    public static void main(String[] args) {
        HotelService service = new HotelService();

        service.setRoom(1, RoomType.STANDARD, 1000);
        service.setRoom(2, RoomType.JUNIOR, 2000);
        service.setRoom(3, RoomType.SUITE, 3000);

        service.setUser(1, 5000);
        service.setUser(2, 10000);

        LocalDate checkIn1 = LocalDate.of(2026, 6, 30);
        LocalDate checkOut1 = LocalDate.of(2026, 7, 7);
        service.bookRoom(1, 2, checkIn1, checkOut1, 2000);

        LocalDate invalidCheckIn = LocalDate.of(2026, 7, 7);
        LocalDate invalidCheckOut = LocalDate.of(2026, 6, 30);
        try {
            service.bookRoom(1, 2, invalidCheckIn, invalidCheckOut, 2000);
        } catch (IllegalArgumentException e) {
            System.out.println("Expected error : " + e.getMessage());
        }

        LocalDate checkIn2 = LocalDate.of(2026, 7, 7);
        LocalDate checkOut2 = LocalDate.of(2026, 7, 8);
        service.bookRoom(1, 1, checkIn2, checkOut2, 1000);

        LocalDate checkIn3 = LocalDate.of(2026, 7, 7);
        LocalDate checkOut3 = LocalDate.of(2026, 7, 9);
        service.bookRoom(2, 1, checkIn3, checkOut3, 1000);

        LocalDate checkIn4 = LocalDate.of(2026, 7, 7);
        LocalDate checkOut4 = LocalDate.of(2026, 7, 8);
        service.bookRoom(2, 3, checkIn4, checkOut4, 3000);

        service.setRoom(1, RoomType.SUITE, 10000);

        System.out.println("\n************************* Final results *************************");
        service.printAll();
        service.printAllUsers();
    }
}