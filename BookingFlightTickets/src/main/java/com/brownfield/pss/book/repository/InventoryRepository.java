package com.brownfield.pss.book.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.brownfield.pss.book.entity.Inventory;

@Repository
public interface InventoryRepository extends JpaRepository<Inventory, Long> 
{	
	Inventory findByFlightNumberAndFlightDate(String flightNumber,String flightDate);
}
