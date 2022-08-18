package com.brownfield.pss.book.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.brownfield.pss.book.entity.BookingRecord;

@Repository
public interface BookingRepository extends JpaRepository<BookingRecord, Long>{

}
