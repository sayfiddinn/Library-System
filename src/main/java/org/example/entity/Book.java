package org.example.entity;

import lombok.*;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Book {
    @NonNull
    private UUID id;
    @NonNull
    private String name;
    @NonNull
    private String author;
    @NonNull
    private Short count;
    @NonNull
    private Double price;
    @NonNull
    private Integer libraryId;

    @Override
    public String toString() {
        return String.format("| %-36s | %-15s | %-15s | %-6.2f | %-5d |", id, author, name, price, count);
    }
}
