
package com.example.inventorycrud;

import com.example.inventorycrud.entity.Product;
import com.example.inventorycrud.repository.ProductRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

    private final ProductRepository repository;

    public ProductController(ProductRepository repository) {
        this.repository = repository;
    }

    @PostMapping
    public ResponseEntity<Product> createProduct(
            @RequestBody Product product) {

        product.setId(null);
        Product saved = repository.save(product);

        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
    }

    @GetMapping
    public List<Product> getAllProducts() {
        return repository.findAll();
    }

    @GetMapping("/{id}")
    public Product getProduct(@PathVariable Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Product not found"));
    }

    @PutMapping("/{id}")
    public Product updateProduct(
            @PathVariable Long id,
            @RequestBody Product updatedProduct) {

        Product existing = repository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Product not found"));

        existing.setName(updatedProduct.getName());
        existing.setCategory(updatedProduct.getCategory());
        existing.setPrice(updatedProduct.getPrice());
        existing.setQuantity(updatedProduct.getQuantity());

        return repository.save(existing);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long id) {

        if (!repository.existsById(id)) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "Product not found");
        }

        repository.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/category/{category}")
    public List<Product> getByCategory(
            @PathVariable String category) {

        return repository.findByCategoryIgnoreCase(category);
    }

    @GetMapping("/search")
    public List<Product> searchByName(
            @RequestParam String name) {

        return repository.findByNameContainingIgnoreCase(name);
    }

    @GetMapping("/low-stock")
    public List<Product> getLowStock(
            @RequestParam(defaultValue = "10") Integer threshold) {

        return repository.findByQuantityLessThan(threshold);
    }

    @GetMapping("/average-price")
    public Double getAveragePrice() {
        return repository.findAveragePrice();
    }

    @PostMapping("/{id}/restock")
    public Product restock(
            @PathVariable Long id,
            @RequestParam Integer amount) {

        if (amount <= 0) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "Restock amount must be greater than zero");
        }

        Product product = repository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Product not found"));

        product.setQuantity(product.getQuantity() + amount);

        return repository.save(product);
    }

    @PostMapping("/{id}/sell")
    public Product sell(
            @PathVariable Long id,
            @RequestParam Integer amount) {

        if (amount <= 0) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "Sell amount must be greater than zero");
        }

        Product product = repository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Product not found"));

        if (product.getQuantity() < amount) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "Not enough stock available");
        }

        product.setQuantity(product.getQuantity() - amount);

        return repository.save(product);
    }
}
