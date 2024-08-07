package com.example.service;

import com.example.repository.BookRepository;

public class BookService {
    private BookRepository bookRepository;

    // Setter method for dependency injection
    public void setBookRepository(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    // Example method to demonstrate the service
    public void addBook(String bookName) {
        bookRepository.save(bookName);
    }
}
