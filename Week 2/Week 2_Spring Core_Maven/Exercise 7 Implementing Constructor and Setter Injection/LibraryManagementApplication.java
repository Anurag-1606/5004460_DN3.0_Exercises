package com.example;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import com.example.service.BookService;

public class LibraryManagementApplication {
    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        
        BookService bookService = context.getBean("bookService", BookService.class);
        
        // Example usage of bookService
        // bookService.addBook(new Book("Title", "Author"));
        
        System.out.println("BookService successfully created with injected dependencies.");
    }
}
