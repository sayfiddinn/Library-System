package org.example.util;

import org.example.database.ListSource;
import org.example.entity.Book;
import org.example.entity.User;

import java.util.Objects;

public class Validation {
    public static boolean checkEmail(String email) {
        for (User user : ListSource.users) {
            if (Objects.equals(user.getEmail(), email)) {
                return false;
            }
        }
        return true;
    }

    public static User existUser(String email, String password) {
        for (User user : ListSource.users) {
            if (Objects.equals(user.getEmail(), email) && password.equals(user.getPassword())) {
                return user.isBlocked() ? null : user;
            }
        }
        return null;
    }

    public static boolean getBook(String bookId) {
        for (Book book : ListSource.books) {
            if (Objects.equals(book.getId().toString(), bookId) && book.getCount() > 0) {
                book.setCount((short) (book.getCount() - 1));
                return true;
            }
        }
        return false;
    }
}
