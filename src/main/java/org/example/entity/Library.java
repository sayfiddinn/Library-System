package org.example.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;




@Data
@AllArgsConstructor
@NoArgsConstructor
public class Library {
    @NonNull
    private Integer id;
    @NonNull
    private String name;
    @NonNull
    private Short floorCount;
    @NonNull
    private Short caseCount;
    @NonNull
    private Short shelfCount;
    @NonNull
    private Integer bookCount;

    {
        floorCount = 4;
        caseCount = (short) (floorCount * 20);
        shelfCount = (short) (caseCount * 10);
        bookCount = 10;
    }
}
