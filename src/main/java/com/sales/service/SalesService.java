package com.sales.service;

import com.sales.dto.SalesDtos.*;
import com.sales.entity.Product;
import com.sales.entity.Sale;
import com.sales.entity.User;
import com.sales.repository.ProductRepository;
import com.sales.repository.SalesRepository;
import com.sales.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class SalesService {

    private final SalesRepository salesRepository;
    private final ProductRepository productRepository;
    private final UserRepository userRepository;

    public SalesService(SalesRepository salesRepository, ProductRepository productRepository, UserRepository userRepository) {
        this.salesRepository = salesRepository;
        this.productRepository = productRepository;
        this.userRepository = userRepository;
    }

    public List<SaleResponse> getAllSales() {
        return salesRepository.findAll()
                .stream().map(this::toResponse).collect(Collectors.toList());
    }

    public SaleResponse getSaleById(Long id) {
        Sale sale = salesRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Sale not found with id: " + id));
        return toResponse(sale);
    }

    public SaleResponse createSale(SaleRequest request) {
        Product product = productRepository.findById(request.getProductId())
                .orElseThrow(() -> new RuntimeException("Product not found with id: " + request.getProductId()));
        User user = userRepository.findById(request.getUserId())
                .orElseThrow(() -> new RuntimeException("User not found with id: " + request.getUserId()));

        if (!product.isActive())
            throw new RuntimeException("Product is not active: " + product.getName());
        if (product.getStockQuantity() < request.getQuantity())
            throw new RuntimeException("Insufficient stock. Available: " + product.getStockQuantity());

        BigDecimal totalAmount = product.getPrice().multiply(BigDecimal.valueOf(request.getQuantity()));

        Sale sale = Sale.builder()
                .product(product)
                .user(user)
                .quantity(request.getQuantity())
                .unitPrice(product.getPrice())
                .totalAmount(totalAmount)
                .status("COMPLETED")
                .saleDate(LocalDateTime.now())
                .customerName(request.getCustomerName())
                .customerEmail(request.getCustomerEmail())
                .notes(request.getNotes())
                .build();

        // Deduct stock
        product.setStockQuantity(product.getStockQuantity() - request.getQuantity());
        productRepository.save(product);

        return toResponse(salesRepository.save(sale));
    }

    public SaleResponse updateSaleStatus(Long id, String status) {
        Sale sale = salesRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Sale not found with id: " + id));

        // Restore stock if cancelled
        if ("CANCELLED".equals(status) && "COMPLETED".equals(sale.getStatus())) {
            Product product = sale.getProduct();
            product.setStockQuantity(product.getStockQuantity() + sale.getQuantity());
            productRepository.save(product);
        }

        sale.setStatus(status);
        return toResponse(salesRepository.save(sale));
    }

    public void deleteSale(Long id) {
        if (!salesRepository.existsById(id))
            throw new RuntimeException("Sale not found with id: " + id);
        salesRepository.deleteById(id);
    }

    public List<SaleResponse> getSalesByUser(Long userId) {
        return salesRepository.findByUserId(userId)
                .stream().map(this::toResponse).collect(Collectors.toList());
    }

    public List<SaleResponse> getSalesByProduct(Long productId) {
        return salesRepository.findByProductId(productId)
                .stream().map(this::toResponse).collect(Collectors.toList());
    }

    public List<SaleResponse> getSalesByDateRange(LocalDateTime start, LocalDateTime end) {
        return salesRepository.findBySaleDateBetween(start, end)
                .stream().map(this::toResponse).collect(Collectors.toList());
    }

    public DashboardStats getDashboardStats() {
        DashboardStats stats = new DashboardStats();

        BigDecimal totalRevenue = salesRepository.getTotalRevenue();
        stats.setTotalRevenue(totalRevenue != null ? totalRevenue : BigDecimal.ZERO);
        stats.setTotalSales(salesRepository.count());
        stats.setTotalProducts(productRepository.count());
        stats.setTotalUsers(userRepository.count());

        LocalDateTime startOfMonth = LocalDateTime.now().withDayOfMonth(1).withHour(0).withMinute(0).withSecond(0);
        LocalDateTime now = LocalDateTime.now();
        BigDecimal monthlyRevenue = salesRepository.getRevenueByDateRange(startOfMonth, now);
        stats.setRevenueThisMonth(monthlyRevenue != null ? monthlyRevenue : BigDecimal.ZERO);
        stats.setSalesThisMonth(salesRepository.countCompletedSalesByDateRange(startOfMonth, now));

        return stats;
    }

    private SaleResponse toResponse(Sale s) {
        SaleResponse res = new SaleResponse();
        res.setId(s.getId());
        res.setProductName(s.getProduct().getName());
        res.setProductId(s.getProduct().getId());
        res.setSalesRepName(s.getUser().getFullName());
        res.setUserId(s.getUser().getId());
        res.setQuantity(s.getQuantity());
        res.setUnitPrice(s.getUnitPrice());
        res.setTotalAmount(s.getTotalAmount());
        res.setStatus(s.getStatus());
        res.setSaleDate(s.getSaleDate());
        res.setCustomerName(s.getCustomerName());
        res.setCustomerEmail(s.getCustomerEmail());
        res.setNotes(s.getNotes());
        return res;
    }
}
