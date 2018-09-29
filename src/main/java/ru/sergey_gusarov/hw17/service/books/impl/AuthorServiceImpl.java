package ru.sergey_gusarov.hw17.service.books.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
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
    public Optional<Author> getById(String id) {
        return authorRepository.findById(id);
    }

    @Override
    public Author findById(String id) {
        return authorRepository.findById(id).get();
    }

    @Override
    public Optional<Author> findByName(String name) {
        List<Author> authors = authorRepository.findByName(name);
        if (authors.size() > 0)
            return Optional.ofNullable(authors.get(0));
        else
            return Optional.empty();
    }

    @Override
    public void deleteById(String id) {
        authorRepository.deleteById(id);
    }

    @Override
    public List<Author> deleteByIdAndRetList(String id) {
        authorRepository.deleteById(id);
        return authorRepository.findAll();
    }

    @Override
    public Author save(Author author) {
        return authorRepository.save(author);
    }

    @Override
    public List<Author> findAll() {
        return authorRepository.findAll(new Sort(Sort.Direction.ASC, "name"));
    }
}
