package com.library;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class LibraryManagementApplication {
    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");

        // Retrieve the BookService bean to test the configuration
        BookService bookService = context.getBean(BookService.class);
        System.out.println("BookService bean retrieved: " + bookService);
    }
}
