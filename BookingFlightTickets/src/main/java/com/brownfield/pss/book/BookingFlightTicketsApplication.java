package com.brownfield.pss.book;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.brownfield.pss.book.entity.BookingRecord;
import com.brownfield.pss.book.entity.Passenger;
import com.brownfield.pss.book.service.BookingService;

@SpringBootApplication
public class BookingFlightTicketsApplication {

	@Autowired
	BookingService bookingService;
	
	public static void main(String[] args) {
		SpringApplication.run(BookingFlightTicketsApplication.class, args);
	}

	public void run(String... strings) throws Exception {
		
		BookingRecord booking = new BookingRecord("BF101", "NYC","SFO","22-JAN-16",new Date(),"101",null);
		Set<Passenger> passengers = new HashSet<Passenger>();
		passengers.add(new Passenger("Prasanna","Pearl","Male", booking));
	 	
		booking.setPassengers(passengers);
 		long record  = bookingService.book(booking);
 		
		System.out.println("Booking successfully saved..." + record);
	}
	
}
