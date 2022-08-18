package com.brownfield.pss.book.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.brownfield.pss.book.entity.BookingRecord;
import com.brownfield.pss.book.service.BookingService;

@RestController
@CrossOrigin
@RequestMapping("/booking")
public class BookingController {

	BookingService bookingService;
	
	@Autowired
	BookingController(BookingService bookingService){
		this.bookingService = bookingService;
	}

	@RequestMapping(value="/create" , method = RequestMethod.POST)
	long book(@RequestBody BookingRecord record){
		System.out.println("Booking Request" + record); 
		return bookingService.book(record);
	}
	
}
