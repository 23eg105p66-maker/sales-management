package com.sales.repository;

import com.sales.entity.Sale;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface SalesRepository extends JpaRepository<Sale, Long> {

    List<Sale> findByUserId(Long userId);
    List<Sale> findByProductId(Long productId);
    List<Sale> findByStatus(String status);
    List<Sale> findByCustomerEmail(String customerEmail);

    List<Sale> findBySaleDateBetween(LocalDateTime start, LocalDateTime end);

    @Query("SELECT s FROM Sale s WHERE s.user.id = :userId AND s.saleDate BETWEEN :start AND :end")
    List<Sale> findByUserIdAndDateRange(@Param("userId") Long userId,
                                        @Param("start") LocalDateTime start,
                                        @Param("end") LocalDateTime end);

    @Query("SELECT SUM(s.totalAmount) FROM Sale s WHERE s.status = 'COMPLETED'")
    BigDecimal getTotalRevenue();

    @Query("SELECT SUM(s.totalAmount) FROM Sale s WHERE s.status = 'COMPLETED' AND s.saleDate BETWEEN :start AND :end")
    BigDecimal getRevenueByDateRange(@Param("start") LocalDateTime start, @Param("end") LocalDateTime end);

    @Query("SELECT COUNT(s) FROM Sale s WHERE s.status = 'COMPLETED' AND s.saleDate BETWEEN :start AND :end")
    Long countCompletedSalesByDateRange(@Param("start") LocalDateTime start, @Param("end") LocalDateTime end);

    @Query("SELECT s.product.id, s.product.name, SUM(s.quantity) as totalQty FROM Sale s " +
           "WHERE s.status = 'COMPLETED' GROUP BY s.product.id, s.product.name ORDER BY totalQty DESC")
    List<Object[]> findTopSellingProducts();
}
