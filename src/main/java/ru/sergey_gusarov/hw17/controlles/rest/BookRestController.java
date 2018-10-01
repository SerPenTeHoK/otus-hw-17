package ru.sergey_gusarov.hw17.controlles.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.sergey_gusarov.hw17.domain.books.Author;
import ru.sergey_gusarov.hw17.domain.books.Book;
import ru.sergey_gusarov.hw17.domain.books.BookComment;
import ru.sergey_gusarov.hw17.domain.books.Genre;
import ru.sergey_gusarov.hw17.exception.NotFoundException;
import ru.sergey_gusarov.hw17.service.books.AuthorService;
import ru.sergey_gusarov.hw17.service.books.BookService;

import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/book")
public class BookRestController {
    private final BookService bookService;
    private final AuthorService authorService;

    @Autowired
    public BookRestController(BookService bookService, AuthorService authorService) {
        this.bookService = bookService;
        this.authorService = authorService;
    }

    @GetMapping
    public Flux<Book> listBookPage() {
        return bookService.findAll();

    }

    @PostMapping
    public Flux<Book> addBook(@RequestBody Book book) {
        bookService.save(book);
        return bookService.findAll();
    }

    @DeleteMapping("/{id}")
    public Flux<Book> deleteBook(@PathVariable String id) {
        bookService.deleteById(id);
        return bookService.findAll();
    }

    @PutMapping
    public Mono<Book> editBook(@RequestBody Book book) {
        bookService.save(book);
        return bookService.getById(book.getId());
    }

    @DeleteMapping("/deleteGenreFromBook/{bookId}")
    public Flux<Genre> deleteGenre(@PathVariable String bookId, @RequestBody HashMap requestBody) {
        return Flux.empty();
    }

    @DeleteMapping("/deleteCommentFromBook/{bookId}")
    public Flux<BookComment> deleteComment(@PathVariable String bookId, @RequestBody HashMap requestBody) {
        return Flux.empty();
    }

    @DeleteMapping("/deleteAuthorFromBook/{bookId}")
    public Flux<Author> deleteAuthor(@PathVariable String bookId, @RequestBody HashMap requestBody) {
        return Flux.empty();
    }


    @PostMapping("/addGenreToBook/{bookId}")
    public Flux<Genre> addGenreToBook(@PathVariable String bookId, @RequestBody HashMap requestBody) {
        return Flux.empty();
    }

    @PostMapping("/addCommentToBook/{bookId}")
    public Flux<BookComment> addCommentToBook(@PathVariable String bookId, @RequestBody HashMap requestBody) {
        return Flux.empty();
    }

    @PostMapping("/addAuthorToBook/{bookId}")
    public Flux<Author> addAuthorToBook(@PathVariable String bookId, @RequestBody HashMap requestBody) {
        return Flux.empty();
    }

    @GetMapping("/getAuthorsFromBook/{bookId}")
    public Flux<Author> listAuthorBook(@PathVariable String bookId) {
        return Flux.empty();
    }

}
