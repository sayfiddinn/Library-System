package org.example.util;

import org.example.database.ListSource;
import org.example.entity.Book;
import org.example.entity.Library;
import org.example.entity.User;
import org.example.entity.enums.Role;

import java.util.UUID;

public class Runner {
    public static void generateDefault() {
        User user = new User();
        user.setId(UUID.randomUUID());
        user.setEmail("admin@gmail.com");
        user.setPassword("admin_root123");
        user.setRole(Role.ADMIN);
        ListSource.users.add(user);

        User user1 = new User();
        user1.setId(UUID.randomUUID());
        user1.setEmail("moder@gmail.com");
        user1.setPassword("moder_root123");
        user1.setRole(Role.MODERATOR);
        ListSource.users.add(user1);

        Library library = new Library();
        library.setId(1);
        library.setName("first_library");
        library.setBookCount(1);
        ListSource.libraries.add(library);

        Book book = new Book();
        book.setId(UUID.randomUUID());
        book.setName("Diqqat");
        book.setAuthor("Kel Nyuport");
        book.setCount((short) 10);
        book.setPrice(30000D);
        book.setLibraryId(1);
        ListSource.books.add(book);
    }
}
