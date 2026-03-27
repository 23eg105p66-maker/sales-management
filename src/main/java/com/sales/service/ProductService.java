package com.sales.service;

import com.sales.dto.SalesDtos.*;
import com.sales.entity.Product;
import com.sales.repository.ProductRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class ProductService {

    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<ProductResponse> getAllProducts() {
        return productRepository.findAll()
                .stream().map(this::toResponse).collect(Collectors.toList());
    }

    public List<ProductResponse> getActiveProducts() {
        return productRepository.findByActive(true)
                .stream().map(this::toResponse).collect(Collectors.toList());
    }

    public ProductResponse getProductById(Long id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found with id: " + id));
        return toResponse(product);
    }

    public ProductResponse createProduct(ProductRequest request) {
        if (request.getSku() != null && productRepository.findBySku(request.getSku()).isPresent())
            throw new RuntimeException("SKU already exists: " + request.getSku());

        Product product = Product.builder()
                .name(request.getName())
                .description(request.getDescription())
                .price(request.getPrice())
                .stockQuantity(request.getStockQuantity())
                .category(request.getCategory())
                .sku(request.getSku())
                .active(true)
                .build();

        return toResponse(productRepository.save(product));
    }

    public ProductResponse updateProduct(Long id, ProductRequest request) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found with id: " + id));

        if (request.getName() != null) product.setName(request.getName());
        if (request.getDescription() != null) product.setDescription(request.getDescription());
        if (request.getPrice() != null) product.setPrice(request.getPrice());
        if (request.getStockQuantity() != null) product.setStockQuantity(request.getStockQuantity());
        if (request.getCategory() != null) product.setCategory(request.getCategory());
        if (request.getSku() != null) product.setSku(request.getSku());

        return toResponse(productRepository.save(product));
    }

    public void deleteProduct(Long id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found with id: " + id));
        product.setActive(false);
        productRepository.save(product);
    }

    public List<ProductResponse> getProductsByCategory(String category) {
        return productRepository.findByCategory(category)
                .stream().map(this::toResponse).collect(Collectors.toList());
    }

    public List<ProductResponse> getLowStockProducts(int threshold) {
        return productRepository.findLowStockProducts(threshold)
                .stream().map(this::toResponse).collect(Collectors.toList());
    }

    public List<String> getAllCategories() {
        return productRepository.findAllCategories();
    }

    public List<ProductResponse> searchProducts(String name) {
        return productRepository.findByNameContainingIgnoreCase(name)
                .stream().map(this::toResponse).collect(Collectors.toList());
    }

    public void updateStock(Long id, int quantity) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found with id: " + id));
        product.setStockQuantity(product.getStockQuantity() + quantity);
        productRepository.save(product);
    }

    private ProductResponse toResponse(Product p) {
        ProductResponse res = new ProductResponse();
        res.setId(p.getId());
        res.setName(p.getName());
        res.setDescription(p.getDescription());
        res.setPrice(p.getPrice());
        res.setStockQuantity(p.getStockQuantity());
        res.setCategory(p.getCategory());
        res.setSku(p.getSku());
        res.setActive(p.isActive());
        return res;
    }
}
