package org.example.controller;

import org.example.service.AuthService;
import org.example.service.AuthServiceImpl;

import java.util.Scanner;


public class MainController {
    private final AuthService authService=new AuthServiceImpl();
    static Scanner scanner = new Scanner(System.in);

    public void run() {
        System.out.println("Welcome our library!");
        while (true) {
            System.out.println("""
                    \n1.SignIn
                    2.Register
                    0.exit
                    """);
            switch (scanner.nextLine()) {
                case "1" -> authService.signIn();
                case "2" -> authService.register();
                case "0" -> {
                    return;
                }
                default -> System.err.println("Mavjud bo'lmagan menu tanlandi ! ");
            }
        }
    }
}
