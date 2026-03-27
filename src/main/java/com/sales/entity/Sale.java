package com.sales.entity;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "sales")
public class Sale {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column(nullable = false)
    private Integer quantity;

    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal unitPrice;

    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal totalAmount;

    @Column(nullable = false)
    private String status; // PENDING, COMPLETED, CANCELLED, REFUNDED

    @Column(name = "sale_date", nullable = false)
    private LocalDateTime saleDate;

    @Column(name = "customer_name")
    private String customerName;

    @Column(name = "customer_email")
    private String customerEmail;

    @Column(length = 500)
    private String notes;

    // Constructors
    public Sale() {
    }

    public Sale(Long id, Product product, User user, Integer quantity, BigDecimal unitPrice, BigDecimal totalAmount, String status, LocalDateTime saleDate, String customerName, String customerEmail, String notes) {
        this.id = id;
        this.product = product;
        this.user = user;
        this.quantity = quantity;
        this.unitPrice = unitPrice;
        this.totalAmount = totalAmount;
        this.status = status;
        this.saleDate = saleDate;
        this.customerName = customerName;
        this.customerEmail = customerEmail;
        this.notes = notes;
    }

    // Builder pattern
    public static SaleBuilder builder() {
        return new SaleBuilder();
    }

    public static class SaleBuilder {
        private Long id;
        private Product product;
        private User user;
        private Integer quantity;
        private BigDecimal unitPrice;
        private BigDecimal totalAmount;
        private String status;
        private LocalDateTime saleDate;
        private String customerName;
        private String customerEmail;
        private String notes;

        public SaleBuilder id(Long id) {
            this.id = id;
            return this;
        }

        public SaleBuilder product(Product product) {
            this.product = product;
            return this;
        }

        public SaleBuilder user(User user) {
            this.user = user;
            return this;
        }

        public SaleBuilder quantity(Integer quantity) {
            this.quantity = quantity;
            return this;
        }

        public SaleBuilder unitPrice(BigDecimal unitPrice) {
            this.unitPrice = unitPrice;
            return this;
        }

        public SaleBuilder totalAmount(BigDecimal totalAmount) {
            this.totalAmount = totalAmount;
            return this;
        }

        public SaleBuilder status(String status) {
            this.status = status;
            return this;
        }

        public SaleBuilder saleDate(LocalDateTime saleDate) {
            this.saleDate = saleDate;
            return this;
        }

        public SaleBuilder customerName(String customerName) {
            this.customerName = customerName;
            return this;
        }

        public SaleBuilder customerEmail(String customerEmail) {
            this.customerEmail = customerEmail;
            return this;
        }

        public SaleBuilder notes(String notes) {
            this.notes = notes;
            return this;
        }

        public Sale build() {
            return new Sale(id, product, user, quantity, unitPrice, totalAmount, status, saleDate, customerName, customerEmail, notes);
        }
    }

    @PrePersist
    public void prePersist() {
        if (saleDate == null) saleDate = LocalDateTime.now();
        if (totalAmount == null && unitPrice != null && quantity != null)
            totalAmount = unitPrice.multiply(BigDecimal.valueOf(quantity));
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public BigDecimal getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(BigDecimal unitPrice) {
        this.unitPrice = unitPrice;
    }

    public BigDecimal getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(BigDecimal totalAmount) {
        this.totalAmount = totalAmount;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public LocalDateTime getSaleDate() {
        return saleDate;
    }

    public void setSaleDate(LocalDateTime saleDate) {
        this.saleDate = saleDate;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getCustomerEmail() {
        return customerEmail;
    }

    public void setCustomerEmail(String customerEmail) {
        this.customerEmail = customerEmail;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }
}
