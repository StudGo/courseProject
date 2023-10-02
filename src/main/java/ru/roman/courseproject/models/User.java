package ru.roman.courseproject.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @NotNull
    @Column(name = "username")
    @Size(min = 2, max = 50, message = "Логин должен быть от 2 до 50 символов")
    private String username;

    @NotNull
    @Column(name = "password")
    private String password;

    @OneToMany(mappedBy = "owner")
    private List<Book> books;
}
