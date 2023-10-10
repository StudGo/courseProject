package ru.roman.courseproject.util;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import ru.roman.courseproject.models.Book;
import ru.roman.courseproject.models.User;

@Component
public class UserValidator implements Validator {
    @Override
    public boolean supports(Class<?> clazz) {
        return User.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        User user = (User) target;

        if(user.getUsername() == null){
            errors.rejectValue("username", "", "Username не должно быть пустым");
        }
        if(user.getEmail() == null){
            errors.rejectValue("email", "", "Email не должно быть пустым");
        }
        if(user.getFullName() == null){
            errors.rejectValue("fullName", "", "Имя не должно быть пустым");
        }
        if(user.getUsername().length() < 2 || user.getUsername().length() > 50){
            errors.rejectValue("username", "", "Username должен быть от 2 до 50 символов");
        }
        if(user.getEmail().length() < 2 || user.getEmail().length() > 100){
            errors.rejectValue("email", "", "Email должнен быть от 2 до 100 символов");
        }
        if(user.getFullName().length() < 2 || user.getFullName().length() > 100){
            errors.rejectValue("fullName", "", "Имя должно быть от 2 до 100 символов");
        }
        if(!user.getEmail().matches("^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$")){
            errors.rejectValue("email", "", "Email должнен быть корректным");
        }
    }
}
