import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@RestController
@RequestMapping("/api/books")
public class BookController {

    @Autowired
    private BookService bookService;

    @Autowired
    private BookModelAssembler assembler;

    @PostMapping
    public ResponseEntity<BookModel> createBook(@Validated @RequestBody Book book) {
        Book savedBook = bookService.createBook(book);
        BookModel bookModel = assembler.toModel(savedBook);
        return ResponseEntity.created(bookModel.getRequiredLink(IanaLinkRelations.SELF).toUri()).body(bookModel);
    }

    @GetMapping("/{id}")
    public ResponseEntity<BookModel> getBookById(@PathVariable Long id) {
        Optional<Book> book = bookService.getBookById(id);
        return book.map(value -> ResponseEntity.ok(assembler.toModel(value)))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping
    public CollectionModel<BookModel> getAllBooks() {
        List<Book> books = bookService.getAllBooks();
        return assembler.toCollectionModel(books);
    }

    @PutMapping("/{id}")
    public ResponseEntity<BookModel> updateBook(@PathVariable Long id, @Validated @RequestBody Book bookDetails) {
        Book updatedBook = bookService.updateBook(id, bookDetails);
        return ResponseEntity.ok(assembler.toModel(updatedBook));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBook(@PathVariable Long id) {
        bookService.deleteBook(id);
        return ResponseEntity.noContent().build();
    }
}
