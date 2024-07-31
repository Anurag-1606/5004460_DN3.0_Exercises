import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

class Book {
    private int bookId;
    private String title;
    private String author;

    // Constructor
    public Book(int bookId, String title, String author) {
        this.bookId = bookId;
        this.title = title;
        this.author = author;
    }

    // Getters
    public int getBookId() {
        return bookId;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    @Override
    public String toString() {
        return "Book{" +
                "bookId=" + bookId +
                ", title='" + title + '\'' +
                ", author='" + author + '\'' +
                '}';
    }
}

class Library {
    private List<Book> books;

    public Library() {
        books = new ArrayList<>();
    }

    public void addBook(Book book) {
        books.add(book);
    }

    // Linear Search
    public List<Book> linearSearchByTitle(String title) {
        List<Book> foundBooks = new ArrayList<>();
        for (Book book : books) {
            if (book.getTitle().equalsIgnoreCase(title)) {
                foundBooks.add(book);
            }
        }
        return foundBooks;
    }

    // Binary Search
    public List<Book> binarySearchByTitle(String title) {
        List<Book> foundBooks = new ArrayList<>();
        int left = 0;
        int right = books.size() - 1;

        while (left <= right) {
            int mid = left + (right - left) / 2;
            int comparison = books.get(mid).getTitle().compareToIgnoreCase(title);

            if (comparison == 0) {
                foundBooks.add(books.get(mid));
                // Search left for duplicate titles
                int temp = mid - 1;
                while (temp >= 0 && books.get(temp).getTitle().equalsIgnoreCase(title)) {
                    foundBooks.add(books.get(temp));
                    temp--;
                }
                // Search right for duplicate titles
                temp = mid + 1;
                while (temp < books.size() && books.get(temp).getTitle().equalsIgnoreCase(title)) {
                    foundBooks.add(books.get(temp));
                    temp++;
                }
                break; // Exit after finding duplicates
            } else if (comparison < 0) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }

        return foundBooks;
    }

    // Sort books by title before binary search
    public void sortBooksByTitle() {
        Collections.sort(books, Comparator.comparing(Book::getTitle));
    }
}

public class Main {
    public static void main(String[] args) {
        Library library = new Library();
        
        // Adding books
        library.addBook(new Book(1, "The Great Gatsby", "F. Scott Fitzgerald"));
        library.addBook(new Book(2, "To Kill a Mockingbird", "Harper Lee"));
        library.addBook(new Book(3, "1984", "George Orwell"));
        library.addBook(new Book(4, "The Catcher in the Rye", "J.D. Salinger"));
        library.addBook(new Book(5, "The Great Gatsby", "F. Scott Fitzgerald")); // Duplicate title
        
        // Linear search
        List<Book> linearSearchResults = library.linearSearchByTitle("The Great Gatsby");
        System.out.println("Linear Search Results:");
        for (Book book : linearSearchResults) {
            System.out.println(book);
        }
        
        // Sorting books for binary search
        library.sortBooksByTitle();
        
        // Binary search
        List<Book> binarySearchResults = library.binarySearchByTitle("The Great Gatsby");
        System.out.println("\nBinary Search Results:");
        for (Book book : binarySearchResults) {
            System.out.println(book);
        }
    }
}
