package com.brownfield.pss.book;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.brownfield.pss.book.entity.BookingRecord;
import com.brownfield.pss.book.entity.Passenger;
import com.brownfield.pss.book.service.BookingService;

@SpringBootTest
class BookingFlightTicketsApplicationTests {

	@Autowired
	BookingService bookingService;
	
	@Test
	public void bookingserviceTest()
	{
		BookingRecord booking = new BookingRecord("BF101", "NYC","SFO","22-JAN-16",new Date(),"101",null);
		Set<Passenger> passengers = new HashSet<Passenger>();
		passengers.add(new Passenger("Prasanna","Pearl","Male", booking));
	 	
		booking.setPassengers(passengers);
 		long record  = bookingService.book(booking);
 		
		System.out.println("Booking successfully saved..." + record);
	}

}
