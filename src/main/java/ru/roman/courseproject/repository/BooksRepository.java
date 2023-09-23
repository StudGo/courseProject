package ru.roman.courseproject.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.roman.courseproject.models.Book;

@Repository
public interface BooksRepository extends JpaRepository<Book, Integer> {
}
