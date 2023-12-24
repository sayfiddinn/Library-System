package org.example.service;

import org.example.database.ListSource;
import org.example.entity.Book;
import org.example.entity.Booking;
import org.example.entity.Library;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

import static org.example.util.Utils.*;

public class ModerServiceImpl implements ModerService {
    private final UserService userService = new UserServiceImpl();

    @Override
    public void takenBook() {
        showBookingWithUsers(ListSource.bookings);
        System.out.println(BLUE + "Enter book Id : " + RESET);
        String bookId = scanner.nextLine();
        if (bookId.isBlank()) {
            System.err.println("Kitob Id si kiritilmadi !");
            return;
        }
        for (Booking booking : ListSource.bookings) {
            if (Objects.equals(booking.getBookId().toString(), bookId)) {
                ListSource.booksInUse.add(UUID.fromString(bookId));
                System.out.println("Kitob olib ketilgan kitoblar ro'yxatiga qo'shildi !");
                return;
            }
        }
        System.out.println("Bunday Id li kitob bron qilingan kitoblar ichidan topilmadi !");
    }

    @Override
    public void addNewBook() {
        for (Library library : ListSource.libraries) {
            System.out.println(library);
        }
        System.out.println("Enter Library id : ");

        try {
            int libraryId = scanner.nextInt();
            for (Library library : ListSource.libraries) {
                if (library.getId().equals(libraryId)) {
                    if (library.getBookCount() + 1 > library.getShelfCount()) {
                        System.out.println("Enter author : ");
                        String author = scanner.nextLine();
                        System.out.println("Enter name : ");
                        String name = scanner.nextLine();
                        if (author.isBlank() || name.isBlank()) {
                            System.err.println("Kitob avtori yoki nomi kiritilmadi !");
                            return;
                        }
                        System.out.println("Enter price : ");
                        Double price = scanner.nextDouble();
                        Book book = new Book();
                        book.setId(UUID.randomUUID());
                        book.setName(name);
                        book.setAuthor(author);
                        book.setPrice(price);
                        book.setCount((short) 1);
                        book.setLibraryId(libraryId);
                        ListSource.books.add(book);
                        library.setBookCount(library.getBookCount() + 1);
                        System.out.println(BLUE + "Kitob muvaffaqiyatli qo'shildi" + RESET);
                    } else {
                        System.err.println("Bu kutubxonda bo'sh joy qolmagan !");
                    }
                    return;

                }
            }
            System.err.println("Bunday Id li kutubxona toplimadi !");
        } catch (Exception e) {
            System.err.println("Id noto'g'ri formatda kiritildi !");
        }
    }

    @Override
    public void addBook() {
        userService.showBooks();
        System.out.println(BLUE + "Enter book Id" + RESET);
        String bookId = scanner.nextLine();
        if (bookId.isBlank()) {
            System.err.println("Kitob Id si kiritilmadi !");
            return;
        }
        for (Book book : ListSource.books) {
            if (Objects.equals(book.getId().toString(), bookId)) {
                System.out.println("Kitob sonini kirting");
                short count = scanner.nextShort();
                if (!getLibraryBooksCountLimit(book.getLibraryId(), count)) {
                    System.err.println("Kutubxonaga buncha kitob sig'maydi !");
                    return;
                }
                book.setCount((short) (book.getCount() + count));
                System.out.println("Kitob qo'shildi");
                return;
            }
        }
        System.err.println("Bunday Id li kitob topilmadi !");
    }

    @Override
    public void searchBook() {
        System.out.println("""
                1. By name
                2. By author
                0. exit
                """);
        switch (scanner.nextLine()) {
            case "1" -> byName();
            case "2" -> byAuthor();
        }
    }

    private void byAuthor() {
        System.out.println("Author name");
        String author = scanner.nextLine();
        if (author.isBlank()) {
            System.err.println("Avtor nomi kiritilmadi !");
            return;
        }
        showBook(author, null);
    }

    private void byName() {
        System.out.println("Book name");
        String book = scanner.nextLine();
        if (book.isBlank()) {
            System.err.println("Kitob nomi kiritilmadi !");
            return;
        }
        showBook(null, book);
    }

    @Override
    public void showBooking() {
        showBookingWithUsers(ListSource.bookings);
    }

    @Override
    public void showDeadline() {
        List<Booking> bookingList = new ArrayList<>();
        for (Booking booking : ListSource.bookings) {
            if (!booking.isSuccessful() && booking.getBronDate().plusDays(3)
                    .isBefore(LocalDate.now())) {
                bookingList.add(booking);
            }
        }
        if (bookingList.isEmpty()) {
            System.out.println("Deadline o'tib ketgan kitoblar topilmadi");
            return;
        }
        showBookingWithUsers(bookingList);
    }

    private void showBookingWithUsers(List<Booking> bookings) {
        for (int i = 0; i < 117; i++) {
            System.out.print("_");
        }
        System.out.println();
        System.out.printf("| %-36s | %-36s | %-10s | %-10s | %-9s |",
                "userId", "bookId", "bronDate", "deadline", "IsSuccess");
        System.out.println();
        for (Booking booking : bookings) {
            System.out.printf("|%-38s+%-38s+%-12s+%-12s+%-11s|", "--------------------------------------",
                    "--------------------------------------", "------------", "------------", "-----------");
            System.out.println();
            LocalDate bronDate = booking.getBronDate();
            System.out.printf("| %-36s | %-36s | %-10s | %-10s | %-9s |%n",
                    booking.getUserId(), booking.getBookId(), bronDate,
                    bronDate.plusDays(3), booking.isSuccessful());
        }
        for (int i = 0; i < 117; i++) {
            System.out.print("-");
        }
        System.out.println();
    }

    private boolean getLibraryBooksCountLimit(Integer libraryId, short count) {
        for (Library library : ListSource.libraries) {
            if (library.getId().equals(libraryId)) {
                short limit = (short) (library.getShelfCount() * 10 - library.getBookCount());
                if (count <= limit) {
                    library.setBookCount(library.getBookCount() + count);
                    return true;
                } else return false;
            }
        }
        return false;
    }

    private void showBook(String author, String name) {
        for (int i = 0; i < 95; i++) {
            System.out.print("_");
        }
        System.out.println();
        System.out.printf("| %-36s | %-15s | %-15s | %-8s | %-5s |", "id",
                "author", "name", "price", "count");
        System.out.println();
        for (Book book : ListSource.books) {
            if (Objects.equals(book.getAuthor(), author) || Objects.equals(book.getName(), name)) {
                System.out.printf("|%-38s+%-17s+%-17s+%-10s+%-7s|", "--------------------------------------",
                        "-----------------", "-----------------", "----------", "-------");
                System.out.println("\n" + book);
            }
        }
        for (int i = 0; i < 95; i++) {
            System.out.print("-");
        }
        System.out.println();
    }
}
