package ru.sergey_gusarov.hw17.service.books.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.sergey_gusarov.hw17.domain.books.Author;
import ru.sergey_gusarov.hw17.repository.author.AuthorRepository;
import ru.sergey_gusarov.hw17.service.books.AuthorService;

import java.util.List;
import java.util.Optional;

@Service
public class AuthorServiceImpl implements AuthorService {
    private final AuthorRepository authorRepository;

    @Autowired
    public AuthorServiceImpl(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    @Override
    public Mono<Author> getById(String id) {
        return authorRepository.findById(id);
    }

    @Override
    public Mono<Author> findById(String id) {
        return authorRepository.findById(id);
    }

    @Override
    public Flux<Author> findByName(String name) {
        return authorRepository.findByName(name);
    }

    @Override
    public Mono<Void> deleteById(String id) {
        return authorRepository.deleteById(id);
    }

    @Override
    public Flux<Author> deleteByIdAndRetList(String id) {
        authorRepository.deleteById(id);
        return authorRepository.findAll();
    }

    @Override
    public Mono<Author> save(Author author) {
        return authorRepository.save(author);
    }

    @Override
    public Flux<Author> findAll() {
        return authorRepository.findAll();
    }
}
