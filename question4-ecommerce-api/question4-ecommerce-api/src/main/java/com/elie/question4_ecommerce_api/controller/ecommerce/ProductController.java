package com.elie.question4_ecommerce_api.controller.ecommerce;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.elie.question4_ecommerce_api.model.ecommerce.Product;


@RestController
@RequestMapping("/api/products")
public class ProductController {


    private List<Product> products = new ArrayList<>();

    // Initialize sample products (10 items)
    public ProductController() {
        products.add(new Product(1L, "iPhone 14", "Apple smartphone", 999.99, "Electronics", 5, "Apple"));
        products.add(new Product(2L, "Galaxy S23", "Samsung smartphone", 899.99, "Electronics", 10, "Samsung"));
        products.add(new Product(3L, "MacBook Pro", "Apple laptop", 1999.99, "Computers", 3, "Apple"));
        products.add(new Product(4L, "Dell XPS 13", "Dell laptop", 1499.99, "Computers", 7, "Dell"));
        products.add(new Product(5L, "Sony Headphones", "Noise-canceling", 199.99, "Accessories", 15, "Sony"));
        products.add(new Product(6L, "Nike Air Max", "Running shoes", 129.99, "Footwear", 20, "Nike"));
        products.add(new Product(7L, "Adidas Sneakers", "Casual shoes", 109.99, "Footwear", 12, "Adidas"));
        products.add(new Product(8L, "Canon EOS 90D", "DSLR Camera", 1199.99, "Photography", 4, "Canon"));
        products.add(new Product(9L, "GoPro Hero 11", "Action camera", 399.99, "Photography", 8, "GoPro"));
        products.add(new Product(10L, "Instant Pot", "Multi-cooker", 99.99, "Kitchen", 10, "Instant Pot"));
    }

    // GET all products
    @GetMapping
    public ResponseEntity<List<Product>> getAllProducts(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int limit) {

        int start = page * limit;
        int end = Math.min(start + limit, products.size());

        if (start >= products.size()) {
            return ResponseEntity.ok(new ArrayList<>());
        }

        return ResponseEntity.ok(products.subList(start, end));
    }

    // GET product by ID
    @GetMapping("/{productId}")
    public ResponseEntity<Product> getProductById(@PathVariable Long productId) {
        for (Product product : products) {
            if (product.getProductId().equals(productId)) {
                return ResponseEntity.ok(product);
            }
        }
        return ResponseEntity.notFound().build();
    }

    // GET by category
    @GetMapping("/category/{category}")
    public ResponseEntity<List<Product>> getByCategory(@PathVariable String category) {
        List<Product> result = new ArrayList<>();
        for (Product product : products) {
            if (product.getCategory().equalsIgnoreCase(category)) {
                result.add(product);
            }
        }
        return ResponseEntity.ok(result);
    }

    // GET by brand
    @GetMapping("/brand/{brand}")
    public ResponseEntity<List<Product>> getByBrand(@PathVariable String brand) {
        List<Product> result = new ArrayList<>();
        for (Product product : products) {
            if (product.getBrand().equalsIgnoreCase(brand)) {
                result.add(product);
            }
        }
        return ResponseEntity.ok(result);
    }

    // Search by keyword in name or description
    @GetMapping("/search")
    public ResponseEntity<List<Product>> searchProducts(@RequestParam String keyword) {
        List<Product> result = new ArrayList<>();
        for (Product product : products) {
            if (product.getName().toLowerCase().contains(keyword.toLowerCase()) ||
                product.getDescription().toLowerCase().contains(keyword.toLowerCase())) {
                result.add(product);
            }
        }
        return ResponseEntity.ok(result);
    }

    // GET products within price range
    @GetMapping("/price-range")
    public ResponseEntity<List<Product>> getProductsByPriceRange(
            @RequestParam double min, @RequestParam double max) {
        List<Product> result = new ArrayList<>();
        for (Product product : products) {
            if (product.getPrice() >= min && product.getPrice() <= max) {
                result.add(product);
            }
        }
        return ResponseEntity.ok(result);
    }

    // GET in-stock products
    @GetMapping("/in-stock")
    public ResponseEntity<List<Product>> getInStockProducts() {
        List<Product> result = new ArrayList<>();
        for (Product product : products) {
            if (product.getStockQuantity() > 0) {
                result.add(product);
            }
        }
        return ResponseEntity.ok(result);
    }

    // POST add new product
    @PostMapping
    public ResponseEntity<Product> addProduct(@RequestBody Product product) {
        products.add(product);
        return ResponseEntity.status(HttpStatus.CREATED).body(product);
    }

    // PUT update product details
    @PutMapping("/{productId}")
    public ResponseEntity<Product> updateProduct(@PathVariable Long productId,
                                                 @RequestBody Product updatedProduct) {
        for (Product product : products) {
            if (product.getProductId().equals(productId)) {
                product.setName(updatedProduct.getName());
                product.setDescription(updatedProduct.getDescription());
                product.setPrice(updatedProduct.getPrice());
                product.setCategory(updatedProduct.getCategory());
                product.setStockQuantity(updatedProduct.getStockQuantity());
                product.setBrand(updatedProduct.getBrand());
                return ResponseEntity.ok(product);
            }
        }
        return ResponseEntity.notFound().build();
    }

    // PATCH update stock quantity
    @PatchMapping("/{productId}/stock")
    public ResponseEntity<Product> updateStock(@PathVariable Long productId,
                                               @RequestParam int quantity) {
        for (Product product : products) {
            if (product.getProductId().equals(productId)) {
                product.setStockQuantity(quantity);
                return ResponseEntity.ok(product);
            }
        }
        return ResponseEntity.notFound().build();
    }

    // DELETE product
    @DeleteMapping("/{productId}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long productId) {
        for (Product product : products) {
            if (product.getProductId().equals(productId)) {
                products.remove(product);
                return ResponseEntity.noContent().build();
            }
        }
        return ResponseEntity.notFound().build();
    }
 
}
