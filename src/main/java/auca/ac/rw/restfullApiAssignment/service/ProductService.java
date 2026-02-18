package auca.ac.rw.restfullApiAssignment.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import auca.ac.rw.restfullApiAssignment.modal.Product;
import auca.ac.rw.restfullApiAssignment.repository.ProductRepository;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepo;

    public Product create(Product product) {
        // With auto-generated IDs, the client should not send an ID on create.
        product.setId(null);
        return productRepo.save(product);
    }

    public List<Product> getAll() {
        return productRepo.findAll();
    }

    public Product getById(Long id) {
        return productRepo.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Product not found: " + id));
    }

    public Product update(Long id, Product updated) {
        Product existing = getById(id);
        existing.setName(updated.getName());
        existing.setDescription(updated.getDescription());
        existing.setPrice(updated.getPrice());
        existing.setCategory(updated.getCategory());
        existing.setStockQuantity(updated.getStockQuantity());
        return productRepo.save(existing);
    }

    public void delete(Long id) {
        Product existing = getById(id);
        productRepo.delete(existing);
    }
}
