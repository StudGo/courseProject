package ru.roman.courseproject.controllers;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.roman.courseproject.models.User;
import ru.roman.courseproject.service.BooksService;
import ru.roman.courseproject.service.UsersService;
import ru.roman.courseproject.util.UserValidator;
import org.springframework.ui.Model;

@Controller
@RequestMapping("/users")
public class UsersController {

    private final UsersService usersService;
    private final UserValidator userValidator;
    private final BooksService booksService;

    @Autowired
    public UsersController(UsersService usersService, UserValidator userValidator, BooksService booksService) {
        this.usersService = usersService;
        this.userValidator = userValidator;
        this.booksService = booksService;
    }

    @GetMapping()
    public String index(Model model){
        model.addAttribute("users", usersService.findAll());
        return "users/index";
    }

    @GetMapping("/{id}")
    public String show(@PathVariable("id") int id, Model model){
        model.addAttribute("user", usersService.findOne(id));
        model.addAttribute("books", usersService.getBooksByUserId(id));
        return "users/show";
    }

    @GetMapping("/{id}/edit")
    public String edit(Model model, @PathVariable("id") int id){
        model.addAttribute("user", usersService.findOne(id));
        return "users/edit";
    }

    @PostMapping("/{id}")
    public String update(@ModelAttribute("user") @Valid User user,
                         @PathVariable("id") int id,
                         BindingResult bindingResult,
                         Model model){

        if(bindingResult.hasErrors()){
            model.addAttribute("user", usersService.findOne(id));
            return "users/edit";
        }


        usersService.update(id, user);
        return "redirect:/users/" + id;
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id){
        usersService.delete(id);
        return "redirect:/users";
    }
}
