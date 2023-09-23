package ru.roman.courseproject.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.roman.courseproject.models.User;

@Repository
public interface UsersRepository extends JpaRepository<User, Integer> {
}
