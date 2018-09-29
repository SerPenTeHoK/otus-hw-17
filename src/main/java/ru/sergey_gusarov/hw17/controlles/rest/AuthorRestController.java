package ru.sergey_gusarov.hw17.controlles.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.sergey_gusarov.hw17.domain.books.Author;
import ru.sergey_gusarov.hw17.exception.NotFoundException;
import ru.sergey_gusarov.hw17.service.books.AuthorService;

import java.util.List;

@RestController
@RequestMapping("/author")
public class AuthorRestController {
    private final AuthorService authorService;

    @Autowired
    public AuthorRestController(AuthorService authorService) {
        this.authorService = authorService;
    }

    @GetMapping
    public Flux<Author> listAuthorPage() {
        List<Author> authors = authorService.findAll();
        return Flux.fromStream(authors.stream());
    }

    @GetMapping("/{id}")
    public Mono<Author> getAuthor(@PathVariable String id) {
        Author author = authorService.getById(id).orElseThrow(NotFoundException::new);
        return Mono.just(author);
    }

    @PostMapping
    public Flux<Author> addAuthor(@RequestBody Author author) {
        authorService.save(author);
        return Flux.fromStream(authorService.findAll().stream());
    }

    @DeleteMapping("/{id}")
    public Flux<Author> deleteAuthor(@PathVariable String id) {
        authorService.deleteById(id);
        return Flux.fromStream(authorService.findAll().stream());
    }

    @PutMapping
    public Mono<Author> editAuthor(@RequestBody Author author) {
        Author authorFromDb = authorService.getById(author.getId()).orElseThrow(NotFoundException::new);
        authorFromDb.setName(author.getName());
        authorService.save(authorFromDb);
        return Mono.just(authorService.getById(authorFromDb.getId()).orElseThrow(NotFoundException::new));
    }
}
