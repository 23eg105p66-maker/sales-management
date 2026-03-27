package com.sales.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class SalesDtos {

    // ─── User DTOs ─────────────────────────────────────────────────
    public static class UserRequest {
        private String username;
        private String password;
        private String email;
        private String role;
        private String fullName;

        // Constructors
        public UserRequest() {
        }

        public UserRequest(String username, String password, String email, String role, String fullName) {
            this.username = username;
            this.password = password;
            this.email = email;
            this.role = role;
            this.fullName = fullName;
        }

        // Getters and Setters
        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getRole() {
            return role;
        }

        public void setRole(String role) {
            this.role = role;
        }

        public String getFullName() {
            return fullName;
        }

        public void setFullName(String fullName) {
            this.fullName = fullName;
        }
    }

    public static class UserResponse {
        private Long id;
        private String username;
        private String email;
        private String role;
        private String fullName;
        private boolean active;

        // Constructors
        public UserResponse() {
        }

        public UserResponse(Long id, String username, String email, String role, String fullName, boolean active) {
            this.id = id;
            this.username = username;
            this.email = email;
            this.role = role;
            this.fullName = fullName;
            this.active = active;
        }

        // Getters and Setters
        public Long getId() {
            return id;
        }

        public void setId(Long id) {
            this.id = id;
        }

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getRole() {
            return role;
        }

        public void setRole(String role) {
            this.role = role;
        }

        public String getFullName() {
            return fullName;
        }

        public void setFullName(String fullName) {
            this.fullName = fullName;
        }

        public boolean isActive() {
            return active;
        }

        public void setActive(boolean active) {
            this.active = active;
        }
    }

    public static class LoginRequest {
        private String username;
        private String password;

        // Constructors
        public LoginRequest() {
        }

        public LoginRequest(String username, String password) {
            this.username = username;
            this.password = password;
        }

        // Getters and Setters
        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }
    }

    // ─── Product DTOs ──────────────────────────────────────────────
    public static class ProductRequest {
        private String name;
        private String description;
        private BigDecimal price;
        private Integer stockQuantity;
        private String category;
        private String sku;

        // Constructors
        public ProductRequest() {
        }

        public ProductRequest(String name, String description, BigDecimal price, Integer stockQuantity, String category, String sku) {
            this.name = name;
            this.description = description;
            this.price = price;
            this.stockQuantity = stockQuantity;
            this.category = category;
            this.sku = sku;
        }

        // Getters and Setters
        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public BigDecimal getPrice() {
            return price;
        }

        public void setPrice(BigDecimal price) {
            this.price = price;
        }

        public Integer getStockQuantity() {
            return stockQuantity;
        }

        public void setStockQuantity(Integer stockQuantity) {
            this.stockQuantity = stockQuantity;
        }

        public String getCategory() {
            return category;
        }

        public void setCategory(String category) {
            this.category = category;
        }

        public String getSku() {
            return sku;
        }

        public void setSku(String sku) {
            this.sku = sku;
        }
    }

    public static class ProductResponse {
        private Long id;
        private String name;
        private String description;
        private BigDecimal price;
        private Integer stockQuantity;
        private String category;
        private String sku;
        private boolean active;

        // Constructors
        public ProductResponse() {
        }

        public ProductResponse(Long id, String name, String description, BigDecimal price, Integer stockQuantity, String category, String sku, boolean active) {
            this.id = id;
            this.name = name;
            this.description = description;
            this.price = price;
            this.stockQuantity = stockQuantity;
            this.category = category;
            this.sku = sku;
            this.active = active;
        }

        // Getters and Setters
        public Long getId() {
            return id;
        }

        public void setId(Long id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public BigDecimal getPrice() {
            return price;
        }

        public void setPrice(BigDecimal price) {
            this.price = price;
        }

        public Integer getStockQuantity() {
            return stockQuantity;
        }

        public void setStockQuantity(Integer stockQuantity) {
            this.stockQuantity = stockQuantity;
        }

        public String getCategory() {
            return category;
        }

        public void setCategory(String category) {
            this.category = category;
        }

        public String getSku() {
            return sku;
        }

        public void setSku(String sku) {
            this.sku = sku;
        }

        public boolean isActive() {
            return active;
        }

        public void setActive(boolean active) {
            this.active = active;
        }
    }

    // ─── Sale DTOs ─────────────────────────────────────────────────
    public static class SaleRequest {
        private Long productId;
        private Long userId;
        private Integer quantity;
        private String customerName;
        private String customerEmail;
        private String notes;

        // Constructors
        public SaleRequest() {
        }

        public SaleRequest(Long productId, Long userId, Integer quantity, String customerName, String customerEmail, String notes) {
            this.productId = productId;
            this.userId = userId;
            this.quantity = quantity;
            this.customerName = customerName;
            this.customerEmail = customerEmail;
            this.notes = notes;
        }

        // Getters and Setters
        public Long getProductId() {
            return productId;
        }

        public void setProductId(Long productId) {
            this.productId = productId;
        }

        public Long getUserId() {
            return userId;
        }

        public void setUserId(Long userId) {
            this.userId = userId;
        }

        public Integer getQuantity() {
            return quantity;
        }

        public void setQuantity(Integer quantity) {
            this.quantity = quantity;
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

    public static class SaleResponse {
        private Long id;
        private String productName;
        private Long productId;
        private String salesRepName;
        private Long userId;
        private Integer quantity;
        private BigDecimal unitPrice;
        private BigDecimal totalAmount;
        private String status;
        private LocalDateTime saleDate;
        private String customerName;
        private String customerEmail;
        private String notes;

        // Constructors
        public SaleResponse() {
        }

        // Getters and Setters
        public Long getId() {
            return id;
        }

        public void setId(Long id) {
            this.id = id;
        }

        public String getProductName() {
            return productName;
        }

        public void setProductName(String productName) {
            this.productName = productName;
        }

        public Long getProductId() {
            return productId;
        }

        public void setProductId(Long productId) {
            this.productId = productId;
        }

        public String getSalesRepName() {
            return salesRepName;
        }

        public void setSalesRepName(String salesRepName) {
            this.salesRepName = salesRepName;
        }

        public Long getUserId() {
            return userId;
        }

        public void setUserId(Long userId) {
            this.userId = userId;
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

    // ─── Dashboard DTO ─────────────────────────────────────────────
    public static class DashboardStats {
        private BigDecimal totalRevenue;
        private Long totalSales;
        private Long totalProducts;
        private Long totalUsers;
        private BigDecimal revenueThisMonth;
        private Long salesThisMonth;

        // Constructors
        public DashboardStats() {
        }

        // Getters and Setters
        public BigDecimal getTotalRevenue() {
            return totalRevenue;
        }

        public void setTotalRevenue(BigDecimal totalRevenue) {
            this.totalRevenue = totalRevenue;
        }

        public Long getTotalSales() {
            return totalSales;
        }

        public void setTotalSales(Long totalSales) {
            this.totalSales = totalSales;
        }

        public Long getTotalProducts() {
            return totalProducts;
        }

        public void setTotalProducts(Long totalProducts) {
            this.totalProducts = totalProducts;
        }

        public Long getTotalUsers() {
            return totalUsers;
        }

        public void setTotalUsers(Long totalUsers) {
            this.totalUsers = totalUsers;
        }

        public BigDecimal getRevenueThisMonth() {
            return revenueThisMonth;
        }

        public void setRevenueThisMonth(BigDecimal revenueThisMonth) {
            this.revenueThisMonth = revenueThisMonth;
        }

        public Long getSalesThisMonth() {
            return salesThisMonth;
        }

        public void setSalesThisMonth(Long salesThisMonth) {
            this.salesThisMonth = salesThisMonth;
        }
    }

    // ─── Status Update DTO ─────────────────────────────────────────
    public static class StatusUpdateRequest {
        private String status;

        // Constructors
        public StatusUpdateRequest() {
        }

        public StatusUpdateRequest(String status) {
            this.status = status;
        }

        // Getters and Setters
        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }
    }
}
