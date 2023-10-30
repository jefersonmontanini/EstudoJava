package MyProject.rest.controller;

import MyProject.domain.entity.Product;
import MyProject.domain.repository.Products;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.regex.Matcher;

import static org.springframework.http.HttpStatus.*;

@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private Products products;

    @GetMapping("{id}")
    public Product getById(@PathVariable Integer id) {
        return products
                .findById(id)
                    .orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND, "não foi possivel encontrar o produto"));
    }

    @GetMapping
    public List<Product> getByFilter(Product filter) {
        ExampleMatcher exampleMatcher = ExampleMatcher
                .matching()
                .withIgnoreCase()
                .withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING);

        Example example = Example.of(filter, exampleMatcher);
        return products.findAll(example);
    }

    @PostMapping
    @ResponseStatus(CREATED)
    public Product save(@RequestBody @Valid Product product) {
        return products.save(product);
    }

    @PutMapping("{id}")
    @ResponseStatus(NO_CONTENT)
    public void update( @Valid @PathVariable Integer id,
                       @RequestBody Product product) {
        products.findById(id)
                .map( product1 -> {
                    product.setId(product1.getId());
                    products.save(product);
                    return product1;
                }).orElseThrow( ()-> new ResponseStatusException(HttpStatus.NOT_FOUND, "não foi possivel encontrar o produto") );
    }

    @DeleteMapping("{id}")
    @ResponseStatus(NO_CONTENT)
    public void delete(@PathVariable Integer id) {
        products.findById(id)
                .map(product -> {
                    products.delete(product);
                    return product;
                }).orElseThrow( ()-> new ResponseStatusException(HttpStatus.NOT_FOUND, "não foi possivel encontrar o produto") );
    }

}
