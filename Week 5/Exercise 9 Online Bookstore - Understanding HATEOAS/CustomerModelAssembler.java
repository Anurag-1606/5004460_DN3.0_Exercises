import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@Component
public class CustomerModelAssembler extends RepresentationModelAssemblerSupport<Customer, CustomerModel> {

    public CustomerModelAssembler() {
        super(CustomerController.class, CustomerModel.class);
    }

    @Override
    public CustomerModel toModel(Customer entity) {
        CustomerModel model = instantiateModel(entity);
        model.setId(entity.getId());
        model.setFirstName(entity.getFirstName());
        model.setLastName(entity.getLastName());
        model.setEmail(entity.getEmail());

        model.add(linkTo(methodOn(CustomerController.class).getCustomerById(entity.getId())).withSelfRel());
        model.add(linkTo(methodOn(CustomerController.class).getAllCustomers()).withRel("customers"));
        return model;
    }
}
