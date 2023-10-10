package ru.roman.courseproject.util;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import ru.roman.courseproject.models.Book;

@Component
public class BookValidator implements Validator {
    @Override
    public boolean supports(Class<?> clazz) {
        return Book.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {

        Book book = (Book) target;

        if(book.getTitle() == null){
            errors.rejectValue("title", "", "Название книги не должно быть пустым");
        }
        if(book.getAuthor() == null){
            errors.rejectValue("author", "", "Имя автора не должно быть пустым");
        }
        if(book.getYear() < 1 || book.getYear() > 3000){
            errors.rejectValue("year", "", "Год должен быть больше 0, но меньше 3000");
        }
        if(book.getTitle().length() < 2 || book.getTitle().length() > 100){
            errors.rejectValue("title", "", "Название книги должно быть от 2 до 100 символов");
        }
        if(book.getAuthor().length() < 2 || book.getAuthor().length() > 100){
            errors.rejectValue("author", "", "Имя автора должно быть от 2 до 100 символов");
        }
    }
}
