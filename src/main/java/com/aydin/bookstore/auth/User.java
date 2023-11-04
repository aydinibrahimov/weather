package com.aydin.bookstore.auth;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;


public class User {
    @Email
    private String email;
    @Size(min = 8, max = 85)
    private String password;
    @Column(nullable = false)
    private Role role;
}
