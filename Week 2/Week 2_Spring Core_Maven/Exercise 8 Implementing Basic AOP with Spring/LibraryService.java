package com.library;

import org.springframework.stereotype.Service;

@Service
public class LibraryService {

    public void addBook(String bookName) {
        System.out.println("Adding book: " + bookName);
    }

    public void deleteBook(String bookName) {
        System.out.println("Deleting book: " + bookName);
    }
}
