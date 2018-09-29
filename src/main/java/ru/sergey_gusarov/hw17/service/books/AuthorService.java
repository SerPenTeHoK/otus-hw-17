package ru.sergey_gusarov.hw17.service.books;

import reactor.core.publisher.Flux;
import ru.sergey_gusarov.hw17.domain.books.Author;

import java.util.List;
import java.util.Optional;

public interface AuthorService {
    Optional<Author> getById(String id);
    Author findById(String id);

    Optional<Author> findByName(String name);

    void deleteById(String id);

    List<Author> deleteByIdAndRetList(String id);

    Author save(Author author);

    List<Author> findAll();
}
