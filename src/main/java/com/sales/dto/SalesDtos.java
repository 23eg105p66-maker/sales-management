package com.sales.dto;

import lombok.Data;
import java.math.BigDecimal;
import java.time.LocalDateTime;

public class SalesDtos {

    // ─── User DTOs ─────────────────────────────────────────────────
    @Data
    public static class UserRequest {
        private String username;
        private String password;
        private String email;
        private String role;
        private String fullName;
    }

    @Data
    public static class UserResponse {
        private Long id;
        private String username;
        private String email;
        private String role;
        private String fullName;
        private boolean active;
    }

    @Data
    public static class LoginRequest {
        private String username;
        private String password;
    }

    // ─── Product DTOs ──────────────────────────────────────────────
    @Data
    public static class ProductRequest {
        private String name;
        private String description;
        private BigDecimal price;
        private Integer stockQuantity;
        private String category;
        private String sku;
    }

    @Data
    public static class ProductResponse {
        private Long id;
        private String name;
        private String description;
        private BigDecimal price;
        private Integer stockQuantity;
        private String category;
        private String sku;
        private boolean active;
    }

    // ─── Sale DTOs ─────────────────────────────────────────────────
    @Data
    public static class SaleRequest {
        private Long productId;
        private Long userId;
        private Integer quantity;
        private String customerName;
        private String customerEmail;
        private String notes;
    }

    @Data
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
    }

    // ─── Dashboard DTO ─────────────────────────────────────────────
    @Data
    public static class DashboardStats {
        private BigDecimal totalRevenue;
        private Long totalSales;
        private Long totalProducts;
        private Long totalUsers;
        private BigDecimal revenueThisMonth;
        private Long salesThisMonth;
    }

    // ─── Status Update DTO ─────────────────────────────────────────
    @Data
    public static class StatusUpdateRequest {
        private String status;
    }
}
