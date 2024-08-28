import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@Component
public class BookModelAssembler extends RepresentationModelAssemblerSupport<Book, BookModel> {

    public BookModelAssembler() {
        super(BookController.class, BookModel.class);
    }

    @Override
    public BookModel toModel(Book entity) {
        BookModel model = instantiateModel(entity);
        model.setId(entity.getId());
        model.setTitle(entity.getTitle());
        model.setAuthor(entity.getAuthor());
        model.setPrice(entity.getPrice());

        model.add(linkTo(methodOn(BookController.class).getBookById(entity.getId())).withSelfRel());
        model.add(linkTo(methodOn(BookController.class).getAllBooks()).withRel("books"));
        return model;
    }
}
