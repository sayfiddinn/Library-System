package org.example.database;

import org.example.entity.Book;
import org.example.entity.Booking;
import org.example.entity.Library;
import org.example.entity.User;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class ListSource {
    public static List<Library> libraries = new ArrayList<>();
    public static List<User> users = new ArrayList<>();
    public static List<Book> books = new ArrayList<>();
    public static List<Booking> bookings = new ArrayList<>();
    public static List<UUID> booksInUse = new ArrayList<>();
}
