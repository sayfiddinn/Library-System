package org.example.controller;

import org.example.service.UserService;
import org.example.service.UserServiceImpl;

import java.util.Scanner;
import java.util.UUID;

public class UserController {
    private final UserService userService = new UserServiceImpl();
    static Scanner scanner = new Scanner(System.in);

    public void run(UUID userId) {
        while (true) {
            System.out.println("""
                    \n1. Kitob bron qilish
                    2. Bron qilingan kitoblar
                    3. Bron qilingan kitobni bekor qilish
                    4. Avval olingan kitoblar
                    5. Kitoblarni ro'yxati
                    0. exit
                    """);
            switch (scanner.nextLine()) {
                case "1" -> userService.booking(userId);
                case "2" -> userService.newHistory(userId);
                case "3" -> userService.cancelBooking(userId);
                case "4" -> userService.allHistory(userId);
                case "5" -> userService.showBooks();
                case "0" -> {
                    return;
                }
                default -> System.err.println("Mavjud bo'lmagan menu tanlandi ! ");
            }
        }
    }
}
