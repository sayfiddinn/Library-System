package org.example.service;

import org.example.database.ListSource;
import org.example.entity.User;
import org.example.entity.enums.Role;

import java.util.Objects;

import static org.example.util.Utils.*;

public class AdminServiceImpl implements AdminService {
    @Override
    public void blockedUser() {
        usersList();
        System.out.println(BLUE + "Enter User Id" + RESET);
        String userId = scanner.nextLine();
        if (userId.isBlank()) {
            System.err.println("User Id kiritilmadi !");
            return;
        }
        for (User user : ListSource.users) {
            if (Objects.equals(user.getId().toString(), userId) && !user.getRole().equals(Role.ADMIN)) {
                if (user.isBlocked()) {
                    System.out.println("User blokdan chiqarildi");
                } else System.out.println("User bloklandi");
                user.setBlocked(!user.isBlocked());
                return;
            }
        }
        System.err.println("User bazadan topilmadi yoki u admin");
    }


    @Override
    public void addModer() {
        usersList();
        System.out.println(BLUE + "Enter User Id" + RESET);
        String userId = scanner.nextLine();
        if (userId.isBlank()) {
            System.err.println("User Id kiritilmadi !");
            return;
        }
        for (User user : ListSource.users) {
            if (Objects.equals(user.getId().toString(), userId) && !user.getRole().equals(Role.ADMIN)) {
                user.setRole(Role.MODERATOR);
                System.out.println("Userga moderator roli berildi");
                return;
            }
        }
        System.err.println("User bazadan topilmadi yoki u admin");
    }

    @Override
    public void removeModer() {
        usersList();
        System.out.println(BLUE + "Enter User Id" + RESET);
        String userId = scanner.nextLine();
        if (userId.isBlank()) {
            System.err.println("User Id kiritilmadi !");
            return;
        }
        for (User user : ListSource.users) {
            if (Objects.equals(user.getId().toString(), userId) && !user.getRole().equals(Role.ADMIN)) {
                user.setRole(Role.USER);
                System.out.println("Userdan moderator roli olindi");
                return;
            }
        }
        System.err.println("User bazadan topilmadi yoki u admin");
    }

    @Override
    public void showUsers() {
        usersList();
    }

    private void usersList() {
        for (User user : ListSource.users) {
            System.out.println(user);
        }
    }
}
