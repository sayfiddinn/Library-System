package org.example.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import org.example.entity.enums.Role;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
    @NonNull
    private UUID id;
    @NonNull
    private String email;
    @NonNull
    private String password;
    @NonNull
    private Role role;
    private boolean isBlocked;
}
