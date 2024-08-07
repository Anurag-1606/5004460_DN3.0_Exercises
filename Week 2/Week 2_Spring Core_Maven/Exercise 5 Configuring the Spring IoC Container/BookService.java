package com.example.library;

public class BookService {
    private BookRepository bookRepository;

    // Setter method for BookRepository
    public void setBookRepository(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public void addBook(String bookName) {
        bookRepository.save(bookName);
    }
}
