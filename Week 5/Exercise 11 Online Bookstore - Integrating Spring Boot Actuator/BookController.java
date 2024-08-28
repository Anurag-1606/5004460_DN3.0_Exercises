import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BookController {

    private final BookService bookService;
    private final BookMetrics bookMetrics;

    public BookController(BookService bookService, BookMetrics bookMetrics) {
        this.bookService = bookService;
        this.bookMetrics = bookMetrics;
    }

    @GetMapping("/books/{bookId}")
    public Book getBook(@PathVariable String bookId) {
        Book book = bookService.findBookById(bookId);
        bookMetrics.incrementBookView(bookId);  // Record custom metric
        return book;
    }
}
