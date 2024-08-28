import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/books")
public class BookController {

    @GetMapping(produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public List<Book> getAllBooks() {
        return Arrays.asList(
                new Book(1, "The Catcher in the Rye", "J.D. Salinger"),
                new Book(2, "To Kill a Mockingbird", "Harper Lee")
        );
    }
}
