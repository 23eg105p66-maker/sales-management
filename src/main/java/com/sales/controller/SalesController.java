package com.sales.controller;

import com.sales.dto.SalesDtos.*;
import com.sales.service.SalesService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/sales")
@CrossOrigin(origins = "*")
public class SalesController {

    private final SalesService salesService;

    public SalesController(SalesService salesService) {
        this.salesService = salesService;
    }

    @GetMapping
    public ResponseEntity<List<SaleResponse>> getAllSales() {
        return ResponseEntity.ok(salesService.getAllSales());
    }

    @GetMapping("/{id}")
    public ResponseEntity<SaleResponse> getSaleById(@PathVariable Long id) {
        return ResponseEntity.ok(salesService.getSaleById(id));
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<SaleResponse>> getSalesByUser(@PathVariable Long userId) {
        return ResponseEntity.ok(salesService.getSalesByUser(userId));
    }

    @GetMapping("/product/{productId}")
    public ResponseEntity<List<SaleResponse>> getSalesByProduct(@PathVariable Long productId) {
        return ResponseEntity.ok(salesService.getSalesByProduct(productId));
    }

    @GetMapping("/date-range")
    public ResponseEntity<List<SaleResponse>> getSalesByDateRange(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime start,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime end) {
        return ResponseEntity.ok(salesService.getSalesByDateRange(start, end));
    }

    @GetMapping("/dashboard")
    public ResponseEntity<DashboardStats> getDashboardStats() {
        return ResponseEntity.ok(salesService.getDashboardStats());
    }

    @PostMapping
    public ResponseEntity<SaleResponse> createSale(@RequestBody SaleRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(salesService.createSale(request));
    }

    @PatchMapping("/{id}/status")
    public ResponseEntity<SaleResponse> updateSaleStatus(@PathVariable Long id,
                                                          @RequestBody StatusUpdateRequest request) {
        return ResponseEntity.ok(salesService.updateSaleStatus(id, request.getStatus()));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSale(@PathVariable Long id) {
        salesService.deleteSale(id);
        return ResponseEntity.noContent().build();
    }
}
