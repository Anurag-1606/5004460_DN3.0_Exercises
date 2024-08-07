package com.example.service;

import com.example.repository.BookRepository;

public class BookService {
    private BookRepository bookRepository;

    // Constructor for constructor injection
    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    // Setter for setter injection
    public void setBookRepository(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    // Additional methods for the service
    public void addBook(Book book) {
        bookRepository.save(book);
    }
}
