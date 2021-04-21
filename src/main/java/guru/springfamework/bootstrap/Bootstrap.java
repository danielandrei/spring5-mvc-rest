package guru.springfamework.bootstrap;

import guru.springfamework.domain.Category;
import guru.springfamework.domain.Customer;
import guru.springfamework.repositories.CategoryRepository;
import guru.springfamework.repositories.CustomerRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class Bootstrap implements CommandLineRunner {

    private final CategoryRepository categoryRepository;
    private final CustomerRepository customerRepository;

    public Bootstrap(CategoryRepository categoryRepository, CustomerRepository customerRepository) {
        this.categoryRepository = categoryRepository;
        this.customerRepository = customerRepository;
    }


    @Override
    public void run(String... args) throws Exception {
        loadCategories();
        loadCustomers();
    }

    private void loadCustomers() {
        customerRepository.save(Customer.builder().firstName("David").lastName("Winter").build());
        customerRepository.save(Customer.builder().firstName("Anne").lastName("Hine").build());
        customerRepository.save(Customer.builder().firstName("Alice").lastName("Eastman").build());
        customerRepository.save(Customer.builder().firstName("sushmitha").lastName("k").build());
        customerRepository.save(Customer.builder().firstName("Freddy").lastName("Meyers").build());
        customerRepository.save(Customer.builder().firstName("Freddy").lastName("Meyers").build());
        customerRepository.save(Customer.builder().firstName("Freddy").lastName("Meyers").build());
        customerRepository.save(Customer.builder().firstName("Freddy").lastName("Meyers").build());

        System.out.println("Customers Loaded = " + customerRepository.count());
    }

    private void loadCategories() {
        Category fruits = new Category();
        fruits.setName("Fruits");

        Category dried = new Category();
        dried.setName("Dried");

        Category fresh = new Category();
        fresh.setName("Fresh");

        Category exotic = new Category();
        exotic.setName("Exotic");

        Category nuts = new Category();
        nuts.setName("Nuts");

        categoryRepository.save(fruits);
        categoryRepository.save(dried);
        categoryRepository.save(fresh);
        categoryRepository.save(exotic);
        categoryRepository.save(nuts);

        System.out.println("Categories Loaded = "+ categoryRepository.count());
    }
}
