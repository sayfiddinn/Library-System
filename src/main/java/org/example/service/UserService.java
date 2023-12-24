package org.example.service;

import java.util.UUID;

public interface UserService {
    void booking(UUID userId);

    void newHistory(UUID userId);

    void cancelBooking(UUID userId);

    void allHistory(UUID userId);

    void showBooks();
}
