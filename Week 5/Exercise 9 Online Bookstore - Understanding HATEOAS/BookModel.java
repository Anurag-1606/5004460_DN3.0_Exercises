import org.springframework.hateoas.RepresentationModel;
import lombok.Data;

@Data
public class BookModel extends RepresentationModel<BookModel> {
    private Long id;
    private String title;
    private String author;
    private double price;
}
