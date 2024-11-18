package com.ziad.inventoryservice.repository;

import com.ziad.inventoryservice.model.Inventory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface InventoryRepository extends JpaRepository<Inventory , Long> {
    @Query("SELECT i FROM Inventory i where i.skuCode LIKE %:query%")
    Optional<Inventory> findBySkuCode(@Param("query") String query);

//    @Query("SELECT i FROM Inventory i where i.skuCode LIKE %:query%")
    List<Inventory> findBySkuCodeIn(List<String> skuCode);
}
