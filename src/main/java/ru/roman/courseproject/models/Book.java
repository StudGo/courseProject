package ru.roman.courseproject.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor

@Entity
@Table(name = "book")
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @NotNull
    @Column(name = "title")
    @Size(min = 2, max = 100, message = "Название книги должно быть от 2 до 100 символов")
    private String title;

    @NotNull
    @Column(name = "author")
    @Size(min = 2, max = 100, message = "Имя автора должно быть от 2 до 100 символов")
    private String author;

    @NotNull
    @Size(max = 3000, message = "Год должен быть корректным")
    @Column(name = "year")
    private int year;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User owner;
}
