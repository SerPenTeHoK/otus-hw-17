package ru.sergey_gusarov.hw17.service.books.impl;

import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.sergey_gusarov.hw17.domain.books.Author;
import ru.sergey_gusarov.hw17.domain.books.Book;
import ru.sergey_gusarov.hw17.domain.books.BookComment;
import ru.sergey_gusarov.hw17.domain.books.Genre;
import ru.sergey_gusarov.hw17.exception.NotFoundException;
import ru.sergey_gusarov.hw17.repository.author.AuthorRepository;
import ru.sergey_gusarov.hw17.repository.book.BookRepository;
import ru.sergey_gusarov.hw17.service.books.BookService;

import java.util.List;
import java.util.Optional;

@Service
public class BookServiceImpl implements BookService {
    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;

    public BookServiceImpl(BookRepository bookRepository, AuthorRepository authorRepository) {
        this.bookRepository = bookRepository;
        this.authorRepository = authorRepository;
    }

    @Override
    public Mono<Long> count() {
        return bookRepository.count();
    }

    @Override
    public Mono<Book> getById(String id) {
        return bookRepository.findById(id);
    }

    @Override
    public Mono<Book> findById(String id) {
        return bookRepository.findById(id);
    }

    @Override
    public Flux<Book> findByTitle(String title) {
        return bookRepository.findByTitle(title);
    }

    @Override
    public Mono<Void> deleteById(String id) {
        return bookRepository.deleteById(id);
    }

    @Override
    public Mono<Book> save(Book book) {
        return bookRepository.save(book);
    }

    @Override
    public Mono<Book> add(String title, List<Author> authors, List<Genre> genres) {
        Book book = new Book();
        book.setTitle(title);
        authors.forEach(author -> {
            Flux<Author> authorList = authorRepository.findByName(author.getName());
            authorRepository.save(author);
        });
        book.setGenres(genres);
        book.setAuthors(authors);
        return bookRepository.save(book);
    }

    @Override
    public Flux<Book> findAll() {
        return bookRepository.findAll();
    }

    @Override
    public Mono<Void> deleteAll() {
        return bookRepository.deleteAll();
    }

    @Override
    public Mono<Void> addComment(String id, String comment) {
        return null;
    }

}
