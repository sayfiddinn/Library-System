package org.example.entity;

import lombok.Data;

import java.time.LocalDate;
import java.util.UUID;

@Data
public class Booking {
    private UUID userId;
    private UUID bookId;
    private LocalDate bronDate;
    {
        bronDate =LocalDate.now();
    }
    private boolean isSuccessful;
    @Override
    public String toString() {
        return String.format("| %-36s | %-10s | %-10s | %-9s |", bookId, bronDate, bronDate.plusDays(3), isSuccessful);
    }
}
