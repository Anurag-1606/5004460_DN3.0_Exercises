package com.example;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import com.example.service.BookService;

public class LibraryManagementApplication {
    public static void main(String[] args) {
        // Load the Spring context from applicationContext.xml
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");

        // Retrieve the BookService bean
        BookService bookService = context.getBean("bookService", BookService.class);

        // Test the BookService functionality
        bookService.addBook("The Great Gatsby");
        System.out.println("Book added successfully!");
    }
}
