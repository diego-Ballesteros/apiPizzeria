package com.platzi.pizza.persistence.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "users")
@Getter
@Setter
@NoArgsConstructor
public class UserEntity {
    @Id
    @Column(nullable = false, length = 20)
    private String username;
    @Column(nullable = false, length = 200)
    private String password;
    @Column(length = 100)
    private String email;
    @Column(nullable = false, columnDefinition = "TINYINT")
    private Boolean locked;
    @Column(nullable = false, columnDefinition = "TINYINT")
    private Boolean disable;
    @OneToMany(mappedBy = "user", fetch = FetchType.EAGER)
    private List<UserRoleEntity> roles;

    @Override
    public String toString() {
        return "UserEntity{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", locked=" + locked +
                ", disable=" + disable +
                ", roles=" + roles +
                '}';
    }
}