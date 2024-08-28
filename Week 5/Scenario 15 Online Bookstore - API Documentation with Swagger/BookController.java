package com.example.bookstore.controller;

import com.example.bookstore.model.Book;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/books")
public class BookController {

    private List<Book> books = new ArrayList<>();

    @GetMapping
    public List<Book> getAllBooks() {
        return books;
    }

    @PostMapping
    public Book addBook(@RequestBody Book book) {
        books.add(book);
        return book;
    }

    @GetMapping("/{id}")
    public Book getBookById(@PathVariable String id) {
        return books.stream().filter(book -> book.getId().equals(id)).findFirst().orElse(null);
    }

    @DeleteMapping("/{id}")
    public void deleteBook(@PathVariable String id) {
        books.removeIf(book -> book.getId().equals(id));
    }
    private List<Book> books = new ArrayList<>();

    @Operation(summary = "Get all books", description = "Retrieve a list of all books available in the bookstore")
    @GetMapping
    public List<Book> getAllBooks() {
        return books;
    }

    @Operation(summary = "Add a new book", description = "Add a new book to the bookstore")
    @PostMapping
    public Book addBook(
            @Parameter(description = "Book object that needs to be added to the bookstore", required = true)
            @RequestBody Book book) {
        books.add(book);
        return book;
    }

    @Operation(summary = "Find book by ID", description = "Returns a single book by ID")
    @GetMapping("/{id}")
    public Book getBookById(
            @Parameter(description = "ID of the book to be obtained", required = true)
            @PathVariable String id) {
        return books.stream().filter(book -> book.getId().equals(id)).findFirst().orElse(null);
    }

    @Operation(summary = "Delete a book", description = "Delete a book by its ID")
    @DeleteMapping("/{id}")
    public void deleteBook(
            @Parameter(description = "ID of the book to be deleted", required = true)
            @PathVariable String id) {
        books.removeIf(book -> book.getId().equals(id));
    }
}
