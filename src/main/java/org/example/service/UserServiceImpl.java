package org.example.service;

import org.example.database.ListSource;
import org.example.entity.Book;
import org.example.entity.Booking;
import org.example.util.Validation;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;


import static org.example.util.Utils.*;

public class UserServiceImpl implements UserService {
    @Override
    public void booking(UUID userId) {
        showBook();
        System.out.println(BLUE + "Olmoqchi bo'lgan kitobingizni Id sini kiriting !" + RESET);
        String bookId = scanner.nextLine();
        if (bookId.isBlank()) {
            System.err.println("Id kiritilmadi ! ");
            return;
        }
        if (Validation.getBook(bookId)) {
            Booking booking = new Booking();
            booking.setBookId(UUID.fromString(bookId));
            booking.setUserId(userId);
            ListSource.bookings.add(booking);
            LocalDate bronDate = booking.getBronDate();
            System.out.println("Kitob sizning nomingizga bron qilindi");
            System.out.println("Olingan sana : " + BLUE + bronDate + RESET);
            System.out.println("Olib ketishning oxirgi sanasi : " + RED + bronDate.plusDays(3) + RESET);
            System.out.println(CYAN + "Kitobdan foydalanish muddati 3 kun !" + RESET);
        } else System.err.println("Bunday Id li kitob toplimadi yoki qolmagan!");
    }


    @Override
    public void newHistory(UUID userId) {
        List<Booking> bookings = getBooks(userId, false);
        showBooking(bookings);
    }

    @Override
    public void cancelBooking(UUID userId) {
        List<Booking> bookings = getBooks(userId, false);
        if (!bookings.isEmpty()) {
            showBooking(bookings);
            System.out.println("Kitob Id sini kiriting : ");
            String bookId = scanner.nextLine();
            if (bookId.isBlank()) {
                System.out.println(BLUE + "Kitob Id si kiritilmadi !" + RESET);
            }
            for (Booking booking : bookings) {
                if (Objects.equals(booking.getBookId().toString(), bookId)) {
                    if (ListSource.booksInUse.contains(UUID.fromString(bookId))) {
                        System.err.println("Bu kitobni allaqachon olib ketgansiz, " +
                                "buyurtmani bekor qilib bo'lmaydi !");
                        return;
                    }
                    addBookCount(UUID.fromString(bookId));
                    ListSource.bookings.remove(booking);
                    System.out.println("Buyurtma bekor qilindi !");
                    return;
                }
            }
            System.err.println("Mavjud bo'lmagan Id kiritildi !");
        } else System.err.println("Sizda bron qilingan hech qanday kitob topilmadi !");
    }

    private void addBookCount(UUID bookId) {
        for (Book book : ListSource.books) {
            if (book.getId().equals(bookId)) {
                book.setCount((short) (book.getCount() + 1));
            }
        }
    }

    @Override
    public void allHistory(UUID userId) {
        List<Booking> bookings = getBooks(userId, true);
        showBooking(bookings);
    }

    @Override
    public void showBooks() {
        showBook();
    }

    private List<Booking> getBooks(UUID userId, boolean successful) {
        List<Booking> bookingList = new ArrayList<>();
        for (Booking booking : ListSource.bookings) {
            if (booking.getUserId().equals(userId) && booking.isSuccessful() == successful) {
                bookingList.add(booking);
            }
        }
        return bookingList;
    }

    private void showBook() {
        for (int i = 0; i < 95; i++) {
            System.out.print("_");
        }
        System.out.println();
        System.out.printf("| %-36s | %-15s | %-15s | %-8s | %-5s |", "id",
                "author", "name", "price", "count");
        System.out.println();
        for (Book book : ListSource.books) {
            System.out.printf("|%-38s+%-17s+%-17s+%-10s+%-7s|", "--------------------------------------",
                    "-----------------", "-----------------", "----------", "-------");
            System.out.println("\n" + book);
        }
        for (int i = 0; i < 95; i++) {
            System.out.print("-");
        }
        System.out.println();
    }

    private void showBooking(List<Booking> bookings) {
        for (int i = 0; i < 78; i++) {
            System.out.print("_");
        }
        System.out.println();
        System.out.printf("| %-36s | %-10s | %-10s | %-9s |",
                "bookId", "bronDate", "deadline", "IsSuccess");
        System.out.println();
        for (Booking booking : bookings) {
            System.out.printf("|%-38s+%-12s+%-12s+%-11s|", "--------------------------------------",
                    "------------", "------------", "-----------");
            System.out.println("\n" + booking);
        }
        for (int i = 0; i < 78; i++) {
            System.out.print("-");
        }
        System.out.println();
    }

}
