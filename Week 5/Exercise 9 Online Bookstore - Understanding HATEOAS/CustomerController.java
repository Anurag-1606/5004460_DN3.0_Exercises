import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@RestController
@RequestMapping("/api/customers")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @Autowired
    private CustomerModelAssembler assembler;

    @PostMapping
    public ResponseEntity<CustomerModel> createCustomer(@Validated @RequestBody Customer customer) {
        Customer savedCustomer = customerService.createCustomer(customer);
        CustomerModel customerModel = assembler.toModel(savedCustomer);
        return ResponseEntity.created(customerModel.getRequiredLink(IanaLinkRelations.SELF).toUri()).body(customerModel);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CustomerModel> getCustomerById(@PathVariable Long id) {
        Optional<Customer> customer = customerService.getCustomerById(id);
        return customer.map(value -> ResponseEntity.ok(assembler.toModel(value)))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping
    public CollectionModel<CustomerModel> getAllCustomers() {
        List<Customer> customers = customerService.getAllCustomers();
        return assembler.toCollectionModel(customers);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CustomerModel> updateCustomer(@PathVariable Long id, @Validated @RequestBody Customer customerDetails) {
        Customer updatedCustomer = customerService.updateCustomer(id, customerDetails);
        return ResponseEntity.ok(assembler.toModel(updatedCustomer));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCustomer(@PathVariable Long id) {
        customerService.deleteCustomer(id);
        return ResponseEntity.noContent().build();
    }
}
