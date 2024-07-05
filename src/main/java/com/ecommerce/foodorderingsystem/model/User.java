package com.ecommerce.foodorderingsystem.model;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.util.Set;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class User implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String firstName;

    private String lastName;

    private String email;

    private String password;

    private boolean isLoggedIn;

    @Enumerated(EnumType.STRING)
    private Role role;

    public String getFullName(){
        return firstName + " " + lastName;
    }
}
