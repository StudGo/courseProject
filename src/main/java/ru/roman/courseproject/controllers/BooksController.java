package ru.roman.courseproject.controllers;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.roman.courseproject.models.Book;
import ru.roman.courseproject.models.User;
import ru.roman.courseproject.service.BooksService;
import ru.roman.courseproject.service.UsersService;

@Controller
@RequestMapping("/books")
public class BooksController {
    private final BooksService booksService;
    private final UsersService usersService;

    @Autowired
    public BooksController(BooksService booksService, UsersService usersService) {
        this.booksService = booksService;
        this.usersService = usersService;
    }

    @GetMapping()
    public String index(Model model,
                        @RequestParam(value = "page", required = false) Integer page,
                        @RequestParam(value = "books_per_page", required = false) Integer booksPerPage){
        if(page == null || booksPerPage == null)
            model.addAttribute("books", booksService.findAll());
        else
            model.addAttribute("books", booksService.findWithPagination(page, booksPerPage));
        return "books/index";
    }

    @GetMapping("/{id}")
    public String show(@PathVariable("id") int id, Model model,
                       @ModelAttribute("user") User user){
        model.addAttribute("book", booksService.findOne(id));

        User bookOwner = booksService.getBookOwner(id);

        if(bookOwner != null)
            model.addAttribute("owner", bookOwner);
        else
            model.addAttribute("users", usersService.findAll());

        return "books/show";
    }

    @GetMapping("/{id}/edit")
    public String edit(@PathVariable("id") int id,
                       Model model){
        model.addAttribute("book", booksService.findOne(id));
        return "books/edit";
    }

    @PostMapping("/{id}")
    public String update(@ModelAttribute("book") Book book, @PathVariable("id") int id,
                         BindingResult bindingResult){
        if(bindingResult.hasErrors())
            return "books/edit";

        booksService.update(id, book);
        return "redirect:/books/" + id;
    }

    @GetMapping("/new")
    public String newBook(@ModelAttribute("book") Book book){
        return "books/new";
    }

    @PostMapping()
    public String create(@ModelAttribute("book") @Valid Book book,
                         BindingResult bindingResult) {

        if (bindingResult.hasErrors())
            return "books/new";

        booksService.save(book);
        return "redirect:/books";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id){
        booksService.delete(id);
        return "redirect:/books";
    }

    @PatchMapping("/{id}/release")
    public String release(@PathVariable("id") int id){
        booksService.release(id);
        return "redirect:/books/" + id;
    }

    @PatchMapping("/{id}/assign")
    public String assign(@PathVariable("id") int id,
                         @ModelAttribute("user") User user){
        booksService.assign(id, user);
        return "redirect:/books/" + id;
    }

    @GetMapping("/search")
    public String search(){
        return "books/search";
    }

    @PostMapping("/search")
    public String doSearch(Model model, @RequestParam("query") String query){
        model.addAttribute("books", booksService.searchByTitle(query));
        return "books/search";
    }
}
