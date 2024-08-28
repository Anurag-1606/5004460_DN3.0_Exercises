import org.springframework.hateoas.RepresentationModel;
import lombok.Data;

@Data
public class CustomerModel extends RepresentationModel<CustomerModel> {
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
}
