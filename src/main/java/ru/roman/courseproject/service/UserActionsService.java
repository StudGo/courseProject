package ru.roman.courseproject.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.roman.courseproject.repository.UsersRepository;

@Service
@Transactional(readOnly = true)
public class UserActionsService {
    private final UsersRepository usersRepository;

    @Autowired
    public UserActionsService(UsersRepository usersRepository) {
        this.usersRepository = usersRepository;
    }


}
