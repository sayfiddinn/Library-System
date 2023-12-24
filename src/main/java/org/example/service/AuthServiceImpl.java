package org.example.service;

import org.example.controller.AdminController;
import org.example.controller.ModerController;
import org.example.controller.UserController;
import org.example.database.ListSource;
import org.example.entity.User;
import org.example.entity.enums.Role;
import org.example.util.Validation;

import java.util.Scanner;
import java.util.UUID;

public class AuthServiceImpl implements AuthService {
    static Scanner scanner = new Scanner(System.in);
    private final UserController userController = new UserController();
    private final AdminController adminController = new AdminController();
    private final ModerController moderController = new ModerController();

    @Override
    public void register() {
        String email;
        while (true) {
            System.out.println("Email : ");
            email = scanner.nextLine();
            if (!Validation.checkEmail(email)) {
                System.err.println("Bunday email mavjud,boshqa email kiriting !");
            } else break;
        }
        System.out.println("Password : ");
        String password = scanner.nextLine();
        if (email.isBlank() || password.isBlank()) {
            System.err.println("Email yoki parol kiritilmadi ! ");
            return;
        }
        System.out.println("Confirm password : ");
        String confirmPas = scanner.nextLine();
        if (!password.equals(confirmPas)) {
            System.err.println("Parol va Tasdiqlovchi parol teng emas ! ");
            return;
        }
        ListSource.users.add(new User(UUID.randomUUID(),
                email, password, Role.USER, false));

    }

    @Override
    public void signIn() {
        System.out.println("Email : ");
        String email = scanner.nextLine();
        System.out.println("Password : ");
        String password = scanner.nextLine();
        if (email.isBlank() || password.isBlank()) {
            System.err.println("Email yoki parol kiritilmadi ! ");
            return;
        }
        User user = Validation.existUser(email, password);
        if (user == null) {
            System.err.println("User bazada mavjud emas yoki bloklangan! ");
            return;
        }
        switch (user.getRole()) {
            case USER -> userController.run(user.getId());
            case ADMIN -> adminController.run();
            case MODERATOR -> moderController.run();
            default -> System.err.println("Nimadir nito ! ");
        }
    }
}
