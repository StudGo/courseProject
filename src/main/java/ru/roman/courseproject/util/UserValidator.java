package ru.roman.courseproject.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import ru.roman.courseproject.models.User;
import ru.roman.courseproject.service.UsersService;

@Component
public class UserValidator implements Validator {

    private final UsersService usersService;

    @Autowired
    public UserValidator(UsersService usersService) {
        this.usersService = usersService;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return User.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        User user = (User) target;

        if(usersService.getUserByUsername(user.getUsername()).isPresent()){
            errors.rejectValue("username", "", "Человек с таким никнеймом уже существует");
        }

    }
}
